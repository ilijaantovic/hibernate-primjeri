package model;

import java.util.List;
import java.util.Set;


public class Dobavljac {

	private int dobavljacID;
	private String naziv;
	private String adresa;
	
	private Set<Proizvod> proizvodi;

	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getDobavljacID() {
		return dobavljacID;
	}

	public void setDobavljacID(int dobavljacID) {
		this.dobavljacID = dobavljacID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public void setProizvodi(Set<Proizvod> proizvodi) {
		this.proizvodi = proizvodi;
	}
	
	@Override
	public String toString() {
		
		return "Dobavljac ID: "+dobavljacID+", Naziv: "+naziv+", Adresa: "+adresa+", Proizvodi: "+proizvodi.toString();
	}
	
}
