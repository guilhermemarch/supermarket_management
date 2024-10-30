package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import carrinho.entidades.Produto;
import carrinho.entidades.Estoque;
import carrinho.entidades.Carrinho;

public class estoqueBD {

    private Connection getConnection() throws SQLException {
        return DB.getConnection();
    }

        public void inserirProduto(Produto produto) throws SQLException {
            String sql = "INSERT INTO estoque (id, nome, categoria, valor, quantidade) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, produto.getId());
                stmt.setString(2, produto.getNomeProduto());
                stmt.setString(3, produto.getCategoriaProduto());
                stmt.setDouble(4, produto.getPrecoProduto());
                stmt.setInt(5, produto.getQuantidadeProduto());
                stmt.executeUpdate();
            }
        }

        public void atualizarEstoque(Produto produto) throws SQLException {
            String sql = "UPDATE estoque SET quantidade = ? WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, produto.getQuantidadeProduto());
                stmt.setLong(2, produto.getId());
                stmt.executeUpdate();
            }
        }

        public Produto buscarPorId(long id) throws SQLException {
            String sql = "SELECT * FROM estoque WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Produto(rs.getInt("id"), rs.getString("nome"),
                            rs.getString("categoria"), rs.getDouble("valor"),
                            rs.getInt("quantidade"));
                }
            }
            return null;
        }

        public List<Produto> consultarEstoque() throws SQLException {
            List<Produto> produtos = new ArrayList<>();
            String sql = "SELECT * FROM estoque";
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"),
                            rs.getString("categoria"), rs.getDouble("valor"),
                            rs.getInt("quantidade"));
                    produtos.add(produto);
                }
            }
            return produtos;
        }
    }


