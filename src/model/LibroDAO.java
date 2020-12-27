package model;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.exceptions.DatabaseException;


public class LibroDAO {
	
	public List<Libro> getLibros() throws RuntimeException{
		
	        
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Biblioteca");
		EntityManager em = factory.createEntityManager();
		TypedQuery<Libro> q = em.createQuery("select l from Libro l",Libro.class);
		List<Libro> listaLibros = null;
		try {
			listaLibros = q.getResultList();
		} finally {
			em.close();
		}
		
		return listaLibros; 
	}

	public void insertar(Libro libro) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Biblioteca");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(libro);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new RuntimeException("Error insertando libro" , e);
		}
		finally {
			em.close();
		}
	}
	
	
}
