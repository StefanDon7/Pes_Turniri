/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Igrac;
import domen.Klub;
import domen.Liga;
import domen.Statistika;
import domen.Turnir;
import domen.Ucesnik;
import domen.Utakmica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sleza
 */
public class DBBroker {

    private Connection connection;

    public void ucitajDrajver() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public void otvoriKonekciju() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/pes_turniri", "root", "");
        connection.setAutoCommit(false);
    }

    public void zatvoriKonekciju() throws SQLException {
        connection.close();
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback();
    }

    public ArrayList<Klub> vratiMiSveKlubove() throws SQLException {
        ArrayList<Klub> lista = new ArrayList<>();
        String upit = "Select * from Klub order by naziv asc";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            int id = rs.getInt("id");
            String naziv = rs.getString("naziv");
            int ligaid = rs.getInt("ligaID");
            Liga l = vratiMiLigu(ligaid);
            double brojZvezdica = rs.getDouble("brojZvezdica");
            Klub k = new Klub(id, naziv, l, brojZvezdica);
            lista.add(k);
        }
        return lista;
    }

    public Liga vratiMiLigu(int ligaID) throws SQLException {
        Liga l = new Liga();
        String upit = "Select * from Liga where id=" + ligaID;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            int id = rs.getInt("id");
            String naziv = rs.getString("naziv");
            l = new Liga(id, naziv);
        }
        return l;
    }

    public ArrayList<Liga> vratiMiSveLige() throws SQLException {
        ArrayList<Liga> lista = new ArrayList<>();
        String upit = "Select * from Liga order by naziv asc";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            int id = rs.getInt("id");
            String naziv = rs.getString("naziv");
            Liga l = new Liga(id, naziv);
            lista.add(l);
        }
        return lista;
    }

    public ArrayList<Igrac> vratiMiSveIgrace() throws SQLException {
        ArrayList<Igrac> lista = new ArrayList<>();
        String upit = "Select * from Igrac order by korisnickoIme asc";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            int id = rs.getInt("id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String korisnickoIme = rs.getString("korisnickoIme");
            Igrac i = new Igrac(id, ime, prezime, korisnickoIme);
            lista.add(i);
        }
        return lista;
    }

    public void napraviTurnir(Turnir t) throws SQLException {
        String upit = "INSERT INTO TURNIR(id,naziv,datum) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, t.getId());
        ps.setString(2, t.getNaziv());
        ps.setDate(3, new java.sql.Date(t.getDatum().getTime()));
        ps.execute();
    }

    public void unesiUcesnike(Ucesnik ucesnik) throws SQLException {
        String upit = "INSERT INTO UCESNIK(igrac,turnir,klub,mesto) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, ucesnik.getIgrac().getId());
        ps.setInt(2, ucesnik.getTurnir().getId());
        ps.setInt(3, ucesnik.getKlub().getId());
        ps.setInt(4, ucesnik.getMesto());
        ps.execute();
    }

    public void napraviUtakmicu(Utakmica utakmica, Turnir t) throws SQLException {
        String upit = "INSERT INTO Utakmica(domacin,gost,datum,turnir) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, utakmica.getDomacin().getId());
        ps.setInt(2, utakmica.getGost().getId());
        ps.setDate(3, new java.sql.Date(utakmica.getDatum().getTime()));
        ps.setInt(4, t.getId());
        ps.execute();
    }

    public int vratiMiZaTurnirID() throws SQLException {
        int broj = 0;
        String upit = "SELECT MAX(id) FROM turnir;";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            broj = rs.getInt("max(id)");
        }
        return ++broj;
    }

    public void napraviIgraca(Igrac i) throws SQLException {
        String upit = "INSERT INTO IGRAC(id,ime,prezime,korisnickoIme) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, i.getId());
        ps.setString(2, i.getIme());
        ps.setString(3, i.getPrezime());
        ps.setString(4, i.getKorisnickoIme());
        ps.execute();
    }

    public void napraviKlub(Klub k) throws SQLException {
        String upit = "INSERT INTO Klub(id,naziv,ligaID,brojZvezdica) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, k.getId());
        ps.setString(2, k.getNaziv());
        ps.setInt(3, k.getLiga().getId());
        ps.setDouble(4, k.getBrojZvezdica());
        ps.execute();
    }

    public ArrayList<Turnir> vratiMiSveTurnire() throws SQLException {
        ArrayList<Turnir> lista = new ArrayList<>();
        String upit = "Select * from Turnir order by id desc";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            int id = rs.getInt("id");
            String naziv = rs.getString("naziv");
            java.util.Date datum = new java.util.Date(rs.getDate("datum").getTime());
            Turnir t = new Turnir(id, naziv, datum);
            lista.add(t);
        }
        return lista;
    }

//    private Ucesnik vratiMiPobednika(Turnir t) throws SQLException {
//        String upit = "Select * from Ucesnik where turnir=" + t.getId() + " and mesto=" + 1;
//        Statement stat = connection.createStatement();
//        ResultSet rs = stat.executeQuery(upit);
//        Ucesnik u = new Ucesnik();
//        while (rs.next()) {
//            int igracid = rs.getInt("igrac");
//            int klubid = rs.getInt("klub");
//            int mesto = rs.getInt("mesto");
//            Igrac i = vratiMiIgraca(igracid);
//            Klub k = vratiMiKlub(klubid);
//            u = new Ucesnik(i, k, t, mesto);
//        }
//        return u;
//    }
    private Igrac vratiMiIgraca(int igracid) throws SQLException {
        String upit = "Select * from Igrac where id=" + igracid;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        Igrac i = new Igrac();
        while (rs.next()) {
            int id = rs.getInt("id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String korisnickoIme = rs.getString("korisnickoIme");
            i = new Igrac(id, ime, prezime, korisnickoIme);
        }
        return i;
    }

    private Klub vratiMiKlub(int klubid) throws SQLException {
        String upit = "Select * from Klub where id=" + klubid;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        Klub k = new Klub();
        while (rs.next()) {
            int id = rs.getInt("id");
            String naziv = rs.getString("naziv");
            int ligaid = rs.getInt("ligaID");
            double brojZvezdica = rs.getDouble("brojZvezdica");
            Liga liga = vratiMiLigu(ligaid);
            k = new Klub(id, naziv, liga, brojZvezdica);
        }
        return k;
    }

    private Turnir vratiMiTurnir(int turnirid) throws SQLException {
        String upit = "Select * from Turnir where id=" + turnirid;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        Turnir t = new Turnir();
        while (rs.next()) {
            int id = rs.getInt("id");
            String naziv = rs.getString("naziv");
            java.util.Date datum = new java.util.Date(rs.getDate("datum").getTime());
            t = new Turnir(id, naziv, datum);
        }
        return t;
    }

    public ArrayList<Ucesnik> vratiMiSveUcesnikeTurnira(Turnir t2) throws SQLException {
        ArrayList<Ucesnik> lista = new ArrayList<>();
        String upit = "Select * from Ucesnik where turnir=" + t2.getId();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            int id = rs.getInt("id");
            int igrac = rs.getInt("igrac");
            Igrac i = vratiMiIgraca(igrac);
            int turnir = rs.getInt("turnir");
            Turnir t = vratiMiTurnir(turnir);
            int klub = rs.getInt("klub");
            Klub k = vratiMiKlub(klub);
            int mesto = rs.getInt("mesto");
            Ucesnik u = new Ucesnik(id, i, t, k, mesto);
            lista.add(u);
        }
        return lista;
    }

    public ArrayList<Utakmica> vratiMiSveUtakmiceTurnira(Turnir t2) throws SQLException {
        ArrayList<Utakmica> lista = new ArrayList<>();
        String upit = "Select * from Utakmica where turnir=" + t2.getId() + " order by domacin";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            int d = rs.getInt("domacin");
            Ucesnik domacin = vratiMiUcesnika(d);
            int g = rs.getInt("gost");
            Ucesnik gost = vratiMiUcesnika(g);
            java.util.Date datum = new java.util.Date(rs.getDate("datum").getTime());
            int golDomacin = rs.getInt("golDomacin");
            int golGost = rs.getInt("golGost");
            Utakmica u = new Utakmica(domacin, gost, datum, golDomacin, golGost);
            lista.add(u);
        }
        return lista;
    }

    private Ucesnik vratiMiUcesnika(int id2) throws SQLException {
        String upit = "Select * from Ucesnik where id=" + id2;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        Ucesnik u = new Ucesnik();
        while (rs.next()) {
            int id = rs.getInt("id");
            int igrac = rs.getInt("igrac");
            Igrac i = vratiMiIgraca(igrac);
            int turnir = rs.getInt("turnir");
            Turnir t = vratiMiTurnir(turnir);
            int klub = rs.getInt("klub");
            Klub k = vratiMiKlub(klub);
            int mesto = rs.getInt("mesto");
            u = new Ucesnik(id, i, t, k, mesto);
        }
        return u;
    }

    public void izmeniUtakmicu(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Utakmica Utakmica SET golDomacin =?, golGost=? where domacin = ? AND gost = ?";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, utakmica.getGolDomacin());
        ps.setInt(2, utakmica.getGolGost());
        ps.setInt(3, utakmica.getDomacin().getId());
        ps.setInt(4, utakmica.getGost().getId());
        ps.executeUpdate();
        ps.close();
    }

    public int vratiMiMaxIdZaIgraca() throws SQLException {
        int max = 0;
        String upit = "Select max(id) as max from Igrac";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            max = rs.getInt("max");
            max++;
        }
        return max;
    }

    public int vratiMiMaxIdZaKlubove() throws SQLException {
        int max = 0;
        String upit = "Select max(id) as max from Klub";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            max = rs.getInt("max");
            max++;
        }
        return max;
    }

    public void unesiStatistiku(Statistika s) throws SQLException {
        String upit = "INSERT INTO Statistika(igracGlavni,igracSporedni,brojGolovaDatih,brojGolovaPrimljenih,brojPobeda,brojNeresenih,brojPoraza) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, s.getIgracGlavni().getId());
        ps.setInt(2, s.getIgracSporedni().getId());
        ps.setInt(3, s.getUkupnoDatihGolova());
        ps.setInt(4, s.getUkupnoPrimljenihGolova());
        ps.setInt(5, s.getUkupnoPobeda());
        ps.setInt(6, s.getUkupnoNeresenih());
        ps.setInt(7, s.getUkupnoIzgubljenih());
        ps.execute();
    }

    public Statistika vratiMiStatistiku(Ucesnik ucesnikGlavni, Ucesnik ucesnikSporedni) throws SQLException {
        String upit = "Select * from Statistika where igracGlavni=" + ucesnikGlavni.getIgrac().getId() + " and igracsporedni=" + ucesnikSporedni.getIgrac().getId();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        Statistika s = null;
        while (rs.next()) {
            int igracGlavni = rs.getInt("igracGlavni");
            Igrac glavni = vratiMiIgraca(igracGlavni);
            int IgracSporedni = rs.getInt("igracSporedni");
            Igrac sporedni = vratiMiIgraca(IgracSporedni);
            int brojGolovaDatih = rs.getInt("brojGolovaDatih");
            int brojGolovaPrimljenih = rs.getInt("brojGolovaPrimljenih");
            int pobede = rs.getInt("brojPobeda");
            int neresene = rs.getInt("brojNeresenih");
            int izgubljene = rs.getInt("brojPoraza");
            s = new Statistika(glavni, sporedni, pobede, neresene, izgubljene, brojGolovaDatih, brojGolovaPrimljenih);
        }
        return s;
    }

    public void sacuvajStatistikuPojedinacnuDomacin(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Statistika SET brojGolovaDatih = brojGolovaDatih+?, brojPobeda=brojPobeda+?, brojNeresenih=brojNeresenih+?, brojPoraza=brojPoraza+?, brojGolovaPrimljenih = brojGolovaPrimljenih+? WHERE igracGlavni =? AND igracSporedni = -1";
        int pobeda = 0;
        int nereseno = 0;
        int izgubljena = 0;
        if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
            pobeda++;
        } else if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
            izgubljena++;
        } else {
            nereseno++;
        }
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, utakmica.getGolDomacin());
        ps.setInt(2, pobeda);
        ps.setInt(3, nereseno);
        ps.setInt(4, izgubljena);
        ps.setInt(5, utakmica.getGolGost());
        ps.setInt(6, utakmica.getDomacin().getIgrac().getId());
        ps.executeUpdate();
        ps.close();
    }

    public void sacuvajStatistikuZajednickuDomacin(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Statistika SET brojGolovaDatih = brojGolovaDatih+?,brojPobeda=brojPobeda+?, brojNeresenih=brojNeresenih+?, brojPoraza=brojPoraza+?, brojGolovaPrimljenih = brojGolovaPrimljenih+? WHERE igracGlavni =? AND igracSporedni =?";
        int pobeda = 0;
        int nereseno = 0;
        int izgubljena = 0;
        if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
            pobeda++;
        } else if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
            izgubljena++;
        } else {
            nereseno++;
        }
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, utakmica.getGolDomacin());
        ps.setInt(2, pobeda);
        ps.setInt(3, nereseno);
        ps.setInt(4, izgubljena);
        ps.setInt(5, utakmica.getGolGost());
        ps.setInt(6, utakmica.getDomacin().getIgrac().getId());
        ps.setInt(7, utakmica.getGost().getIgrac().getId());
        ps.executeUpdate();
        ps.close();
    }

    public void sacuvajStatistikuPojedinacnuGost(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Statistika SET brojGolovaDatih = brojGolovaDatih+?,brojPobeda=brojPobeda+?, brojNeresenih=brojNeresenih+?, brojPoraza=brojPoraza+?, brojGolovaPrimljenih = brojGolovaPrimljenih+? WHERE igracGlavni =? AND igracSporedni = -1";
        int pobeda = 0;
        int nereseno = 0;
        int izgubljena = 0;
        if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
            pobeda++;
        } else if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
            izgubljena++;
        } else {
            nereseno++;
        }
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, utakmica.getGolGost());
        ps.setInt(2, pobeda);
        ps.setInt(3, nereseno);
        ps.setInt(4, izgubljena);
        ps.setInt(5, utakmica.getGolDomacin());
        ps.setInt(6, utakmica.getGost().getIgrac().getId());

        ps.executeUpdate();
        ps.close();
    }

    public void sacuvajStatistikuZajednickuGost(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Statistika SET brojGolovaDatih = brojGolovaDatih+?, brojPobeda=brojPobeda+?, brojNeresenih=brojNeresenih+?, brojPoraza=brojPoraza+?,brojGolovaPrimljenih = brojGolovaPrimljenih+? WHERE igracGlavni =? AND igracSporedni =?";
        PreparedStatement ps = connection.prepareStatement(upit);
        int pobeda = 0;
        int nereseno = 0;
        int izgubljena = 0;
        if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
            pobeda++;
        } else if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
            izgubljena++;
        } else {
            nereseno++;
        }
        ps.setInt(1, utakmica.getGolGost());
        ps.setInt(2, pobeda);
        ps.setInt(3, nereseno);
        ps.setInt(4, izgubljena);
        ps.setInt(5, utakmica.getGolDomacin());
        ps.setInt(6, utakmica.getGost().getIgrac().getId());
        ps.setInt(7, utakmica.getDomacin().getIgrac().getId());
        ps.executeUpdate();
        ps.close();
    }

    public ArrayList<Statistika> vratiMiStatistiku(Igrac i) throws SQLException {
        ArrayList<Statistika> lista = new ArrayList<>();
        String upit = "Select * from Statistika where igracGlavni=" + i.getId() + " order by igracsporedni asc";
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        Statistika s = null;
        while (rs.next()) {
            int igracGlavni = rs.getInt("igracGlavni");
            Igrac glavni = vratiMiIgraca(igracGlavni);
            int IgracSporedni = rs.getInt("igracSporedni");
            Igrac sporedni = vratiMiIgraca(IgracSporedni);
            int brojGolovaDatih = rs.getInt("brojGolovaDatih");
            int brojGolovaPrimljenih = rs.getInt("brojGolovaPrimljenih");
            int pobede = rs.getInt("brojPobeda");
            int neresene = rs.getInt("brojNeresenih");
            int izgubljene = rs.getInt("brojPoraza");
            s = new Statistika(glavni, sporedni, pobede, neresene, izgubljene, brojGolovaDatih, brojGolovaPrimljenih);
            lista.add(s);
        }
        return lista;
    }

    public boolean utakmicaPostojiAlSeMenjaRezultat(Utakmica utakmica) throws SQLException {
        boolean postoji = false;
        Utakmica u = vratiMiUtakmicu(utakmica.getDomacin().getId(), utakmica.getGost().getId());
        if (u.getGolGost() != -1 && u.getGolDomacin() != -1) {
            if (u.getGolDomacin() == utakmica.getGolDomacin() && u.getGolGost() == u.getGolGost()) {
                postoji = true;
            } else {
                izmeniStatistikuZaPojedincaDomacin(u);
                izmeniStatistikuZaZajednickuDomacin(u);
                izmeniStatistikuZaPojedincaGost(u);
                izmeniStatistikuZaZajednickuGost(u);
            }
        }
        return postoji;
    }

    private Utakmica vratiMiUtakmicu(int id, int id0) throws SQLException {
        String upit = "Select * from Utakmica where domacin=" + id + " and gost=" + id0;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        Utakmica utakmica = new Utakmica();
        while (rs.next()) {
            int d = rs.getInt("domacin");
            Ucesnik domacin = vratiMiUcesnika(d);
            int g = rs.getInt("gost");
            Ucesnik gost = vratiMiUcesnika(g);
            java.util.Date datum = new java.util.Date(rs.getDate("datum").getTime());
            int golDomacin = rs.getInt("golDomacin");
            int golGost = rs.getInt("golGost");
            utakmica = new Utakmica(domacin, gost, datum, golDomacin, golGost);
        }

        return utakmica;
    }

    private void izmeniStatistikuZaPojedincaDomacin(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Statistika SET brojGolovaDatih = brojGolovaDatih-?, brojPobeda=brojPobeda-?, brojNeresenih=brojNeresenih-?, brojPoraza=brojPoraza-?, brojGolovaPrimljenih = brojGolovaPrimljenih-? WHERE igracGlavni =? AND igracSporedni = -1";
        int pobeda = 0;
        int nereseno = 0;
        int izgubljena = 0;
        if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
            pobeda++;
        } else if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
            izgubljena++;
        } else {
            nereseno++;
        }
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, utakmica.getGolDomacin());
        ps.setInt(2, pobeda);
        ps.setInt(3, nereseno);
        ps.setInt(4, izgubljena);
        ps.setInt(5, utakmica.getGolGost());
        ps.setInt(6, utakmica.getDomacin().getIgrac().getId());
        ps.executeUpdate();
        ps.close();
    }

    private void izmeniStatistikuZaZajednickuDomacin(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Statistika SET brojGolovaDatih = brojGolovaDatih-?,brojPobeda=brojPobeda-?, brojNeresenih=brojNeresenih-?, brojPoraza=brojPoraza-?, brojGolovaPrimljenih = brojGolovaPrimljenih-? WHERE igracGlavni =? AND igracSporedni =?";
        int pobeda = 0;
        int nereseno = 0;
        int izgubljena = 0;
        if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
            pobeda++;
        } else if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
            izgubljena++;
        } else {
            nereseno++;
        }
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, utakmica.getGolDomacin());
        ps.setInt(2, pobeda);
        ps.setInt(3, nereseno);
        ps.setInt(4, izgubljena);
        ps.setInt(5, utakmica.getGolGost());
        ps.setInt(6, utakmica.getDomacin().getIgrac().getId());
        ps.setInt(7, utakmica.getGost().getIgrac().getId());
        ps.executeUpdate();
        ps.close();
    }

    private void izmeniStatistikuZaPojedincaGost(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Statistika SET brojGolovaDatih = brojGolovaDatih-?,brojPobeda=brojPobeda-?, brojNeresenih=brojNeresenih-?, brojPoraza=brojPoraza-?, brojGolovaPrimljenih = brojGolovaPrimljenih-? WHERE igracGlavni =? AND igracSporedni = -1";
        int pobeda = 0;
        int nereseno = 0;
        int izgubljena = 0;
        if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
            pobeda++;
        } else if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
            izgubljena++;
        } else {
            nereseno++;
        }
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, utakmica.getGolGost());
        ps.setInt(2, pobeda);
        ps.setInt(3, nereseno);
        ps.setInt(4, izgubljena);
        ps.setInt(5, utakmica.getGolDomacin());
        ps.setInt(6, utakmica.getGost().getIgrac().getId());

        ps.executeUpdate();
        ps.close();
    }

    private void izmeniStatistikuZaZajednickuGost(Utakmica utakmica) throws SQLException {
        String upit = "UPDATE Statistika SET brojGolovaDatih = brojGolovaDatih-?, brojPobeda=brojPobeda-?, brojNeresenih=brojNeresenih-?, brojPoraza=brojPoraza-?,brojGolovaPrimljenih = brojGolovaPrimljenih-? WHERE igracGlavni =? AND igracSporedni =?";
        PreparedStatement ps = connection.prepareStatement(upit);
        int pobeda = 0;
        int nereseno = 0;
        int izgubljena = 0;
        if (utakmica.getGolDomacin() < utakmica.getGolGost()) {
            pobeda++;
        } else if (utakmica.getGolDomacin() > utakmica.getGolGost()) {
            izgubljena++;
        } else {
            nereseno++;
        }
        ps.setInt(1, utakmica.getGolGost());
        ps.setInt(2, pobeda);
        ps.setInt(3, nereseno);
        ps.setInt(4, izgubljena);
        ps.setInt(5, utakmica.getGolDomacin());
        ps.setInt(6, utakmica.getGost().getIgrac().getId());
        ps.setInt(7, utakmica.getDomacin().getIgrac().getId());
        ps.executeUpdate();
        ps.close();
    }

}
