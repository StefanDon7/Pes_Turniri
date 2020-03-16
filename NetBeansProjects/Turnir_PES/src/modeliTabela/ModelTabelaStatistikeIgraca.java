/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeliTabela;

import domen.Igrac;
import domen.Utakmica;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sleza
 */
public class ModelTabelaStatistikeIgraca extends AbstractTableModel {

    ArrayList<Utakmica> listaUtakmica = new ArrayList<>();
    ArrayList<Igrac> listaIgraca = new ArrayList<>();
    String[] kolone = {"R.B", "Igrac", "U", "P", "N", "I", "Dg", "Pg", "GR"};

    @Override
    public int getRowCount() {
        return listaIgraca.size() - 1;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Igrac i = listaIgraca.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return i;
            case 2:
                return vratiMiUkupnoOdigranihUtakmica();
            case 3:
                return vratiMiUkupnoPobeda();
            case 4:
                return vratiMiUkupnoNeresenih();
            case 5:
                return vratiMiUkupnoIzgubljenih();
            case 6:
                return vratiDateGolove();
            case 7:
                return vratiPrimljeneGolove();
            case 8:
                return vratiOdnosDatihIPrimljenih();
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        Utakmica u = lista.get(rowIndex);
//        switch (columnIndex) {
//            case 2:
//                int gol1 = (int) aValue;
//                u.setGolDomacin(gol1);
//                return;
//            case 3:
//                int gol2 = (int) aValue;
//                u.setGolGost(gol2);
//                break;
//            default:
//                return;
//        }
    }

    private Object vratiMiUkupnoOdigranihUtakmica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object vratiMiUkupnoPobeda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object vratiMiUkupnoIzgubljenih() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object vratiMiUkupnoNeresenih() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object vratiDateGolove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object vratiPrimljeneGolove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object vratiOdnosDatihIPrimljenih() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
