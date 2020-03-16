/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Dule
 */
public class Utakmica {

    private Ucesnik domacin;
    private Ucesnik gost;
    private Date datum;
    private int golDomacin = -1;
    private int golGost = -1;

    public Utakmica() {
    }

    public Utakmica(Ucesnik domacin, Ucesnik gost, Date datum) {
        this.domacin = domacin;
        this.gost = gost;
        this.datum = datum;
    }

    public Utakmica(Ucesnik domacin, Ucesnik gost, Date datum, int golDomacin, int golGost) {
        this.domacin = domacin;
        this.gost = gost;
        this.datum = datum;
        this.golDomacin = golDomacin;
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

    @Override
    public String toString() {
        return domacin.getKlub() + " " + getGolDomacin() + ":" + getGolGost() + " " + gost.getKlub();
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
        final Utakmica other = (Utakmica) obj;
        if (!Objects.equals(this.domacin, other.domacin)) {
            return false;
        }
        if (!Objects.equals(this.gost, other.gost)) {
            return false;
        }
        return true;
    }

}
