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

    private int ucesnikid;
    private Klub klub;
    private Igrac igrac;
    private Mesto mesto;

    public Ucesnik() {
    }

    public Ucesnik(int ucesnikid, Klub klub, Igrac igrac, Mesto mesto) {
        this.ucesnikid = ucesnikid;
        this.klub = klub;
        this.igrac = igrac;
        this.mesto = mesto;
    }

    public int getUcesnikid() {
        return ucesnikid;
    }

    public void setUcesnikid(int ucesnikid) {
        this.ucesnikid = ucesnikid;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
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
        if (!Objects.equals(this.igrac, other.igrac)) {
            return false;
        }
        if (!Objects.equals(this.klub, other.klub)) {
            return false;
        }
        return true;
    }

}
