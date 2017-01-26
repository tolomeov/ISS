/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lucas
 */
public class Estoque {
     private int Id;
     private Double fator;
     private Double valor;
     private String status;
     private Integer deleted;

    public Estoque() {
    }

    public Estoque(int Id, Double fator, Double valor, String status, Integer deleted) {
        this.Id = Id;
        this.fator = fator;
        this.valor = valor;
        this.status = status;
        this.deleted = deleted;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Double getFator() {
        return fator;
    }

    public void setFator(Double fator) {
        this.fator = fator;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    

}
