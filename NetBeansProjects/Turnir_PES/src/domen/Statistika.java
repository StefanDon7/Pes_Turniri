/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author sleza
 */
public class Statistika {

    private Igrac igracGlavni;
    private Igrac igracSporedni;

    private int ukupnoPobeda;
    private int ukupnoNeresenih;
    private int ukupnoIzgubljenih;

    private int ukupnoDatihGolova;
    private int ukupnoPrimljenihGolova;

    public Statistika() {
    }

    public Statistika(Igrac igracGlavni, Igrac igracSporedni, int ukupnoPobeda, int ukupnoNeresenih, int ukupnoIzgubljenih, int ukupnoDatihGolova, int ukupnoPrimljenihGolova) {
        this.igracGlavni = igracGlavni;
        this.igracSporedni = igracSporedni;
        this.ukupnoPobeda = ukupnoPobeda;
        this.ukupnoNeresenih = ukupnoNeresenih;
        this.ukupnoIzgubljenih = ukupnoIzgubljenih;
        this.ukupnoDatihGolova = ukupnoDatihGolova;
        this.ukupnoPrimljenihGolova = ukupnoPrimljenihGolova;
    }

    public int getUkupnoPrimljenihGolova() {
        return ukupnoPrimljenihGolova;
    }

    public void setUkupnoPrimljenihGolova(int ukupnoPrimljenihGolova) {
        this.ukupnoPrimljenihGolova = ukupnoPrimljenihGolova;
    }

    public Igrac getIgracGlavni() {
        return igracGlavni;
    }

    public void setIgracGlavni(Igrac igracGlavni) {
        this.igracGlavni = igracGlavni;
    }

    public Igrac getIgracSporedni() {
        return igracSporedni;
    }

    public void setIgracSporedni(Igrac igracSporedni) {
        this.igracSporedni = igracSporedni;
    }

    public int getUkupnoPobeda() {
        return ukupnoPobeda;
    }

    public void setUkupnoPobeda(int ukupnoPobeda) {
        this.ukupnoPobeda = ukupnoPobeda;
    }

    public int getUkupnoNeresenih() {
        return ukupnoNeresenih;
    }

    public void setUkupnoNeresenih(int ukupnoNeresenih) {
        this.ukupnoNeresenih = ukupnoNeresenih;
    }

    public int getUkupnoIzgubljenih() {
        return ukupnoIzgubljenih;
    }

    public void setUkupnoIzgubljenih(int ukupnoIzgubljenih) {
        this.ukupnoIzgubljenih = ukupnoIzgubljenih;
    }

    public int getUkupnoDatihGolova() {
        return ukupnoDatihGolova;
    }

    public void setUkupnoDatihGolova(int ukupnoDatihGolova) {
        this.ukupnoDatihGolova = ukupnoDatihGolova;
    }

    @Override
    public String toString() {
        return igracGlavni.toString() + igracSporedni;
    }

}
