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

@WebFilter("/Cliente/*")
public class SessionFilter implements Filter {

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

        if (session == null || session.getAttribute("cliente") == null) {
            // No hay sesión activa, redirige al inicio de sesión.
            System.out.println("No hay sesión activa. Redirigiendo al inicio de sesión.");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
            
        } else if (session != null && session.getAttribute("cliente") != null) {
            Boolean inicioSesionDesdeLogin = (Boolean) session.getAttribute("inicioSesionDesdeLogin");

            if (inicioSesionDesdeLogin != null && inicioSesionDesdeLogin) {
                // El usuario inició sesión desde el formulario de inicio de sesión, redirigir a la página de clientes
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/Cliente/index.jsp");
                return;
            } 
        } 
        else {
            // Hay una sesión activa, permite que la solicitud continúe.
            System.out.println("Sesión activa. Continuando con la solicitud.");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed.
    }
}
