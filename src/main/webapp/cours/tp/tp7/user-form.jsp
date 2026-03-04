<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${empty param.action or param.action == 'add' ? 'Ajouter' : 'Modifier'} Utilisateur</title>
    
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
    
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .form-card {
            width: 100%;
            max-width: 500px;
            border: none;
            border-radius: 16px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.2);
        }
        .form-header {
            text-align: center;
            padding: 25px 20px 10px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-radius: 16px 16px 0 0;
        }
        .form-header i {
            font-size: 2.5rem;
        }
        .btn-submit {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            padding: 10px;
            font-weight: 500;
        }
        .btn-submit:hover {
            background: linear-gradient(135deg, #5a6fd6 0%, #6a4190 100%);
        }
        .btn-cancel {
            color: #6c757d;
            text-decoration: none;
        }
        .btn-cancel:hover { color: #495057; }
        .input-group-text {
            background: #f8f9fa;
            border-right: none;
        }
        .form-control {
            border-left: none;
        }
        .form-control:focus {
            box-shadow: none;
            border-color: #667eea;
        }
    </style>
</head>
<body>
    <div class="card form-card">
        
        <!-- En-tête du formulaire -->
        <div class="form-header">
            <i class="bi bi-person-plus-fill"></i>
            <h4 class="mt-2 mb-0">
                ${empty param.action or param.action == 'add' ? 'Nouvel Utilisateur' : 'Modifier Utilisateur'}
            </h4>
        </div>
        
        <div class="card-body p-4">
            
            <!-- Message d'erreur -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-exclamation-triangle-fill me-2"></i>${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/user-form" method="post">
                
                <!-- Champ hidden pour l'id -->
                <input type="hidden" name="id" 
                       value="${not empty editUser and editUser.id > 0 ? editUser.id : ''}">
                
                <!-- Username -->
                <div class="mb-3">
                    <label for="username" class="form-label">
                        <i class="bi bi-person-fill me-1"></i>Nom d'utilisateur
                    </label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-person"></i></span>
                        <input type="text" class="form-control" id="username" name="username" 
                               value="${not empty editUser ? editUser.username : ''}" 
                               required placeholder="Entrez un nom d'utilisateur unique"
                               pattern=".{3,}" title="Minimum 3 caractères">
                    </div>
                    <div class="form-text">Doit être unique (3 caractères minimum)</div>
                </div>
                
                <!-- Password -->
                <div class="mb-4">
                    <label for="password" class="form-label">
                        <i class="bi bi-lock-fill me-1"></i>Mot de passe
                    </label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-key"></i></span>
                        <input type="text" class="form-control" id="password" name="password" 
                               value="${not empty editUser ? editUser.password : ''}" 
                               required placeholder="Entrez un mot de passe"
                               pattern=".{4,}" title="Minimum 4 caractères">
                        <button class="btn btn-outline-secondary" type="button" 
                                onclick="togglePassword()">
                            <i class="bi bi-eye" id="toggleIcon"></i>
                        </button>
                    </div>
                    <div class="form-text">Minimum 4 caractères (stocké en clair pour ce TP)</div>
                </div>
                
                <!-- Boutons -->
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary btn-submit">
                        <i class="bi bi-check-circle me-2"></i>Enregistrer
                    </button>
                </div>
                
                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/user-list" class="btn-cancel">
                        <i class="bi bi-arrow-left me-1"></i>Retour à la liste
                    </a>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Script pour afficher/masquer le mot de passe -->
    <script>
        function togglePassword() {
            const pwd = document.getElementById('password');
            const icon = document.getElementById('toggleIcon');
            if (pwd.type === 'password') {
                pwd.type = 'text';
                icon.classList.replace('bi-eye', 'bi-eye-slash');
            } else {
                pwd.type = 'password';
                icon.classList.replace('bi-eye-slash', 'bi-eye');
            }
        }
    </script>
</body>
</html>