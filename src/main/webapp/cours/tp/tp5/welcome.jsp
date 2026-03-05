<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bienvenue</title>
</head>
<body>
    <h2>🎉 Page de Bienvenue</h2>
    
    <% if (session.getAttribute("user") == null) { %>
        <p>❌ Vous n'êtes pas connecté !</p>
        <a href="<%= request.getContextPath() %>/cours/tp/tp5/login.jsp">→ Aller au login</a>
    <% } else { %>
        <p>✅ Bienvenue <strong>${sessionScope.user}</strong> !</p>
        <p><small>ID de session : ${pageContext.session.id}</small></p>
        <p><small>Créée le : <%= new java.util.Date(session.getCreationTime()) %></small></p>
        
        <!-- encodeURL pour supporter la réécriture d'URL si cookies désactivés -->
        <a href="<%= response.encodeURL(request.getContextPath() + "/logout_tp5") %>">🚪 Se déconnecter</a>
    <% } %>
</body>
</html>