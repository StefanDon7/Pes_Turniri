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
    private Igrac domacin;
    private Igrac gost;
    private Klub klubDomacin;
    private Klub klubGost;
    private int golDomacin;
    private int golGost;
    private Turnir turnir;

    public Utakmica() {
    }

    public Utakmica(int id, Date datum, Igrac domacin, Igrac gost, Klub klubDomacin, Klub klubGost, int golDomacin, int golGost, Turnir turnir) {
        this.id = id;
        this.datum = datum;
        this.domacin = domacin;
        this.gost = gost;
        this.klubDomacin = klubDomacin;
        this.klubGost = klubGost;
        this.golDomacin = golDomacin;
        this.golGost = golGost;
        this.turnir = turnir;
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

    public Igrac getDomacin() {
        return domacin;
    }

    public void setDomacin(Igrac domacin) {
        this.domacin = domacin;
    }

    public Igrac getGost() {
        return gost;
    }

    public void setGost(Igrac gost) {
        this.gost = gost;
    }

    public Klub getKlubDomacin() {
        return klubDomacin;
    }

    public void setKlubDomacin(Klub klubDomacin) {
        this.klubDomacin = klubDomacin;
    }

    public Klub getKlubGost() {
        return klubGost;
    }

    public void setKlubGost(Klub klubGost) {
        this.klubGost = klubGost;
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

    public Turnir getTurnir() {
        return turnir;
    }

    public void setTurnir(Turnir turnir) {
        this.turnir = turnir;
    }
    
    
    
    
}
