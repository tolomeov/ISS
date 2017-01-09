/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import model.Produto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import plasnedo.util.HibernateUtil;

/**
 *
 * @author pedro
 */
public class ProdutoDAO {
    
    public static void createProduto(Produto produto) {
        Optional<Transaction> trns = Optional.empty();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = Optional.of(session.beginTransaction());
            Serializable id = session.save(produto);
            trns.get().commit();
            
        } catch(Exception e) {
            
            trns.ifPresent(t -> t.rollback());
            System.err.println("Erro ao salvar Produto " + produto.getCodigo() +
                    ": " + e.getMessage());
            
        } finally {
            session.flush();
            session.close();
        }
    }
    
    /**
     * Busca um Produto pelo seu código;
     * Retorna um Optional que será `empty` se nao existir um produto com o dado código
     * @param idProduto: ID a ser procurado
     * @return Optional que contém o produto desejado ou Optional.empty() caso contrário
     */
    public static Optional<Produto> getProdutoById(int idProduto) {
        Optional<Produto> produto = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try{ 
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            produto = Optional.ofNullable( (Produto) session.get(Produto.class, idProduto) );
        } catch(Exception e) {
            System.err.println("Erro buscando produto por id: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s->s.close());
        }
        
        return produto;
    }
    
    public static void deleteProdutoById(int idProduto) {
        Optional<Transaction> transopt = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            transopt = Optional.of(session.beginTransaction());
            Produto produto = (Produto) session.get(Produto.class, idProduto);

            session.delete(produto);
            transopt.get().commit();
        } catch(Exception e) {
            transopt.ifPresent(t->t.rollback());
            System.err.println("Erro ao excluir produto: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s->{
                s.flush();
                s.close();
            });
        }
    }
    
    /**
     * @return Retorna uma lista de todos os Produtos no banco de dados
     * Se ocorrer uma exceção, retorna uma lista vazia imutável
     */
    @SuppressWarnings("unchecked")
    public static List<Produto> listProdutos() {
        Optional<List<Produto>> produtos = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            produtos = Optional.of(session.createQuery("from Produto").list());
        } catch(Exception e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
        return produtos.orElse(Collections.emptyList());
    }
    
    /**
     * Atualiza um produto no BD
     * @param produto 
     */
    public static void updateProduto(Produto produto) {
        Optional<Transaction> transopt = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            transopt = Optional.of(session.beginTransaction());
            session.update(produto);
            transopt.get().commit();
        } catch(Exception e) {
            transopt.ifPresent(t -> t.rollback());
            System.err.println("Erro ao atualizar um produto: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Produto> listProdutosWhereNomeLike(String nome) {
        Optional<List<Produto>> produtos = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            Criteria query = session.createCriteria(Produto.class);
            query.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
            
            produtos = Optional.of(query.list());
        } catch(Exception e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
        return produtos.orElse(Collections.emptyList());
    }
    
    @SuppressWarnings("unchecked")
    public static List<Produto> listProdutosWhereCodigoEquals(int codigo) {
        Optional<List<Produto>> produtos = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            Criteria query = session.createCriteria(Produto.class);
            query.add(Restrictions.eq("codigo", codigo));
            
            produtos = Optional.of(query.list());
        } catch(Exception e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
        return produtos.orElse(Collections.emptyList());
    }
    
}
