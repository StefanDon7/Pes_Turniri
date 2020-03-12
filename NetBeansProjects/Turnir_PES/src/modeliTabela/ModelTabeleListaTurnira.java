/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliTabela;

import domen.Turnir;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sleza
 */
public class ModelTabeleListaTurnira extends AbstractTableModel {

    List<Turnir> lista = new ArrayList<Turnir>();
    SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy");
    String[] kolone = {"Datum", "Naziv", "Pobednik"};

    public ModelTabeleListaTurnira() {
        lista = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();

    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Turnir t = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return smf.format(t.getDatum());
            case 1:
                return t.getNaziv();
            case 2:
                if (t.getPobednik() == null) {
                    return "U toku...";
                }
                return t.getPobednik().getIgrac().toString() + " - " + t.getPobednik().getKlub().toString();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        for (int i = 0; i < kolone.length; i++) {
            return kolone[column];
        }
        return "Cao";
    }

    public void osveziTabelu() {
        fireTableDataChanged();
    }

    public void dodajUcesnika(Turnir t) {
        lista.add(t);
        osveziTabelu();
    }

    public List<Turnir> getLista() {
        return lista;
    }

    public void obirisIzTabele(Turnir t) {
        lista.remove(t);
        osveziTabelu();
    }

    public void setLista(List<Turnir> lista) {
        this.lista = lista;
    }

}
