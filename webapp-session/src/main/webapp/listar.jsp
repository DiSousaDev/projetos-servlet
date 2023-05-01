<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 19/04/2023
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, br.dev.diego.entities.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Lista de produtos</title>
</head>
<body>
<h1>Listando produtos</h1>
<c:if test="${username.present}">
    <div>Olá ${username.get()}, seja bem vindo(a)!</div>
    <p><a href="${pageContext.request.contextPath}/produtos/form">Novo produto [+]</a></p>
</c:if>
<table>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Sku</th>
        <th>Data Cadastro</th>
        <c:if test="${username.present}">
            <th>Preço</th>
            <th>Adicionar</th>
            <th>Editar</th>
            <th>Excluir</th>
        </c:if>
    </tr>
    <c:forEach items="${produtos}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.nome}</td>
            <td>${p.categoria.nome}</td>
            <td>${p.sku}</td>
            <td>${p.dataRegistro}</td>
            <c:if test="${username.present}">
                <td>${p.preco}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/adicionar-item?id=${p.id}">Adicionar</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/produtos/form?id=${p.id}">Editar</a>
                </td>
                <td>
                    <a
                            onclick="return confirm('Tem certeza que deseja excluir?')"
                            href="${pageContext.request.contextPath}/produtos/excluir?id=${p.id}">Excluir
                    </a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<p>${applicationScope.atributoApp}</p>
<p>${requestScope.atributoRequest}</p>
</body>
</html>