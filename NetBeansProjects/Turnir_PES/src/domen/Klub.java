/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author Dule
 */
public class Klub {

    private int id;
    private String naziv;
    private Liga liga;
    private double brojZvezdica;

    public Klub() {
    }

    public Klub(int id, String naziv, Liga liga, double brojZvezdica) {
        this.id = id;
        this.naziv = naziv;
        this.liga = liga;
        this.brojZvezdica = brojZvezdica;
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

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public double getBrojZvezdica() {
        return brojZvezdica;
    }

    public void setBrojZvezdica(double brojZvezdica) {
        this.brojZvezdica = brojZvezdica;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klub other = (Klub) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
