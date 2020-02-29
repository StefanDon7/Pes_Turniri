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

    public Mesto() {
    }

    public Mesto(Turnir turnir, Igrac igrac) {
        this.turnir = turnir;
        this.igrac = igrac;
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
    
    
}
