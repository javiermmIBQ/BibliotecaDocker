package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CategoriaDAO {
	
	public List<Categoria> getCategorias() throws RuntimeException{
		
        
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Biblioteca");
		EntityManager em = factory.createEntityManager();
		TypedQuery<Categoria> q = em.createQuery("select c from Categoria c",Categoria.class);
		List<Categoria> listaCategorias = null;
		try {
			listaCategorias = q.getResultList();
		} finally {
			em.close();
		}
		
		return listaCategorias; 
	}
	
	
	public Categoria getCategoria(int id) {
		

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Biblioteca");
		EntityManager em = factory.createEntityManager();
		TypedQuery<Categoria> q = em.createQuery("select c from Categoria c where c.id = ?1",Categoria.class);
		q.setParameter(1, id);
		try {
			return (Categoria)q.getSingleResult();
		} finally {
			em.close();
		}
		
	}

}
