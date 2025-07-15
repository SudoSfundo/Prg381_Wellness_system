// Handles POST request from login.jsp
// - Retrieve and validate input
// - Fetch user using email and password (check hashed password match)
// - If valid, create session and redirect to dashboard.jsp
// - If invalid, redirect to login.jsp with error message
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

// imports 
import dao.DBUtil;
import dao.UserDAO;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Barend Blom 600228
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // check if inputs are filled in
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Email and password are required");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Create connection
        try (Connection conn = DBUtil.getConnection()) {
            User user = UserDAO.findByEmail(email, conn);

            if (user == null || user.getPassword() == null || !BCrypt.checkpw(password, user.getPassword())) {
                request.setAttribute("error", "Invalid credentials");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();

            session.setAttribute("studentNumber", user.getStudentNumber());
            session.setAttribute("name", user.getName());

            // Go to Dashboard
            response.sendRedirect("dashboard.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database Error");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }

}