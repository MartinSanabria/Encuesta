<%@page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="../AdminLayouts/header.jsp"/>
<div class="container mt-5">
    <h1 class="mb-4">Perfil del Usuario</h1>

    <c:if test="${not empty perfil}">
            <form action="/labEncuesta/UsuarioController?action=actualizarPerfil" method="post">
             <div class="form-group d-none">
        <label for="userId">ID de Usuario</label>
        <p>${perfil.user_id}</p>
        <input type="hidden" name="userId" value="${perfil.user_id}">
    </div>

            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" class="form-control" name="nombre" value="${perfil.nombre}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" name="email" value="${perfil.email}">
            </div>
            
             <div class="form-group position-relative">
                <label for="password" class="col-3 col-form-label">Contraseña</label>
                <div class="col-12">
                    <input type="password" class="form-control" name="password" id="password" value="${perfil.getPassword()}" required>
                    <span class="fa fa-fw fa-eye field-icon toggle-password position-absolute" style="top: 75%; transform: translateY(-50%); right: 1rem;" toggle="#password"></span>
                </div>
            </div>
            <!-- Otros campos de entrada según sea necesario -->

            <button type="submit" class="btn btn-primary mt-3">Editar</button>
        </form>
    </c:if>
</div>


<script>
// JavaScript para alternar la visibilidad de la contraseña
document.querySelectorAll(".toggle-password").forEach(function (icon) {
    icon.addEventListener("click", function () {
        const passwordInput = document.querySelector(this.getAttribute("toggle"));
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
        } else {
            passwordInput.type = "password";
        }
    });
});

</script>


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
<script>
    document.getElementById("printButton").addEventListener("click", function() {
        window.print();
    });
</script>

<jsp:include page="../AdminLayouts/footer.jsp"/>