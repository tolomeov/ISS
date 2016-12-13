
package control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.ContaReceber;
import model.ParcelaReceber;

/**
 *  Classe de validação/controle de Contas/Parcelas
 * 
 * Uma Conta[Pagar/Receber] tem uma "quantidade de parcelas" N
 * Estas N parcelas sao associadas com esta conta pelo ID da conta
 * 
 * @author pedro
 */
public class ContaReceberControl {
    
    /**
     * Retorna true se as parcelas condizem com a ContaReceber cr
     * @param cr
     * @return true se cr e parcelas forem válidas entre si
     */
    static public boolean validateContaReceber(ContaReceber cr)
    {
        BigDecimal acc = BigDecimal.ZERO;
        for(Object parcelaObject : cr.getParcelas()){
            ParcelaReceber parcela = (ParcelaReceber)parcelaObject;
            acc = acc.add(parcela.getValor());
        }
        
        return acc.compareTo(cr.getValor()) == 0;
    }
    
    
    static public List<ParcelaReceber> createParcelasFromValor(BigDecimal valorTotal, int numParcelas, Date primeiroVencimento)
    {
        assert(valorTotal != BigDecimal.ZERO);
        
        List<ParcelaReceber> ret = new ArrayList<>(numParcelas);
        
        BigDecimal valorParcial = valorTotal.divide(BigDecimal.valueOf(numParcelas), 
                2, BigDecimal.ROUND_HALF_EVEN); //arredondamento para o mais proximo, 2 casas decimais
        BigDecimal checkSum = BigDecimal.ZERO;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(primeiroVencimento);
        
        for(int i = 0; i < numParcelas; i++) {
            ret.add(i, new ParcelaReceber(cal.getTime(), null, valorParcial, false));
            cal.add(Calendar.MONTH, 1);
            checkSum = checkSum.add(valorParcial);
        }
        
        BigDecimal diff = valorTotal.subtract(checkSum);
        if(diff.compareTo(BigDecimal.ZERO) != 0) {
            ParcelaReceber p = ret.get(0);
            p.setValor(p.getValor().add(diff));
        }
        
        return ret;
    }
    
    /*
    static public void main(String... args) {
        ContaReceber cr = new ContaReceber(null, BigDecimal.valueOf(10000, 2), 6, "bla", false);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        List<ParcelaReceber> parcelas = createParcelasFromValor(cr.getValor(), cr.getQtdparcelas(), cal.getTime());
        cr.setParcelas(parcelas);
        parcelas.forEach((p) -> System.out.println(p));
        
    }
    */
    
}
