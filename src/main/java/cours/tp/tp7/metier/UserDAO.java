package cours.tp.tp7.metier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // ========================================================================
    // AUTHENTIFICATION
    // ========================================================================
    public boolean validateUser(String username, String password) {
        String user = (username != null) ? username.trim() : "";
        String pass = (password != null) ? password.trim() : "";
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        
        System.out.println("[DAO] validateUser: '" + user + "'");
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user);
            ps.setString(2, pass);
            
            try (ResultSet rs = ps.executeQuery()) {
                boolean valid = rs.next();
                System.out.println("[DAO] validateUser result: " + valid);
                return valid;
            }
            
        } catch (SQLException e) {
            System.err.println("[DAO] ❌ Error in validateUser: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ========================================================================
    // READ ALL - Liste des utilisateurs
    // ========================================================================
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, password FROM users ORDER BY id";
        
        System.out.println("[DAO] getAllUsers: fetching...");
        
        // ✅ Try-with-resources COMPLET : Connection + Statement + ResultSet
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
                ));
            }
            System.out.println("[DAO] getAllUsers: loaded " + users.size() + " users");
            return users;
            
        } catch (SQLException e) {
            System.err.println("[DAO] ❌ Error in getAllUsers: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // ========================================================================
    // CREATE - Ajouter un utilisateur
    // ========================================================================
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        System.out.println("[DAO] addUser: '" + user.getUsername() + "'");
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            
            int rows = ps.executeUpdate();
            System.out.println("[DAO] addUser: " + rows + " row(s) affected");
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("[DAO] ❌ Error in addUser: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ========================================================================
    // UPDATE - Modifier un utilisateur
    // ========================================================================
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        System.out.println("[DAO] updateUser: id=" + user.getId());
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            
            int rows = ps.executeUpdate();
            System.out.println("[DAO] updateUser: " + rows + " row(s) affected");
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("[DAO] ❌ Error in updateUser: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ========================================================================
    // DELETE - Supprimer un utilisateur
    // ========================================================================
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        System.out.println("[DAO] deleteUser: id=" + id);
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            int rows = ps.executeUpdate();
            System.out.println("[DAO] deleteUser: " + rows + " row(s) affected");
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("[DAO] ❌ Error in deleteUser: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ========================================================================
    // GET BY ID - Pour l'édition 
    // ========================================================================
    public User getUserById(int id) {
        String sql = "SELECT id, username, password FROM users WHERE id = ?";
        System.out.println("[DAO] getUserById: id=" + id);
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) { 
            
          
            ps.setInt(1, id);
   
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                    System.out.println("[DAO] getUserById: ✅ found '" + user.getUsername() + "'");
                    return user;
                }
                return null;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
