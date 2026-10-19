package main;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Dobavljac;
import model.Proizvod;


import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtility;

public class Main {

	/**
	 * @param args
	 */
    public static void main(String[] args) {
        //HibernateDBBR.konekcija();
        Main m=new Main();
        Set<Proizvod> proizvodi=new HashSet<Proizvod>();
        Proizvod p1=new Proizvod(4,"Kukuruzno brasno",40);
        Proizvod p2=new Proizvod(5,"Griz",30);
        proizvodi.add(p1);
        proizvodi.add(p2);
        m.kreirajIUbaciDobavljaca(4, "Centroprom", "Beograd", proizvodi);
        List l=m.listDobavljac();
        Iterator i=l.iterator();
        while(i.hasNext()){
            Object p=i.next();
            System.out.println(p);
        }
        HibernateUtility.getSessionFactory().close();
    }
    public void kreirajIUbaciDobavljaca(int dobavljacID, String naziv, String adresa, Set<Proizvod> proizvodi){
        Session session=HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Dobavljac  d=new Dobavljac();
        d.setDobavljacID(dobavljacID);
        d.setNaziv(naziv);
        d.setAdresa(adresa);
        d.setProizvodi(proizvodi);
        session.saveOrUpdate(d);
        session.getTransaction().commit();
        
    }
    
    public List listDobavljac(){
        Session session=HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Query query=session.createQuery("from Dobavljac");
        List lista=query.list();//session.createQuery("from Proizvod").list();
        session.getTransaction().commit();
        return lista;
    }
}
