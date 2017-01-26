/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.ContaReceberControl;
import control.PessoaControl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.ContaReceber;
import model.ParcelaReceber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author barbiero
 */
public class ContaReceberTest {

    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test     
    public void validateContaReceber()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        ContaReceber cr = new ContaReceber(null, BigDecimal.TEN, 3, "", false);
        List<ParcelaReceber> prs = new ArrayList<>();
        
        for(int i = 0; i < 3; i++) {
            prs.add(i, new ParcelaReceber(cal.getTime(), null, new BigDecimal(2), false));
            cal.add(Calendar.MONTH, 1);
        }
        cr.setParcelas(prs);
        
        assertEquals(ContaReceberControl.validateContaReceber(cr), false);
        
        prs = new ArrayList<>();
        BigDecimal valorParcial = BigDecimal.TEN.divide(BigDecimal.valueOf(3), 
                2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal checkSum = BigDecimal.ZERO;
        
        for(int i = 0; i < 3; i++) {
            prs.add(i, new ParcelaReceber(cal.getTime(), null, valorParcial, false));
            cal.add(Calendar.MONTH, 1);
            checkSum = checkSum.add(valorParcial);
        }
        
        BigDecimal diff = BigDecimal.TEN.subtract(checkSum);
        if(diff.compareTo(BigDecimal.ZERO) != 0) {
            ParcelaReceber p = prs.get(0);
            p.setValor(p.getValor().add(diff));
        }
        
        cr.setParcelas(prs);
        assertEquals(ContaReceberControl.validateContaReceber(cr), true);
        
    }
    
    @Test     
    public void createParcelasFromValor() throws Exception
    {
        BigDecimal valorTotal = BigDecimal.TEN;
        int numParcelas = 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.YEAR, 2016);
        
        Date primeiroVencimento = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        
        List<ParcelaReceber> esperado = Arrays.asList(
                new ParcelaReceber(primeiroVencimento, null, BigDecimal.TEN, false)
        );
        
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.YEAR, 2016);
        List<ParcelaReceber> result = 
                ContaReceberControl.createParcelasFromValor(valorTotal, numParcelas, primeiroVencimento);
        assertEquals(esperado.size(), result.size());
        
        for(int i = 0; i < esperado.size(); i++) {
            assertEquals(esperado.get(i).getValor(), result.get(i).getValor());
        }
        
        numParcelas = 0;
        result = 
                ContaReceberControl.createParcelasFromValor(valorTotal, numParcelas, primeiroVencimento);
        
        assertEquals(result, Collections.emptyList());
        
        numParcelas = 1;
        valorTotal = BigDecimal.ONE.negate();
        exception.expectMessage("parcela deve ter um valor positivo");
        result = 
                ContaReceberControl.createParcelasFromValor(valorTotal, numParcelas, primeiroVencimento);
    }

    
}
