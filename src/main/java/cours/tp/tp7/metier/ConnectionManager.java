package cours.tp.tp7.metier;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Assurez-vous que le nom de la BDD, l'user et le mdp correspondent à votre config
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_app", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
