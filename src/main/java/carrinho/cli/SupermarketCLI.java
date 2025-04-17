package carrinho.cli;

import carrinho.entidades.Estoque;
import carrinho.entidades.Carrinho;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "supermarket", mixinStandardHelpOptions = true, version = "1.0",
        description = "Supermarket Management System CLI")
public class SupermarketCLI implements Callable<Integer> {

    private final Estoque estoque;
    private final Carrinho carrinho;

    public SupermarketCLI(Estoque estoque, Carrinho carrinho) {
        this.estoque = estoque;
        this.carrinho = carrinho;
    }

    @Command(name = "estoque", description = "Manage inventory")
    public void estoque(
            @Option(names = {"-a", "--add"}, description = "Add product") boolean add,
            @Option(names = {"-r", "--remove"}, description = "Remove product") boolean remove,
            @Option(names = {"-l", "--list"}, description = "List all products") boolean list,
            @Parameters(paramLabel = "ID", description = "Product ID") Long id,
            @Parameters(paramLabel = "QUANTITY", description = "Quantity") Integer quantity) {
        try {
            if (add) {
            } else if (remove) {
            } else if (list) {
                estoque.exibirEstoque();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Command(name = "carrinho", description = "Manage shopping cart")
    public void carrinho(
            @Option(names = {"-a", "--add"}, description = "Add product") boolean add,
            @Option(names = {"-r", "--remove"}, description = "Remove product") boolean remove,
            @Option(names = {"-l", "--list"}, description = "List cart contents") boolean list,
            @Parameters(paramLabel = "ID", description = "Product ID") Long id,
            @Parameters(paramLabel = "QUANTITY", description = "Quantity") Integer quantity) {
        try {
            if (add) {
            } else if (remove) {
            } else if (list) {
                carrinho.exibirCarrinho();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Integer call() {
        System.out.println("Welcome to Supermarket Management System CLI");
        System.out.println("Use 'supermarket --help' for available commands");
        return 0;
    }

    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Carrinho carrinho = new Carrinho();
        int exitCode = new CommandLine(new SupermarketCLI(estoque, carrinho)).execute(args);
        System.exit(exitCode);
    }
} 