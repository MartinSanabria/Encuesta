<%-- 
    Document   : index
    Created on : 21 sep. 2023, 17:22:13
    Author     : martinsanabria
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    
<c:choose>
    <c:when test="${not empty cliente}">
        <%-- Redirige al cliente a su página correspondiente si ya ha iniciado sesión --%>
        <c:redirect url="/Cliente/index.jsp" />
    </c:when>
    <c:when test="${not empty admin}">
        <%-- Redirige al administrador a su página correspondiente si ya ha iniciado sesión --%>
        <c:redirect url="/Admin/index.jsp" />
    </c:when>
</c:choose>

<head>
  <title>Centro de encuestas</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="https://i.ibb.co/wRy137Z/encuesta.png" type="image/x-icon">

     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
        .gradient-custom-2 {
            /* fallback for old browsers */
            background: #a0d8d5;

            /* Chrome 10-25, Safari 5.1-6 */
            background: -webkit-linear-gradient(to right, #36d1dc, #5d9cec, #7e5be8, #a72693);

            /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
            background: linear-gradient(to right, #36d1dc, #5d9cec, #7e5be8, #a72693);
        }

        @media (min-width: 768px) {
            .gradient-form {
                height: 100vh !important;
            }
        }

        @media (min-width: 769px) {
            .gradient-custom-2 {
                border-top-right-radius: .3rem;
                border-bottom-right-radius: .3rem;
            }
        }
    </style>
    <div class="container">
       <c:choose>
        <c:when test="${empty cliente && empty admin}">

        <section class="h-100 gradient-form">
            <div class="container py-5 h-100 ">
              <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-xl-10">
                  <div class="card rounded-3 text-black border shadow">
                    <div class="row g-0">
                      <div class="col-lg-6">
                        <div class="card-body p-md-5 mx-md-4">

                          <div class="text-center">
                        <img src="https://i.ibb.co/wRy137Z/encuesta.png" alt="encuesta"  style="width: 125px;" alt="logo" border="0">                             
                            <h4 class="mt-1 mb-5 pb-1">Bienvenido</h4>
                          </div>

                            <form method="post" action="/labEncuesta/LoginController?action=login">
                            <p>Por favor ingresa con tu cuenta.</p>

                            <div class="form-outline mb-4">
                              <label class="form-label" for="email">Correo: </label>
                              <input type="email" class="form-control" name="email" id="email" placeholder="example@gmail.com" required>
                             
                            </div>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="password">Contraseña: </label>
                                <input type="password" id="password" name="password" class="form-control" required/>
                             
                            </div>

                            <div class="text-center d-grid gap-2 col-6 mx-auto pt-1 mb-5 pb-1">
                              <button class="btn btn-primary gradient-custom-2 mb-3" type="submit">
                                  Iniciar sesion
                              </button>
               
                            </div>

                            <div class="d-flex align-items-center justify-content-center pb-4">
                              <p class="mb-0 me-2">No tienes una cuenta?</p>
                              <a href="register.jsp" class="btn btn-outline-primary"> Registrarse</a>
                            </div>

                          </form>

                        </div>
                      </div>
                      <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                        <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                                  <h4 class="mb-4">Disfruta tu estancia EncuestanOS.</h4>
                          <p class="small mb-0">
                             Somos una empresa comprometida con tus necesdidades apoyanos dandonos ideas
                             que se adapten a tus preferencias EncuestanOS.</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
          </c:when>
            <c:when test="${not empty cliente}">
                <h4 class="mb-4">Bienvenido Cliente</h4>
                <p class="small mb-0">Contenido específico para clientes.</p>
            </c:when>
            <c:when test="${not empty admin}">
                <h4 class="mb-4">Bienvenido Administrador</h4>
                <p class="small mb-0">Contenido específico para administradores.</p>
            </c:when>
            <c:otherwise>
                <h4 class="mb-4">Disfruta tu estancia.</h4>
                <p class="small mb-0">Contenido general para usuarios no autenticados.</p>
            </c:otherwise>

        </c:choose>

    </div>
<c:if test="${not empty successMessage}">
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
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
  </script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
    integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
  </script>
  
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <script>
 

        // Función para recargar la página cuando se utiliza el botón "Atrás" del navegador
        window.onbeforeunload = function() {
          window.location.reload();
        };
  </script>

</body>

</html>