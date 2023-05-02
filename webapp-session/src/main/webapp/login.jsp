<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="row my-2">
        <label for="username" class="form-label" >Usuário</label>
        <div>
            <input type="text" id="username" name="username" class="form-control">
        </div>
    </div>
    <div class="row my-2">
        <label for="password" class="form-label" >Senha</label>
        <div>
            <input type="text" id="password" name="password" class="form-control">
        </div>
    </div>
    <div class="row my-2">
        <input type="submit" value="Login" class="btn btn-primary">
    </div>
</form>
<jsp:include page="layout/footer.jsp" />