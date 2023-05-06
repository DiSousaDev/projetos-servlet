<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 01/05/2023
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/produtos/form" method="post">
    <div class="row mb-2">
        <label for="nome" class="col-form-label col-sm-2">Nome</label>
        <div class="col-sm-4">
            <input type="text" name="nome" id="nome" value="${produto.nome}" class="form-control">
        </div>
        <c:if test="${erros != null && erros.containsKey('nome')}">
            <div style="color: red;">${erros.nome}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="preco" class="col-form-label col-sm-2">Preço</label>
        <div class="col-sm-4">
            <input type="number" name="preco" id="preco" value="${produto.preco > 0 ? produto.preco : ""}"
                   class="form-control">
        </div>
        <c:if test="${erros != null && not empty erros.preco}">
            <div style="color: red;">${erros.preco}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="sku" class="col-form-label col-sm-2">Sku</label>
        <div class="col-sm-4">
            <input type="text" name="sku" id="sku" value="${produto.sku}" class="form-control">
        </div>
        <c:if test="${erros != null && erros.containsKey('sku')}">
            <div style="color: red;">${erros.sku}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="data_registro" class="col-form-label col-sm-2">Data Registro</label>
        <div class="col-sm-4">
            <input type="date" name="data_registro" id="data_registro" class="form-control"
                   value="${produto.dataRegistro != null ? produto.dataRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}">
        </div>
        <c:if test="${erros != null && not empty erros.data_registro}">
            <div style="color: red;">${erros.data_registro}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label for="categoria" class="col-form-label col-sm-2">Categoria</label>
        <div class="col-sm-4">
            <select name="categoria" id="categoria" class="form-select">
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
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary" type="submit" value="${produto.id != null ? "Editar" : "Cadastrar"}">
        </div>
        <input type="hidden" name="id" value="${produto.id}">
    </div>
</form>
<jsp:include page="layout/footer.jsp" />