<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<ul class="list-group">
    <li class="list-group-item active">Menu de opçoes</li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/produtos">Lista de produtos</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/login.html">Login</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
    <li class="list-group-item"><a href="${pageContext.request.contextPath}/ver-carrinho">Ver Carrinho</a></li>
</ul>
<jsp:include page="layout/footer.jsp" />