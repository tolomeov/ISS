/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author pedro
 */
public class ParcelaReceber {
    private int idParcela;
    private Date datavencimento;
    private Date datapagamento;
    private BigDecimal valor;
    private boolean status;
    
    public ParcelaReceber() {
    }

    public ParcelaReceber(Date datavencimento, Date datapagamento, BigDecimal valor, boolean status) {
        this.datavencimento = datavencimento;
        this.datapagamento = datapagamento;
        this.valor = valor;
        this.status = status;
    }
   
    public Integer getIdParcela() {
        return this.idParcela;
    }
    
    public void setIdParcela(Integer idParcela) {
        this.idParcela = idParcela;
    }
    
    public Date getDatavencimento() {
        return this.datavencimento;
    }
    
    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }
    public Date getDatapagamento() {
        return this.datapagamento;
    }
    
    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "ParcelaReceber{ Vencimento = " + datavencimento + ", Pagamento = " + (datapagamento==null? "null" : datapagamento.toString()) +
                ", Valor = " + valor + ", pago = " + status + "}";
    }
}
