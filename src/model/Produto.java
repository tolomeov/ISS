/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pedro
 */
public class Produto {

    public Produto() {
        
    }
    
    public Produto(int codigo, String nome, String grupo, String subgrupo, String unidade, double largura, double altura, double espessura, int deleted) {
        this.codigo = codigo;
        this.nome = nome;
        this.grupo = grupo;
        this.subgrupo = subgrupo;
        this.unidade = unidade;
        this.largura = largura;
        this.altura = altura;
        this.espessura = espessura;
        this.deleted = deleted;
    }
    
    /**
     * 
     */
    private int idProduto;
    public int getIdProduto(){ return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }
    
    
    /**
     * Codigo do produto
     */
    private int codigo;
    
    /**
     * Nome do produto em questão
     * @NaturalId significa que o hibernate irá buscar por nome de forma mais fácil
     */
    private String nome;
    
    /**
     * Tipo do Produto; apenas para descrição e consultas
     */
    private String grupo;
    private String subgrupo;
    
    /**
     * Unidade em que o produto é vendido
     */
    private String unidade;
    
    
    /**
     * largura da frente da sacola em metros
     */
    private double largura;
    /**
     * Altura da frente da sacola em metros
     */
    private double altura;
    /**
     * Espessura da sacola em metros
     * Note que a espessura é geralmente expressa em micrometros(1e-6),
     * porém a notacao se mantém em metros para ficar consistente
     */
    private double espessura;

    /**
     * Deleção lógica
     */
    private int deleted;

    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * Retorna o valor em metros da largura
     *
     * @return the value of largura
     */
    public double getLargura() {
        return largura;
    }

    /**
     * Seta o valor em metros da largura
     *
     * @param largura new value of largura
     */
    public void setLargura(double largura) {
        this.largura = largura;
    }

    /**
     * Retorna o valor em metros da altura
     *
     * @return the value of altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Seta o valor em metros da altura
     *
     * @param altura_cm new value of altura
     */
    public void setAltura(double altura_cm) {
        this.altura = altura_cm;
    }

    /**
     * Retorna o valor da espessura em metros
     *
     * @return the value of espessura
     */
    public double getEspessura() {
        return espessura;
    }

    /**
     * Seta o valor da espessura em metros
     *
     * @param espessura new value of espessura
     */
    public void setEspessura(double espessura) {
        this.espessura = espessura;
    }

    /** 
     * Calcula o volume do produto de acordo com suas medidas
     * @return Volume do produto em Litros
     */
    public double getVolume() {
        //Calcular área do circulo do topo da sacola
        //Largura = diametro
        //A = pi* r^2
        double raio = getLargura()/2;
        double area_circ = Math.PI*raio*raio;
        
        //Area_circulo * altura = volume
        double volume = area_circ * getAltura();
      
        //"volume" está em m³, deve ser convertido para Litros
        return volume/1000;
    }
    

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
