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
                return u.getMesto();
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
                return vratiGolRazliku(u);
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

    private Object vratiGolRazliku(Ucesnik u) {
        int brojDatih = 0;
        int brojPrimljenih = 0;
        for (Utakmica utakmica : listaUtakmica) {
            if (utakmica.getDomacin().equals(u) && utakmica.getGolDomacin() != -1) {
                brojDatih += utakmica.getGolDomacin();
                brojPrimljenih += utakmica.getGolGost();

            }
            if (utakmica.getGost().equals(u) && utakmica.getGolGost() != -1) {
                brojPrimljenih += utakmica.getGolDomacin();
                brojDatih += utakmica.getGolGost();

            }
        }
        return brojDatih + ":" + brojPrimljenih;
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
                return brojBodova2 - brojBodova1;
            }

        });
    }

}
