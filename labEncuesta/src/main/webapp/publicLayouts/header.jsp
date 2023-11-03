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
 <!-- Jquery -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

    <!-- DataTable -->
    <script src="https://cdn.datatables.net/1.11.6/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/plug-ins/1.11.5/i18n/Spanish.json"></script>

    <!-- DATA-TABLES CDN -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.11.3/datatables.min.css" />
</head>

<body>

<!-- Agrega la librería Font Awesome para los íconos -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="">Encuestas</a>
                </li>
             
            </ul>
            <div class="d-flex me-5 pe-4">
                <c:if test="${not empty sessionScope.userName}">
                    <!-- Botón Dropdown para el perfil del usuario -->
                    <div class="dropdown">
                        <a class="nav-link dropdown-toggle text-white" href="#" id="profileDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user-circle"></i> ${sessionScope.userName}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="profileDropdown">
                            <li><a class="dropdown-item" href="#">Mi perfil</a></li>
                            <li><a class="dropdown-item" href="#">Configuración</a></li>
                            <li class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/labEncuesta/LogoutController">Cerrar Sesión</a></li>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</nav>
