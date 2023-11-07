/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Encuesta;
import modelo.PasswordDecryptor;
import modelo.Usuario;
import modeloDAO.encuestaDAO;
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
             if (request.getParameter("action").equals("login")) {
                usuarioDAO adminDao = new usuarioDAO();
                PasswordDecryptor decryptor = new PasswordDecryptor();
                String decryptedPassword = decryptor.encrypt(request.getParameter("password"));
                Usuario user = adminDao.ConsultaUsuario(request.getParameter("email"), decryptedPassword, 0);

                if (user.getEmail() != null && user.getPassword() != null) {
                    if (user.getEmail().equals(request.getParameter("email")) && user.getPassword().equals(decryptedPassword)) {
                        // El usuario se encontró en la tabla de clientes
                        HttpSession session = request.getSession(true);
                        session.setAttribute("admin", user);
                        // Aquí guardamos el ID y el nombre del usuario en la sesión
                        session.setAttribute("userId", user.getUser_id());
                        session.setAttribute("userName", user.getNombre());

                        String successMessage = "Inicio de sesión satisfactorio.";
                        request.setAttribute("successMessage", successMessage);
                        
                        // Utilizamos RequestDispatcher para redirigir al administrador
                            Encuesta encuesta = new Encuesta();
                            encuestaDAO encuestaDao = new encuestaDAO();
                            List<Encuesta> encuestas = encuestaDao.buscarTodasLasEncuestas();

                        request.setAttribute("encuestas", encuestas);
                        session.setAttribute("inicioSesionDesdeLogin", true);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/index.jsp");
                        dispatcher.forward(request, response);
                        return; // Importante: detener la ejecución del código después de la redirección
                    }
                }

                usuarioDAO userDao = new usuarioDAO();
                decryptedPassword = decryptor.encrypt(request.getParameter("password"));
                Usuario cliente = userDao.ConsultaUsuario(request.getParameter("email"), decryptedPassword, 1);

                if (cliente.getEmail() != null && cliente.getPassword() != null) {
                    if (cliente.getEmail().equals(request.getParameter("email")) && cliente.getPassword().equals(decryptedPassword)) {
                        // El usuario se encontró en la tabla de clientes
                        HttpSession session = request.getSession(true);
                        session.setAttribute("cliente", cliente);
                        session.setAttribute("userId", cliente.getUser_id());
                        session.setAttribute("userName", cliente.getNombre());
                        // Verificar si el usuario ya realizó una encuesta
                        encuestaDAO encuestaDao = new encuestaDAO();
                        boolean usuarioRealizoEncuesta = encuestaDao.usuarioRealizoEncuesta(cliente.getUser_id());

                        if (usuarioRealizoEncuesta) {
                            // El usuario ya realizó una encuesta, toma las acciones necesarias
                            session.setAttribute("encuestaRealizada", "1");
                        } else {
                            // El usuario aún no ha realizado una encuesta, toma las acciones necesarias
                            session.setAttribute("encuestaRealizada", "0");
                        }
                        String successMessage = "Inicio de sesión satisfactorio.";
                        request.setAttribute("successMessage", successMessage);
                        // Después de autenticar al usuario con éxito
                        session.setAttribute("inicioSesionDesdeLogin", true);

                        // Utilizamos RequestDispatcher para redirigir al cliente
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/index.jsp");
                        dispatcher.forward(request, response);
                        return; // Importante: detener la ejecución del código después de la redirección
                    } else {
                        String errorMessage = "Credenciales incorrectas.";
                        request.setAttribute("errorMessage", errorMessage);
                    }
                } else {
                    String errorMessage = "Credenciales incorrectas.";
                    request.setAttribute("errorMessage", errorMessage);
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
                        PasswordDecryptor decryptor = new PasswordDecryptor();
                        String decryptedPassword = decryptor.encrypt(request.getParameter("password"));
                         Usuario user = new Usuario(request.getParameter("nombre"),request.getParameter("email"),decryptedPassword,1);
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
