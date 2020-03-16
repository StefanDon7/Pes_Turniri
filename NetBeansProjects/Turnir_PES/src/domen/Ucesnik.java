/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.Objects;

/**
 *
 * @author sleza
 */
public class Ucesnik {

    private int id;
    private Igrac igrac;
    private Turnir turnir;
    private Klub klub;
    private int mesto;

    public Ucesnik() {
    }

    public Ucesnik(int id, Igrac igrac, Turnir turnir, Klub klub, int mesto) {
        this.id = id;
        this.igrac = igrac;
        this.turnir = turnir;
        this.klub = klub;
        this.mesto = mesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    public Turnir getTurnir() {
        return turnir;
    }

    public void setTurnir(Turnir turnir) {
        this.turnir = turnir;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    public int getMesto() {
        return mesto;
    }

    public void setMesto(int mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return igrac.toString() + " - " + klub.toString();
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
        final Ucesnik other = (Ucesnik) obj;
        if (!Objects.equals(this.klub, other.klub)) {
            return false;
        }
        if (!Objects.equals(this.igrac, other.igrac)) {
            return false;
        }
        if (!Objects.equals(this.turnir, other.turnir)) {
            return false;
        }
        return true;
    }

}
