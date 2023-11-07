import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter("/Admin/*")
public class AdminSessionFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // Initialization code, if needed.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false); // false significa que no se creará una sesión si no existe

        if (session == null || session.getAttribute("admin") == null) {
            // No hay sesión activa de administrador, redirige al inicio de sesión de administrador.
            System.out.println("No hay sesión activa de administrador. Redirigiendo al inicio de sesión de administrador.");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp"); // Cambia esto según la ruta correcta
            
        } else if (session != null && session.getAttribute("admin") != null) {
            Boolean inicioSesionDesdeLogin = (Boolean) session.getAttribute("inicioSesionDesdeLogin");

            if (inicioSesionDesdeLogin != null && inicioSesionDesdeLogin) {
                // El administrador inició sesión desde el formulario de inicio de sesión, redirigir a la página de administrador
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/Admin/index.jsp"); // Cambia esto según la ruta correcta
                return;
            }
        } else {
            // Hay una sesión activa de administrador, permite que la solicitud continúe.
            System.out.println("Sesión activa de administrador. Continuando con la solicitud.");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed.
    }
}
