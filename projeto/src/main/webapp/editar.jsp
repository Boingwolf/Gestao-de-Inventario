<%@ page import="com.pt.model.Produto" %>
<%
    Produto produto = (Produto) request.getAttribute("produto");
%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/style.css">
    <title>Editar Produto</title>
</head>
<body>
    <header>
        <h1>Gestão de Inventário</h1>
        <nav>
            <a href="<%=request.getContextPath()%>/index.html">Início</a> |
            <a href="<%=request.getContextPath()%>/produtos">Listar Produtos</a>
        </nav>
        <hr>
    </header>

    <main>
        <h2>Editar Produto</h2>

        <% String erro = (String) request.getAttribute("erro"); %>
        <% if (erro != null && !erro.trim().isEmpty()) { %>
            <p style="color: #ffb4b4; margin-bottom: 12px;">
                <strong>Erro:</strong> <%= erro %>
            </p>
        <% } %>

        <form action="<%=request.getContextPath()%>/produtos" method="post">
            <input type="hidden" name="acao" value="atualizar">
            <input type="hidden" name="id" value="<%= produto != null ? produto.getId() : 0 %>">

            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" value="<%= produto != null ? produto.getNome() : "" %>" required>

            <label for="categoria">Categoria</label>
            <input type="text" id="categoria" name="categoria" value="<%= produto != null ? produto.getCategoria() : "" %>" required>

            <label for="quantidade">Quantidade</label>
            <input type="number" id="quantidade" name="quantidade" min="0" value="<%= produto != null ? produto.getQuantidade() : 0 %>" required>

            <label for="preco">Preço</label>
            <input type="number" id="preco" name="preco" min="0" step="0.01" value="<%= produto != null ? produto.getPreco() : 0 %>" required>

            <button type="submit">Atualizar</button>
        </form>
    </main>

    <footer>
        <hr>
        <br>
        <p>Projeto Java Web • Maven • MySQL</p>
    </footer>
</body>
</html>
