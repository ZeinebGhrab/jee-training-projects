<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="test.tp2.CreditModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Crédit Bancaire</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Simulateur de Crédit</h2>
        <form action="calcul_tp2" method="post">
            <table>
                <tr>
                    <td><label>Montant:</label></td>
                    <%-- 
                    name="montant" : C'est l'identifiant du champ. 
                    Il doit correspondre exactement à ce que la Servlet lit avec request.getParameter("montant").
                    --%>
                    <td><input type="text" name="montant" value="<%= request.getAttribute("resultat") != null ? ((CreditModel)request.getAttribute("resultat")).getMontant() : "" %>" required></td>
                    <%--
                    value="..." : C'est ici que se fait le Data Binding du Modèle vers la Vue (Etape 5, point 4 du TP).
                    Le code entre <%= ... %> est une expression JSP. Elle est exécutée et le résultat est affiché dans l'attribut value.
                    Logique : request.getAttribute("resultat") != null ? ... : ""
                    Si c'est la première fois qu'on ouvre la page, resultat est null. On affiche rien ("").
                    Si on revient après un calcul, resultat existe. On récupère l'objet, on le caste en CreditModel, et on appelle getMontant().
                    Utilité : Cela permet de réafficher les valeurs saisies par l'utilisateur après le calcul, au lieu de vider le formulaire.
                     --%>
                    <td>dt</td>
                </tr>
                <tr>
                    <td><label>Durée:</label></td>
                    <td><input type="text" name="duree" value="<%= request.getAttribute("resultat") != null ? ((CreditModel)request.getAttribute("resultat")).getDuree() : "" %>" required></td>
                    <td>mois</td>
                </tr>
                <tr>
                    <td><label>Taux:</label></td>
                    <td><input type="text" name="taux" value="<%= request.getAttribute("resultat") != null ? ((CreditModel)request.getAttribute("resultat")).getTaux() : "" %>" required></td>
                    <td>%</td>
                </tr>
            </table>
            <button type="submit">Calculer</button>
        </form>
        
        <% 
            CreditModel cm = (CreditModel) request.getAttribute("resultat");
            if (cm != null && cm.getMensualite() > 0) { 
        %>
        <div class="resultat">
            <strong>Mensualité = </strong> 
            <span class="montant"><%= String.format("%.2f", cm.getMensualite()) %> dt</span>
        </div>
        <% } %>
        
        <%--
        <% ... %> (Scriptlet) : Ce bloc contient du code Java pur exécuté côté serveur.
        Il récupère l'objet resultat stocké par la Servlet dans la requête.
        La condition if (cm != null ...) vérifie qu'un calcul a bien eu lieu avant d'essayer d'afficher le résultat. 
        Cela évite les erreurs lors du premier chargement de la page.
        <%= ... %> (Expression) : Ce bloc affiche directement le résultat dans le HTML.
        cm.getMensualite() : Récupère la valeur calculée par la couche métier.
        String.format("%.2f", ...) : Formate le nombre pour n'afficher que 2 chiffres après la virgule (ex: 1265.30 au lieu de 1265.2987...), ce qui est standard pour une devise.
         --%>
    </div>
</body>
</html>