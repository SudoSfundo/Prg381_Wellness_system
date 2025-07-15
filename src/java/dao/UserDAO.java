// This class performs CRUD operations on the Users table.
// - registerUser(User user): inserts a new user
// - emailExists(String email): checks for duplicate emails
// - authenticate(String email, String password): fetches user for login
// - Use prepared statements to prevent SQL injection

package dao;

import model.User;
import dao.DBUtil;
import dao.PasswordUtil;

import java.sql.*;

public class UserDAO {

    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (student_number, name, surname, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(user.getStudentNumber()));
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, PasswordUtil.hashPassword(user.getPassword()));

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User authenticate(String email, String rawPassword) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashed = rs.getString("password");
                if (PasswordUtil.checkPassword(rawPassword, hashed)) {
                    User user = new User();
                    user.setStudentNumber(rs.getString("student_number"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add this method to fetch user by email for authentication
    public static User findByEmail(String email, Connection conn) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setStudentNumber(rs.getString("student_number"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }
        return null;
    }
}
