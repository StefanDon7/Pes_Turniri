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
        String upit = "INSERT INTO UCESNIK(igrac,klub,turnir,mesto) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, ucesnik.getIgrac().getId());
        ps.setInt(2, ucesnik.getKlub().getId());
        ps.setInt(3, ucesnik.getTurnir().getId());
        ps.setInt(4, ucesnik.getPozicija());
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

}
