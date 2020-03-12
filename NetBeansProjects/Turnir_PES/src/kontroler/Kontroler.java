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
import domen.Turnir;
import domen.Ucesnik;
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

    public boolean napraviTurnir(Turnir t) {
        boolean uspesno = false;
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            db.napraviTurnir(t);
            uspesno = true;
            db.commitTransaction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.rollbackTransaction();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.rollbackTransaction();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uspesno;
    }

    public boolean unesiUcesnike(ArrayList<Ucesnik> lista) {
        boolean uspesno = false;
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            for (Ucesnik ucesnik : lista) {
                db.unesiUcesnike(ucesnik);
            }
            uspesno = true;
            db.commitTransaction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.rollbackTransaction();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.rollbackTransaction();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uspesno;
    }

    public int vratiMiZaTurnirID() {
        int broj = 0;
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            broj = db.vratiMiZaTurnirID();
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
        return broj;
    }

    public boolean unesiIgraca(Igrac i) {
        boolean uspesno = false;
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            db.napraviIgraca(i);
            uspesno = true;
            db.commitTransaction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.rollbackTransaction();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.rollbackTransaction();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uspesno;
    }

    public boolean unesiKlub(Klub k) {
        boolean uspesno = false;
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            db.napraviKlub(k);
            uspesno = true;
            db.commitTransaction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.rollbackTransaction();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
                db.rollbackTransaction();
            } catch (SQLException ex1) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return uspesno;
    }

    public ArrayList<Turnir> vratiMiSveTurnire() {
        ArrayList<Turnir> lista = new ArrayList<>();
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            lista = db.vratiMiSveTurnire();
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
