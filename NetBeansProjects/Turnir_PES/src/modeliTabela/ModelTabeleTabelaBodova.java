/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliTabela;

import domen.Klub;
import domen.Ucesnik;
import domen.Utakmica;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sleza
 */
public class ModelTabeleTabelaBodova extends AbstractTableModel {

    List<Ucesnik> listaUcesnika = new ArrayList<>();
    List<Utakmica> listaUtakmica = new ArrayList<>();
    String[] kolone = {"R.B.", "Tim", "OS", "P", "N", "I", "G", "B"};

    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public int getRowCount() {
        return listaUcesnika.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ucesnik u = listaUcesnika.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ++rowIndex;
            case 1:
                return u.getKlub().getNaziv();
            case 2:
                return vratiBrojOdigraniUtakmica(u);
            case 3:
                return vratiBrojPobeda(u);
            case 4:
                return vratiBrojNeresenih(u);
            case 5:
                return vratiBrojIzgubljenih(u);
            case 6:
                return vratiGolRazlikuZaTabelu(u);
            case 7:
                return vratiBrojBodova(u);
            default:
                return "CAOOOO";
        }
    }

    @Override
    public String getColumnName(int column) {
        for (int i = 0; i < kolone.length; i++) {
            return kolone[column];
        }
        return "Cao";
    }

    private Object vratiBrojOdigraniUtakmica(Ucesnik u) {
        int broj = 0;
        for (Utakmica utakmica : listaUtakmica) {
            if (utakmica.getDomacin().equals(u) || utakmica.getGost().equals(u)) {
                if (utakmica.getGolDomacin() != -1) {
                    broj++;
                }
            }
        }
        return broj;
    }

    private Object vratiBrojPobeda(Ucesnik u) {
        int broj = 0;
        for (Utakmica utakmica : listaUtakmica) {
            if (utakmica.getDomacin().equals(u)) {
                if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
                    broj++;
                }
            }
            if (utakmica.getGost().equals(u)) {
                if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
                    broj++;
                }
            }
        }
        return broj;
    }

    private Object vratiBrojIzgubljenih(Ucesnik u) {
        int broj = 0;
        for (Utakmica utakmica : listaUtakmica) {
            if (utakmica.getDomacin().equals(u)) {
                if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
                    broj++;
                }
            }
            if (utakmica.getGost().equals(u)) {
                if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
                    broj++;
                }
            }
        }
        return broj;
    }

    private Object vratiBrojNeresenih(Ucesnik u) {
        int broj = 0;
        for (Utakmica utakmica : listaUtakmica) {
            if (utakmica.getDomacin().equals(u) || utakmica.getGost().equals(u)) {
                if (utakmica.getGolDomacin() == utakmica.getGolGost() && utakmica.getGolDomacin() != -1) {
                    broj++;
                }
            }
        }
        return broj;
    }

    private Object vratiGolRazlikuZaTabelu(Ucesnik u) {
        return vratiBrojDatih(u) + ":" + vratiBrojPrimljenih(u);
    }

    private Object vratiBrojBodova(Ucesnik u) {
        int pobede = (int) vratiBrojPobeda(u);
        int nereseno = (int) vratiBrojNeresenih(u);
        return pobede * 3 + nereseno;
    }

    public List<Ucesnik> getListaUcesnika() {
        return listaUcesnika;
    }

    public void setListaUcesnika(List<Ucesnik> listaUcesnika) {
        this.listaUcesnika = listaUcesnika;
        sortiraj(listaUcesnika);
        fireTableDataChanged();
    }

    public List<Utakmica> getListaUtakmica() {
        return listaUtakmica;
    }

    public void setListaUtakmica(List<Utakmica> listaUtakmica) {
        this.listaUtakmica = listaUtakmica;
        fireTableDataChanged();
    }

    private void sortiraj(List<Ucesnik> listaUcesnika) {
        Collections.sort(listaUcesnika, new Comparator<Ucesnik>() {
            @Override
            public int compare(Ucesnik o1, Ucesnik o2) {
                int brojBodova1 = (int) vratiBrojBodova(o1);
                int brojBodova2 = (int) vratiBrojBodova(o2);
                if (brojBodova1 != brojBodova2) {
                    return brojBodova2 - brojBodova1;
                }
                int brojBodovaMedjusobno = brojBodovaMedjusobno(o1, o2);
                if (brojBodovaMedjusobno != 0) {
                    return brojBodovaMedjusobno;
                }
                int vratiRazliku = vratiBrojGolovaUGostima(o1, o2);
                if (vratiRazliku != 0) {
                    return vratiRazliku;
                }
                int golRazlika = golRazlika(o1, o2);
                if (golRazlika != 0) {
                    return golRazlika;
                }
                int brojDatih = brojDatih(o1, o2);
                if (brojDatih != 0) {
                    return brojDatih;
                } else {

                }
                return 0;
            }

        });
    }

    private int vratiBrojGolovaUGostima(Ucesnik o1, Ucesnik o2) {
        int brojGolovaPrviUGostima = 0;
        int brojGolovaDrugiUGostima = 0;
        for (Utakmica u : listaUtakmica) {
            if (u.getDomacin().equals(o1) && u.getGost().equals(o2)) {
                brojGolovaDrugiUGostima += u.getGolGost();

            }
            if (u.getDomacin().equals(o2) && u.getGost().equals(o1)) {
                brojGolovaPrviUGostima += u.getGolGost();
            }
        }
        return brojGolovaDrugiUGostima - brojGolovaPrviUGostima;
    }

    private int golRazlika(Ucesnik o1, Ucesnik o2) {
        return vratiGolRazliku(o2) - vratiGolRazliku(o1);
    }

    private int brojDatih(Ucesnik o1, Ucesnik o2) {
        return vratiBrojDatih(o2) - vratiBrojDatih(o1);
    }

    private int brojBodovaMedjusobno(Ucesnik o1, Ucesnik o2) {
        int brojBodovaPrvi = 0;
        int brojBodovaDrugi = 0;
        for (Utakmica utakmica : listaUtakmica) {
            if (utakmica.getDomacin().equals(o1) && utakmica.getGost().equals(o2)) {
                if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
                    brojBodovaPrvi += 3;
                } else if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
                    brojBodovaDrugi += 3;
                } else {
                    brojBodovaDrugi++;
                    brojBodovaPrvi++;
                }
            }
            if (utakmica.getDomacin().equals(o2) && utakmica.getGost().equals(o1)) {
                if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
                    brojBodovaDrugi += 3;
                } else if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
                    brojBodovaPrvi += 3;
                } else {
                    brojBodovaDrugi++;
                    brojBodovaPrvi++;
                }
            }
        }
        return brojBodovaDrugi - brojBodovaPrvi;
    }

    private int vratiBrojDatih(Ucesnik o1) {
        int brojDatih = 0;
        for (Utakmica utakmica : listaUtakmica) {
            if (utakmica.getDomacin().equals(o1) && utakmica.getGolDomacin() != -1) {
                brojDatih += utakmica.getGolDomacin();
            }
            if (utakmica.getGost().equals(o1) && utakmica.getGolGost() != -1) {
                brojDatih += utakmica.getGolGost();
            }
        }
        return brojDatih;
    }

    private int vratiBrojPrimljenih(Ucesnik o1) {
        int brojPrimljenih = 0;
        for (Utakmica utakmica : listaUtakmica) {
            if (utakmica.getDomacin().equals(o1) && utakmica.getGolDomacin() != -1) {
                brojPrimljenih += utakmica.getGolGost();
            }
            if (utakmica.getGost().equals(o1) && utakmica.getGolGost() != -1) {
                brojPrimljenih += utakmica.getGolDomacin();

            }
        }
        return brojPrimljenih;
    }

    private int vratiGolRazliku(Ucesnik o2) {
        return vratiBrojDatih(o2) - vratiBrojPrimljenih(o2);
    }

}
