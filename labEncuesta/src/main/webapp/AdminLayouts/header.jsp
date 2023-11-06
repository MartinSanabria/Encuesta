<!doctype html>
<html lang="en">

<head>
  <title>Centro de encuestas</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
       <!-- Agrega las bibliotecas de DataTables -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.11.3/datatables.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.11.3/datatables.min.js"></script>
 
    

    <style>
        /* Estilo para la tabla de pedidos */
        .table {
            width: 100%;
            border-collapse: collapse;
        }

        .table th, .table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }

        .table th {
            background-color: #f2f2f2;
        }

        .btn {
            padding: 5px 10px;
            text-decoration: none;
            border: none;
            cursor: pointer;
        }

        .btn-primary {
            background-color: #007bff;
            color: #fff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-danger {
            background-color: #dc3545;
            color: #fff;
        }

        .btn-danger:hover {
            background-color: #c82333;
        }
          .indice-oculto {
        display: none; /* Oculta la celda */
    }
    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/labEncuesta/index.jsp">Encuestas</a>
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
                            <li><a class="dropdown-item" href="/labEncuesta/LoginController?action=login">Ver encuestas</a></li>
                            <li><a class="dropdown-item" href="/labEncuesta/index.jsp">Graficos</a></li>

                            <li class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/labEncuesta/LogoutController">Cerrar Sesión</a></li>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</nav>
