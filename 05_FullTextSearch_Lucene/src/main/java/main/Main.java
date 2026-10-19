package main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Dobavljac;
import org.hibernate.HibernateException;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;

/*
//prethodne verzije
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
 */
import util.HibernateUtility;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws HibernateException {
        Main m = new Main();

        List<Dobavljac> dobavljaci = m.pronadjiDobavljaceFullText("čačak кремпита");//Чачак Котор //Čačak Котор //кремпита
        System.out.println(dobavljaci);
        HibernateUtility.getSessionFactory().close();
    }

    public List<Dobavljac> pronadjiDobavljaceFullText(String keyword) {
        List dobavljaci = null;
        try {
            
            SearchSession searchSession = Search.session(HibernateUtility.getSessionFactory().openSession());

            MassIndexer indexer = searchSession.massIndexer(Dobavljac.class)
                    .threadsToLoadObjects(7);

            indexer.startAndWait();

            //pretraga
            SearchResult<Dobavljac> result = searchSession.search(Dobavljac.class)
                    .where(f -> f//.match()
                        .simpleQueryString()
                        .fields("naziv", "adresa", "proizvodi.naziv")
                        .matching(keyword)
                    ).fetch(20);

            //long totalHitCount = result.total().hitCount();
            dobavljaci = result.hits();
            return dobavljaci;
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dobavljaci;
    }

}
