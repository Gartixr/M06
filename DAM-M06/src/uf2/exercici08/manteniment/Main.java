package uf2.exercici08.manteniment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
		EntityManager em = emf.createEntityManager();

		// Store 1000 Point objects in the database:
		em.getTransaction().begin();
		for (int i = 0; i < 1000; i++) {
			Propietari p = new Propietari(dataPeticio, nomPropietari, taller, premium);
			em.persist(p);
		}
		em.getTransaction().commit();

		Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
		System.out.println("Total Points: " + q1.getSingleResult());

		Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
		System.out.println("Average X: " + q2.getSingleResult());

		TypedQuery<Propietari> query =
				em.createQuery("SELECT p FROM Point p", Propietari.class);
		List<Propietari> results = query.getResultList();
		for (Propietari p : results) {
			System.out.println(p);
		}

		em.close();
		emf.close();

	}

}
