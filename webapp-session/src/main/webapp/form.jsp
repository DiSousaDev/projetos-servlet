<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 01/05/2023
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, br.dev.diego.entities.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> erros = (Map<String, String>) request.getAttribute("erros");
    Produto produto = (Produto) request.getAttribute("produto");
    String data = produto.getDataRegistro() != null ? produto.getDataRegistro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): "";
%>
<html>
<head>
    <title>Formulario de Produtos</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/produtos/form" method="post">
    <div>
        <label for="nome">Nome</label>
        <div>
            <input type="text" name="nome" id="nome" value="<%=produto.getNome() != null ? produto.getNome() : "" %>">
        </div>
        <% if(erros != null && erros.containsKey("nome")) { %>
            <div style="color: red;"><%=erros.get("nome")%></div>
        <% } %>
    </div>
    <div>
        <label for="preco">Preço</label>
        <div>
            <input type="number" name="preco" id="preco" value="<%=produto.getPreco() > 0 ? produto.getPreco() : "" %>">
        </div>
        <% if(erros != null && erros.containsKey("preco")) { %>
            <div style="color: red;"><%=erros.get("preco")%></div>
        <% } %>
    </div>
    <div>
        <label for="sku">Sku</label>
        <div>
            <input type="text" name="sku" id="sku" value="<%=produto.getSku() != null ? produto.getSku() : ""%>">
        </div>
        <% if(erros != null && erros.containsKey("sku")) { %>
            <div style="color: red;"><%=erros.get("sku")%></div>
        <% } %>
    </div>
    <div>
        <label for="data_registro">Data Registro</label>
        <div>
            <input type="date" name="data_registro" id="data_registro" value="<%=data%>">
        </div>
        <% if(erros != null && erros.containsKey("data_registro")) { %>
            <div style="color: red;"><%=erros.get("data_registro")%></div>
        <% } %>
    </div>
    <div>
        <label for="categoria">Categoria</label>
        <div>
            <select name="categoria" id="categoria">
                <option value="">-- Selecione Categoria --</option>
                <% for(Categoria c : categorias) { %>
                <option value="<%=c.getId()%>" <%=c.getId().equals(produto.getCategoria().getId()) ? "selected" : ""%>><%=c.getNome()%></option>
                <% } %>
            </select>
        </div>
        <% if(erros != null && erros.containsKey("categoria")) { %>
            <div style="color: red;"><%=erros.get("categoria")%></div>
        <% } %>
    </div>
    <div>
        <input type="submit" value="<%=produto.getId() != null? "Editar" : "Cadastrar"%>">
        <input type="hidden" name="id" value="<%=produto.getId()%>">
    </div>
</form>
</body>
</html>
