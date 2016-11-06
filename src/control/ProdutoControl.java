/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Produto;

/**
 * Implementa funções de controle de produto.
 * @author pedro
 */
public class ProdutoControl {
    
    public static final double CM = 0.01;
    public static final double MICRA = 0.000001;
    
    /*
    Valores de dimensões para os produtos
    Estes valores são deliberadamente acima ou abaixo do que algo que existiria na vida real
    São utilizados na validação do produto.
    */
    private static final double LARGURA_MIN = 1*CM;
    private static final double LARGURA_MAX = 1000*CM;
    private static final double ALTURA_MIN = 1*CM;
    private static final double ALTURA_MAX = 1000*CM;
    private static final double ESPESSURA_MIN = 0.1 * MICRA;
    private static final double ESPESSURA_MAX = 1000 * MICRA;
    
    
    public static void validarLargura(double largura) throws Exception {
        if(largura < LARGURA_MIN) {
            throw new Exception("Erro de validação: Largura do produto muito pequena: " + largura/CM + "cm" );
        }
        
        if(largura > LARGURA_MAX) {
            throw new Exception("Erro de validação: Largura do produto muito grande: " + largura/CM + "cm");
        }
    }
    
    public static void validarAltura(double altura) throws Exception {
        if(altura < ALTURA_MIN) {
            throw new Exception("Erro de validação: Altura do produto muito pequena: " + altura/CM + "cm");
        }
        
        if(altura > ALTURA_MAX) {
            throw new Exception("Erro de validação: Altura do produto muito grande: " + altura/CM + "cm");
        }
    }
    
    public static void validarEspessura(double espessura) throws Exception {
        if(espessura < ESPESSURA_MIN) {
            throw new Exception("Erro de validação: Espessura do produto muito pequena: " + espessura/MICRA + "μm");
        }
        
        if(espessura > ESPESSURA_MAX) {
            throw new Exception("Erro de validação: Espessura do produto muito grande: " + espessura/MICRA + "μm");
        }
    }
    
    public static void validarDimensoes(Produto produto) throws Exception {
        validarLargura(produto.getLargura());
        validarAltura(produto.getAltura());
        validarEspessura(produto.getEspessura());
    }
    
    public static void validarDimensoes(int largura, int altura, int espessura) throws Exception{
        validarLargura(largura);
        validarAltura(altura);
        validarEspessura(espessura);
    }
    
    
    
    
    
}
