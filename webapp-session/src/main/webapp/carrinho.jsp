<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 18/04/2023
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<c:if test="${!sessionScope.pedido.itemPedidos.isEmpty()}">
    <table class="table table-hover table-striped">
        <tr>
            <th>Id</th>
            <th>Produto</th>
            <th>Quantidade</th>
            <th>Preço UN</th>
            <th>Total</th>
            <th>Remover Item</th>
        </tr>
        <c:forEach items="${sessionScope.pedido.itemPedidos}" var="item">
            <tr>
                <td>${item.produto.id}</td>
                <td>${item.produto.nome}</td>
                <td>${item.quantidade}</td>
                <td>${item.produto.preco}</td>
                <td>${item.valorTotal}</td>
                <td><a href="${pageContext.request.contextPath}/remover-item?id=${item.produto.id}">Excluir</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" style="text-align: right">Valor Total:</td>
            <td>${sessionScope.pedido.totalPedido}</td>
        </tr>
    </table>
</c:if>
<c:if test="${sessionScope.pedido.itemPedidos.isEmpty()}">
    <div class="alert alert-warning">Nenhum item adicionado ao carrinho de compras.</div>
</c:if>
<div class="my-2">
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/index.jsp">Voltar</a>
    <c:if test="${!sessionScope.pedido.itemPedidos.isEmpty()}">
        <a class="btn btn-success" href="${pageContext.request.contextPath}/produtos">Continuar comprando</a>
    </c:if>
</div>
<jsp:include page="layout/footer.jsp" />
