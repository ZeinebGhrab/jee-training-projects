<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Utilisateurs</title>
    
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
    
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .main-card {
            border: none;
            border-radius: 16px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.15);
        }
        .table-avatar {
            width: 32px;
            height: 32px;
            border-radius: 50%;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            font-weight: 600;
            font-size: 0.9rem;
        }
        .btn-action {
            padding: 4px 12px;
            font-size: 0.85rem;
        }
        .btn-edit { background: #ffc107; border: none; color: #000; }
        .btn-edit:hover { background: #e0a800; }
        .btn-delete { background: #dc3545; border: none; color: #fff; }
        .btn-delete:hover { background: #c82333; }
    </style>
</head>
<body>
    <div class="container py-4">
        
        <!-- En-tête -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h2 class="text-white mb-0">
                    <i class="bi bi-people-fill me-2"></i>Gestion des Utilisateurs
                </h2>
                <small class="text-white-50">Application CRUD - TP 07 JEE</small>
            </div>
            <div class="d-flex gap-2">
                <span class="text-white align-self-center">
                    👤 <strong>${currentUser}</strong>
                </span>
                <a href="${pageContext.request.contextPath}/logout" 
                   class="btn btn-outline-light btn-sm">
                    <i class="bi bi-box-arrow-right me-1"></i>Déconnexion
                </a>
            </div>
        </div>
        
        <!-- Carte principale -->
        <div class="card main-card">
            <div class="card-header bg-white py-3 d-flex justify-content-between align-items-center">
                <h5 class="mb-0">Liste des utilisateurs</h5>
                <a href="${pageContext.request.contextPath}/user-form?action=add" 
                   class="btn btn-primary btn-sm">
                    <i class="bi bi-plus-circle me-1"></i>Ajouter un utilisateur
                </a>
            </div>
            <div class="card-body p-0">
                
                <!-- Message si liste vide -->
                <c:choose>
                    <c:when test="${empty users}">
                        <div class="text-center py-5">
                            <i class="bi bi-inbox fs-1 text-muted"></i>
                            <p class="text-muted mt-3 mb-0">Aucun utilisateur trouvé</p>
                            <a href="${pageContext.request.contextPath}/user-form?action=add" 
                               class="btn btn-primary btn-sm mt-3">
                                <i class="bi bi-plus"></i> Créer le premier utilisateur
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="table-responsive">
                            <table class="table table-hover mb-0 align-middle">
                                <thead class="table-light">
                                    <tr>
                                        <th scope="col" class="ps-4">#</th>
                                        <th scope="col">Utilisateur</th>
                                        <th scope="col">Mot de passe</th>
                                        <th scope="col" class="text-end pe-4">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="u" items="${users}">
                                        <tr>
                                            <td class="ps-4">
                                                <span class="table-avatar">${u.username.substring(0,1)}</span>
                                            </td>
                                            <td>
                                                <strong>${u.username}</strong>
                                                <br><small class="text-muted">ID: ${u.id}</small>
                                            </td>
                                            <td>
                                                <code class="bg-light px-2 py-1 rounded">${u.password}</code>
                                            </td>
                                            <td class="text-end pe-4">
                                                <div class="btn-group">
                                                    <a href="${pageContext.request.contextPath}/user-form?action=edit&id=${u.id}" 
                                                       class="btn btn-action btn-edit" title="Modifier">
                                                        <i class="bi bi-pencil"></i>
                                                    </a>
                                                    <a href="${pageContext.request.contextPath}/user-delete?id=${u.id}" 
                                                       class="btn btn-action btn-delete" 
                                                       onclick="return confirm('Supprimer l\'utilisateur ${u.username} ?')"
                                                       title="Supprimer">
                                                        <i class="bi bi-trash"></i>
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${not empty users}">
                <div class="card-footer bg-white py-2 text-muted small">
                    <i class="bi bi-info-circle me-1"></i>
                    ${users.size()} utilisateur(s) au total
                </div>
            </c:if>
        </div>
        
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>