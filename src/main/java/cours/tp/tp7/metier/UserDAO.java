package cours.tp.tp7.metier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	

    // Authentification
    public boolean validateUser(String username, String password) {
        // Trim pour éviter les espaces invisibles
        String user = (username != null) ? username.trim() : "";
        String pass = (password != null) ? password.trim() : "";
        
        // DEBUG : À supprimer en production
        System.out.println("[DEBUG] Login attempt - user: '" + user + "', pass: '" + pass + "'");
        
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user);
            ps.setString(2, pass);
            
            System.out.println("[DEBUG] Executing query: " + ps);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("[DEBUG] Login SUCCESS for user: " + user);
                    return true;
                } else {
                    System.out.println("[DEBUG] Login FAILED - no matching user");
                    return false;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("[ERROR] Database error during login:");
            e.printStackTrace();
            return false;
        }
    }
    // READ ALL
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return users;
    }

    // CREATE
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // UPDATE
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username=?, password=? WHERE id=?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // DELETE
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    
    // GET BY ID (pour l'édition)
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

}
