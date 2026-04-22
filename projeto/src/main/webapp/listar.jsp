<%@ page import="java.util.List" %>
<%@ page import="com.pt.model.Produto" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/style.css">
    <title>Listar Produtos</title>
</head>
<body>
    <header>
        <h1>Gestão de Inventário</h1>
        <nav>
            <a href="<%=request.getContextPath()%>/index.html">Início</a> |
            <a href="<%=request.getContextPath()%>/produtos?acao=novo">Novo Produto</a>
        </nav>
        <hr>
    </header>

    <main>
        <h2>Lista de Produtos</h2>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Categoria</th>
                    <th>Quantidade</th>
                    <th>Preco</th>
                    <th>Acoes</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
                if (produtos != null && !produtos.isEmpty()) {
                    for (Produto p : produtos) {
            %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getNome() %></td>
                    <td><%= p.getCategoria() %></td>
                    <td><%= p.getQuantidade() %></td>
                    <td><%= String.format("%.2f", p.getPreco()) %></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/produtos?acao=editar&id=<%=p.getId()%>">Editar</a>
                        |
                        <a href="<%=request.getContextPath()%>/produtos?acao=apagar&id=<%=p.getId()%>" onclick="return confirm('Apagar este produto?');">Apagar</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="6">Sem produtos cadastrados.</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </main>

    <footer>
        <hr>
        <br>
        <p>Projeto Java Web • Maven • MySQL</p>
    </footer>
</body>
</html>
