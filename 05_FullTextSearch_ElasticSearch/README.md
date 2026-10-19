"# NST-ORM-HIBERNATE-FullTextSearch" 


## HTTPS povezivanje sa lokalnim Elasticsearch serverom

NetBeans Run akcija u nbactions.xml koristi projektni config/elasticsearch-truststore.p12. U njemu je CA sertifikat iz lokalnog Elasticsearch 8.18.0 servera. JVM opcije trustStore, trustStoreType i trustStorePassword moraju se proslediti i pri drugim nacinima pokretanja aplikacije. Ponovo ucitati projekat u NetBeans-u pre pokretanja.

Lozinka changeit odnosi se samo na truststore sa javnim CA sertifikatom, ne na Elasticsearch korisnika elastic. HTTPS i provera sertifikata ostaju ukljuceni. hibernate.cfg.xml treba da koristi protocol=https i odgovarajuce kredencijale za server. Ako se server ponovo instalira i dobije novi CA sertifikat, potrebno je obnoviti truststore.

HibernateUtility sada prosledjuje neuspeh kreiranja SessionFactory umesto vracanja null i naknadnog NullPointerException.

TLS provera 17.10.2026: lokalni HTTPS odgovor presrece Avast Web/Mail Shield i daje sertifikat sa izdavaocem Avast Web/Mail Shield Untrusted Root. Zato Java i uz ispravan Elasticsearch CA truststore jos prijavljuje PKIX. Za lokalni server potrebno je podesiti usko URL izuzece u Avast Web Guard-u za https://localhost:9200/ (po potrebi i https://127.0.0.1:9200/), pa ponoviti proveru. Antivirus konfiguracija nije menjana. Nije dodavan Avast Untrusted Root u truststore i nije iskljucena Java provera TLS sertifikata.
