package cours.tp.tp7.metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    
    // ⚠️ ADAPTEZ À VOTRE ENVIRONNEMENT
    private static final String URL = "jdbc:mysql://localhost:3306/crud_app" +
            "?useSSL=false" +
            "&serverTimezone=UTC" +
            "&characterEncoding=UTF-8" +
            "&allowPublicKeyRetrieval=true";
    
    private static final String USER = "root";
    private static final String PASS = "";  
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";  

    public static Connection getConnection() throws SQLException {
        try {
            // Chargement explicite du driver (optionnel avec JDBC 4+, mais sûr)
            Class.forName(DRIVER);
            
            // ✅ Création de la connexion SANS try-with-resources
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            
            // Vérification de sécurité
            if (conn == null || conn.isClosed()) {
                throw new SQLException("Impossible d'obtenir une connexion valide");
            }
            
            System.out.println("[DB] ✅ Connexion créée: " + conn);
            return conn;  
            
        } catch (ClassNotFoundException e) {
            System.err.println("[DB] ❌ Driver MySQL introuvable: " + DRIVER);
            throw new SQLException("Driver not found", e);
        }
    }
}