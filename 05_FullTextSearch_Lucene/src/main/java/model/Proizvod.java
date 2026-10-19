package model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

@Entity
@Indexed
public class Proizvod {

    @Id
    private int proizvodID;

    @FullTextField(analyzer = "customanalyzer")
    private String naziv;

    private double cena;

    @ManyToMany(mappedBy = "proizvodi")
    private List<Dobavljac> dobavljaci;

    public Proizvod() {
    }

    public Proizvod(int proizvodID, String naziv, double cena) {
        this.proizvodID = proizvodID;
        this.naziv = naziv;
        this.cena = cena;
    }

    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
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

    public List<Dobavljac> getDobavljaci() {
        return dobavljaci;
    }

    public void setDobavljaci(List<Dobavljac> dobavljaci) {
        this.dobavljaci = dobavljaci;
    }

    @Override
    public String toString() {
        return "ProizvodID: " + proizvodID + "\tNaziv: " + naziv + "\tCena: " + cena;
    }
}
