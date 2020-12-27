package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Categoria;
import model.CategoriaDAO;
import model.Libro;
import model.LibroDAO;

/**
 * Servlet implementation class BibliotecaController
 */
@WebServlet(urlPatterns ={"","/insertar","/borrar"})
public class BibliotecaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BibliotecaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		RequestDispatcher despachador = null;
	
		if (request.getServletPath().equals("")) {
			try {
				LibroDAO libroDAO = new LibroDAO();
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				ArrayList<Libro> libros = new ArrayList<Libro>(libroDAO.getLibros());
				ArrayList<Categoria> categorias = new ArrayList<Categoria>(categoriaDAO.getCategorias());
				request.setAttribute("libros", libros);
				request.setAttribute("categorias", categorias);
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error",e.getMessage());
			}
			despachador = request.getServletContext().getRequestDispatcher("/index.jsp");
						
		} else if (request.getServletPath().equals("/insertar")) {
			try {
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				LibroDAO libroDAO = new LibroDAO();
				
				Categoria categoria = categoriaDAO.getCategoria(Integer.parseInt(request.getParameter("categorias")));
				Libro libro = new Libro(Integer.parseInt(request.getParameter("isbn"))
						,request.getParameter("titulo"),request.getParameter("autor"),categoria);
				libroDAO.insertar(libro);
				request.setAttribute("info","Libro "+ libro +" insertado");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error",e.getMessage());
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error",e.getMessage());
			}
			
			despachador = request.getServletContext().getRequestDispatcher("/");
			
		}  else if (request.getServletPath().equals("/borrar")) {
			try {
				LibroDAO libroDAO = new LibroDAO();
				libroDAO.borrar(libroDAO.getLibro(Integer.parseInt(request.getParameter("isbn"))));
				request.setAttribute("info","Libro "+ request.getParameter("isbn") +" borrado");
			} catch (RuntimeException e) {
				request.setAttribute("error",e.getMessage());
								
			}
			despachador = request.getServletContext().getRequestDispatcher("/");
		}
		
		despachador.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
