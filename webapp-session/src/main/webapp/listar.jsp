<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 19/04/2023
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, br.dev.diego.entities.*" %>
<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
    String atributoApp = (String) session.getServletContext().getAttribute("att1");
    String atributoRequest = (String) request.getAttribute("att2");
%>
<html>
<head>
    <title>Lista de produtos</title>
</head>
<body>
<h1>Listando produtos</h1>
<% if(username.isPresent()) { %>
<div>Olá <%=username.get()%>, seja bem vindo(a)!</div>
<p><a href="<%=request.getContextPath()%>/produtos/form">Novo produto [+]</a></p>
<% } %>
<table>
    <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Sku</th>
        <th>Data Cadastro</th>
        <% if(username.isPresent()) { %>
        <th>Preço</th>
        <th>Adicionar</th>
        <th>Editar</th>
        <th>Excluir</th>
        <% } %>
    </tr>
    <% for(Produto p : produtos) { %>
    <tr>
        <td><%=p.getId()%></td>
        <td><%=p.getNome()%></td>
        <td><%=p.getCategoria().getNome()%></td>
        <td><%=p.getSku()%></td>
        <td><%=p.getDataRegistro()%></td>
        <% if(username.isPresent()) { %>
        <td><%=p.getPreco()%></td>
        <td><a href="<%=request.getContextPath()%>/adicionar-item?id=<%=p.getId()%>">Adicionar</a></td>
        <td><a href="<%=request.getContextPath()%>/produtos/form?id=<%=p.getId()%>">Editar</a></td>
        <td><a onclick="return confirm('Tem certeza que deseja excluir?')"
                href="<%=request.getContextPath()%>/produtos/excluir?id=<%=p.getId()%>">Excluir</a>
        </td>
        <% } %>
    </tr>
    <% } %>
</table>
<p><%=atributoApp%></p>
<p><%=atributoRequest%></p>
</body>
</html>
