<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>

<%
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuários</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h3>Cadastro de Usuários</h3>
    <%if (errors != null && errors.size() > 0) {%>
    <ul class="alert alert-danger">
        <%for (String erro : errors.values()) {%>
        <li class="mx-2"><%=erro%></li>
        <%}%>
    </ul>
    <%}%>
    <form action="/webapp-form/cadastrar" method="POST">
        <div class="row mb-3">
            <label for="username" class="col-form-label col-sm-2">Usuário</label>
            <div class="col-sm-4">
                <input type="text" name="username" id="username" class="form-control" value="${param.username}">
            </div>
            <% if (errors != null && errors.containsKey("username")) {
                out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("username") + "</small>");
            }%>
        </div>
        <div class="row mb-3">
            <label for="password" class="col-form-label col-sm-2">Password</label>
            <div class="col-sm-4">
                <input type="password" name="password" id="password" class="form-control">
            </div>
            <% if (errors != null && errors.containsKey("password")) {
                out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("password") + "</small>");
            }%>
        </div>
        <div class="row mb-3">
            <label for="email" class="col-form-label col-sm-2">E-mail</label>
            <div class="col-sm-4">
                <input type="email" name="email" id="email" class="form-control" value="${param.email}">
            </div>
            <% if (errors != null && errors.containsKey("email")) {
                out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("email") + "</small>");
            }%>
        </div>
        <div class="row mb-3">
            <label for="pais" class="col-form-label col-sm-2">País</label>
            <div class="col-sm-4">
                <select name="pais" id="pais" class="form-select">
                    <option value="">-- Selecione --</option>
                    <option value="BR" ${param.pais.equals("BR") ? "selected" : ""}>Brasil</option>
                    <option value="ES" ${param.pais.equals("ES") ? "selected" : ""}>Espanha</option>
                    <option value="ME" ${param.pais.equals("ME") ? "selected" : ""}>México</option>
                    <option value="CO" ${param.pais.equals("CO") ? "selected" : ""}>Colômbia</option>
                    <option value="AR" ${param.pais.equals("AR") ? "selected" : ""}>Argentina</option>
                    <option value="CH" ${param.pais.equals("CH") ? "selected" : ""}>Chile</option>
                    <option value="PY" ${param.pais.equals("PY") ? "selected" : ""}>Paraguay</option>
                </select>
            </div>
            <% if (errors != null && errors.containsKey("pais")) {
                out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("pais") + "</small>");
            }%>
        </div>
        <div class="row mb-3">
            <label for="linguagens" class="col-form-label col-sm-2">Linguagens de Programação</label>
            <div class="col-sm-4">
                <select name="linguagens" id="linguagens" multiple class="form-select">
                    <option value="java" ${paramValues.linguagens.stream().anyMatch(v->v.equals("java")).get() ? "selected":""}>Java SE</option>
                    <option value="jakartaee" ${paramValues.linguagens.stream().anyMatch(v->v.equals("jakartaee")).get() ? "selected":""}>Jakarta EE9</option>
                    <option value="spring" ${paramValues.linguagens.stream().anyMatch(v->v.equals("spring")).get() ? "selected":""}>Spring Boot</option>
                    <option value="js" ${paramValues.linguagens.stream().anyMatch(v->v.equals("js")).get() ? "selected":""}>Java Script</option>
                    <option value="an" ${paramValues.linguagens.stream().anyMatch(v->v.equals("an")).get() ? "selected":""}>Angular</option>
                    <option value="re" ${paramValues.linguagens.stream().anyMatch(v->v.equals("re")).get() ? "selected":""}>React</option>
                </select>
            </div>
            <% if (errors != null && errors.containsKey("linguagens")) {
                out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("linguagens") + "</small>");
            }%>
        </div>
        <div class="row mb-3">
            <label class="col-form-label col-sm-2">Roles</label>
            <div class="form-check col-sm-2">
                <input type="checkbox" name="roles" value="ROLE_ADMIN" class="form-check-input"
                ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_ADMIN")).get() ? "checked":""}>
                <label class="form-check-label">Administrador</label>
            </div>
            <div class="form-check col-sm-2">
                <input type="checkbox" name="roles" value="ROLE_USER" class="form-check-input"
                ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_USER")).get() ? "checked":""}>
                <label class="form-check-label">Usuário</label>
            </div>
            <div class="form-check col-sm-2">
                <input type="checkbox" name="roles" value="ROLE_MODERATOR" class="form-check-input"
                ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_MODERATOR")).get() ? "checked":""}>
                <label class="form-check-label">Moderador</label>
            </div>
            <% if (errors != null && errors.containsKey("roles")) {
                out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("roles") + "</small>");
            }%>
        </div>
        <div class="row mb-3">
            <label class="col-form-label col-sm-2">Idiomas</label>
            <div class="form-check col-sm-2">
                <input type="radio" name="idioma" value="pt-br" class="form-check-input" ${param.idioma.equals("pt-br") ? "checked" : ""}>
                <label class="form-check-label">Português</label>
            </div>
            <div class="form-check col-sm-2">
                <input type="radio" name="idioma" value="en" class="form-check-input" ${param.idioma.equals("en") ? "checked" : ""}>
                <label class="form-check-label">Inglês</label>
            </div>
            <div class="form-check col-sm-2">
                <input type="radio" name="idioma" value="es" class="form-check-input" ${param.idioma.equals("es") ? "checked" : ""}>
                <label class="form-check-label">Espanhol</label>
            </div>
            <% if (errors != null && errors.containsKey("idioma")) {
                out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("idioma") + "</small>");
            }%>
        </div>
        <div class="row mb-3">
            <label class="col-form-label col-sm-2">Habilitar</label>
            <div class="form-check col-sm-2">
                <input type="checkbox" name="habilitar" id="habilitar" checked class="form-check-input">
            </div>
        </div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Cadastrar" class="btn btn-primary"/>
            </div>
        </div>
        <input type="hidden" name="secret" value="123456"/>
    </form>
</div>
</body>
</html>