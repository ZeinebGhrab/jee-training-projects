<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire d'enregistrement</title>
</head>
<body bgcolor="orange" text="green">
    <h2>Enregistrement des coordonnées</h2>
    <hr width="75%">
    <%-- 
    "controleur" correspond au mapping de ta Servlet
    @WebServlet(name = "controleur", urlPatterns = {"/controleur"})
     --%>
    <form action="controleur" method="post">
        <label>Titre :</label>
        <select name="titre">
            <option value="M.">M.</option>
            <option value="Mme">Mme</option>
            <option value="Mlle">Mlle</option>
        </select><br><br>
        
        <label>Prénom :</label>
        <input type="text" name="prenom" required><br><br>
        
        <label>Nom :</label>
        <input type="text" name="nom" required><br><br>
        
        <label>Âge :</label>
        <input type="number" name="age" min="1" required><br><br>
        
        <input type="submit" value="Envoyer">
    </form>
</body>
</html>