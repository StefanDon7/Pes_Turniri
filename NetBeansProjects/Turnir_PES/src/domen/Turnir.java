/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.Date;

/**
 *
 * @author Dule
 */
public class Turnir {

    private int id;
    private String naziv;
    private Date datum;
    private Ucesnik pobednik = null;

    public Turnir() {
    }

    public Turnir(int id, String naziv, Date datum, Ucesnik pobednik) {
        this.id = id;
        this.naziv = naziv;
        this.datum = datum;
        this.pobednik = pobednik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Ucesnik getPobednik() {
        return pobednik;
    }

    public void setPobednik(Ucesnik pobednik) {
        this.pobednik = pobednik;
    }

}
