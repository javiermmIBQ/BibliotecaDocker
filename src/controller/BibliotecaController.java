package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Categoria;
import model.CategoriaDAO;
import model.Libro;
import model.LibroDAO;
import model.Usuario;
import model.UsuarioDAO;

/**
 * Servlet implementation class BibliotecaController
 */
@WebServlet(urlPatterns ={"","/insertar","/borrar","/modificar","/registro","/login","/logout"})
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
		} else if (request.getServletPath().equals("/modificar")) {
			if (request.getParameter("titulo") == null) {
				try {
					LibroDAO libroDAO = new LibroDAO();
					CategoriaDAO categoriaDAO = new CategoriaDAO();
					ArrayList<Categoria> categorias = new ArrayList<Categoria>(categoriaDAO.getCategorias());
					Libro libro = libroDAO.getLibro(Integer.parseInt(request.getParameter("isbn")));
					
					request.setAttribute("libro", libro);
					request.setAttribute("categorias", categorias);
				} catch (RuntimeException e) {
					// TODO Auto-generated catch block
					request.setAttribute("error",e.getMessage());
				}
				
				despachador = request.getServletContext().getRequestDispatcher("/modificar.jsp");
			} else {
				LibroDAO libroDAO = new LibroDAO();
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				Libro libro = new Libro(Integer.parseInt(request.getParameter("isbn"))
						,request.getParameter("titulo"),request.getParameter("autor"),
						categoriaDAO.getCategoria(Integer.parseInt(request.getParameter("categorias"))));
				libroDAO.modificar(libro);
				request.setAttribute("info","Libro "+ request.getParameter("isbn") +" modificado");
				despachador = request.getServletContext().getRequestDispatcher("/");
			}
		} else if (request.getServletPath().equals("/registro")) {
			if (request.getParameter("usuario") == null) {
				despachador = request.getServletContext().getRequestDispatcher("/registro.jsp");
			} else {
				try {
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					Usuario usuario = new Usuario(request.getParameter("usuario"),request.getParameter("password"));
					String respuesta = usuarioDAO.register(usuario);
					if ((respuesta == "exito")) {
						request.setAttribute("info","Usuario añadido");
						HttpSession session = request.getSession();
						session.setAttribute("user", usuario);
					} else {
						request.setAttribute("info","respuesta");
					}
					despachador = request.getServletContext().getRequestDispatcher("/");
				} catch (RuntimeException e) {
					// TODO Auto-generated catch block
					request.setAttribute("error",e.getMessage());
				}
			}
		}  else if (request.getServletPath().equals("/login")) {
			if (request.getParameter("usuario") == null) {
				despachador = request.getServletContext().getRequestDispatcher("/login.jsp");
			} else {
				try {
				    UsuarioDAO usuarioDAO = new UsuarioDAO();
					Usuario usuario = new Usuario(request.getParameter("usuario"),request.getParameter("password"));
					if (usuarioDAO.login(usuario)=="exito") {
						HttpSession session = request.getSession();
						session.setAttribute("user", usuario);
					}
				} catch (RuntimeException e) {
					// TODO Auto-generated catch block
					request.setAttribute("error",e.getMessage());
				}
				despachador = request.getServletContext().getRequestDispatcher("/");
			}
		} else if (request.getServletPath().equals("/logout")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", null);
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
