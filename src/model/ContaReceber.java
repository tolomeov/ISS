/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author pedro
 */
public class ContaReceber  implements java.io.Serializable {
    private int idContaReceber;
    //Pessoa da qual a ContaReceber se origina(devedor)
    //TODO: tavlez devesse especializar para PessoaFisica?
    private Pessoa pessoa; 
    private BigDecimal valor;
    private int qtdparcelas;
    private String descricao;
    private boolean status;
    private List<ParcelaReceber> parcelas;

    public ContaReceber() {
    }

    public ContaReceber(Pessoa pessoa, BigDecimal valor, int qtdparcelas, String descricao, boolean status) {
       this.pessoa = pessoa;
       this.valor = valor;
       this.qtdparcelas = qtdparcelas;
       this.descricao = descricao;
       this.status = status;
    }
    
    public int getIdContaReceber() {
        return this.idContaReceber;
    }
    
    public void setIdContaReceber(int idContaReceber) {
        this.idContaReceber = idContaReceber;
    }
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public int getQtdparcelas() {
        return this.qtdparcelas;
    }
    
    public void setQtdparcelas(int qtdparcelas) {
        this.qtdparcelas = qtdparcelas;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public List<ParcelaReceber> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ParcelaReceber> parcelas) {
        this.parcelas = parcelas;
    }
     
    
}
