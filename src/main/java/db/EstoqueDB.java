package db;

import carrinho.entidades.Produto;
import entity.Estoque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

import java.util.List;

public class EstoqueDB {
    public void adicionarProduto(Estoque produto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void removerProduto(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Estoque produto = em.find(Estoque.class, id);
            if (produto != null) {
                em.remove(produto);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void atualizarProduto(Estoque produto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Estoque> listarProdutos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Estoque> query = em.createQuery("SELECT e FROM Estoque e", Estoque.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Estoque buscarProdutoPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Estoque.class, id);
        } finally {
            em.close();
        }
    }

    public List<Estoque> buscarProdutosPorCategoria(String categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Estoque> query = em.createQuery(
                "SELECT e FROM Estoque e WHERE e.categoria = :categoria", Estoque.class);
            query.setParameter("categoria", categoria);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int obterQuantidade(int i) {
    }

    public void atualizarQuantidadeDB(int i, int novaQuantidade) {
    }

    public List<Produto> consultarEstoque() {
    }

    public Produto buscarPorId(Long id) {
    }

    public void excluirProduto(Long id) {
    }

    public void atualizarQuantidade(Produto produto) {
    }

    public void inserirProduto(Produto produto) {
    }

    public void limparEstoque() {
    }

    public Produto buscarProdutoEstoqueID(Long produtoId) {
    }
}
