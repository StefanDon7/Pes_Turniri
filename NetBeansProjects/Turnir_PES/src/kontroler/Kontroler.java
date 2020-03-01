/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import db.DBBroker;
import domen.Igrac;
import domen.Klub;
import domen.Liga;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sleza
 */
public class Kontroler {

    private static Kontroler instance;
    private DBBroker db;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<Klub> vratiMiSveKlubove() {
        ArrayList<Klub> lista = new ArrayList<>();
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            lista = db.vratiMiSveKlubove();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public ArrayList<Liga> vratiMiSveLige() {
        ArrayList<Liga> lista = new ArrayList<>();
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            lista = db.vratiMiSveLige();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public ArrayList<Igrac> vratiMiSveIgrace() {
        ArrayList<Igrac> lista = new ArrayList<>();
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            lista = db.vratiMiSveIgrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

}
