/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliTabela;

import domen.Igrac;
import domen.Statistika;
import domen.Utakmica;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sleza
 */
public class ModelTabelaStatistikeIgraca extends AbstractTableModel {

    String[] kolone = {"R.B", "Igrac", "U", "P", "N", "I", "Dg", "Pg", "GR"};
    ArrayList<Statistika> lista = new ArrayList<Statistika>();

    public ModelTabelaStatistikeIgraca() {

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
        Statistika s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return s.getIgracSporedni();
            case 2:
                return s.getUkupnoPobeda() + s.getUkupnoNeresenih() + s.getUkupnoIzgubljenih();
            case 3:
                return s.getUkupnoPobeda();
            case 4:
                return s.getUkupnoNeresenih();
            case 5:
                return s.getUkupnoIzgubljenih();
            case 6:
                return s.getUkupnoDatihGolova();
            case 7:
                return s.getUkupnoPrimljenihGolova();
            case 8:
                return s.getUkupnoDatihGolova() - s.getUkupnoPrimljenihGolova();
            default:
                return "Cao";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public ArrayList<Statistika> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Statistika> lista) {
        this.lista = lista;
    }

}
