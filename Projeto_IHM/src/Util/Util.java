/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author willi
 */
public class Util {
    public static final String AANG = "Aang";
    public static final String KATARA = "Katara";
    public static final String TOPH = "Toph";
    public static final String ZUCO = "Zuco";
    public static final String SOKKA = "Sokka";
    public static final int PORTA_SERVIDOR = 3333;
    public static final int ALTURA = 768;
    public static final int LARGURA = 1366;
    public static final String DIREITA = "direita";
    public static final String ESQUERDA = "esquerda";
    
    public static final String MUSICA_TEMA = "Arquivos\\Sons\\01 Main Title.wav";
    
    public static final String AANG_HIT = "Arquivos\\Sons\\aang_hit.wav";
    public static final String KATARA_HIT = "Arquivos\\Sons\\katara_hit.wav";
    public static final String SOKKA_HIT = "Arquivos\\Sons\\sokka_hit.wav";
    public static final String TOPH_HIT = "Arquivos\\Sons\\toph_hit.wav";
    public static final String ZUCO_HIT = "Arquivos\\Sons\\zuko_hit.wav";
    
    public static final String AANG_ATTACK = "Arquivos\\Sons\\aang_attack.wav";
    public static final String KATARA_ATTACK = "Arquivos\\Sons\\katara_attack2.wav";
    public static final String SOKKA_ATTACK = "Arquivos\\Sons\\sokka_attack.wav";
    public static final String TOPH_ATTACK = "Arquivos\\Sons\\toph_attack4.wav";
    public static final String ZUCO_ATTACK = "Arquivos\\Sons\\zuko_attack4.wav";
    
    public static int multiplicador = 1, erros = 0;
    
    public static boolean efeitosSonoros = true;
    
    public static boolean flagAtacandoPoder1 = false;
    public static boolean flagAtacandoPoder2 = false;
    public static boolean flagAtacandoP1 = false;
    public static boolean flagAtacandoP2 = false;
    
    public static boolean flagDefesaP1 = false;
    public static boolean flagDefesaP2 = false;
    public static boolean flagDefedendo = false;
    
    public static boolean flagElementosP1 = false;
    public static boolean flagElementosP2 = false;
    
    public static boolean flagFerido = false;
    public static boolean flagFerido2 = false;
    
    public static boolean flagSomAnimacaoAtaque = true;
    
    public static boolean flagGanhou = false;
    public static boolean flagPerdeu = false;
    
    
    public static Font sansSerif = new Font("Sans Serif", Font.PLAIN, 12);
    public static Color color = new Color(51,51,51);
     public static Font chewy = getFont("Chewy", 30);
    
    
    public static Font getFont( String nomeFonte, int tamanho ){  
        Font font = null;  
        try{
        	font = Font.createFont(Font.TRUETYPE_FONT, new File("Arquivos/Fontes/"+nomeFonte+".ttf"));
        }
        catch(IOException|FontFormatException e){
             //Mensagem.mostrar("Erro ao carregar fonte!", Util.ERRRO);
        }
            font = font.deriveFont(Font.PLAIN, tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
    } 
}
