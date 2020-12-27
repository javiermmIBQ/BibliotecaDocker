<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Libro" %>
<%@ page import="model.Categoria" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<%Libro libro = (Libro)request.getAttribute("libro"); %>
	<div>
	  <form action="modificar">
	    <label for="isbn">ISBN</label>
	    <input type="text" id="isbn" name="isbn" value="<%=libro.getIsbn() %>" placeholder="ISBN..">
	
	    <label for="titulo">Título</label>
	    <input type="text" id="titulo" name="titulo" value="<%=libro.getTitulo() %>" placeholder="Titulo..">
	
	    <label for="autor">Autor</label>
	    <input type="text" id="autor" name="autor" value="<%=libro.getAutor() %>" placeholder="Autor..">
	    
	    <label for="categoria">Categoria</label>
	    <select name="categorias" id="categorias">
	    <% ArrayList<Categoria> categorias=(ArrayList<Categoria>)request.getAttribute("categorias"); 
	    	if (categorias != null){	
	         for(Categoria c:categorias){%>
		 		 <option value="<%=c.getId()%>"
		 		 <%if (c.getId() == libro.getCategoria().getId()){
		 		 		%> selected<% 
		 		 	} %>
		 		 ><%=c.getNombre()%>
		 		 	
		 		 </option>
		  		<%}
	         }%>
		</select>
	  
	    <input type="submit" value="Modificar">
	  </form>
	</div>
</body>
</html>