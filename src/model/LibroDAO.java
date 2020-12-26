package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ApplicationProperties;

public class LibroDAO {
	
	private Connection conn = null;
	private Statement stm = null;
	PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public LibroDAO() throws RuntimeException {
		super();
		// TODO Auto-generated constructor stub
		 ApplicationProperties properties = new ApplicationProperties("db.properties");
		 try {
			 	Class.forName(properties.readProperty("JDBC_DRIVER"));
	            conn = DriverManager.getConnection(properties.readProperty("DB_URL"),
	            		properties.readProperty("DB_USER"), properties.readProperty("DB_PASS"));
	     } catch (ClassNotFoundException e) {
	            throw new RuntimeException("ERROR: failed to load MySQL JDBC driver.",e);
	     } catch (SQLException e) {
	    	 	throw new RuntimeException("ERROR: failed to access database.",e);
	     }
	}
	
	public boolean insertar(Libro libro) throws RuntimeException {
		try {
			ps = conn.prepareStatement("insert into libros values(?,?,?)");
			ps.setInt(1, libro.getIsbn());
			ps.setString(2, libro.getTitulo());
			ps.setString(3, libro.getAutor());
			int i = ps.executeUpdate();
			
			if (i==1) {
				return true;
			}
		} catch (SQLException e) {
    	 	throw new RuntimeException("ERROR: failed to insert libro",e);
		}
		return false;
	}
	
	public ArrayList<Libro> getLibros() throws RuntimeException {
		try {
			stm = conn.createStatement();
			String consulta = "select * from libros";
			rs = stm.executeQuery(consulta);
			ArrayList<Libro> libros = new ArrayList<Libro>();
			while (rs.next()) {
				Libro libro = new Libro(rs.getInt("isbn"),rs.getString("titulo"),rs.getString("autor"));
				libros.add(libro);
			}
			
			return libros;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("failed to create statement",e);
		} 
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            	throw new RuntimeException("Error cerrando la conexión",e);
            }
        }
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException("Error cerrando el resultset",e);
			}
		}
		
	}
	
}
