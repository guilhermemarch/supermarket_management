package carrinho.modelo;

import java.util.List;

public class Carrinho {

    private List<Produto> produtos;
    double valorTotalCarrinho;

    public Carrinho(double valorTotalCarrinho, List<Produto> produtos) {
        this.valorTotalCarrinho = valorTotalCarrinho;
        this.produtos = produtos;
    }

    public void adicionarProduto(Produto produto, int quantidade) {

        //verificar se o produto ja existe
        //chamar metodo pra calcular o valor total do carrinho

    }

    public void removerProduto(int id) {
        //percorrer a lista verificando o ID, caso seja igual remover usando metodo
        //depois de remover chamar o metodo pra atualizar o valor total do carrinho

    }

    public void calcularValorTotal() {
        //valor unitario * quantidade
        //nao precisa retornar nada, apenas atualizar
    }

}
