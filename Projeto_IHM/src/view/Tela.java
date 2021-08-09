/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.Util;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.Jogador;
import model.Sons;

/**
 *
 * @author willi
 */
public class Tela extends JFrame {

    /*
    Telas:
     */
    private InicialPanel inicialPanel;
    private JogoPanel jogoPanel;
    
    
    /*
    Modelos:
     */
    private Jogador jogador, jogador2;
    private Scanner leitor;
    private PrintWriter escrever;

    //Construtor
    public Tela(InicialPanel inicialPanel, JogoPanel jogoPanel) {

        this.inicialPanel = inicialPanel;
        this.jogoPanel = jogoPanel;

        this.jogador = new Jogador();
        this.jogador2 = new Jogador();

        setSize(Util.LARGURA, Util.ALTURA);
        setResizable(false);
        //setExtendedState(6);
        setLocationRelativeTo(null);
        setTitle("JAvatar Quiz");
        setIconImage(new ImageIcon("Arquivos\\Imagens\\Telas\\icone.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Sons.tocar(Util.MUSICA_TEMA);
        add(inicialPanel).setBounds(0, 0, Util.LARGURA, Util.ALTURA);
        add(jogoPanel).setBounds(0, 0, Util.LARGURA, Util.ALTURA);

        setVisible(true);
    }

    public InicialPanel getInicialPanel() {
        return inicialPanel;
    }

    public void setInicialPanel(InicialPanel inicialPanel) {
        this.inicialPanel = inicialPanel;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public JogoPanel getJogoPanel() {
        return jogoPanel;
    }

    public void setJogoPanel(JogoPanel jogoPanel) {
        this.jogoPanel = jogoPanel;
    }

    public Scanner getLeitor() {
        return leitor;
    }

    public PrintWriter getEscrever() {
        return escrever;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

}
