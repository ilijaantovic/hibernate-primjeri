/*
 * RacunMain.java
 *
 * Created on March 13, 2007, 10:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package main;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import model.Proizvod;
import model.Racun;
import model.StavkaRacuna;

import org.hibernate.query.Query;
import org.hibernate.Session;

import util.HibernateUtility;

/**
 *
 * @author ilijaa
 */
public class Main {
    Session session;
    /** Creates a new instance of RacunMain */
    public Main() {
    }
        public static void main(String[] args) {
        Main pm=new Main();
        Proizvod p1=new Proizvod(1,"pera",89);
        Proizvod p2=new Proizvod(2,"peric",10);
        StavkaRacuna sr1=new StavkaRacuna(1,p1,3);
        //StavkaRacuna sr2=new StavkaRacuna(2,p2,1);
        Racun r=new Racun();
        r.setRacunID(111);
        r.setDatum(new Date());
        List<StavkaRacuna> stavke=new ArrayList<StavkaRacuna>();
        sr1.setRacun(r);
        //sr2.setRacun(r);
        stavke.add(sr1);
        //stavke.add(sr2);
        r.setStavkeRacuna(stavke);
        
        pm.kreirajIUbaciRacun(r);
        List l=pm.listRacun();
        Iterator i=l.iterator();
        while(i.hasNext()){
            Object p=i.next();
            System.out.println(p);
        }
        
        //pm.session.flush();
        
        HibernateUtility.getSessionFactory().close();
    }
    
    public void kreirajIUbaciRacun(Racun r){
        session=HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(r);
        session.getTransaction().commit();
    }
    
    public List listRacun(){
        session=HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Query query=session.createQuery("from Racun", Racun.class);
        List lista=query.list();
        session.getTransaction().commit();
        return lista;
    }
}
