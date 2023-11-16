<%@page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<jsp:include page="../AdminLayouts/header.jsp"/>

<div class="container mt-3">
    Bienvenido a la pagina del administrador
    <h1>Encuesta</h1>
    <div class="container text-center" style="justify-content: center; align-content: center; align-self: center; margin-left: 10%; margin-bottom: 50px;">
    <form action="/labEncuesta/UsuarioController?action=verEncuestasF" method="post" class="mt-2">
        <div class="row mt-3">
            <div class="col-md-4 mb-2">
                <div class="form-group">
                    <label for="fecha">Seleccionar fecha:</label>
                    <input type="date" class="form-control" id="fecha" name="fecha">
                    
                    <div class="d-grid gap-1 col-6 mx-auto mt-2">
                        <button type="submit" class="btn btn-primary">Buscar por fecha</button>
                    </div>
                </div>
                
            </div>
            
            <div class="col-md-4 mb-2">
                 <form action="/labEncuesta/UsuarioController?action=verEncuestasN" method="post" id="formNombre" class="mt-4 mb-2">
                    <div class="form-group">
                        <label for="nombre">Buscar por nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre">
                    </div>
                      <div class="d-grid gap-1 col-6 mx-auto mt-2">
                        <button type="submit" class="btn btn-primary mt-2">Buscar por nombre</button>
                     </div>
                </form>
            </div>
            
            <div class="col-md-4 mt-3">
                    <form action="/labEncuesta/UsuarioController?action=verEncuestas" method="post">
                        <button type="submit" class="btn btn-primary mt-2">Buscar todos</button>
                    </form>
            </div>
            
        </div>
       
    </form>
</div>


  

  
    <c:choose>
        <c:when test="${empty encuestas}">
            <p>No hay encuestas realizadas.</p>
        </c:when>
        <c:otherwise>
              <!-- Formulario con campo de fecha -->
               
            <table id="miTabla" class="table table-responsive table-stripped">
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