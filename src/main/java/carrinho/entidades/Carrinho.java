package carrinho.entidades;

import db.CarrinhoDB;

import java.sql.SQLException;
import java.util.List;

public class Carrinho {
    private CarrinhoDB carrinhoDB;

    public Carrinho() {
        carrinhoDB = new CarrinhoDB();
    }

    public void adicionarProdutoCarrinho(long id, Produto produto, int quantidade) throws SQLException {
        carrinhoDB.inserirProdutoCarrinho(id, produto, quantidade);
    }
    public void adicionarProdutoRemovendo(long id, Produto produto, int quantidade, long produtoId) throws SQLException {
        carrinhoDB.inserirProdutoCarrinho(id, produto, quantidade);
        carrinhoDB.removerProdutoCarrinho(produtoId);
    }

    public void removerProdutoCarrinho(long produtoId) throws SQLException {
        carrinhoDB.removerProdutoCarrinho(produtoId);
    }

    public Produto buscarProdutoCarrinho(long produtoId) throws SQLException {
        return carrinhoDB.buscarProdutoCarrinho(produtoId);
    }

    public List<Produto> consultarCarrinho() throws SQLException {
        return carrinhoDB.consultarCarrinho();
    }

    public void exibirCarrinho() throws SQLException {
        List<Produto> produtos = consultarCarrinho();
        System.out.println("=================================================================");
        for (Produto p : produtos) {
            System.out.printf("Produto: %-20s | Codigo: %-5s | Quantidade: %d\n", p.getNomeProduto(), p.getId(), p.getQuantidadeProduto());
        }
        System.out.println("=================================================================");
    }

    public List<Produto> retornarProdutosCarrinho() throws SQLException {
        List<Produto> produtos = consultarCarrinho();
        return produtos;
    }


    public void limparCarrinho() throws SQLException {
        carrinhoDB.limparCarrinho();
    }


}
