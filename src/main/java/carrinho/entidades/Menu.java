package carrinho.entidades;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void gerenciarEstoque(Scanner scanner, Estoque estoque) throws SQLException {
        while (true) {
            System.out.println("\n===== MENU ESTOQUE =====");
            System.out.println("1. Adicionar produto ao estoque");
            System.out.println("2. Remover produto do estoque");
            System.out.println("3. Consultar produto por ID");
            System.out.println("4. Visualizar estoque completo");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcaoEstoque = scanner.nextInt();

            switch (opcaoEstoque) {
                case 1 -> {
                    System.out.print("ID do Produto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Produto produtoExistente = estoque.buscarPorId(id);
                    if (produtoExistente != null) {
                        System.out.print("Produto já existente! Adicione a quantidade: ");
                        int quantidade = scanner.nextInt();
                        estoque.atualizarQuantidade(produtoExistente, quantidade);
                    } else {
                        System.out.print("Nome do Produto: ");
                        String nome = scanner.nextLine();
                        System.out.print("Categoria do Produto: ");
                        String categoria = scanner.nextLine();
                        System.out.print("Preço do Produto: ");
                        double preco = scanner.nextDouble();
                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();

                        Produto produto = new Produto(id, nome, categoria, preco, quantidade);
                        estoque.adicionarProduto(produto, quantidade);
                        System.out.println("Produto adicionado ao estoque.");
                    }
                }
                case 2 -> {
                    System.out.print("ID do Produto a remover: ");
                    long idRemover = scanner.nextLong();
                    System.out.print("Quantidade a remover: ");
                    int quantidadeRemover = scanner.nextInt();
                    estoque.removerProduto(idRemover, quantidadeRemover);
                }
                case 3 -> {
                    System.out.print("ID do Produto: ");
                    long idConsulta = scanner.nextLong();
                    Produto produto = estoque.buscarPorId(idConsulta);
                    if (produto != null) {
                        System.out.println("Produto: " + produto.getNomeProduto() + " | Quantidade: " + produto.getQuantidadeProduto());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }
                case 4 -> estoque.exibirEstoque();
                case 5 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void gerenciarCarrinho(Scanner scanner, Carrinho carrinho, Estoque estoque) throws SQLException {
        while (true) {
            System.out.println("\n===== MENU CARRINHO =====");
            System.out.println("1. Adicionar produto ao carrinho");
            System.out.println("2. Remover produto do carrinho");
            System.out.println("3. Exibir conteúdo do carrinho");
            System.out.println("4. Limpar Carrinho");
            System.out.println("5. Finalizar Compra");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcaoCarrinho = scanner.nextInt();

            switch (opcaoCarrinho) {
                case 1 -> {
                    System.out.print("ID do Produto a adicionar no carrinho: ");
                    long idCarrinho = scanner.nextLong();
                    System.out.print("Quantidade: ");
                    int quantidadeCarrinho = scanner.nextInt();

                    Produto produto = estoque.buscarPorId(idCarrinho);


                    if (produto != null) {
                        //funcionando, mas depois que o produto entra no carrnho a unica forma de adicionar mais e remover e depois adicionar de novo
                        //colocar mensagem 'nao tem nada no carrinho' se nao tiver nada no carrinho
                        //adicionar finalizar compra - feito
                        if (carrinho.buscarProdutoCarrinho(idCarrinho) == null) {
                            carrinho.adicionarProdutoCarrinho(idCarrinho, produto, quantidadeCarrinho);
                            System.out.println("Produto adicionado ao carrinho.");
                        }
                        else {
                            System.out.println("Produto encontrado no carrinho, certifique-se de remover antes de adicionar novamente!");
                        }
                    }
                }
                case 2 -> {
                    System.out.print("ID do Produto a remover do carrinho: ");
                    long idRemoverCarrinho = scanner.nextLong();
                    carrinho.removerProdutoCarrinho(idRemoverCarrinho);
                    System.out.println("Produto removido do carrinho.");
                }
                case 3 -> carrinho.exibirCarrinho();
                case 4 -> {
                    carrinho.limparCarrinho();
                    System.out.println("Carrinho limpo.");
                }
                case 5 -> {
                    try {
                        System.out.println("Compra Finalizada! ");
                        List<Produto> produtosTeste = carrinho.retornarProdutosCarrinho();
                        for (Produto p : produtosTeste) {
                            estoque.adicionarProduto(p, p.getQuantidadeProduto());
                        }
                        carrinho.limparCarrinho();
                        //remover do carrinho e adicionar no estoque
                        //limpar carrinho
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                case 6 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
