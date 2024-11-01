package carrinho.entidades;

import db.EstoqueDB;

import java.sql.SQLException;
import java.util.List;

public class Estoque {
    private EstoqueDB estoqueDB;

    public Estoque() {
        estoqueDB = new EstoqueDB();
    }

    public void adicionarProduto(Produto produto, int quantidade) throws SQLException {
        Produto produtoExistente = estoqueDB.buscarPorId(produto.getId());
        if (produtoExistente != null) {
            int novaQuantidade = produtoExistente.getQuantidadeProduto() + quantidade;
            produtoExistente.setQuantidadeProduto(novaQuantidade);
            estoqueDB.atualizarQuantidade(produtoExistente);
        } else {
            produto.setQuantidadeProduto(quantidade);
            estoqueDB.inserirProduto(produto);
        }
    }

    public void removerProduto(long id, int quantidade) throws SQLException {
        Produto produto = estoqueDB.buscarPorId(id);
        if (produto != null) {
            if (produto.getQuantidadeProduto() < quantidade) {
                throw new IllegalArgumentException("Quantidade solicitada superior à disponível! Quantidade disponível: " + produto.getQuantidadeProduto());
            }
            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidade);
            estoqueDB.atualizarQuantidade(produto);

            if (produto.getQuantidadeProduto() == 0) {
                estoqueDB.excluirProduto(id);
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public  Produto buscarPorId(long id) throws SQLException {
        return estoqueDB.buscarPorId(id);
    }

    public List<Produto> consultarEstoque() throws SQLException {
        return estoqueDB.consultarEstoque();
    }

    public void exibirEstoque() throws SQLException {
        List<Produto> produtos = consultarEstoque();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado no estoque.");
            return;
        }
        for (Produto p : produtos) {
            System.out.println("==============================================================");
            System.out.printf("Produto: %-20s | Código: %-5s | Quantidade: %d\n",
                    p.getNomeProduto(), p.getId(), p.getQuantidadeProduto());
            System.out.println("==============================================================");
        }
    }


    public void atualizarQuantidade(Produto produto, int quantidadeAdicional) throws SQLException {
        int novaQuantidade = produto.getQuantidadeProduto() + quantidadeAdicional;
        produto.setQuantidadeProduto(novaQuantidade);
        estoqueDB.atualizarQuantidadeDB((int) produto.getId(), novaQuantidade);
    }

    public void limparEstoque() throws SQLException {
        estoqueDB.limparEstoque();
    }
    public void exibirInfoID(Produto produto) {
        if (produto != null) {
            System.out.println("=================================================================");
            System.out.printf(" Codigo: %d \n Produto:  %-20s\n Categoria: %-15s\n Quantidade: %d\n  Preço: %.2f",
                    produto.getId(),
                    produto.getNomeProduto(),
                    produto.getCategoriaProduto(),
                    produto.getQuantidadeProduto(),
                    produto.getPrecoProduto());
            System.out.println("\n=================================================================");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public Produto buscarProdutoEstoqueID(long produtoId) throws SQLException {
        return estoqueDB.buscarProdutoEstoqueID(produtoId);
    }



}
