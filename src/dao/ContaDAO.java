/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.ContaPagar;
import model.ParcelasPagar;
import org.hibernate.Session;
import org.hibernate.Transaction;
import plasnedo.util.HibernateUtil;

/**
 *
 * @author viviane
 */


// CONTAS A PAGAR
public class ContaDAO {
     public static void createContaPagar(ContaPagar conta) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(conta);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            throw new Exception("Error ao criar conta a pagar");
        } finally {
            session.flush();
            session.close();
        }
    }

    
    public static ContaPagar getContaPagarId(int id){
                          
        ContaPagar conta = null;                
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
             conta = (ContaPagar) session.get(ContaPagar.class, id);
        } finally {
            session.flush();
            session.close();
        }

        return conta;
    }
    


    public static void deleteContaPagarId(int id) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            ContaPagar conta = (ContaPagar) session.load(ContaPagar.class, id);
            session.delete(conta);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            throw new Exception("Error ao excluir conta a pagar");
        } finally {
            session.flush();
            session.close();
        }
    }
    
              
    @SuppressWarnings("unchecked")
    public static List<ContaPagar> listContasPagar(){
        List<ContaPagar> contas = new ArrayList<>();
                                     
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            contas = session.createQuery("from ContaPagar").list();

        } catch (RuntimeException e) {
        } finally {
            session.flush();
            session.close();
        }
        return contas;
    }
    

              
    public static void updateContaPagar(ContaPagar conta) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(conta);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            throw new Exception("Error ao atualizar conta a pagar");
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<ContaPagar> contaPagarQuery(String q){
        List<ContaPagar> conta = new ArrayList<>();                             
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            conta = (List<ContaPagar>) session.createQuery(q).list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return conta;
    }
    
//PARCELAS A PAGAR

    public static void createParcelaPagar(ParcelasPagar parcela) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(parcela);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            throw new Exception("Error ao criar parcela a pagar");
        } finally {
            session.flush();
            session.close();
        }
    }
    

    
    public static ParcelasPagar getParcelasPagarId(int id){
                          
        ParcelasPagar parcela = null;                
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
             parcela = (ParcelasPagar) session.get(ParcelasPagar.class, id);
        } finally {
            session.flush();
            session.close();
        }

        return parcela;
    }
        

    public static void deleteParcelaPagarId(int id) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            ParcelasPagar parcela = (ParcelasPagar) session.load(ParcelasPagar.class, id);
            session.delete(parcela);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            throw new Exception("Error ao excluir parcela a pagar");
        } finally {
            session.flush();
            session.close();
        }
    }
              
    @SuppressWarnings("unchecked")
    public static List<ParcelasPagar> listParcelasPagar(){
        List<ParcelasPagar> parcelas = new ArrayList<>();
                                     
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            parcelas = session.createQuery("from ParcelasPagar").list();

        } catch (RuntimeException e) {
        } finally {
            session.flush();
            session.close();
        }
        return parcelas;
    }
    
    @SuppressWarnings("unchecked")
    public static List<ParcelasPagar> listParcelasPagarIDconta(int id){
        List<ParcelasPagar> parcelas = new ArrayList<>();
                                     
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            parcelas = session.createQuery("from ParcelasPagar where idConta = " + id).list();

        } catch (RuntimeException e) {
        } finally {
            session.flush();
            session.close();
        }
        return parcelas;
    }
    
    
              
    public static void updateParcelaPagar(ParcelasPagar parcela) throws Exception{
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(parcela);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            throw new Exception("Error ao atualizar parcela a pagar");
        } finally {
            session.flush();
            session.close();
        }
    }
    
        
    
}

