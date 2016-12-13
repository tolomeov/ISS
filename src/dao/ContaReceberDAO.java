/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import control.ContaReceberControl;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import model.ContaReceber;
import model.ParcelaReceber;
import model.Pessoa;
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
    static public Optional<Integer> createContaReceber(Pessoa comprador,
            BigDecimal valorTotal, 
            int numeroParcelas, String descricao,
            Date primeiroVencimento) {
        
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
    
    static public void main(String... args) {
        Pessoa p = PessoaDAO.getPessoaId(1);
        
        BigDecimal valorTotal = BigDecimal.valueOf(100);
        int numeroParcelas = 6;
        String descr = "";
        Date primeiroVencimento = Calendar.getInstance().getTime();
        
        Optional<Integer> id = createContaReceber(p, valorTotal, numeroParcelas, descr, primeiroVencimento);
        System.out.println("id == " + id.orElse(-1));
        
    }
    
}
