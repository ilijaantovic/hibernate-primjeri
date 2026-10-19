package model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

@Entity
@Indexed
public class Dobavljac {

    @Id
    private int dobavljacID;

    @FullTextField(analyzer = "customanalyzer")
    private String naziv;

    @FullTextField(analyzer = "customanalyzer")
    private String adresa;

    @ManyToMany
    @JoinTable(
        name = "dobavlja",
        joinColumns = @JoinColumn(name = "dobavljacID"),
        inverseJoinColumns = @JoinColumn(name = "proizvodID")
    )
    @Cascade(CascadeType.ALL)
    @IndexedEmbedded
    private List<Proizvod> proizvodi;

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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(List<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    @Override
    public String toString() {
        return "Dobavljac ID: " + dobavljacID + ", Naziv: " + naziv + ", Adresa: " + adresa + ", Proizvodi: " + proizvodi;
    }
}
