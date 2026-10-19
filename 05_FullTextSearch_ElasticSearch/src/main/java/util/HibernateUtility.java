package util;



import model.Dobavljac;
import model.Proizvod;
import org.hibernate.*;
import org.hibernate.boot.Metadata;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtility {
    private static StandardServiceRegistry registry;
   private static SessionFactory sessionFactory;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory == null) {
         try {
            
            registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml") // Loads configuration from hibernate.cfg.xml
                        .build();

                // Create a MetadataSources object
                MetadataSources sources = new MetadataSources(registry);

                // Add annotated entity classes
                //sources.addAnnotatedClass(YourEntityClass.class); // Replace YourEntityClass

                // Build the Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Build the SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
         } catch (Exception e) {
            if (registry != null) {
               StandardServiceRegistryBuilder.destroy(registry);
            }
            throw new IllegalStateException("Kreiranje SessionFactory nije uspjelo.", e);
         }
      }
      return sessionFactory;
   }

   public static void shutdown() {
      if (registry != null) {
         StandardServiceRegistryBuilder.destroy(registry);
      }
   }
}
