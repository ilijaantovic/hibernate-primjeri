package main;

import java.util.Iterator;
import java.util.List;

import model.Proizvod;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtility;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //HibernateDBBR.konekcija();
        Main m = new Main();
        m.kreirajIUbaciProizvod(55, "Brasno", 40);
        List l = m.listProizvod();
        Iterator i = l.iterator();
        while (i.hasNext()) {
            Object p = i.next();
            System.out.println(p);
        }
        HibernateUtility.getSessionFactory().close();
    }

    public void kreirajIUbaciProizvod(int id, String naziv, double cena) {
        try ( Session session = HibernateUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Proizvod p = new Proizvod();
                p.setProizvodID(id);
                p.setNaziv(naziv);
                p.setCena(cena);
                session.saveOrUpdate(p);
                transaction.commit();
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw ex;
            }
        }
    }

    public List listProizvod() {
        try ( Session session = HibernateUtility.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createQuery("from Proizvod");//List<Proizvod> lista = session.createQuery("from Proizvod", Proizvod.class).getResultList();
                List lista = query.list();//session.createQuery("from Proizvod").list();
                transaction.commit();
                return lista;
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw ex;
            }
        }
    }
}
