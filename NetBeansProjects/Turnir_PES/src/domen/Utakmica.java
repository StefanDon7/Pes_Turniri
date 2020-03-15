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
public class Utakmica {

    private int id;
    private Date datum;
    private Ucesnik domacin;
    private Ucesnik gost;
    private int golDomacin;
    private int golGost;

    public Utakmica() {
    }

    public Utakmica(int id, Date datum, Ucesnik domacin, Ucesnik gost, int golDomacin, int golGost) {
        this.id = id;
        this.datum = datum;
        this.domacin = domacin;
        this.gost = gost;
        this.golDomacin = golDomacin;
        this.golGost = golGost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getGolDomacin() {
        return golDomacin;
    }

    public void setGolDomacin(int golDomacin) {
        this.golDomacin = golDomacin;
    }

    public int getGolGost() {
        return golGost;
    }

    public void setGolGost(int golGost) {
        this.golGost = golGost;
    }

    public Ucesnik getDomacin() {
        return domacin;
    }

    public void setDomacin(Ucesnik domacin) {
        this.domacin = domacin;
    }

    public Ucesnik getGost() {
        return gost;
    }

    public void setGost(Ucesnik gost) {
        this.gost = gost;
    }

    @Override
    public String toString() {
        return domacin.getKlub() + " " + getGolDomacin() + ":" + getGolGost() + " " + gost.getKlub();
    }

}
