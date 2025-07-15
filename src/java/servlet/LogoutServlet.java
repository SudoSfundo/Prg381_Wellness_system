// Handles logout functionality
// - Invalidate session
// - Redirect to login.jsp

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

//imports
import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet(name = "LogoutServlet", urlPatterns = { "/LogoutServlet" })
public class LogoutServlet extends HttpServlet {

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

        // Get the current session, if it exists
        // If the session does not exist, it will return null
        HttpSession session = request.getSession(false);

        // Invalidate the session if it exists
        // This will remove all attributes and invalidate the session
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
        // response.setContentType("text/html;charset=UTF-8");
        // try (PrintWriter out = response.getWriter()) {
        // /* TODO output your page here. You may use following sample code. */
        // out.println("<!DOCTYPE html>");
        // out.println("<html>");
        // out.println("<head>");
        // out.println("<title>Servlet LogoutServlet</title>");
        // out.println("</head>");
        // out.println("<body>");
        // out.println("<h1>Servlet LogoutServlet at " + request.getContextPath() +
        // "</h1>");
        // out.println("</body>");
        // out.println("</html>");
        // }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
