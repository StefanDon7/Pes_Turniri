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
public class Mesto {

    private int id;
    private Turnir turni;
    private int mesto;

    public Mesto() {
    }

    public Mesto(int id, Turnir turni, int mesto) {
        this.id = id;
        this.turni = turni;
        this.mesto = mesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMesto() {
        return mesto;
    }

    public void setMesto(int mesto) {
        this.mesto = mesto;
    }

    public Turnir getTurni() {
        return turni;
    }

    public void setTurni(Turnir turni) {
        this.turni = turni;
    }

    @Override
    public String toString() {
        return mesto + "";
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
        final Mesto other = (Mesto) obj;
        if (this.mesto != other.mesto) {
            return false;
        }
        if (!Objects.equals(this.turni, other.turni)) {
            return false;
        }
        return true;
    }

}
