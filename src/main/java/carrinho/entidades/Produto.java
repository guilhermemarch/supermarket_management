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
}
