<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Enregistrement des coordonnées</title>
</head>
<body bgcolor="orange" text="green">
    <h2>Enregistrement des coordonnées effectué</h2>
    <hr width="75%">

    <%
        String titre = (String) request.getAttribute("titre");
        String prenom = (String) request.getAttribute("prenom");
        String nom = (String) request.getAttribute("nom");
        String age = (String) request.getAttribute("age");
        String message = (String) request.getAttribute("message");
    %>

    <p><b>Bonjour <%= titre %> <%= prenom %> <%= nom %></b></p>
    <p><b>Âge : <%= age %> ans</b></p>
    <p><b><%= message %></b></p>
    
    <br>
    <a href="controleur">Retour au formulaire</a> 
    <%-- "controleur" correspond à l’URL de ta Servlet 
    @WebServlet(name = "controleur", urlPatterns = {"/controleur"})
     --%>
</body>
</html>