package carrinho;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import carrinho.modelo.Estoque;
import carrinho.modelo.Produto;


import db.DB;

public class Program {

    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        DB.closeConnection();

        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto(1, "Calculadora", "Eletrônicos", 500.0, 1));
        produtos.add(new Produto(2, "relogio", "Eletrônicos", 600.0, 1));
        produtos.add(new Produto(3, "Calculadora", "Eletrônicos", 500.0,2));
        produtos.add(new Produto(4, "relogio", "Eletrônicos", 600.0,2));

        Estoque estoque = new Estoque(produtos);

        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("Menu do Carrinho de Compras:");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Atualizar quantidade do produto");
            System.out.println("3. Remover produto");
            System.out.println("4. Gerenciar estoque");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int escolhaUsuario = sc.nextInt();
            sc.nextLine();

            switch (escolhaUsuario) {
                case 1:
                    System.out.println("Você escolheu adicionar um produto.");

                    break;
                case 2:
                    System.out.println("Você escolheu atualizar a quantidade do produto.");

                    break;
                case 3:
                    System.out.println("Você escolheu remover um produto.");

                    break;
                case 4:
                    while (true) {
                        System.out.println();
                        System.out.println("Menu de Gerenciamento de Estoque:");
                        System.out.println("1. Ver estoque");
                        System.out.println("2. Adicionar novo produto ao estoque");
                        System.out.println("3. Atualizar quantidade de produto no estoque");
                        System.out.println("4. Remover produto do estoque");
                        System.out.println("5. Voltar ao menu principal");
                        System.out.print("Escolha uma opção: ");
                        int escolhaEstoque = sc.nextInt();
                        sc.nextLine();
                        switch (escolhaEstoque) {
                            case 1:
                                System.out.println();
                                System.out.println("Você escolheu ver o estoque.");
                                    estoque.consultarEstoque();
                                break;
                            case 2:
                                System.out.println("Adicionar um novo produto ao estoque.");
                                break;
                            case 3:
                                System.out.println("Atualizar a quantidade de um produto no estoque.");
                                break;
                            case 4:
                                System.out.println("Remover remover um produto do estoque.");

                                break;
                            case 5:
                                System.out.println("Voltando ao menu principal.");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }

                        if (escolhaEstoque == 5) {
                            break;
                        }
                        System.out.println();
                    }
                    break;


                case 5:
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }


    }
}