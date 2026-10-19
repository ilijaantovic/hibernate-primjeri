package model;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//import javax.persistence.Cacheable;
//import javax.persistence.Entity;
//import javax.persistence.Id;



@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Proizvod {
    
	@Id
	    private int proizvodID;
	    private String naziv;
	    private double cena;
	    
	    public Proizvod() {
	    }
	    
	    public Proizvod(int proizvodID, String naziv, double cena) {
	        this.proizvodID=proizvodID;
	        this.naziv=naziv;
	        this.cena=cena;
	    }

	    public String getNaziv() {
	        return naziv;
	    }

	    public void setNaziv(String naziv) {
	        this.naziv = naziv;
	    }

	    public double getCena() {
	        return cena;
	    }

	    public void setCena(double cena) {
	        this.cena = cena;
	    }
	    
	    public String toString(){
	        return "ProizvodID: "+getProizvodID()+"\tNaziv: "+naziv+"\tCena: "+cena;
	    }

	    public int getProizvodID() {
	        return proizvodID;
	    }

	    public void setProizvodID(int proizvodID) {
	        this.proizvodID = proizvodID;
	    }
	    
}
