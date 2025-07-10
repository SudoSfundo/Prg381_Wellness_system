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

//import com.sun.jdi.connect.spi.Connection;
import com.sun.jdi.connect.spi.Connection;

import dao.DBUtil;
import dao.UserDAO;
import User.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recieve attributes
        String StudentNo = request.getParameter("student_number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        
        //Check if email is valid
        if (!isValid(email)) {
            request.setAttribute("error", "Invalid email format");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        
        try(Connection conn = DBUtil.getConnection()) {
            //Check if email exists
            if(UserDAO.emailExists(email)){
                request.setAttribute("error", "Email already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return; 
            }
            
            // Hash password for later
            String hashedPas = BCrypt.hashpw(password, hashedPas);
            
            // Save to database 
            User user = new User(StudentNo, name, email, hashedPas);
            UserDAO.saveUser(user, conn);
            
            request.setAttribute("Success", "Registration successful!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database Error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
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
    }

    private boolean isValid(String email){
        return email.matches(".+@.+\\..+");
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
