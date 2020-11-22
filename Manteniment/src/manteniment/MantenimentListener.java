package manteniment;
 
import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
 
@WebListener
public class MantenimentListener implements ServletContextListener {

    // Prepare the EntityManagerFactory & Enhance:
    public void contextInitialized(ServletContextEvent e) {
        com.objectdb.Enhancer.enhance("manteniment.*");
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/manteniment.odb");
        e.getServletContext().setAttribute("emf", emf);
    }
 
    // Release the EntityManagerFactory:
    public void contextDestroyed(ServletContextEvent e) {
        EntityManagerFactory emf =
            (EntityManagerFactory)e.getServletContext().getAttribute("emf");
        emf.close();
    }
}