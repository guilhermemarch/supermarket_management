//package carrinho.servico;
//
//import carrinho.entidades.Carrinho;
//import carrinho.entidades.Estoque;
//import carrinho.entidades.Produto;
//import db.carrinhoBD;
//import db.estoqueBD;
//
//import java.sql.SQLException;
//
//
//public class servicoCarrinho {
//
//    // Atributos
//    private Carrinho carrinho;
//    private Estoque estoque;
//    private carrinhoBD carrinhoBD;
//
//    public servicoCarrinho(Estoque estoque, carrinhoBD carrinhoBD) {
//        this.carrinho = new Carrinho();
//        this.estoque = estoque;
//        this.carrinhoBD = carrinhoBD;
//    }
//
//    // Método para adicionar um produto ao carrinho
//    public void adicionarProdutoCarrinho(long id, int quantidade) throws SQLException {
//        Produto produto = estoque.buscarPorId(id);
//
//        if (produto != null && produto.getQuantidadeProduto() >= quantidade) {
//            carrinho.adicionarProduto(produto, quantidade);
//            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidade);
//            estoque.atualizarEstoque(produto);
//        } else {
//            System.out.println("Produto não disponível ou quantidade insuficiente.");
//        }
//    }
//
//    public void removerProdutoCarrinho(int id) throws SQLException {
//        Produto produto = carrinho.buscarProduto(id);
//
//        if (produto != null) {
//            carrinho.removerProduto(id);
//            produto.setQuantidadeProduto(produto.getQuantidadeProduto() + produto.getQuantidadeProduto());
//            estoque.atualizarEstoque(produto);
//        } else {
//            System.out.println("Produto não encontrado no carrinho.");
//        }
//    }
//
//
//
//    public void visualizarCarrinho() {
//       System.out.println("Produtos no carrinho:");
//       for (Produto produto : produto.getProduto()) {
//            System.out.println("Produto: " + produto.getNomeProduto() + ", Quantidade: " + produto.getQuantidadeProduto());
//        }
//        System.out.println("Valor total do carrinho: " + carrinho.calcularValorTotal());
//    }
//
//
//}
