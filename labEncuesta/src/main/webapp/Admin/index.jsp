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
    Bienvenido a la pagina del administrador
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
<jsp:include page="../AdminLayouts/footer.jsp"/>