package db;

import carrinho.entidades.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDB {

    private Connection getConnection() throws SQLException {
        return DB.getConnection();
    }

    public void inserirProdutoCarrinho(long produtoId, Produto produto, int quantidade) throws SQLException {
        String sql = "INSERT INTO carrinho (id, produto_id, nome, categoria, valor, quantidade, valor_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            stmt.setLong(2, produto.getId());
            stmt.setString(3, produto.getNomeProduto());
            stmt.setString(4, produto.getCategoriaProduto());
            stmt.setDouble(5, produto.getPrecoProduto());
            stmt.setInt(6, quantidade);
            stmt.setDouble(7, produto.getPrecoProduto() * quantidade);
            stmt.executeUpdate();
        }
    }

    public void removerProdutoCarrinho(long produtoId) throws SQLException {
        String sql = "DELETE FROM carrinho WHERE produto_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            stmt.executeUpdate();
        }
    }

    public Produto buscarProdutoCarrinho(long produtoId) throws SQLException {
        String sql = "SELECT * FROM carrinho JOIN estoque ON carrinho.produto_id = estoque.id WHERE estoque.id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getInt("estoque.id"),
                        rs.getString("carrinho.nome"),
                        rs.getString("carrinho.categoria"),
                        rs.getDouble("carrinho.valor"),
                        rs.getInt("carrinho.quantidade")
                );
            }
        }
        return null;
    }

    public List<Produto> consultarCarrinho() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM carrinho INNER JOIN estoque ON carrinho.produto_id = estoque.id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("estoque.id"),
                        rs.getString("carrinho.nome"),
                        rs.getString("carrinho.categoria"),
                        rs.getDouble("carrinho.valor"),
                        rs.getInt("carrinho.quantidade")
                );
                produtos.add(produto);
            }
        }
        return produtos;
    }

    public int obterQuantidade(int produtoId) throws SQLException {
        int quantidade = 0;
        String sql = "SELECT quantidade FROM estoque WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produtoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    quantidade = rs.getInt("quantidade");
                }
            }
        }
        return quantidade;
    }

    public void limparCarrinho() throws SQLException {
        String sql = "DELETE FROM carrinho";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public double valorCarrinho() throws SQLException {
        String sql = "SELECT SUM(valor_total) AS total FROM carrinho";
        double total = 0.0;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        }
        return total;
    }

}
