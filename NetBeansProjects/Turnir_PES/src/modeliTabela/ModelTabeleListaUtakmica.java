/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliTabela;

import domen.Klub;
import domen.Turnir;
import domen.Utakmica;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sleza
 */
public class ModelTabeleListaUtakmica extends AbstractTableModel {

    List<Utakmica> lista = new ArrayList<Utakmica>();
    String[] kolone = {"R.B.", "Domacin", "Golovi domacin", "Golovi Gost", "Gost"};
    private final Class[] columnsType = new Class[]{Integer.class, Klub.class, Integer.class, Klub.class, Integer.class};
    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
        Utakmica u = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return u.getDomacin().getKlub();
            case 2:
                return u.getGolDomacin();
            case 3:
                return u.getGolGost();
            case 4:
                return u.getGost().getKlub();
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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 2 || columnIndex == 3) {
            return true;
        }
        return false;
    }

}
