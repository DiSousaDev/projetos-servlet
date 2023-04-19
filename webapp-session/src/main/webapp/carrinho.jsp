<%--
  Created by IntelliJ IDEA.
  User: ederc
  Date: 18/04/2023
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="br.dev.diego.entities.*" %>
<%
    Pedido pedido = (Pedido) session.getAttribute("pedido");
    boolean temPedido = !pedido.getItemPedidos().isEmpty();
%>
<html>
<head>
    <title>Carrinho de compras</title>
</head>
<body>
<h1>Carrinho de Compras</h1>
<% if (temPedido) { %>
    <table>
        <tr>
           <th>Id</th>
           <th>Produto</th>
           <th>Quantidade</th>
           <th>Preço UN</th>
           <th>Total</th>
           <th>Remover Item</th>
        </tr>
        <% for(ItemPedido item : pedido.getItemPedidos()) { %>
            <tr>
               <td><%=item.getProduto().getId()%></td>
               <td><%=item.getProduto().getNome()%></td>
               <td><%=item.getQuantidade()%></td>
               <td><%=item.getProduto().getPreco()%></td>
               <td><%=item.getValorTotal()%></td>
               <td><a href="<%=request.getContextPath()%>/remover-item?id=<%=item.getProduto().getId()%>">Excluir</a></td>
            </tr>
        <% } %>
        <tr>
            <td colspan="4" style="text-align: right">Valor Total:</td>
            <td><%=pedido.getTotalPedido()%></td>
        </tr>
    </table>
<% } else { %>
    <p>Nenhum item adicionado ao carrinho de compras.</p>
<% } %>
<% if (temPedido) { %>
<p><a href="<%=request.getContextPath()%>/produtos">Continuar comprando</a></p>
<% } %>
<p><a href="<%=request.getContextPath()%>/index.html">Voltar</a></p>
</body>
</html>
