package carrinho;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import carrinho.servico.servicoCarrinho;
import db.estoqueBD;
import db.carrinhoBD;

import carrinho.entidades.Produto;

public class Program {

    public static void main(String[] args) throws SQLException {
        estoqueBD estoqueBD = new estoqueBD();
        carrinhoBD carrinhoBD = new carrinhoBD();
        servicoCarrinho servicoCarrinho = new servicoCarrinho(estoqueBD, carrinhoBD);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gerenciar Estoque");
            System.out.println("2. Gerenciar Carrinho");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcaoPrincipal = scanner.nextInt();

            switch (opcaoPrincipal) {
                case 1 -> gerenciarEstoque(scanner, estoqueBD);
                case 2 -> gerenciarCarrinho(scanner, servicoCarrinho, carrinhoBD);
                case 3 -> {
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarEstoque(Scanner scanner, estoqueBD estoqueBD) throws SQLException {
        while (true) {
            System.out.println("\n===== MENU ESTOQUE =====");
            System.out.println("1. Adicionar produto ao estoque");
            System.out.println("2. Remover produto do estoque");
            System.out.println("3. Consultar produto por ID no estoque");
            System.out.println("4. Visualizar estoque completo");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcaoEstoque = scanner.nextInt();

            switch (opcaoEstoque) {
                case 1 -> {
                    System.out.print("ID do Produto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer
                    System.out.print("Nome do Produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Categoria do Produto: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Preço do Produto: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();

                    Produto produto = new Produto(id, nome, categoria, preco, quantidade);

                    //em vez de usar o estoqueBD, eu queria primeiro usar o estoque.java, e dentro do estoque.java jogar pro estoquebd.java

                    estoqueBD.inserirProduto(produto);

                    System.out.println("Produto adicionado ao estoque.");
                }
                case 2 -> {
                    System.out.print("ID do Produto a remover: ");
                    long idRemover = scanner.nextLong();
                    System.out.print("Quantidade a remover: ");
                    int quantidadeRemover = scanner.nextInt();
                    Produto produto = estoqueBD.buscarPorId(idRemover);
                    if (produto != null) {
                        produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidadeRemover);
                        estoqueBD.atualizarEstoque(produto);
                        System.out.println("Produto removido do estoque.");
                    } else {
                        System.out.println("Produto não encontrado no estoque.");
                    }
                }
                case 3 -> {
                    System.out.print("ID do Produto: ");
                    long idConsulta = scanner.nextLong();
                    Produto produto = estoqueBD.buscarPorId(idConsulta);
                    if (produto != null) {
                        System.out.println("Produto encontrado: " + produto.getNomeProduto() + " | Quantidade: " + produto.getQuantidadeProduto());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.println(estoqueBD.consultarEstoque());
                }
                case 5 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarCarrinho(Scanner scanner, servicoCarrinho servicoCarrinho, carrinhoBD carrinhoBD) throws SQLException {
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
                    servicoCarrinho.adicionarProdutoCarrinho(idCarrinho, quantidadeCarrinho);
                    System.out.println("Produto adicionado ao carrinho.");
                }
                case 2 -> {
                    System.out.print("ID do Produto a remover do carrinho: ");
                    int idRemoverCarrinho = scanner.nextInt();
                    servicoCarrinho.removerProdutoCarrinho(idRemoverCarrinho);
                    System.out.println("Produto removido do carrinho.");
                }
                case 3 -> {
                    List<Produto> produtosCarrinho = carrinhoBD.consultarCarrinho();
                    System.out.println("\n===== Produtos no Carrinho =====");
                    for (Produto produtoCarrinho : produtosCarrinho) {
                        System.out.println("Produto: " + produtoCarrinho.getNomeProduto() + ", Quantidade: " + produtoCarrinho.getQuantidadeProduto());
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
