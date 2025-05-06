package carrinho.entidades;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "estoque")
public class Produto {
    @Id
    private Long id;
    
    @Column(name = "nome", nullable = false)
    private String nomeProduto;
    
    @Column(name = "categoria", nullable = false)
    private String categoriaProduto;
    
    @Column(name = "valor", nullable = false)
    private double precoProduto;
    
    @Column(name = "quantidade", nullable = false)
    private int quantidadeProduto;

    public Produto() {}

    public Produto(Long id, String nomeProduto, String categoriaProduto, double precoProduto, int quantidadeProduto) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.precoProduto = precoProduto;
        this.quantidadeProduto = quantidadeProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
