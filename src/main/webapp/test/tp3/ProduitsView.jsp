<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion Produits</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Gestion des Produits</h2>
        
        <!-- Formulaire de recherche -->
        <div class="card mt-3">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/test_tp3" method="post" class="form-inline">
                    <input type="hidden" name="action" value="search">
                    <input type="text" name="motCle" class="form-control mr-2" value="${modele.motCle}" placeholder="Mot clé...">
                    <input type="submit" value="Chercher" class="btn btn-primary">
                </form>
            </div>
        </div>

        <!-- Formulaire d'ajout -->
        <div class="card mt-3">
            <div class="card-header">Ajouter un produit</div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/test_tp3" method="post" class="form-inline">
                    <input type="hidden" name="action" value="ajouter">
                    <input type="text" name="nom" class="form-control mr-2" placeholder="Nom produit" required>
                    <input type="number" step="0.01" name="prix" class="form-control mr-2" placeholder="Prix" required>
                    <input type="submit" value="Ajouter" class="btn btn-success">
                </form>
            </div>
        </div>

        <!-- Formulaire d'édition (affiché si action=edit) -->
        <c:if test="${modele.idProduit != null}">
        <div class="card mt-3 border-warning">
            <div class="card-header bg-warning">Modifier le produit</div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/test_tp3" method="post" class="form-inline">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${modele.idProduit}">
                    <input type="text" name="nom" class="form-control mr-2" value="${modele.motCle}" required>
                    <input type="number" step="0.01" name="prix" class="form-control mr-2" value="${modele.produits[0].prix}" required> 
                    <input type="submit" value="Modifier" class="btn btn-warning">
                    <a href="${pageContext.request.contextPath}/test_tp3?action=liste" class="btn btn-secondary ml-2">Annuler</a>
                </form>
            </div>
        </div>
        </c:if>

        <!-- Tableau des produits -->
        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prix</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${modele.produits}" var="p">
                    <tr>
                        <td>${p.idProduit}</td>
                        <td>${p.nomProduit}</td>
                        <td>${p.prix}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/test_tp3?action=edit&id=${p.idProduit}" class="btn btn-sm btn-info">Éditer</a>
                            <a href="${pageContext.request.contextPath}/test_tp3?action=supprimer&id=${p.idProduit}" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>