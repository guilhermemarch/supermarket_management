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

    public int obterQuantidade(int i)
    {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Estoque produto = em.find(Estoque.class, (long) i);
            return produto != null ? produto.getQuantidade() : 0;
        } finally {
            em.close();
        }
    }

    public void atualizarQuantidadeDB(int i, int novaQuantidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Estoque produto = em.find(Estoque.class, (long) i);
            if (produto != null) {
                produto.setQuantidade(novaQuantidade);
                em.merge(produto);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Produto> consultarEstoque() {


        return null;
    }

    public Produto buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }

    }

    public void excluirProduto(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);
            if (produto != null) {
                em.remove(produto);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public void atualizarQuantidade(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Produto produtoExistente = em.find(Produto.class, produto.getId());
            if (produtoExistente != null) {
                produtoExistente.setQuantidadeProduto(produto.getQuantidadeProduto());
                em.merge(produtoExistente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public void inserirProduto(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public void limparEstoque() {

    }

    public Produto buscarProdutoEstoqueID(Long produtoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Produto.class, produtoId);
        } finally {
            em.close();
        }
    }
}
