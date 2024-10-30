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
                    try {
                        int id = scanner.nextInt();

                        scanner.nextLine();

                        Produto testarExistencia = estoque.buscarPorId(id);
                        if (testarExistencia != null) {
                            System.out.print("Produto já existente no estoque!\n Digite a quantidade que deseje adicionar: ");
                            int quantidade = scanner.nextInt();
                            estoque.atualizarQuantidade(testarExistencia, quantidade);
                        }
                        else {
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
                    } catch (SQLException e) {
                        System.out.println("Erro ao adicionar produto ao estoque " + e.getMessage());
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
                        System.out.println("Produto encontrado: " + produto.getNomeProduto() + " | Quantidade: " + produto.getQuantidadeProduto());
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

    public static void gerenciarCarrinho(Scanner scanner, Carrinho carrinho) throws SQLException {
        while (true) {
            System.out.println("\n===== MENU CARRINHO =====");
            System.out.println("1. Adicionar produto ao carrinho");
            System.out.println("2. Remover produto do carrinho");
            System.out.println("3. Exibir conteúdo do carrinho");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcaoCarrinho = scanner.nextInt();

            switch (opcaoCarrinho) {
                case 1 -> {
                    System.out.print("ID do Produto a adicionar no carrinho: ");
                    long idCarrinho = scanner.nextLong();
                    System.out.print("Quantidade: ");
                    int quantidadeCarrinho = scanner.nextInt();
                    //        carrinhoBD.adicionarProdutoCarrinho(/* aqui eu tenho o id do carrinho e eu preciso retorar um Produto*/, quantidadeCarrinho);
                    System.out.println("Produto adicionado ao carrinho.");
                }
                case 2 -> {
                    System.out.print("ID do Produto a remover do carrinho: ");
                    int idRemoverCarrinho = scanner.nextInt();
                    carrinho.removerProdutoCarrinho(idRemoverCarrinho);
                    System.out.println("Produto removido do carrinho.");
                }
                case 3 -> {
                    List<Produto> produtosCarrinho = carrinho.consultarCarrinho();
                    System.out.println("\n===== Produtos no Carrinho =====");
                    for (Produto produtoCarrinho : produtosCarrinho) {
                        System.out.printf("Produto: %-20s | Quantidade: %d\n", produtoCarrinho.getNomeProduto(), produtoCarrinho.getQuantidadeProduto());
                    }
                    System.out.println("===============================");
                }
                case 4 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
