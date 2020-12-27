package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Categoria
 *
 */
@Entity
@Table(name="categorias")
public class Categoria implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String nombre;
	
	@OneToMany(mappedBy = "categoria")
    private final List<Libro> listaLibros = new ArrayList<Libro>();
	
	private static final long serialVersionUID = 1L;

	public Categoria() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Libro> getListaLibros() {
		return listaLibros;
	}
	
	
   
}
