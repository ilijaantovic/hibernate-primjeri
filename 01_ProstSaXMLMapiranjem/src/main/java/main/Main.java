package main;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;

import model.Proizvod;

import org.hibernate.query.Query;
import org.hibernate.query.NativeQuery;
import org.hibernate.Session;

import util.HibernateUtility;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Main m = new Main();
        m.kreirajIUbaciProizvod(543210, "Ruski kvas", 61);

        //Proizvod p=new Proizvod(56,"aaaaaaaa",50);
        //m.obrisiProizvod(p);
        //  System.out.println(m.procitajProizvod(56));
        //m.criteriaProizvod();
        List l = m.listProizvodSQL();
        //List l = m.findByCenaVecaOd(50);
        //System.out.println("qbe: "+m.loadByExample());

        //List l=m.listProizvod();
        Iterator i = l.iterator();
        while (i.hasNext()) {
            Object p1 = i.next();
            System.out.println(p1);
        }
        HibernateUtility.getSessionFactory().close();
    }

    public void kreirajIUbaciProizvod(int id, String naziv, double cena) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Proizvod p = new Proizvod();
        p.setProizvodID(id);
        p.setNaziv(naziv);
        p.setCena(cena);
        //specificno Hibernate (INSERT/UPDATE)
        //session.saveOrUpdate(p);
        //uskladjeno sa JPA (INSERT/UPDATE)
        Proizvod managedProizvod = session.merge(p);//managedProizvod ima dodeljeni ID ukoliko je autoinc, ali p nema, p je i dalje NEW
        //uskladjeno sa JPA (samo INSERT)
        //session.persist(p);//p ima dodeljeni ID ukoliko je autoinc
        session.getTransaction().commit();
        session.close();
    }

    public List listProizvod() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Proizvod> query = session.createQuery("from Proizvod", Proizvod.class);
        List lista = query.getResultList();
        //ili 
        /*
        var query = session.createSelectionQuery("select p from Proizvod p", Proizvod.class); 
        List<Proizvod> lista = query.getResultList();
         */
        session.getTransaction().commit();
        session.close();
        return lista;
    }

    public List<Proizvod> findByCenaVecaOd(double minimalnaCena) {

        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        
        String hql = "from Proizvod p where p.cena > :minCena";
        Query<Proizvod> query = session.createQuery(hql, Proizvod.class);
        query.setParameter("minCena", minimalnaCena);
        List<Proizvod> lista = query.getResultList();
        
        session.getTransaction().commit();
        session.close();
        return lista;
    }

    public List<Proizvod> criteriaProizvod() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        //kreiranje CriteriaBuilder iz sesije
        CriteriaBuilder cb = session.getCriteriaBuilder();
        //kreiranje CriteriaQuery za klasu Proizvod
        CriteriaQuery<Proizvod> cq = cb.createQuery(Proizvod.class);
        // pravi se osnova upita SELECT * FROM PROIZVOD
        Root<Proizvod> root = cq.from(Proizvod.class);
        // WHERE naziv LIKE 'p%'
        cq.select(root).where(cb.like(root.get("naziv"), "p%"));
        // kreiranje objekta klase Query iz CriteriaQuery
        Query<Proizvod> query = session.createQuery(cq);
        query.setMaxResults(3);  // postavljanje ogranicenja za rezultat
        List<Proizvod> lista = query.getResultList();
        session.getTransaction().commit();
        session.close();
        System.out.println(lista);
        return lista;
    }

    /*
    public List criteriaProizvod(){
        Session sesija=HibernateUtility.getSessionFactory().openSession();
        sesija.beginTransaction();
        Criteria crit = sesija.createCriteria(Proizvod.class);
        //postavlja maksimalni broj procitanih slogova
        crit.setMaxResults(3);
        //uvodi kriterijum da naziv proiyvoda treba da pocinje slovom 'p'
        crit.add(Restrictions.like("naziv", "p%"));
        List lista = crit.list();
        sesija.getTransaction().commit();
        System.out.println(lista);
        return lista;
    }
    
    
    public Proizvod loadByExample() {
    	Session sesija=HibernateUtility.getSessionFactory().openSession();
    	sesija.beginTransaction();
    	Proizvod p = new Proizvod();
    	p.setNaziv("Cokoladna Bananica");
    	p.setCena(70);
    	Criteria crit = sesija.createCriteria(Proizvod.class);
    	crit.add(Example.create(p));
    	p = (Proizvod) crit.uniqueResult();
    	sesija.getTransaction().commit();
    	return p;
   }
     */
    public Proizvod procitajProizvod(int i) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Proizvod p = session.get(Proizvod.class, i);//ili load, ali baca Exception ako ne nadje, nije preporu;eno u novijim verzijama
        System.out.println("Proizvod preko get: " + p);
        p.setCena(p.getCena() + 1);
        session.getTransaction().commit();
        session.close();
        return p;
    }

    public void azurirajProizvod(Proizvod p) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        //specificno Hibernate
        //session.update(p);
        //uskladjeno sa JPA
        session.merge(p);
        session.getTransaction().commit();
        session.close();
    }

    public void obrisiProizvod(Proizvod p) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        //specificno Hibernate
        //session.delete(p);
        //uskladjeno sa JPA
        Proizvod managedProizvod = session.merge(p);
        session.remove(managedProizvod);
        session.getTransaction().commit();
        session.close();
    }

    public List<Proizvod> listProizvodSQL() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        NativeQuery<Proizvod> query = session.createNativeQuery("SELECT * FROM Proizvod", Proizvod.class);
        List<Proizvod> lista = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return lista;
    }
}
