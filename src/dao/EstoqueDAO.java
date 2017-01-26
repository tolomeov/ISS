/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author lucas
 */

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import model.Estoque;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import plasnedo.util.HibernateUtil;


public class EstoqueDAO {
    public static void createEstoque(Estoque estoque) {
        Optional<Transaction> trns = Optional.empty();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = Optional.of(session.beginTransaction());
            Serializable id = session.save(estoque);
            trns.get().commit();
            
        } catch(Exception e) {
            
            trns.ifPresent(t -> t.rollback());
            //System.err.println("Erro ao salvar Estoque " + estoque.getCodigo() +
            //        ": " + e.getMessage());
            
        } finally {
            session.flush();
            session.close();
        }
    }
    
    /**
     * Busca um Estoque pelo seu código;
     * Retorna um Optional que será `empty` se nao existir um estoque com o dado código
     * @param idEstoque: ID a ser procurado
     * @return Optional que contém o estoque desejado ou Optional.empty() caso contrário
     */
    public static Optional<Estoque> getEstoqueById(int idEstoque) {
        Optional<Estoque> estoque = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try{ 
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            estoque = Optional.ofNullable( (Estoque) session.get(Estoque.class, idEstoque) );
        } catch(Exception e) {
            System.err.println("Erro buscando estoque por id: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s->s.close());
        }
        
        return estoque;
    }
    
    public static void deleteEstoqueById(int idEstoque) {
        Optional<Transaction> transopt = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            transopt = Optional.of(session.beginTransaction());
            Estoque estoque = (Estoque) session.get(Estoque.class, idEstoque);

            session.delete(estoque);
            transopt.get().commit();
        } catch(Exception e) {
            transopt.ifPresent(t->t.rollback());
            System.err.println("Erro ao excluir estoque: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s->{
                s.flush();
                s.close();
            });
        }
    }
    
    /**
     * @return Retorna uma lista de todos os Estoques no banco de dados
     * Se ocorrer uma exceção, retorna uma lista vazia imutável
     */
    @SuppressWarnings("unchecked")
    public static List<Estoque> listEstoques() {
        Optional<List<Estoque>> estoques = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            estoques = Optional.of(session.createQuery("from Estoque").list());
        } catch(Exception e) {
            System.err.println("Erro ao listar estoques: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
        return estoques.orElse(Collections.emptyList());
    }
    
    /**
     * Atualiza um estoque no BD
     * @param estoque 
     */
    public static void updateEstoque(Estoque estoque) {
        Optional<Transaction> transopt = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            transopt = Optional.of(session.beginTransaction());
            session.update(estoque);
            transopt.get().commit();
        } catch(Exception e) {
            transopt.ifPresent(t -> t.rollback());
            System.err.println("Erro ao atualizar um estoque: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Estoque> listEstoquesWhereNomeLike(String nome) {
        Optional<List<Estoque>> estoques = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            Criteria query = session.createCriteria(Estoque.class);
            query.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
            
            estoques = Optional.of(query.list());
        } catch(Exception e) {
            System.err.println("Erro ao listar estoques: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
        return estoques.orElse(Collections.emptyList());
    }
    
    @SuppressWarnings("unchecked")
    public static List<Estoque> listEstoquesWhereCodigoEquals(int codigo) {
        Optional<List<Estoque>> estoques = Optional.empty();
        Optional<Session> sessionopt = Optional.empty();
        
        try {
            sessionopt = Optional.of(HibernateUtil.getSessionFactory().openSession());
            Session session = sessionopt.get();
            
            Criteria query = session.createCriteria(Estoque.class);
            query.add(Restrictions.eq("codigo", codigo));
            
            estoques = Optional.of(query.list());
        } catch(Exception e) {
            System.err.println("Erro ao listar estoques: " + e.getMessage());
        } finally {
            sessionopt.ifPresent(s-> {
                s.flush();
                s.close();
            });
        }
        return estoques.orElse(Collections.emptyList());
    }
    
}
