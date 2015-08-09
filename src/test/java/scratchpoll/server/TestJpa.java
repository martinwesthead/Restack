package scratchpoll.server;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import scratchpoll.server.entities.Greeting;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by mwesthead on 8/7/15.
 */
public class TestJpa {

    @Test
    public void testH2(){
        System.out.println("Testing...");

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your H2 JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("H2 JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:h2:~/tmp/h2-3;DB_CLOSE_DELAY=-1", "sa", "");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    @Test
    public void testjJpa(){
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("h2-eclipselink");

        EntityManager em = factory.createEntityManager();
        Greeting hello1 = new Greeting("Hello World!", "English");
        Greeting hello2 = new Greeting("Bonjour le monde!", "Francais");
        Greeting hello3 = new Greeting("Hallo Welt!", "Deutsch");

        em.getTransaction().begin();
        em.persist(hello1);
        em.persist(hello2);
        em.persist(hello3);
        em.getTransaction().commit();
        TypedQuery<Greeting> query = em.createQuery(
                "SELECT g FROM Greeting g", Greeting.class);
        List<Greeting> results = query.getResultList();

        assertEquals(results.size(),3);

    }
}
