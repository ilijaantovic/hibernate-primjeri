package model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "racun")
public class Racun implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID se u postojecoj bazi dodeljuje rucno.
    @Id
    @Column(name = "racunID")
    private int racunID;

    @Temporal(TemporalType.DATE)
    @Column(name = "datum")
    private Date datum;

    // Stavke su sastavni delovi racuna, bez samostalnog JPA identiteta.
    // EAGER pojednostavljuje ucitavanje u ovom nastavnom primeru.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "stavkaracuna",
            joinColumns = @JoinColumn(name = "racunID", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"racunID", "rb"}))
    @OrderBy("rb ASC")
    private List<StavkaRacuna> stavkeRacuna;

    protected Racun() {
        stavkeRacuna = new ArrayList<>();
    }

    public Racun(int racunID, Date datum) {
        this();
        this.racunID = racunID;
        this.datum = datum;
    }

    /** Dodaje stavku koja pripada iskljucivo ovom racunu. */
    public StavkaRacuna dodajStavku(int rb, Proizvod proizvod, double kolicina) {
        if (stavkeRacuna.stream().anyMatch(s -> s.getRB() == rb)) {
            throw new IllegalArgumentException("RB vec postoji na racunu: " + rb);
        }
        StavkaRacuna stavka = new StavkaRacuna(this, rb, proizvod, kolicina);
        stavkeRacuna.add(stavka);
        return stavka;
    }

    public void ukloniStavku(int rb) {
        stavkeRacuna.removeIf(stavka -> {
            if (stavka.getRB() == rb) {
                stavka.setRacun(null);
                return true;
            }
            return false;
        });
    }

    public int getRacunID() {
        return racunID;
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

    @Override
    public String toString() {
        // Bez pristupa lazy kolekciji iz toString().
        return "RacunID: " + racunID + "  Datum: " + datum;
    }
}
