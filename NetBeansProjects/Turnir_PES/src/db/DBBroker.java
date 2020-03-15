/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Igrac;
import domen.Klub;
import domen.Liga;
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
        String upit = "INSERT INTO TURNIR(id,naziv,datum,pobednik) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, t.getId());
        ps.setString(2, t.getNaziv());
        ps.setDate(3, new java.sql.Date(t.getDatum().getTime()));
        ps.setInt(4, -1);
        ps.execute();
    }

    public void unesiUcesnike(Ucesnik ucesnik) throws SQLException {
        String upit = "INSERT INTO UCESNIK(ucesnikid,klub,igrac,mesto) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, ucesnik.getIgrac().getId());
        ps.setInt(2, ucesnik.getKlub().getId());
        ps.setInt(3, ucesnik.getIgrac().getId());
        ps.setInt(4, ucesnik.getPozicija());
        ps.execute();
    }
      public void napraviUtakmicu(Utakmica utakmica) throws SQLException {
        String upit = "INSERT INTO Utakmica(datum,domacin,gost,turnir) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setDate(1, new java.sql.Date(utakmica.getDatum().getTime()));
        ps.setInt(2, utakmica.getDomacin().getIgrac().getId());
        ps.setDouble(3, utakmica.getDomacin().get);
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
        String upit = "INSERT INTO IGRAC(ime,prezime,korisnickoIme) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setString(1, i.getIme());
        ps.setString(2, i.getPrezime());
        ps.setString(3, i.getKorisnickoIme());
        ps.execute();
    }

    public void napraviKlub(Klub k) throws SQLException {
        String upit = "INSERT INTO Klub(naziv,ligaID,brojZvezdica) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setString(1, k.getNaziv());
        ps.setInt(2, k.getLiga().getId());
        ps.setDouble(3, k.getBrojZvezdica());
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
            Ucesnik u;
            Turnir t = new Turnir();
            t.setId(id);
            t.setDatum(datum);
            t.setNaziv(naziv);
            t.setPobednik(null);
            int pobednikid = rs.getInt("pobednik");
            if (pobednikid == -1) {
                u = null;
            } else {
                u = vratiMiPobednika(t);
            }
            t.setPobednik(u);
            lista.add(t);
        }
        return lista;
    }

    private Ucesnik vratiMiPobednika(Turnir t) throws SQLException {
        String upit = "Select * from Ucesnik where turnir=" + t.getId() + " and mesto=" + 1;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        Ucesnik u = new Ucesnik();
        while (rs.next()) {
            int igracid = rs.getInt("igrac");
            int klubid = rs.getInt("klub");
            int mesto = rs.getInt("mesto");
            Igrac i = vratiMiIgraca(igracid);
            Klub k = vratiMiKlub(klubid);
            u = new Ucesnik(i, k, t, mesto);
        }
        return u;
    }

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
            Ucesnik u;
            t = new Turnir();
            t.setId(id);
            t.setDatum(datum);
            t.setNaziv(naziv);
            t.setPobednik(null);
            int pobednikid = rs.getInt("pobednik");
            if (pobednikid == -1) {
                u = null;
            } else {
                u = vratiMiPobednika(t);
            }
            t.setPobednik(u);
        }
        return t;
    }

  
}
