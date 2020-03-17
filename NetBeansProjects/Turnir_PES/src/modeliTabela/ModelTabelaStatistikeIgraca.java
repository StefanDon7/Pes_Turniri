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

    public ModelTabelaStatistikeIgraca() {

    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
