/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import control.ContaReceberControl;
import java.util.List;
import java.util.Optional;
import model.ContaReceber;
import model.ParcelaReceber;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import plasnedo.util.HibernateUtil;

/**
 * Dada Access Object para ContaReceber e ParcelaReceber
 * @author pedro
 */
public class ContaReceberDAO {
    
    /**
     * Cria uma ContaReceber e a salva no BD, retornando o ID da sua criação
     * @param comprador
     * @param valorTotal
     * @param numeroParcelas
     * @param descricao
     * @param primeiroVencimento
     * @return 
     */
    static public Optional<Integer> createContaReceber(model.Pessoa comprador,
            java.math.BigDecimal valorTotal, 
            int numeroParcelas, String descricao,
            java.util.Date primeiroVencimento) throws Exception {
        
        Optional<Transaction> trns = Optional.empty();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Optional<Integer> id = Optional.empty();
        try {
            trns = Optional.of(session.beginTransaction());
            List<ParcelaReceber> parcelas = ContaReceberControl.createParcelasFromValor(valorTotal, numeroParcelas, primeiroVencimento);
            ContaReceber cr = new ContaReceber(comprador, valorTotal, numeroParcelas, descricao, false);
            cr.setParcelas(parcelas);
            id = Optional.ofNullable((Integer)session.save(cr));
            
            trns.get().commit();
        } catch(HibernateException e) {
            trns.ifPresent(Transaction::rollback);
            System.err.println("Erro criando ContaReceber: " + e.getMessage());
        } finally {
            session.close();
        }
        return id;
    }
    
    static public Optional<ContaReceber> getContaReceberById(int idContaReceber) {
        Optional<ContaReceber> conta = Optional.empty();
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            conta = Optional.ofNullable((ContaReceber) session.get(ContaReceber.class, idContaReceber));
            
        } catch(HibernateException e) {
            System.err.println("Erro buscando ContaReceber por id: " + e.getMessage());
        } finally {
            session.close();
        }
        return conta;
    }
    
    static public void main(String... args) {
        ContaReceber cr = getContaReceberById(2).orElse(null);
        if(cr == null) {
            System.out.println("null");
        } else {
            System.out.println("Pessoa = " + cr.getPessoa().getIdPessoa());
            cr.getParcelas().forEach((parcela) -> {
                System.out.println(parcela);
            });
        }
    }
    
}
