<%-- Verifica si se encontr� una encuesta para mostrar --%>
<c:if test="${not empty encuesta}">
    <h1>Encuesta del usuario</h1>
    <p>Nombre de usuario: ${encuesta.nombre}</p>
    <p>Sexo: ${encuesta.sexo}</p>
    <%-- Agrega aqu� otros atributos de la encuesta --%>
</c:if>
<%-- Si no se encuentra una encuesta para mostrar, puedes mostrar un mensaje --%>
<c:if test="${empty encuesta}">
    <p>No se encontr� una encuesta para este usuario.</p>
</c:if>
