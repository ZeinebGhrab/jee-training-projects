<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - TP Session</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { background: #f4f4f4; padding: 20px; border-radius: 5px; }
        .error { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2>🔐 Page de Connexion</h2>
    
    <% if ("empty".equals(request.getParameter("error"))) { %>
        <p class="error">⚠️ Veuillez entrer un nom d'utilisateur</p>
    <% } %>
    
    <form action="<%= request.getContextPath() %>/login_tp5" method="post">
        <label>Username: 
            <input type="text" name="username" required autofocus>
        </label>
        <br><br>
        <input type="submit" value="Se connecter">
    </form>
    
    <p><small>Session ID: <%= session.getId() %></small></p>
</body>
</html>