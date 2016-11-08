package model;
// Generated 06/08/2016 14:48:22 by Hibernate Tools 4.3.1

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;




/**
 * @author viviane
 */
public class InformacoesBancarias  implements java.io.Serializable {


     private int idInformacoesBancarias;
     @OneToOne
     private int pessoa;
     private String banco;
     private String agencia;
     private String contaCorrente;
     private String titular;
     private String cnpj;
     private String cpf;

    public InformacoesBancarias() {
    }

	
    public InformacoesBancarias(int pessoa) {
        this.pessoa = pessoa;
    }
    
    public InformacoesBancarias(int idInformacoesBancarias, int pessoa, String banco, String agencia, String contaCorrente, String titular, String cnpj, String cpf) {
       this.idInformacoesBancarias = idInformacoesBancarias;
       this.pessoa = pessoa;
       this.banco = banco;
       this.agencia = agencia;
       this.contaCorrente = contaCorrente;
       this.titular = titular;
       this.cnpj = cnpj;
       this.cpf = cpf;
    }
   
   
    public int getIdInformacoesBancarias() {
        return this.idInformacoesBancarias;
    }
    
    public void setIdInformacoesBancarias(int idInformacoesBancarias) {
        this.idInformacoesBancarias = idInformacoesBancarias;
    }
    public int getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }
    public String getBanco() {
        return this.banco;
    }
    
    public void setBanco(String banco) {
        this.banco = banco;
    }
    public String getAgencia() {
        return this.agencia;
    }
    
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    public String getContaCorrente() {
        return this.contaCorrente;
    }
    
    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }
    public String getTitular() {
        return this.titular;
    }
    
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public String getCnpj() {
        return this.cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }




}


