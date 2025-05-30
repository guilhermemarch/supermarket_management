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
            System.out.println("5. Limpar estoque");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcaoEstoque = scanner.nextInt();

            switch (opcaoEstoque) {
                case 1 -> {
                    System.out.print("ID do Produto: ");
                    Long id = scanner.nextLong();
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
                    Long idRemover = scanner.nextLong();
                    System.out.print("Quantidade a remover: ");
                    int quantidadeRemover = scanner.nextInt();
                    estoque.removerProduto(idRemover, quantidadeRemover);
                }
                case 3 -> {
                    System.out.print("ID do Produto: ");
                    Long idConsulta = scanner.nextLong();
                    Produto produto = estoque.buscarPorId(idConsulta);
                    estoque.exibirInfoID(produto);
                }
                case 4 -> estoque.exibirEstoque();
                case 5 -> {
                    estoque.limparEstoque();
                    System.out.println("Estoque limpo.");
                }
                case 6 -> {
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
                    Long idCarrinho = scanner.nextLong();
                    System.out.print("Quantidade: ");
                    int quantidadeCarrinho = scanner.nextInt();

                    Produto produto = estoque.buscarProdutoEstoqueID(idCarrinho);

                    if (produto.getQuantidadeProduto() >= quantidadeCarrinho) {
                        if (carrinho.buscarProdutoCarrinho(idCarrinho) == null) {
                            carrinho.adicionarProdutoCarrinho(idCarrinho, produto, quantidadeCarrinho);
                            System.out.println("Produto adicionado ao carrinho.");
                        } else {
                            System.out.println("Produto encontrado no carrinho, certifique-se de remover antes de adicionar novamente!");
                        }
                    } else {
                        System.out.println("Quantidade superior a encontrada no estoque!!!");
                    }
                }
                case 2 -> {
                    System.out.print("ID do Produto a remover do carrinho: ");
                    Long idRemoverCarrinho = scanner.nextLong();
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
                        List<Produto> produtos = carrinho.retornarProdutosCarrinho();

                        if (!produtos.isEmpty()) {
                            System.out.println("Compra Finalizada!");
                            for (Produto p : produtos) {
                                estoque.diminuirQuantidadeNoEstoque(p, p.getQuantidadeProduto());
                            }
                            carrinho.limparCarrinho();
                        } else {
                            System.out.println("O carrinho está vazio!");
                        }
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
