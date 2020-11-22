package manteniment;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VehicleServlet")
public class VehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		EntityManager em = emf.createEntityManager();

		try {
			String dataImportacio = request.getParameter("dataImportacio");
			String nomModel = request.getParameter("dataImportacio");
			String preu = request.getParameter("dataImportacio");
			String arreglat = request.getParameter("arreglat");
			if (dataImportacio != null && nomModel != null && preu != null && arreglat != null) {
				em.getTransaction().begin();
				em.persist(new Vehicle(dataImportacio, nomModel, preu, arreglat));
				em.getTransaction().commit();
			}

				List<Vehicle> guestList = em.createQuery(
						"SELECT g FROM Vehicle g", Vehicle.class).getResultList();
				request.setAttribute("guests", guestList);
				request.getRequestDispatcher("/vehicle.jsp")
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
