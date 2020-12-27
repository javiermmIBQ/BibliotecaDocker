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
	 <% String error = (String)request.getAttribute("error");
	 	if (error!=null){ %>
	 		<p><%=error%></p>
		<%	 
	 }%>
	 
	 <% String info = (String)request.getAttribute("info");
	 	if (info!=null){ %>
	 		<p><%=info%></p>
		<%	 
	 }%>

	<table> 
   		<tr> 
          <th><b>ISBN</b></th> 
          <th><b>Título</b></th> 
          <th><b>Autor</b></th> 
          <th><b>Categoria</b></th> 
          <th><b>Borrar</b></th>
         </tr>
         
         <% ArrayList<Libro> libros=(ArrayList<Libro>)request.getAttribute("libros"); 
	    	if (libros != null){	
	         for(Libro l:libros){%> 
	            <tr> 
	                <td><%=l.getIsbn()%></td> 
	                <td><%=l.getTitulo()%></td> 
	                <td><%=l.getAutor()%></td> 
	                <td><%=l.getCategoria().getNombre()%></td>
	                <td><a href="borrar?isbn=<%=l.getIsbn()%>">Borrar</a></td> 
	            </tr> 
	            
	         <%}
	         }%> 
    </table>  
    
    <div>
	  <form action="insertar">
	    <label for="isbn">ISBN</label>
	    <input type="text" id="isbn" name="isbn" placeholder="ISBN..">
	
	    <label for="titulo">Título</label>
	    <input type="text" id="titulo" name="titulo" placeholder="Titulo..">
	
	    <label for="autor">Autor</label>
	    <input type="text" id="autor" name="autor" placeholder="Autor..">
	    
	    <label for="categoria">Categoria</label>
	    <select name="categorias" id="categorias">
	    <% ArrayList<Categoria> categorias=(ArrayList<Categoria>)request.getAttribute("categorias"); 
	    	if (categorias != null){	
	         for(Categoria c:categorias){%>
		 		 <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
		  		<%}
	         }%>
		</select>
	  
	    <input type="submit" value="Submit">
	  </form>
	</div>

</body>
</html>