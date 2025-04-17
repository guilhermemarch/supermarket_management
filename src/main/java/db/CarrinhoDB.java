package db;

import carrinho.entidades.Produto;
import entity.Carrinho;
import entity.Estoque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class CarrinhoDB {
    public void adicionarAoCarrinho(Carrinho item) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void removerDoCarrinho(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Carrinho item = em.find(Carrinho.class, id);
            if (item != null) {
                em.remove(item);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void atualizarItem(Carrinho item) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Carrinho> listarItens() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Carrinho> query = em.createQuery("SELECT c FROM Carrinho c", Carrinho.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Carrinho buscarItemPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Carrinho.class, id);
        } finally {
            em.close();
        }
    }

    public void limparCarrinho() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Carrinho").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public double calcularTotal() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Double> query = em.createQuery(
                "SELECT SUM(c.valorTotal) FROM Carrinho c", Double.class);
            Double total = query.getSingleResult();
            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }

    public void inserirProdutoCarrinho(long id, Produto produto, int quantidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Carrinho carrinho = new Carrinho();
            carrinho.setId(id);
            carrinho.setProduto(produto);
            carrinho.setQuantidade(quantidade);
            em.persist(carrinho);
            em.getTransaction().commit();
        } finally {
            em.close();
        }



    }


    public void removerProdutoCarrinho(long produtoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Carrinho carrinho = em.find(Carrinho.class, produtoId);
            if (carrinho != null) {
                em.remove(carrinho);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Produto buscarProdutoCarrinho(long produtoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Produto.class, produtoId);
        } finally {
            em.close();
        }
    }

    public double valorCarrinho() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Double> query = em.createQuery(
                "SELECT SUM(c.valorTotal) FROM Carrinho c", Double.class);
            Double total = query.getSingleResult();
            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }

    public List<Produto> consultarCarrinho() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}


