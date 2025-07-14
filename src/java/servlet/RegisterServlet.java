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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Barend Blom 600228
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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

        // Retrieve form data
        String studentNo = request.getParameter("student_number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        
        // Basic validation
        if (!isNotEmpty(studentNo) || !isNotEmpty(name) || !isNotEmpty(surname) || 
            !isValidEmail(email) || !isNotEmpty(phone) || !isNotEmpty(password)) {
            request.setAttribute("error", "All fields must be valid and non-empty");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Create connection and check if email already exists
        // If it exists, return error message
        try (Connection conn = DBUtil.getConnection()) {
            UserDAO userDAO =new UserDAO();

            if (userDAO.emailExists(email)) {
                request.setAttribute("error", "Email already registered");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
                        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(studentNo, name, surname, email, phone, hashedPassword);
            boolean success = userDAO.registerUser(user);
            if (success) {
                response.sendRedirect("login.jsp?success=true");
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
        
        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegisterServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

