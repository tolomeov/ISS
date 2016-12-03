
package plasnedo.settings;

import control.ProdutoControl;
import static control.ProdutoControl.CM;
import static control.ProdutoControl.MICRA;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Classe de leitura de configuração do sistema
 * Permite setar caracteristicas de controle do sistema em tempo real via um arquivo XML simples
 * 
 * O arquivo deve ser recarregado toda vez que for modificado
 * 
 * Usado principalmente em classes de controle
 * @author pedro
 */

class SettingHandler extends DefaultHandler {

    
    /**
     * o Handler recebe a notificacao de que o documento foi iniciado
     */
    @Override
    public void startDocument() {
        //
    }
    
    /**
     * o Handler recebe a notificacao de que o documento chegou no fim
     */
    @Override
    public void endDocument() {
        //
    }
    
    /**
     * O handler recebe a notificacao de que um elemento foi iniciado
     * @param uri: o namespace do elemento, dado pelo atributo xmlns.
     * @param localName: o nome do elemento sem qualificador(ex "foo:bar" tem localName "bar") ou vazio se nao tem qualificador
     * @param qName: o nome do elemento com qualificador
     * @param attributes: os atributos que o elemento possui
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        
        /*
           <produto largura_min="" largura_max="" altura_min="" altura_max="" espessura_min="" espessura_max=""/>
        */
        
        if(qName.equalsIgnoreCase("produto")) {
            for(int i = 0; i < attributes.getLength(); i++) {
                
                Boolean found = false;
                for(Settings.SettingIds id : Settings.SettingIds.values()) {
                    if(id.name().equalsIgnoreCase(attributes.getQName(i))) {
                        found = true;
                        try {
                            double val = Double.valueOf(attributes.getValue(i));
                        
                            switch(id) {
                                case ESPESSURA_MIN:
                                case ESPESSURA_MAX:
                                    val *= MICRA;
                                    break;
                                default:
                                    val *= CM;
                                    break;
                            }

                            Settings.alterSetting(id, val);
                        }
                        catch (java.lang.NumberFormatException nfe){
                            System.err.println("Aviso: atributo " + id.name() + " possui um valor inválido.");
                        }
                    }
                }
                
                if(!found) {
                    System.err.println("Aviso: atributo " + attributes.getQName(i) + " não é uma configuração de produto.");
                }
            }
        }
    }
    
    /**
     * Executado quando o parser entra no texto dentro de um elemento
     * Sempre chamado entre um startElement() e um endElement(), mas nem todo start/end irá chamar characters()
     * @param ch: O texto do elemento
     * @param start: Posicao de inicio do texto
     * @param length: tamanho do texto
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        //
    }
    
    /**
     * Chamado quando o parser termina de ler um elemento. Ver {@link #startElement startElement()}
     * @param uri
     * @param localName
     * @param qName 
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        //
    }
}

abstract class FileWatcher extends TimerTask {
    private long timeStamp;
    private final File file;

    public FileWatcher( File file ) {
        this.file = file;
        this.timeStamp = file.lastModified();
    }

    @Override
    public final void run() {
        long newTimeStamp = file.lastModified();

        if( this.timeStamp != newTimeStamp ) {
            this.timeStamp = newTimeStamp;
            onChange(file);
        }
    }

    protected abstract void onChange( File file );
}

public class Settings {
    
    public enum SettingIds { 
        LARGURA_MIN,
        LARGURA_MAX,
        ALTURA_MIN,
        ALTURA_MAX,
        ESPESSURA_MIN,
        ESPESSURA_MAX
    }
    
    private static EnumMap<SettingIds, Object> settingValues;
    
    
    private static File settings;
    private static SAXParserFactory factory;
    private static SAXParser parser;
    private static SettingHandler sh;
    
    
    
    static {
        resetSettings();

        try //uso de static permite que o arquivo de configuracao seja carregado no inicio do sistema
        {
            settings = new File("settings.xml");
            settings.createNewFile();
            
            
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            sh = new SettingHandler();
            parser.parse(settings, sh);
            
            //Cria um timer que verifica alterações de configuracao a cada segundo
            TimerTask task = new FileWatcher(settings) {
                @Override
                protected void onChange(File file) {
                    resetSettings();
                    try {
                        parser.parse(file, sh);
                    } catch (SAXException | IOException ex) {
                        Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

            Timer timer = new Timer();
            timer.schedule(task, new Date(), 1000);
            

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * Reinicializa as configurações para seus padrões default
     * Deve ser chamado antes de ler o arquivo XML para garantir que o sistema contenha configurações padrão
     */
    static private void resetSettings() {
        /* DEFAULTS
            private static double LARGURA_MIN = 1*CM;
            private static double LARGURA_MAX = 1000*CM;
            private static double ALTURA_MIN = 1*CM;
            private static double ALTURA_MAX = 1000*CM;
            private static double ESPESSURA_MIN = 0.1 * MICRA;
            private static double ESPESSURA_MAX = 1000 * MICRA;
        */
        settingValues = new EnumMap<>(SettingIds.class);
        
        //ProdutoControl
        settingValues.put(SettingIds.LARGURA_MIN, 1*CM);
        settingValues.put(SettingIds.LARGURA_MAX, 1000*CM);
        settingValues.put(SettingIds.ALTURA_MIN,  1*CM);
        settingValues.put(SettingIds.ALTURA_MAX,  1000*CM);
        settingValues.put(SettingIds.ESPESSURA_MIN, 0.1*MICRA);
        settingValues.put(SettingIds.ESPESSURA_MAX, 1000*MICRA);
    }
    
    static public void alterSetting(SettingIds id, Object newValue) {
        settingValues.put(id, newValue);
    }
    
    static public Object getSetting(SettingIds id) {
        return settingValues.get(id);
    }
    
    static public void main(String[] args) {
        //
    }
    
}
