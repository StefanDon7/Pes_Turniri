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
public class Ucesnik {

    private Igrac igrac;
    private Klub klub;
    private Turnir turnir;
    private int pozicija;

    public Ucesnik() {
    }

    public Ucesnik(Igrac igrac, Klub klub, Turnir turnir, int pozicija) {
        this.igrac = igrac;
        this.klub = klub;
        this.turnir = turnir;
        this.pozicija = pozicija;
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

    public Turnir getTurnir() {
        return turnir;
    }

    public void setTurnir(Turnir turnir) {
        this.turnir = turnir;
    }

    public int getPozicija() {
        return pozicija;
    }

    public void setPozicija(int pozicija) {
        this.pozicija = pozicija;
    }

    @Override
    public String toString() {
        return igrac.toString() + " - " + klub.toString();
    }

}
