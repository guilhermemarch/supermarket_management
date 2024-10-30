package carrinho.servico;

import carrinho.entidades.Carrinho;
import carrinho.entidades.Produto;
import db.carrinhoBD;
import db.estoqueBD;

import java.sql.SQLException;


public class servicoCarrinho {

    // Atributos
    private Carrinho carrinho;
    private estoqueBD estoqueBD;
    private carrinhoBD carrinhoBD;

    public servicoCarrinho(estoqueBD estoqueBD, carrinhoBD carrinhoBD) {
        this.carrinho = new Carrinho();
        this.estoqueBD = estoqueBD;
        this.carrinhoBD = carrinhoBD;
    }

    // Método para adicionar um produto ao carrinho
    public void adicionarProdutoCarrinho(long id, int quantidade) throws SQLException {
        // Verificar se o produto está disponível no estoque
        Produto produto = estoqueBD.buscarPorId(id);

        if (produto != null && produto.getQuantidadeProduto() >= quantidade) {
            // Adiciona o produto ao carrinho
            carrinho.adicionarProduto(produto, quantidade);
            // Atualiza o estoque
            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidade);
            estoqueBD.atualizarEstoque(produto);
        } else {
            System.out.println("Produto não disponível ou quantidade insuficiente.");
        }
    }

    // Método para remover um produto do carrinho
    public void removerProdutoCarrinho(int id) throws SQLException {
        // Primeiro, obter o produto do carrinho
        Produto produto = carrinho.buscarProduto(id);

        if (produto != null) {
            // Remove o produto do carrinho
            carrinho.removerProduto(id);
            // Atualiza o estoque
            produto.setQuantidadeProduto(produto.getQuantidadeProduto() + produto.getQuantidadeProduto());
            estoqueBD.atualizarEstoque(produto);
        } else {
            System.out.println("Produto não encontrado no carrinho.");
        }
    }



//    public void visualizarCarrinho() {
//        System.out.println("Produtos no carrinho:");
//        for (Produto produto : produto.getProduto()) {
//            System.out.println("Produto: " + produto.getNomeProduto() + ", Quantidade: " + produto.getQuantidadeProduto());
//        }
//        System.out.println("Valor total do carrinho: " + carrinho.calcularValorTotal());
//    }


}
