/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.InformacoesBancarias;
import model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import plasnedo.util.HibernateUtil;

/**
 *
 * @author viviane
 */
public class PessoaDAO {
    

    //PESSOAS
    public static void createPessoa(Pessoa pessoa) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(pessoa);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            
            throw new Exception("Error ao criar pessoa");
        } finally {
            session.flush();
            session.close();
        }
    }
   
    public static Pessoa getPessoaId(int id){
                          
        Pessoa pessoa = null;                
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
             pessoa = (Pessoa) session.get(Pessoa.class, id);
        } finally {
            session.flush();
            session.close();
        }

        return pessoa;
    }
    

    public static void deletePessoaId(int id) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Pessoa pessoa = (Pessoa) session.load(Pessoa.class, id);
            session.delete(pessoa);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            
            throw new Exception("Error ao excluir pessoa");
        } finally {
            session.flush();
            session.close();
        }
    }
              
    @SuppressWarnings("unchecked")
    public static List<Pessoa> listPessoas(){
        List<Pessoa> pessoas = new ArrayList<>();
                                     
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            pessoas = session.createQuery("from Pessoa").list();

        } catch (RuntimeException e) {
            
        } finally {
            session.flush();
            session.close();
        }
        return pessoas;
    }

              
    public static void updatePessoa(Pessoa pessoa) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(pessoa);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao atualizar pessoa");
        } finally {
            session.flush();
            session.close();
        }
    }
    
    
    //INFOS BANCÁRIAS
    public static void createInformacoesBancarias(InformacoesBancarias infos) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(infos);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao criar informacoes bancárias");
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public static InformacoesBancarias getInformacoesBancariasId(int id){
                          
        InformacoesBancarias info = null;                
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
             info = (InformacoesBancarias) session.get(InformacoesBancarias.class, id);
             
        } finally {
            session.flush();
            session.close();
        }

        return info;
    }

    public static void deleteInformacoesBancariasId(int id) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            InformacoesBancarias info = (InformacoesBancarias) session.load(InformacoesBancarias.class, id);
            session.delete(info);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw new Exception("Error ao excluir informacoes bancárias");
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public static List<InformacoesBancarias> informacoesBancariasQuery(String q){
        List<InformacoesBancarias> infos = new ArrayList<>();                             
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            infos = session.createQuery(q).list();

        } catch (RuntimeException e) {
            System.err.println("Erro buscando informações bancárias: " + e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return infos;
    }
    
    @SuppressWarnings("unchecked")
    public static List<InformacoesBancarias> listInformacoesBancarias(){
        List<InformacoesBancarias> infos = new ArrayList<>();
                                     
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            infos = session.createQuery("from InformacoesBancarias").list();
        } catch (RuntimeException e) {
            System.err.println("Erro listando informações bancárias: " + e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return infos;
    }

              
    public static void updateInformacoesBancarias(InformacoesBancarias infos) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(infos);
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            
            System.err.println("Erro atualizando informacoes bancárias: " + e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }
}
