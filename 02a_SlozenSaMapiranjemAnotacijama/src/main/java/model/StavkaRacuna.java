package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import org.hibernate.annotations.Parent;


@Embeddable /** Stavka je dio racuna, cuvan u njegovoj kolekciji stavki. */
public class StavkaRacuna implements Serializable {
    private static final long serialVersionUID = 1L;

    // Hibernate pri ucitavanju postavlja Racun na kome je stavka.
    @Parent
    private Racun racun;

    @Column(name = "rb", nullable = false)
    private int rb;

    // Proizvod je zaseban, deljeni entitet. Kaskadne izmjene "nadjačavaju" ograničenja relacionog modela, na taj način što izvrša aju dodatne upite!
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "proizvodID")
    private Proizvod proizvod;

    @Column(name = "kolicina")
    private double kolicina;

    protected StavkaRacuna() {
    }

    StavkaRacuna(Racun racun, int rb, Proizvod proizvod, double kolicina) {
        this.racun = Objects.requireNonNull(racun, "racun");
        this.rb = rb;
        this.proizvod = Objects.requireNonNull(proizvod, "proizvod");
        this.kolicina = kolicina;
    }

    public Racun getRacun() {
        return racun;
    }

    // Vezu menja Racun kada upravlja svojom kolekcijom stavki.
    void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRB() {
        return rb;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = Objects.requireNonNull(proizvod, "proizvod");
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "Stavka " + rb + ", kolicina: " + kolicina;
    }
}
