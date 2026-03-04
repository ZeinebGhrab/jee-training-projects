<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
    <h2>Authentification</h2>
    <form action="login" method="post">
        <c:if test="${not empty error}">
            <p style="color:red">${error}</p>
        </c:if>
        <label>Username: <input type="text" name="username" required></label><br><br>
        <label>Password: <input type="password" name="password" required></label><br><br>
        <button type="submit">Se connecter</button>
    </form>
</body>
</html>