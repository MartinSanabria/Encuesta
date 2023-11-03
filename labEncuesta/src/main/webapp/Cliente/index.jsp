<%@page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="../publicLayouts/header.jsp"/>

<c:if test="${empty cliente}">
    <%-- La sesión no está activa, redirige al inicio de sesión --%>
     <c:redirect url="index.jsp" />
</c:if>
<div class="container mt-5">
     <c:choose>
         <c:when test="${sessionScope.encuestaRealizada == '1'}">
            <!-- Mostrar el botón para ver la encuesta -->
            <h1>Encuesta realizada</h1>
            <form  action="/labEncuesta/UsuarioController?action=verform" method="post">
                <input type="hidden" id="userId" name="userId" class="form-control" required value="${sessionScope.userId}">
                <button type="submit" class="btn btn-primary mt-5">Ver encuesta</button>

            </form>
        </c:when>
        <c:otherwise>
            <form action="/labEncuesta/UsuarioController?action=enviarform" method="post">
                <div class="form-group">
                    <label for="userName">Nombre de usuario:</label>
                    <input type="text" id="userName" name="userName" class="form-control" required value="${sessionScope.userName}">
                </div>


                <div class="form-group">
                    <label>Genero</label>
                    <div class="form-check">
                        <input type="radio" id="male" name="gender" value="Male" class="form-check-input">
                        <label for="male" class="form-check-label">Masculino</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" id="female" name="gender" value="Female" class="form-check-input">
                        <label for="female" class="form-check-label">Femenino</label>
                    </div>
                </div>

                <label>Deporte favorito:</label>

                <div class="form-group">
                    <div class="form-check form-check-inline">
                        <input type="radio" id="football" name="sport" value="Football" class="form-check-input">
                        <label for="football" class="form-check-label">Football</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" id="basketball" name="sport" value="Basketball" class="form-check-input">
                        <label for="basketball" class="form-check-label">Basketball</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" id="jockey" name="sport" value="Jockey" class="form-check-input">
                        <label for="jockey" class="form-check-label">Jockey</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" id="baseball" name="sport" value="Baseball" class="form-check-input">
                        <label for "baseball" class="form-check-label">Baseball</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" id="golf" name="sport" value="Golf" class="form-check-input">
                        <label for="golf" class="form-check-label">Golf</label>
                    </div>
                    <!-- Add more sports here -->
                </div>



                <div class="form-group">
                    <label for="educationLevel">Nivel de estudio:</label>
                    <select id="educationLevel" name="educationLevel" class="form-control">
                        <option value="-seleccione un nivel de estudio-">-seleccione un nivel de estudio-</option>
                        <option value="Basico">Básico</option>
                        <option value="Intermedio">Intermedio</option>
                        <option value="Superior">Superior</option>
                    </select>
                </div>

                <label>Temas favoritos:</label>

                 <div class="form-group">
                    <div class="form-check form-check-inline">
                        <input type="radio" id="television" name="favoriteTopic" value="Television" class="form-check-input">
                        <label for="television" class="form-check-label">Televisión</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" id="cooking" name="favoriteTopic" value="Cooking" class="form-check-input">
                        <label for="cooking" class="form-check-label">Cocina</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" id="technology" name="favoriteTopic" value="Technology" class="form-check-input">
                        <label for="technology" class="form-check-label">Tecnología</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" id="music" name="favoriteTopic" value="Music" class="form-check-input">
                        <label for="music" class="form-check-label">Música</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input type="radio" id="sports" name="favoriteTopic" value="Sports" class="form-check-input">
                        <label for="sports" class="form-check-label">Deportes</label>
                    </div>
                    <!-- Agrega más temas aquí -->
                </div>

                <button type="submit" class="btn btn-primary mt-5">Enviar encuesta</button>
            </form>
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