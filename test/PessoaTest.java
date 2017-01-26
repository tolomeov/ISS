/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.PessoaControl;
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
 * @author viviane
 */
public class PessoaTest {
    
    public PessoaTest() {
    }
   
    @Rule
public ExpectedException exception = ExpectedException.none();
    
    @Test     
    public void validarCPF() throws Exception {
    String cpf = "05972162903";
    boolean esperado = true;
    boolean retornou = PessoaControl.validarCPF(cpf);
    assertEquals(esperado, retornou);
    
    cpf = "11111111111";
    exception.expectMessage("cpf invalido");
    PessoaControl.validarCPF(cpf);
        
    }
    
        @Test     
    public void validarCNPJ() throws Exception {
    String cnpj = "15184164000193";
    boolean esperado = true;
    boolean retornou = PessoaControl.validarCNPJ(cnpj);
    assertEquals(esperado, retornou);
    
    cnpj = "11111111111111";
    exception.expectMessage("cnpj invalido");
    PessoaControl.validarCNPJ(cnpj);
        
    }
    
    
        @Test     
    public void validarEmail() throws Exception {
    String email = "tolomeov@union.edu";
    boolean esperado = true;
    boolean retornou = PessoaControl.validarEmail(email);
    assertEquals(esperado, retornou);
    
    email = "11111111111";
    exception.expectMessage("email invalido");
    PessoaControl.validarEmail(email);
        
    }
    
    
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
