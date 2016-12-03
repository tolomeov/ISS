/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Produto;
import plasnedo.settings.Settings;
import static plasnedo.settings.Settings.SettingIds.*;

/**
 * Implementa funções de controle de produto.
 * @author pedro
 */
public class ProdutoControl {
    
    public static final double CM = 0.01;
    public static final double MICRA = 0.000001;
    
    public static void validarLargura(double largura) throws Exception {
        if(largura < (double)Settings.getSetting(LARGURA_MIN)) {
            throw new Exception("Erro de validação: Largura do produto muito pequena: " + largura/CM + "cm" );
        }
        
        if(largura > (double)Settings.getSetting(LARGURA_MAX)) {
            throw new Exception("Erro de validação: Largura do produto muito grande: " + largura/CM + "cm");
        }
    }
    
    public static void validarAltura(double altura) throws Exception {
        if(altura < (double)Settings.getSetting(ALTURA_MIN)) {
            throw new Exception("Erro de validação: Altura do produto muito pequena: " + altura/CM + "cm");
        }
        
        if(altura > (double)Settings.getSetting(ALTURA_MAX)) {
            throw new Exception("Erro de validação: Altura do produto muito grande: " + altura/CM + "cm");
        }
    }
    
    public static void validarEspessura(double espessura) throws Exception {
        if(espessura < (double)Settings.getSetting(ESPESSURA_MIN)) {
            throw new Exception("Erro de validação: Espessura do produto muito pequena: " + espessura/MICRA + "μm");
        }
        
        if(espessura > (double)Settings.getSetting(ESPESSURA_MAX)) {
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
