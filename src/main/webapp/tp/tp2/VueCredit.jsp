<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="tp.tp2.web.CreditModel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Credit Bancaire</title>
</head>
<body>

<%
CreditModel cm=(CreditModel)request.getAttribute("result"); 
%>
<div> <form action="calcul" method="post">
 <table>
 <tr>
 <td> Montant: </td>
 <td><input type="text" name="montant" value="<%= cm != null ? cm.getMontant() : "" %>" required>
 </td><td>dt</td>
 </tr>
 <tr>
 <td> Duree </td>
 <td> <input type="text" name="duree" value="<%= cm != null ? cm.getDuree() : "" %>" required></td>
 <td> mois </td>
 </tr>
 <tr>
 <td> Taux </td>
 <td> <input type="text" name="taux" value="<%= cm != null ? cm.getTaux() : "" %>" required></td>
 <td> % </td>
 </tr>
 </table>
<button type="submit">Calculer</button> 
</form>
</div>
<div> Mensualité= <%= cm != null ? cm.getMensualite(): "" %> </div>
</body>
</html>