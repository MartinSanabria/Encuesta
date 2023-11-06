<%@page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<jsp:include page="../AdminLayouts/header.jsp"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.4/html2pdf.bundle.min.js"></script>

<c:if test="${empty admin}">
    <%-- La sesión no está activa, redirige al inicio de sesión --%>
     <c:redirect url="index.jsp" />
</c:if>
<div class="container mt-3">
    Bienvenido a la pagina del administrador
    <h1>Encuesta</h1>
    <c:choose>
        <c:when test="${empty encuestas}">
            <p>No hay encuestas realizadas.</p>
        </c:when>
        <c:otherwise>
            <table id="miTabla" class="table">
                <thead>
                    <tr>
                        <th>Nombre de usuario</th>
                        <th>Sexo</th>
                        <th>Deporte favorito</th>
                        <th>Nivel de estudio</th>
                        <th>Temas favoritos</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <!-- Agrega más columnas según los atributos de la encuesta -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="encuesta" items="${encuestas}"  varStatus="status">
                        <tr class="encuesta-row">
                            <td>${encuesta.nombre}</td>
                            <td>${encuesta.sexo}</td>
                            <td>${encuesta.deporte_favorito}</td>
                            <td>${encuesta.nivel_estudio}</td>
                            <td>${encuesta.temas_favoritos}</td>
                            <td>${encuesta.fecha}</td>
                            <td>${encuesta.hora}</td>
                            <td class="indice-oculto">${status.index}</td> <!-- Aplica la clase CSS para ocultar la celda -->

                            
                            <!-- Agrega más columnas según los atributos de la encuesta -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>



<c:if test="${not empty successMessage}">
       <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
            const Toast = Swal.mixin({
              toast: true,
              position: 'top-end',
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
              }
            })

            Toast.fire({
              icon: 'success',
              title: '${successMessage}'
            })
    </script>
</c:if>
<c:if test="${not empty errorMessage}">
       <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>

    <script>
            const Toast = Swal.mixin({
              toast: true,
              position: 'top-end',
              showConfirmButton: false,
              timer: 2000,
              timerProgressBar: true,
              didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
              }
            })

            Toast.fire({
              icon: 'error',
              title: '${errorMessage}'
            })
    </script>
</c:if>
      <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>






<jsp:include page="../AdminLayouts/footer.jsp"/>