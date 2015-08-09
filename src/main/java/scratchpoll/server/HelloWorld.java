package scratchpoll.server;

import scratchpoll.server.entities.Greeting;

import javax.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by mwesthead on 8/2/15.
 */
@Path("/world")
public class HelloWorld {

    @GET
    @Produces("application/json")
    public List<Greeting> sayHtmlHello() throws SQLException, ClassNotFoundException {
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

        return results;
    }
}

