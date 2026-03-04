<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Liste Utilisateurs</title></head>
<body>
    <h2>Gestion des Utilisateurs</h2>
    
    <p>Connecté : <strong>${currentUser}</strong> | 
       <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </p>
    
    <a href="${pageContext.request.contextPath}/user-form?action=add">➕ Ajouter</a>
    <hr>
    
    <!-- ✅ Debug : afficher la taille de la liste -->
    <p><em>Nombre d'utilisateurs : ${users.size()}</em></p>
    
    <c:choose>
        <c:when test="${empty users}">
            <p style="color:gray">Aucun utilisateur trouvé.</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID</th><th>Username</th><th>Password</th><th>Actions</th>
                </tr>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.username}</td>
                        <td>${u.password}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user-form?action=edit&id=${u.id}">Modifier</a>
                            <a href="${pageContext.request.contextPath}/user-delete?id=${u.id}" 
                               onclick="return confirm('Supprimer ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>