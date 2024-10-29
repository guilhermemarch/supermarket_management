package carrinho.modelo;

public class Produto {

    private int id;
    private String nomeProduto;
    private String categoriaProduto;
    private double precoProduto;
    private int quantidadeProduto;

    public Produto(int codigoProduto, String nomeProduto, String categoriaProduto, double precoProduto, int quantidadeProduto) {
        this.id = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getId() {
        return id;
    }

    public void setId (int codigoProduto) {
        this.id = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

   public double calcularValorTotal(int quantidade) {

        return quantidade * this.precoProduto;
    }

}
