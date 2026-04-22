package com.pt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pt.db.Conexao;
import com.pt.model.Produto;

public class ProdutoDAO {

    // ============================
    // LISTAR TODOS OS PRODUTOS
    // ============================
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try (Connection conn = Conexao.get();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));

                lista.add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage());
        }

        return lista;
    }

    // ============================
    // BUSCAR PRODUTO POR ID
    // ============================
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id=?";

        try (Connection conn = Conexao.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setCategoria(rs.getString("categoria"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    p.setPreco(rs.getDouble("preco"));
                    return p;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produto por id: " + e.getMessage());
        }

        return null;
    }

    // ============================
    // INSERIR PRODUTO
    // ============================
    public void inserir(Produto p) {
        String sql = "INSERT INTO produtos (nome, categoria, quantidade, preco) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCategoria());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage());
        }
    }

    // ============================
    // ATUALIZAR PRODUTO
    // ============================
    public void atualizar(Produto p) {
        String sql = "UPDATE produtos SET nome=?, categoria=?, quantidade=?, preco=? WHERE id=?";

        try (Connection conn = Conexao.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCategoria());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // ============================
    // APAGAR PRODUTO
    // ============================
    public void apagar(int id) {
        String sql = "DELETE FROM produtos WHERE id=?";

        try (Connection conn = Conexao.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao apagar produto: " + e.getMessage());
        }
    }
}
