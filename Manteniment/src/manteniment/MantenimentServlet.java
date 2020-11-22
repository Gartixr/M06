package manteniment;
 
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.persistence.*;
 
@WebServlet("/MantenimentServlet")
public class MantenimentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Obtain a database connection:
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
 
        try {
        	String dataImportacio = request.getParameter("dataImportacio");
			String nomModel = request.getParameter("nomModel");
			String preu = request.getParameter("preu");
			String arreglat = request.getParameter("arreglat");
			
			String dataPeticio = request.getParameter("dataPeticio");
			String nomPropietari = request.getParameter("nomPropietari");
			String taller = request.getParameter("taller");
			String premium = request.getParameter("premium");
			
			if ((dataImportacio != null && nomModel != null && preu != null && arreglat != null) && (dataPeticio != null && nomPropietari != null && taller != null && premium != null)) {
				em.getTransaction().begin();
				em.persist(new Vehicle(dataImportacio, nomModel, preu, arreglat));
				em.persist(new Propietari(dataPeticio, nomPropietari, taller, premium));
				em.getTransaction().commit();
			}

				List<Vehicle> vehicleList = em.createQuery(
						"SELECT g FROM Vehicle g", Vehicle.class).getResultList();
				request.setAttribute("vehicles", vehicleList);
				request.getRequestDispatcher("/manteniment.jsp")
				.forward(request, response); 
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // Close the database connection:
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}