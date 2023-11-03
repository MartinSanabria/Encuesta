<%@page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="../publicLayouts/header.jsp"/>
<div class="container mt-5">
    <c:choose>
        <c:when test="${not empty encuesta}">
            <h1>Encuesta del usuario</h1>
            <table class="table">
                <tr>
                    <th>Nombre de usuario</th>
                    <th>Sexo</th>
                    <th>Deporte favorito</th>
                    <th>Nivel de estudio</th>
                    <th>Temas favoritos</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                </tr>
                <tr>
                    <td>${encuesta.nombre}</td>
                    <td>${encuesta.sexo}</td>
                    <td>${encuesta.deporte_favorito}</td>
                    <td>${encuesta.nivel_estudio}</td>
                    <td>${encuesta.temas_favoritos}</td>
                    <td>${encuesta.fecha}</td>
                    <td>${encuesta.hora}</td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <p>No se encontró una encuesta para este usuario.</p>
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

<jsp:include page="../publicLayouts/footer.jsp"/>