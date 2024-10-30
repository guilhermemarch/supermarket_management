package carrinho.entidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import db.DB;

public class Estoque {

    private Connection getConnection() throws SQLException {
        return DB.getConnection();
    }

    public void adicionarProduto(Produto produto, int quantidade) throws SQLException {
        Produto produtoExistente = buscarPorId(produto.getId());
        if (produtoExistente != null) {
            produtoExistente.setQuantidadeProduto(produtoExistente.getQuantidadeProduto() + quantidade);
            atualizarEstoque(produtoExistente);
        } else {
            produto.setQuantidadeProduto(quantidade);
            inserirProduto(produto);
        }
    }

    private void inserirProduto(Produto produto) throws SQLException {
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

    private void atualizarEstoque(Produto produto) throws SQLException {
        String sql = "UPDATE estoque SET quantidade = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produto.getQuantidadeProduto());
            stmt.setLong(2, produto.getId());
            stmt.executeUpdate();
        }
    }

    public void removerProduto(long id, int quantidade) throws SQLException {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            if (produto.getQuantidadeProduto() < quantidade) {
                throw new IllegalArgumentException("Quantidade solicitada superior à disponível! Quantidade disponível: " + produto.getQuantidadeProduto());
            }
            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidade);
            atualizarEstoque(produto);

            if (produto.getQuantidadeProduto() == 0) {
                excluirProduto(id);
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void excluirProduto(long id) throws SQLException {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
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

    public void exibirEstoque() throws SQLException {
        System.out.println("\n===== Estoque de Produtos =====");
        List<Produto> produtos = consultarEstoque();
        for (Produto p : produtos) {
            System.out.printf("Produto: %-20s | Quantidade: %d\n", p.getNomeProduto(), p.getQuantidadeProduto());
        }
        System.out.println("===============================");
    }
}
