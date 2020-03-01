/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Igrac;
import domen.Klub;
import domen.Liga;
import java.sql.Connection;
import java.sql.DriverManager;
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

}
