// Handles POST request from register.jsp
// - Retrieve form data
// - Validate server-side (e.g., non-empty fields, email format, phone number)
// - Check if email or student number already exists (via UserDAO)
// - Hash password before storing
// - Save new user to database
// - Redirect to login.jsp with success message or back to register.jsp with error
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

// imports
import dao.DBUtil;
import dao.UserDAO;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.regex.Pattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Barend Blom 600228
 */
@WebServlet(name = "RegisterServlet", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    // Method to validate email format
    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$", email);
    }

    // Method to check if a field is not empty
    // This is used to ensure that required fields are filled in
    private boolean isNotEmpty(String field) {
        return field != null && !field.trim().isEmpty();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read inputs from form
        String nameAndSurname = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate inputs
        if (!isNotEmpty(nameAndSurname) || !isNotEmpty(phone) || !isValidEmail(email) || !isNotEmpty(password)) {
            request.setAttribute("error", "All fields must be valid and non-empty");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Split name and surname
        String[] parts = nameAndSurname.trim().split(" ", 2);
        String name = parts.length > 0 ? parts[0] : "";
        String surname = parts.length > 1 ? parts[1] : "";

        // Extract student number from email
        String studentNo = email.substring(0, 6);

        try (Connection conn = DBUtil.getConnection()) {
            UserDAO userDAO = new UserDAO();

            if (userDAO.emailExists(email)) {
                request.setAttribute("error", "Email already registered");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            User user = new User(studentNo, name, surname, email, phone, password);
            boolean success = userDAO.registerUser(user);

            if (success) {
                // Registration successful, redirect to login with message
                response.sendRedirect("login.jsp?success=Registration successful! Please log in.");
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // This prints to Tomcat logs
            request.setAttribute("error", "Something went wrong: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
