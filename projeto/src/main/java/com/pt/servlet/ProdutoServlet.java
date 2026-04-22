package com.pt.servlet;

import java.io.IOException;
import java.util.List;

import com.pt.dao.ProdutoDAO;
import com.pt.model.Produto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ProdutoDAO dao = new ProdutoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if (acao == null || acao.isEmpty() || "listar".equals(acao)) {
            listar(req, resp);
            return;
        }

        if ("novo".equals(acao)) {
            req.getRequestDispatcher("/form.jsp").forward(req, resp);
            return;
        }

        if ("editar".equals(acao)) {
            abrirEdicao(req, resp);
            return;
        }

        if ("apagar".equals(acao)) {
            apagar(req, resp);
            return;
        }

        listar(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if ("inserir".equals(acao)) {
            inserir(req, resp);
            return;
        }

        if ("atualizar".equals(acao)) {
            atualizar(req, resp);
            return;
        }

        listar(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Produto> produtos = dao.listar();
        req.setAttribute("produtos", produtos);
        req.getRequestDispatcher("/listar.jsp").forward(req, resp);
    }

    private void inserir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Produto p = lerProdutoSemId(req);
            dao.inserir(p);
            resp.sendRedirect(req.getContextPath() + "/produtos");
        } catch (RuntimeException e) {
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }

    private void atualizar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Produto p = lerProdutoSemId(req);
            p.setId(parseInt(req.getParameter("id")));
            dao.atualizar(p);
            resp.sendRedirect(req.getContextPath() + "/produtos");
        } catch (RuntimeException e) {
            int id = parseInt(req.getParameter("id"));
            Produto produto = dao.buscarPorId(id);
            req.setAttribute("produto", produto);
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("/editar.jsp").forward(req, resp);
        }
    }

    private void abrirEdicao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = parseInt(req.getParameter("id"));
        Produto produto = dao.buscarPorId(id);

        if (produto == null) {
            resp.sendRedirect(req.getContextPath() + "/produtos");
            return;
        }

        req.setAttribute("produto", produto);
        req.getRequestDispatcher("/editar.jsp").forward(req, resp);
    }

    private void apagar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = parseInt(req.getParameter("id"));
        dao.apagar(id);
        resp.sendRedirect(req.getContextPath() + "/produtos");
    }

    private Produto lerProdutoSemId(HttpServletRequest req) {
        Produto p = new Produto();
        p.setNome(req.getParameter("nome"));
        p.setCategoria(req.getParameter("categoria"));
        p.setQuantidade(parseInt(req.getParameter("quantidade")));
        p.setPreco(parseDouble(req.getParameter("preco")));
        return p;
    }

    private int parseInt(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double parseDouble(String valor) {
        try {
            return Double.parseDouble(valor == null ? "0" : valor.replace(",", "."));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
