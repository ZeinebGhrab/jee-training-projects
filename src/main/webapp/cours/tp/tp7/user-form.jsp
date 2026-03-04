<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Formulaire Utilisateur</title></head>
<body>
    <h2>${empty param.action or param.action == 'add' ? 'Ajouter' : 'Modifier'} Utilisateur</h2>
    
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
    
    <form action="${pageContext.request.contextPath}/user-form" method="post">
        
        <!-- ✅ Utiliser editUser au lieu de user -->
        <input type="hidden" name="id" value="${not empty editUser ? editUser.id : ''}">
        
        <label>Username: 
            <input type="text" name="username" 
                   value="${not empty editUser ? editUser.username : ''}" 
                   required>
        </label><br><br>
        
        <label>Password: 
            <input type="text" name="password" 
                   value="${not empty editUser ? editUser.password : ''}" 
                   required>
        </label><br><br>
        
        <button type="submit">Enregistrer</button>
        <a href="${pageContext.request.contextPath}/user-list">Annuler</a>
    </form>
</body>
</html>