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
    // Récupération des paramètres
    String titre = request.getParameter("titre");
    String prenom = request.getParameter("prenom");
    String nom = request.getParameter("nom");
    String ageStr = request.getParameter("age");
    
    // Affichage du message de bienvenue
    if (titre != null || prenom != null || nom != null) {
        out.print("<p><b>Bonjour ");
        if (titre != null) out.print(titre + " ");
        if (prenom != null) out.print(prenom + " ");
        if (nom != null) out.print(nom);
        out.print("</b></p>");
    }
    
    // Traitement de l'âge
    if (ageStr != null && !ageStr.isEmpty()) {
        int age = Integer.parseInt(ageStr);
        String message = "Vous êtes un";
        if (age > 0 && age < 12) message += " enfant.";
        else if (age >= 12 && age < 18) message += " adolescent.";
        else if (age >= 18 && age < 60) message += " adulte.";
        else if (age >= 60) message += "e personne du troisième âge.";
%>

<p><b><%= message %></b></p>

<%
    }
%>

</body>
</html>