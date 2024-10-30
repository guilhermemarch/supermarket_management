package carrinho.entidades;

import java.util.List;

public class Estoque {

    private List<Produto> produtos;

    public Estoque(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

   public void adicionarProdutoEstoque(Produto produto, int quantidade) {
            for (Produto p : produtos) {
                if (p.getId() == produto.getId()) {
                    p.setQuantidadeProduto(p.getQuantidadeProduto() + quantidade);
                }
            }
            produto.setQuantidadeProduto(quantidade);
            produtos.add(produto);

   }
    public void removerProdutoEstoque(long id, int quantidade) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                try {
                    if (p.getQuantidadeProduto() < quantidade) {
                        throw new IllegalArgumentException("quantidade solicitada superior a quantidade disponível! quantidade disponível:" + p.getQuantidadeProduto());
                    }
                    p.setQuantidadeProduto(p.getQuantidadeProduto() - quantidade);
                    if (p.getQuantidadeProduto() == 0) {
                        produtos.remove(p);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                return;
            }
        }
        System.out.println("Erro! Produto nao encontrado");
    }

   public Produto consultarProduto(long id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
       return null;
   }
    public void consultarEstoque() {
        System.out.println("\n===== Estoque de Produtos =====");
        for (Produto p : produtos) {
            if (p.getQuantidadeProduto() > 0) {
                System.out.printf("Produto: %-20s | Quantidade: %d\n", p.getNomeProduto(), p.getQuantidadeProduto());

            } else {
                System.out.println("Nenhum produto disponível no estoque.");
            }
        }
        System.out.println("===============================");
    }
}
