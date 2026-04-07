<%@ page language="java" contentType="text/html; charset=windows-1256"
pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- jstl est une ensemple de bibliothéque, on va importer core -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>TP3</title>
</head>
<body>
<form action="tp3" method="post" >
<input type="text" name="motCle" value="${modele.motCle}">
<!-- ${modele.motCle} : il trouve motCle privée s'il trouve getter il le récupére // $ => meme si modele est null ne génére pas un erreur "modele is null" --->
<input type="submit" value="OK">
</form>
<table border="1" width="80%">
<tr>
<th>ID</th> <th>Nom</th> <th>Prix</th>
</tr>
<c:forEach items="${modele.produits}" var="p">
<tr>
<td>${p.idProduit}</td>
<td>${p.nomProduit}</td>
<td>${p.prix}</td>
</tr>
</c:forEach>
</table>
</body>


