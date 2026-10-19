/*
 * StavkaRacuna.java
 *
 * Created on March 13, 2007, 10:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author ilijaa
 */
public class StavkaRacuna implements Serializable{
    private Racun racun;
    private int RB;
    private Proizvod proizvod;
    private double kolicina;
    /** Creates a new instance of StavkaRacuna */
    public StavkaRacuna() {
        RB=0;
        proizvod=null;
        kolicina=0;
    }
    
    public StavkaRacuna(int RB, Proizvod proizvod, double kolicina) {
        this.RB=RB;
        this.proizvod=proizvod;
        this.kolicina=kolicina;
    }

    public int getRB() {
        return RB;
    }

    public void setRB(int RB) {
        this.RB = RB;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }
    
    public String toString(){
        return "\nRB stavke: "+RB+"\tProizvod: ["+proizvod+"]\tKolicina: "+kolicina;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racunID) {
        this.racun = racunID;
    }
    
}
