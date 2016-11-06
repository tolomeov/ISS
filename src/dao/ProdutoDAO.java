/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import model.Produto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            session.save(produto);
            trns.get().commit();
            
        } catch(HibernateException e) {
            
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
     * @param codigo: codigo a ser procurado
     * @return Optional que contém o produto desejado ou Optional.empty() caso contrário
     */
    public static Optional<Produto> getProdutoById(int codigo) {
        Optional<Produto> produto = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try{ 
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            produto = Optional.ofNullable( (Produto) session.get(Produto.class, codigo) );
        } catch(HibernateException e) {
            System.err.println("Erro buscando produto por id: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s->s.close());
        }
        
        return produto;
    }
    
    public static void deleteProdutoById(int codigo) {
        Optional<Transaction> transopt = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            transopt = Optional.of(session.beginTransaction());
            Produto produto = (Produto) session.load(Produto.class, codigo);
            session.delete(produto);
            transopt.get().commit();
        } catch(HibernateException e) {
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
    public static List<Produto> listProdutos() {
        Optional<List<Produto>> produtos = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            produtos = Optional.of(session.createQuery("from Produto").list());
        } catch(HibernateException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
        return produtos.orElse(Collections.emptyList());
    }
    
    public static void updateProduto(Produto produto) {
        Optional<Transaction> transopt = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            transopt = Optional.of(session.beginTransaction());
            session.update(produto);
            transopt.get().commit();
        } catch(HibernateException e) {
            transopt.ifPresent(t -> t.rollback());
            System.err.println("Erro ao atualizar um produto: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
    }
}
