package carrinho.entidades;

import db.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    // Método para obter conexão com o banco de dados
    private Connection getConnection() throws SQLException {
        return DB.getConnection();
    }

    // Adiciona um produto ao carrinho
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

    // Remove um produto do carrinho
    public void removerProdutoCarrinho(long produtoId) throws SQLException {
        String sql = "DELETE FROM carrinho WHERE produto_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, produtoId);
            stmt.executeUpdate();
        }
    }

    // Busca um produto no carrinho
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
        return null; // Retorna null se não encontrar o produto
    }

    // Consulta todos os produtos do carrinho
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
        return produtos; // Retorna a lista de produtos
    }

    // Exibe os produtos no carrinho
    public void exibirCarrinho() throws SQLException {
        System.out.println("\n===== Carrinho =====");
        List<Produto> produtos = consultarCarrinho();
        for (Produto p : produtos) {
            System.out.printf("Produto: %-20s | Quantidade: %d\n", p.getNomeProduto(), p.getQuantidadeProduto());
        }
        System.out.println("===============================");
    }
}
