<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 18/04/2023
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Carrinho de compras</title>
</head>
<body>
<h1>Carrinho de Compras</h1>
<c:if test="${!sessionScope.pedido.itemPedidos.isEmpty()}">
    <table>
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
            <td colspan="4" style="text-align: right">Valor Total:</td>
            <td>${sessionScope.pedido.totalPedido}</td>
        </tr>
    </table>
</c:if>
<c:if test="${sessionScope.pedido.itemPedidos.isEmpty()}">
    <p>Nenhum item adicionado ao carrinho de compras.</p>
</c:if>
<c:if test="${!sessionScope.pedido.itemPedidos.isEmpty()}">
<p><a href="${pageContext.request.contextPath}/produtos">Continuar comprando</a></p>
</c:if>
<p><a href="${pageContext.request.contextPath}/index.html">Voltar</a></p>
</body>
</html>
