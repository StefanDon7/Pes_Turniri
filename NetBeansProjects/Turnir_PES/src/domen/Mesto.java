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
public class Mesto {

    private Turnir turnir;
    private Igrac igrac;
    private int redniBroj;

    public Mesto() {
    }

    public Mesto(Turnir turnir, Igrac igrac, int redniBroj) {
        this.turnir = turnir;
        this.igrac = igrac;
        this.redniBroj = redniBroj;
    }

    public Turnir getTurnir() {
        return turnir;
    }

    public void setTurnir(Turnir turnir) {
        this.turnir = turnir;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }
    

}
