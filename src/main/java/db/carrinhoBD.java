package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import carrinho.entidades.Produto;

public class carrinhoBD {

    // Método para obter a conexão com o banco de dados
    private Connection getConnection() throws SQLException {
        return DB.getConnection();
    }

    // Método para adicionar um produto ao carrinho
    public void adicionarProdutoCarrinho(Produto produto, int quantidade) throws SQLException {
        String sql = "INSERT INTO carrinho (produto_id, quantidade, valor_total) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, produto.getId());
            stmt.setInt(2, quantidade);
            stmt.setDouble(3, produto.calcularValorTotal(quantidade));
            stmt.executeUpdate();
        }
    }

    // Método para remover um produto do carrinho
    public void removerProdutoCarrinho(long produtoId) throws SQLException {
        String sql = "DELETE FROM carrinho WHERE produto_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um produto específico no carrinho
    public Produto buscarProdutoCarrinho(long produtoId) throws SQLException {
        String sql = "SELECT * FROM carrinho INNER JOIN estoque ON carrinho.produto_id = estoque.id WHERE produto_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("categoria"),
                        rs.getDouble("valor"),
                        rs.getInt("quantidade")
                );
            }
        }
        return null;
    }

    // Método para consultar todos os produtos do carrinho
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
}
