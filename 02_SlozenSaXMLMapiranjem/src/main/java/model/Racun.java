

package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ilijaa
 */
public class Racun implements Serializable{
    private int racunID; 
    private Date datum;
    private List<StavkaRacuna> stavkeRacuna;
    /** Creates a new instance of Racun */
    public Racun() {
    }

    public int getRacunID() {
        return racunID;
    }

    public void setRacunID(int racunID) {
        this.racunID = racunID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public List<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(List<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

    public String toString() {
        return "RacunID: "+racunID+"  Datum: "+datum+"   Stavke racuna:\n"+stavkeRacuna.toString();
    }
    
}
