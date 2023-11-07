<%@page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<jsp:include page="../AdminLayouts/header.jsp"/>

<c:if test="${empty admin}">
    <%-- La sesión no está activa, redirige al inicio de sesión --%>
     <c:redirect url="index.jsp" />
</c:if>
<div class="container mt-3">
    <h1>Graficos</h1>

    <select id="chartTypeSelect">
        <option value="bar">Gráfico de Barras</option>
        <option value="pie">Gráfico de Tarta</option>
        <!-- Agrega la opción de gráfico de líneas -->
        <option value="line">Gráfico de Líneas</option>
    </select>

    <select id="dataSelect">
        <option value="sexo">Sexo</option>
        <option value="deportes">Deportes</option>
        <option value="favoritos">Temas favoritos</option>
        <option value="nivel">Nivel de estudio</option>>
    </select>

    <canvas id="myChart"></canvas>

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

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3"></script> <!-- Incluye la biblioteca Chart.js -->

<script>
let maleCount = 0;
let femaleCount = 0;

let footballCount = 0;
let basketballCount = 0;
let jockeyCount = 0;
let baseballCount = 0;
let golfCount = 0;

let televisionCount = 0;
let cookingCount = 0;
let technologyCount = 0;
let musicCount = 0;
let sportsCount = 0;

let basicocount = 0;
let intermediocount = 0;
let superiocount = 0; 

<c:forEach var="encuesta" items="${encuestas}">
    // Contar la cantidad de géneros
    <c:choose>
        <c:when test="${encuesta.sexo == 'Masculino'}">
            maleCount++;
        </c:when>
        <c:when test="${encuesta.sexo == 'Femenino'}">
            femaleCount++;
        </c:when>
    </c:choose>

    // Contar la cantidad de deportes
    <c:choose>
        <c:when test="${encuesta.deporte_favorito == 'Football'}">
            footballCount++;
        </c:when>
        <c:when test="${encuesta.deporte_favorito == 'Basketball'}">
            basketballCount++;
        </c:when>
        <c:when test="${encuesta.deporte_favorito == 'Jockey'}">
            jockeyCount++;
        </c:when>
        <c:when test="${encuesta.deporte_favorito == 'Baseball'}">
            baseballCount++;
        </c:when>
        <c:when test="${encuesta.deporte_favorito == 'Golf'}">
            golfCount++;
        </c:when>
    </c:choose>
</c:forEach>

<c:forEach var="encuesta" items="${encuestas}">
    <c:choose>
        <c:when test="${encuesta.temas_favoritos == 'Television'}">
            televisionCount++;
        </c:when>
        <c:when test="${encuesta.temas_favoritos == 'Cocina'}">
            cookingCount++;
        </c:when>
        <c:when test="${encuesta.temas_favoritos == 'Tecnología'}">
            technologyCount++;
        </c:when>
        <c:when test="${encuesta.temas_favoritos == 'Musica'}">
            musicCount++;
        </c:when>
        <c:when test="${encuesta.temas_favoritos == 'Deportes'}">
            sportsCount++;
        </c:when>
        
            
    </c:choose>
</c:forEach>

<c:forEach var="encuesta" items="${encuestas}">
    <c:choose>
        <c:when test="${encuesta.nivel_estudio == 'Basico'}">
            basicocount++;
        </c:when>
            
        <c:when test="${encuesta.nivel_estudio == 'Intermedio'}">
            intermediocount++;
        </c:when>
            
        <c:when test="${encuesta.nivel_estudio == 'Superior'}">
            superiocount++;
        </c:when>
    </c:choose>

</c:forEach>


    let dataMap = {
        'sexo': {
            labels: ['Male', 'Female'],
            data: [maleCount, femaleCount]
        },
        'deportes': {
            labels: ['Football', 'Basketball', 'Jockey', 'Baseball', 'Golf'],
            data: [footballCount, basketballCount, jockeyCount, baseballCount, golfCount]
        },
        'favoritos':{
            labels: ['Television', 'Cocina', 'Tecnología', 'Musica', 'Deportes'],
            data: [televisionCount, cookingCount, technologyCount, musicCount, sportsCount]
        },
        'nivel':{
            labels: ['Basico', 'Intermedio','Superior'],
            data: [basicocount,intermediocount,superiocount]
        }
    };

    let chartType = 'bar';
    let dataType = 'sexo';

    const ctx = document.getElementById('myChart').getContext('2d');
    let myChart = new Chart(ctx, {
        type: chartType,
        data: {
            labels: dataMap[dataType].labels,
            datasets: [{
                label: 'Cantidad',
                data: dataMap[dataType].data,
                backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)', 'rgba(255, 159, 64, 0.2)', 'rgba(255, 205, 86, 0.2)', 'rgba(54, 162, 235, 0.2)'],
                borderColor: ['rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)', 'rgba(255, 159, 64, 1)', 'rgba(255, 205, 86, 1)', 'rgba(54, 162, 235, 1)'],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });

    const chartTypeSelect = document.getElementById('chartTypeSelect');
    chartTypeSelect.addEventListener('change', function () {
        chartType = chartTypeSelect.value;
        updateChart();
    });

    const dataSelect = document.getElementById('dataSelect');
    dataSelect.addEventListener('change', function () {
        dataType = dataSelect.value;
        updateChart();
    });

    function updateChart() {
        myChart.destroy();
        myChart = new Chart(ctx, {
            type: chartType,
            data: {
                labels: dataMap[dataType].labels,
                datasets: [{
                    label: 'Cantidad',
                    data: dataMap[dataType].data,
                    backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)', 'rgba(255, 159, 64, 0.2)', 'rgba(255, 205, 86, 0.2)', 'rgba(54, 162, 235, 0.2)'],
                    borderColor: ['rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)', 'rgba(255, 159, 64, 1)', 'rgba(255, 205, 86, 1)', 'rgba(54, 162, 235, 1)'],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    }
</script>

<jsp:include page="../AdminLayouts/footer.jsp"/>
