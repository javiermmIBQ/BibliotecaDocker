package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class UsuarioDAO {
	
	public String register(Usuario usuario) throws RuntimeException{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Biblioteca");
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = null;
		try {
			Usuario newUser = em.find(Usuario.class, usuario.getUsuario());
			if (newUser == null){
				tx = em.getTransaction();
				tx.begin();
				em.persist(usuario);
				tx.commit();
				return "exito";
			} else {
				return "error: usuario ya existe";
			}
		} catch (PersistenceException e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			throw new RuntimeException("Error creando usuario" , e);
		} finally {
			em.close();
		}
		
	}

	public String login(Usuario usuario) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Biblioteca");
		EntityManager em = factory.createEntityManager();
		
		try {
			Usuario loginUser = em.find(Usuario.class, usuario.getUsuario());
			if (loginUser == null){
				return "usuario no encontrado";
			} else {
				if (usuario.getPassword().equals(loginUser.getPassword())) {
					return "exito";
				} else {
					return "password incorrecta";
				}
			}
		} catch (PersistenceException e){
			throw new RuntimeException("Login error");
		} finally {
			em.close();
		}
	}

}
