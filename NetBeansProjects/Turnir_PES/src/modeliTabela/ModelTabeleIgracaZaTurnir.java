/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliTabela;

import domen.Ucesnik;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sleza
 */
public class ModelTabeleIgracaZaTurnir extends AbstractTableModel {

    List<Ucesnik> lista = new ArrayList<Ucesnik>();
    String[] kolone = {"Redni broj", "Ime i prezime", "Klub"};

    public ModelTabeleIgracaZaTurnir() {
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
        Ucesnik u = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return u.getIgrac();
            case 2:
                return u.getKlub();
        }
        return null;
    }

    @Override

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return kolone[column];
            case 1:
                return kolone[column];
            case 2:
                return kolone[column];
            default:
                return " ";
        }
    }

    public void osveziTabelu() {
        fireTableDataChanged();
    }

    public void dodajUcesnika(Ucesnik u) {
        lista.add(u);
        osveziTabelu();
    }

    public List<Ucesnik> getLista() {
        return lista;
    }

    public void obirisIzTabele(Ucesnik u) {
        lista.remove(u);
        osveziTabelu();
    }

}
