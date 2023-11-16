package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
 * @author X1
 */
@WebServlet(urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

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
            out.println("<title>Servlet UsuarioController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioController at " + request.getContextPath() + "</h1>");
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
            HttpSession session = request.getSession();  // Obtiene la sesión, o crea una si no existe
            // Después de autenticar al usuario con éxito
            session.setAttribute("inicioSesionDesdeLogin", false);
                

            String action = request.getParameter("action");

           if ("enviarform".equals(action)) {
                // Procesa los datos del formulario y realiza las operaciones necesarias
                String userName = request.getParameter("userName");
                String gender = request.getParameter("gender");
                String sport = request.getParameter("sport");
                String educationLevel = request.getParameter("educationLevel");
                String favoriteTopic = request.getParameter("favoriteTopic");

                // Obtiene la fecha y hora actuales
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String currentDateTimeString = currentDateTime.format(formatter);

                // Realiza las operaciones necesarias con los datos del formulario

                // A continuación, crea una instancia de encuestaDAO y agrega la encuesta a la base de datos
                encuestaDAO encuestaDao = new encuestaDAO();
                    Encuesta encuesta = new Encuesta();
                    encuesta.setUser_id((int) session.getAttribute("userId"));  // Obtén el ID del usuario de la sesión
                    encuesta.setNombre(userName);
                    encuesta.setSexo(gender);
                    encuesta.setDeporte_favorito(sport);
                    encuesta.setNivel_estudio(educationLevel);
                    encuesta.setTemas_favoritos(favoriteTopic);
                    encuesta.setFecha(currentDateTimeString); // Establece la fecha actual
                    encuesta.setHora(currentDateTimeString);  // Establece la hora actual

                boolean encuestaAgregada = encuestaDao.agregar(encuesta);

                if (encuestaAgregada) {
                    // Redirige a una página de confirmación o muestra un mensaje de éxito
                    String successMessage = "Formulario enviado con éxito.";
                    request.setAttribute("successMessage", successMessage);
                    session.setAttribute("encuestaRealizada", "1");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // Maneja el caso en que la encuesta no se agregó correctamente
                    String errorMessage = "Error al enviar el formulario.";
                    request.setAttribute("errorMessage", errorMessage);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/index.jsp");
                    dispatcher.forward(request, response);
                }
            } else if ("verform".equals(action))  {
                // Manejar la acción "verform" aquí
                int userId = Integer.parseInt(request.getParameter("userId"));
                encuestaDAO encuestaDao = new encuestaDAO();
                Encuesta encuesta = encuestaDao.buscarEncuestaPorUsuario(userId);

                // Envía los datos de la encuesta a la vista "encuesta.jsp"
                request.setAttribute("encuesta", encuesta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/encuesta.jsp");
                dispatcher.forward(request, response);
            }else if("verPerfil".equals(action)){
               int userId = Integer.parseInt(request.getParameter("userId"));
                usuarioDAO userDao = new usuarioDAO();
                Usuario perfil = userDao.buscarPorID(userId);
                
                PasswordDecryptor decryptor = new PasswordDecryptor();
                String decryptedPassword = decryptor.decrypt(perfil.getPassword());
                perfil.setPassword(decryptedPassword);
                System.out.println("Contra: " + decryptedPassword);
                request.setAttribute("perfil", perfil);
                session = request.getSession();
                Usuario loggedInUser = (Usuario) session.getAttribute("admin");
                  if (loggedInUser != null) {
                            // Si el usuario logueado es un administrador (tipo 0)
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/perfil.jsp");
                            dispatcher.forward(request, response);
                  } else {
                            // Si el usuario logueado es un cliente (tipo 1)
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/perfil.jsp");
                            dispatcher.forward(request, response);
                   }
            
            }else if("actualizarPerfil".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                String nombre = request.getParameter("nombre");
                String email = request.getParameter("email");
                PasswordDecryptor decryptor = new PasswordDecryptor();
                String decryptedPassword = decryptor.encrypt(request.getParameter("password"));
                usuarioDAO userDao = new usuarioDAO();
                Usuario perfil = userDao.actualizarUser(userId,email,nombre,decryptedPassword);
                request.setAttribute("perfil", perfil);
                session.setAttribute("userName", nombre);
                Usuario loggedInUser = (Usuario) session.getAttribute("admin");
                  if (loggedInUser != null) {
                            // Si el usuario logueado es un administrador (tipo 0)
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/perfil.jsp");
                            dispatcher.forward(request, response);
                  } else {
                            // Si el usuario logueado es un cliente (tipo 1)
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/perfil.jsp");
                            dispatcher.forward(request, response);
                   }


            }else if("verGrafico".equals(action)){
                         Encuesta encuesta = new Encuesta();
                            encuestaDAO encuestaDao = new encuestaDAO();
                            List<Encuesta> encuestas = encuestaDao.buscarTodasLasEncuestas();

                        request.setAttribute("encuestas", encuestas);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/grafico.jsp");
                        dispatcher.forward(request, response);
                       
            }else if("verEncuestas".equals(action)){
                         Encuesta encuesta = new Encuesta();
                            encuestaDAO encuestaDao = new encuestaDAO();
                            List<Encuesta> encuestas = encuestaDao.buscarTodasLasEncuestas();

                        request.setAttribute("encuestas", encuestas);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/index.jsp");
                        dispatcher.forward(request, response);
                       
            }else if("verEncuestasF".equals(action)){
                String fechaParam = request.getParameter("fecha"); // Obtener el parámetro de fecha desde la solicitud

                if (fechaParam != null && !fechaParam.isEmpty()) {
                    Encuesta encuesta = new Encuesta();
                    encuestaDAO encuestaDao = new encuestaDAO();
                    List<Encuesta> encuestas = encuestaDao.buscarEncuestasPorFecha(fechaParam);

                    request.setAttribute("encuestas", encuestas);
                } else {
                    // Manejar el caso en el que no se proporciona la fecha
                    // Puedes mostrar un mensaje de error o realizar alguna acción predeterminada
                    // También puedes redirigir a una página de error si es necesario
                    request.setAttribute("errorMessage", "Debe proporcionar una fecha para realizar la búsqueda.");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/index.jsp");
                dispatcher.forward(request, response);
            }else if("verEncuestasN".equals(action)){
               String nombreParam = request.getParameter("nombre");

                if (nombreParam != null && !nombreParam.isEmpty()) {
                    Encuesta encuesta = new Encuesta();
                    encuestaDAO encuestaDao = new encuestaDAO();
                    List<Encuesta> encuestas = encuestaDao.buscarEncuestasPorNombre(nombreParam);

                    request.setAttribute("encuestas", encuestas);
                } else {
                    request.setAttribute("errorMessage", "Debe proporcionar un nombre para realizar la búsqueda.");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/index.jsp");
                dispatcher.forward(request, response);
            }else if("Borrar".equals(action)){
                String idEncuestaStr = request.getParameter("id_encuesta");

                // Obtener el id_encuesta de alguna manera, puede ser a través de parámetros de solicitud, sesiones, etc.
                int idEncuesta = Integer.parseInt(idEncuestaStr);

                // Crear una instancia de EncuestaDAO
                encuestaDAO encuestaDAO = new encuestaDAO();

                // Intentar borrar la encuesta
                boolean encuestaBorrada = encuestaDAO.borrarEncuestaPorId(idEncuesta);

                if (encuestaBorrada) {
                    // La encuesta fue borrada con éxito
                    encuestaDAO encuestaDao = new encuestaDAO();
                    List<Encuesta> encuestas = encuestaDao.buscarTodasLasEncuestas();

                    request.setAttribute("encuestas", encuestas);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/index.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // Ocurrió un problema al borrar la encuesta
                    // Puedes manejar esto de acuerdo a tus necesidades, por ejemplo, redirigiendo a una página de error
                    response.sendRedirect("/ruta/a/tu/pagina/de/error.jsp");
                }
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
