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

<head>
  <title>Centro de encuestas</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">

</head>

<body>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        .gradient-custom-2 {
        /* fallback for old browsers */
        background: #fccb90;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
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
        <section class="h-100 gradient-form">
            <div class="container py-5 h-100">
              <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-xl-10">
                  <div class="card rounded-3 text-black">
                    <div class="row g-0">
                      <div class="col-lg-6">
                        <div class="card-body p-md-5 mx-md-4">

                          <div class="text-center">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
                              style="width: 185px;" alt="logo">
                            <h4 class="mt-1 mb-5 pb-1">Bienvenido</h4>
                          </div>

                            <form method="post" action="/labEncuesta/LoginController?action=create">
                            <p>Por favor ingresa con tu cuenta.</p>
                            
                            <div class="row">
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="nombre">Nombre: </label>
                                    <input type="text" class="form-control" name="nombre" id="nombre" required>

                                  </div>
                                 <div class="form-outline mb-4">
                                    <label class="form-label" for="email">Correo: </label>
                                    <input type="email" class="form-control" name="email" id="email" placeholder="example@gmail.com" required>

                                  </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label" for="password">Contraseña: </label>
                                    <input type="password" id="password" name="password" class="form-control" required/>

                                </div>

                                <div class="mb-3 row mt-3">
                                    <div class="offset-sm-2 col-sm-8">
                                        <button type="submit" class="btn btn-primary gradient-custom-2 ">Registrarse</button>
                                        <a href="/labEncuesta/" class="btn btn-outline-danger"> Regresar</a>
                                    </div>
                                </div>
                                
                            </div>

                           

                          </form>

                        </div>
                      </div>
                      <div class="col-lg-6 d-flex align-items-center gradient-custom-2">
                        <div class="text-white px-3 py-4 p-md-5 mx-md-4">
                          <h4 class="mb-4">Disfruta tu estancia.</h4>
                          <p class="small mb-0">
                              Buscamos garantizar la mayor calidad y demanda de productos
                              ,para que estos esten a tu alcance de la forma mas facil posible,
                              adquiere nuestros productos y disfruta de la calidad y servicio.
                          </p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
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
</body>

</html>