package carrinho;

import java.sql.SQLException;
import java.util.Scanner;

import carrinho.entidades.Carrinho;
import carrinho.entidades.Menu;
import carrinho.entidades.Estoque;

public class Program {

    public static void main(String[] args) throws SQLException {
        Estoque estoque = new Estoque();
        Carrinho carrinho = new Carrinho();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gerenciar Estoque");
            System.out.println("2. Gerenciar Carrinho");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcaoPrincipal = scanner.nextInt();

            switch (opcaoPrincipal) {
                case 1 -> Menu.gerenciarEstoque(scanner, estoque);
                case 2 -> Menu.gerenciarCarrinho(scanner, carrinho, estoque);
                case 3 -> {
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

    }
}
