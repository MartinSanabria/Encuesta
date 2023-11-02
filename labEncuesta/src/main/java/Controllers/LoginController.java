/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modeloDAO.usuarioDAO;

/**
 *
 * @author martinsanabria
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        try {
            //Validar lo que el action sea login
             if(request.getParameter("action").equals("login")){
                 
                    usuarioDAO adminDao = new usuarioDAO(); 
                    String passwHash = this.hashContrasena(request.getParameter("password"));
                    Usuario user = adminDao.ConsultaUsuario(request.getParameter("email"), passwHash,0);
                if(user.getEmail() != null && user.getPassword() != null){
                   if (user.getEmail().equals(request.getParameter("email")) && user.getPassword().equals(passwHash)) {
                        // El usuario se encontró en la tabla de clientes
                        HttpSession session = request.getSession(true);
                        session.setAttribute("admin", user);
                        String successMessage = "Inicio de sesión satisfactorio.";
                        request.setAttribute("successMessage", successMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/index.jsp");
                        dispatcher.forward(request, response);

                    } 
                }

                    usuarioDAO userDao = new usuarioDAO(); 
                    String passwordHash = this.hashContrasena(request.getParameter("password"));
                    Usuario cliente = userDao.ConsultaUsuario(request.getParameter("email"), passwordHash,1);
                    
                    if(cliente.getEmail()!= null && cliente.getPassword()!= null){
                        if (cliente.getEmail().equals(request.getParameter("email")) && cliente.getPassword().equals(passwordHash)) {
                        // El usuario se encontró en la tabla de clientes
                        HttpSession session = request.getSession(true);
                        session.setAttribute("cliente", cliente);
                        String successMessage = "Inicio de sesión satisfactorio.";
                        request.setAttribute("successMessage", successMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/index.jsp");
                        dispatcher.forward(request, response);

                    } else {
                        String errorMessage = "Credenciales incorrectas.";

                        request.setAttribute("errorMessage", errorMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                        dispatcher.forward(request, response);
                        }
                    } else { 
                        String errorMessage = "Credenciales incorrectas.";

                        request.setAttribute("errorMessage", errorMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                        dispatcher.forward(request, response);
                    }
                    
                
            } else if(request.getParameter("action").equals("create")){
                
                 usuarioDAO client=new usuarioDAO();
                 
                 Usuario userFound = client.buscarPorCorreo(request.getParameter("email"));
                 
                 if( userFound != null) {
                     if(userFound.getEmail()!= null){ 
                          if(userFound.getEmail().equals(request.getParameter("email"))){
                            String errorMessage = "El correo ya se encuentra registrado.";
                            request.setAttribute("errorMessage", errorMessage);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                            dispatcher.forward(request, response);
                         }
                     }
                     else { 
                         String passwordHash = this.hashContrasena(request.getParameter("password"));
                         Usuario user = new Usuario(request.getParameter("nombre"),request.getParameter("email"),passwordHash,1);
                         client.agregar(user);
                         
                         
                        String successMessage = "Se registro satisfactoriamente";
                        request.setAttribute("successMessage", successMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                        dispatcher.forward(request, response);
                     }
                 }
                 
                 
                 
                 
            } 
             
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
       
        
       
    }
    
    private String hashContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(contrasena.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%04x", b));
            }
            return sb.toString().substring(0, 20);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
