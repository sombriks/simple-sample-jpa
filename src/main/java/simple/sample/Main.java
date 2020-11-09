package simple.sample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

  private static final String CREATE_TABLE = "create table if not exists pessoa(id integer not null "
      + "primary key auto_increment, nome varchar(255) not null)";

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-pu");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.createNativeQuery(CREATE_TABLE).executeUpdate();
    tx.commit();

    tx = em.getTransaction();
    tx.begin();

    Pessoa p = new Pessoa();
    p.setNome("Teste " + System.currentTimeMillis());

    em.persist(p);
    tx.commit();

    em.createQuery("select p from Pessoa p", Pessoa.class).getResultList().forEach(e -> {
      System.out.println(e.getNome());
    });

  }
}