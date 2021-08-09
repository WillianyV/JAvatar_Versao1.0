/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.ControladorPanelInicial;
import controller.ControladorPanelJogo;
import dao.XML;
import model.Desenhar;
import model.GerarPerguntas;
import model.Jogador;
import view.InicialPanel;
import view.JogoPanel;
import view.Tela;

/**
 *
 * @author willi
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        GerarPerguntas gerarPerguntas = new GerarPerguntas();
        XML.gerarArquivo(XML.gerarXML(gerarPerguntas.quiz)); 
        XML.leArquivo();
         */
        InicialPanel inicialPanel = new InicialPanel();
        JogoPanel jogoPanel = new JogoPanel();

        Tela tela = new Tela(inicialPanel, jogoPanel);

        ControladorPanelInicial controladorPanelInicial = new ControladorPanelInicial(tela);
        ControladorPanelJogo controladorPanelJogo = new ControladorPanelJogo(tela, controladorPanelInicial);

        Desenhar desenhar = new Desenhar(tela);
        desenhar.start();
    }

}
