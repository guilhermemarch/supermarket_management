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
        String sql = "INSERT INTO carrinho (id, produto_id, quantidade, valor_total) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            stmt.setLong(2, produtoId);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, produto.calcularValorTotal(quantidade));
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
                        rs.getString("estoque.nome"),
                        rs.getString("estoque.categoria"),
                        rs.getDouble("estoque.valor"),
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
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("categoria"),
                        rs.getDouble("valor"),
                        rs.getInt("quantidade")
                );
                produtos.add(produto);
            }
        }
        return produtos;
    }


    //se o produto desejado for maior que o estoque deve acontecer alguma coisa

    public void limparCarrinho() throws SQLException {
        String sql = "DELETE FROM carrinho";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

}
