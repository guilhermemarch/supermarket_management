package carrinho.entidades;

import java.util.List;

public class Carrinho {

    private List<Produto> produtos;
    double valorTotalCarrinho;

    public Carrinho(double valorTotalCarrinho, List<Produto> produtos) {
        this.valorTotalCarrinho = valorTotalCarrinho;
        this.produtos = produtos;
    }

    public Carrinho() {

    }

    public void adicionarProduto(Produto produto, int quantidade) {

        for (Produto p : produtos) {
            if (p.getId() == produto.getId()) {
                p.setQuantidadeProduto(quantidade + p.getQuantidadeProduto());
            }
            produtos.add(produto);
            calcularValorTotal();
        }
    }

    public void removerProduto(int id) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getId() == id) {
                produtos.remove(i);
                break;
            }
        }
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        for (Produto p : produtos) {
            valorTotalCarrinho += p.getPrecoProduto() * p.getQuantidadeProduto();
        }
    }

    public Produto buscarProduto(long id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto; // Retorna o produto encontrado
            }
        }
        return null; // Retorna null se o produto não for encontrado
    }



}
