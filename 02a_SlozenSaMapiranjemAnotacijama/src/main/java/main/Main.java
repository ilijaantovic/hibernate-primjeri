package main;

import java.util.Date;
import java.util.List;
import model.Proizvod;
import model.Racun;
import model.StavkaRacuna;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtility;

public class Main {
    public static void main(String[] args) {
        Main pm = new Main();

        Proizvod p1 = new Proizvod(1212, "s1", 89);
        Proizvod p2 = new Proizvod(2, "s2", 10);

        // Broj 111 vec postoji u prilozenoj bazi.
        Racun r = new Racun(1122334, new Date());
        r.dodajStavku(1, p1, 3);
        r.dodajStavku(2, p2, 1);

        try {
            pm.kreirajIUbaciRacun(r);
            List<Racun> racuni = pm.listRacun();
            for (Racun racun : racuni) {
                System.out.println(racun.getRacunID());
                System.out.println(racun.getDatum());
                for (StavkaRacuna stavka : racun.getStavkeRacuna()) {
                    System.out.println(stavka + ", proizvod: "
                            + (stavka.getProizvod() == null ? "-"
                            : stavka.getProizvod().getNaziv()));
                }
            }
        } finally {
            HibernateUtility.getSessionFactory().close();
        }
    }

    public void kreirajIUbaciRacun(Racun racun) {
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                
                //session.persist(racun);//Samo insert
                session.merge(racun);
                transaction.commit();
            } catch (RuntimeException ex) {
                try {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                } catch (RuntimeException rollbackException) {
                    ex.addSuppressed(rollbackException);
                }
                throw ex;
            }
        }
    }

    public List<Racun> listRacun() {
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                List<Racun> racuni = session.createQuery("from Racun", Racun.class)
                        .getResultList();
                transaction.commit();
                return racuni;
            } catch (RuntimeException ex) {
                try {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                } catch (RuntimeException rollbackException) {
                    ex.addSuppressed(rollbackException);
                }
                throw ex;
            }
        }
    }
}
