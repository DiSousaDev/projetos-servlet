<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 01/05/2023
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Formulario de Produtos</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/produtos/form" method="post">
    <div>
        <label for="nome">Nome</label>
        <div>
            <input type="text" name="nome" id="nome" value="${produto.nome}">
        </div>
        <c:if test="${erros != null && erros.containsKey('nome')}">
            <div style="color: red;">${erros.nome}</div>
        </c:if>
    </div>
    <div>
        <label for="preco">Preço</label>
        <div>
            <input type="number" name="preco" id="preco" value="${produto.preco > 0 ? produto.preco : ""}">
        </div>
        <c:if test="${erros != null && not empty erros.preco}">
            <div style="color: red;">${erros.preco}</div>
        </c:if>
    </div>
    <div>
        <label for="sku">Sku</label>
        <div>
            <input type="text" name="sku" id="sku" value="${produto.sku}">
        </div>
        <c:if test="${erros != null && erros.containsKey('sku')}">
            <div style="color: red;">${erros.sku}</div>
        </c:if>
    </div>
    <div>
        <label for="data_registro">Data Registro</label>
        <div>
            <input type="date" name="data_registro" id="data_registro" value="${produto.dataRegistro != null ? produto.dataRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}">
        </div>
        <c:if test="${erros != null && not empty erros.data_registro}">
            <div style="color: red;">${erros.data_registro}</div>
        </c:if>
    </div>
    <div>
        <label for="categoria">Categoria</label>
        <div>
            <select name="categoria" id="categoria">
                <option value="">-- Selecione Categoria --</option>
                <c:forEach items="${categorias}" var="c">
                    <option value="${c.id}" ${c.id.equals(produto.categoria.id) ? "selected" : ""}>${c.nome}</option>
                </c:forEach>
            </select>
        </div>
        <c:if test="${erros != null && not empty erros.categoria}">
            <div style="color: red;">${erros.categoria}</div>
        </c:if>
    </div>
    <div>
        <input type="submit" value="${produto.id != null ? "Editar" : "Cadastrar"}">
        <input type="hidden" name="id" value="${produto.id}">
    </div>
</form>
</body>
</html>
