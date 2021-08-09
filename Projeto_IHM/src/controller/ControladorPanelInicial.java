/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Jogador;
import view.InicialPanel;
import view.Tela;
import Util.Util;
import model.Desenhar;
import model.Sons;
import view.JogoPanel;
import view.Mensagens;

/**
 *
 * @author willi
 */
public class ControladorPanelInicial {

    Tela tela;
    InicialPanel inicialPanel;
    Controlador controlador;
    Jogador jogador;
    JogoPanel jogoPanel;
    Desenhar desenhar;

    public ControladorPanelInicial(Tela tela) {
        this.tela = tela;
        this.inicialPanel = tela.getInicialPanel();
        this.jogador = tela.getJogador();
        this.jogoPanel = tela.getJogoPanel();
        
        desenhar = new Desenhar(tela);

        controlador = new Controlador();

        /*
        Visualizar paneis ao Clicar 
         */
        inicialPanel.getCustomizarBotaoLabel().addMouseListener(controlador);
        inicialPanel.getJogarBotaoLabel().addMouseListener(controlador);
        inicialPanel.getSomLabel().addMouseListener(controlador);
        inicialPanel.getInfoLabel().addMouseListener(controlador);
        inicialPanel.getSobreLabel().addMouseListener(controlador);

        /*
        Botões da tela inicial
         */
        //Botoões jogar
        //Botoões musica e efeitos
        inicialPanel.getMusicaToggleButton().addActionListener(controlador);
        inicialPanel.getEfeitosToggleButton().addActionListener(controlador);
        //Botões do personagem
        inicialPanel.getAangButton().addActionListener(controlador);
        inicialPanel.getKataraButton().addActionListener(controlador);
        inicialPanel.getSokkaButton().addActionListener(controlador);
        inicialPanel.getTophButton().addActionListener(controlador);
        inicialPanel.getZucoButton().addActionListener(controlador);

    }

    private class Controlador implements MouseListener, ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Botoões para escolher o personagem
            if (e.getSource() == inicialPanel.getAangButton()) {
                jogador.setNick(inicialPanel.getNickTextField().getText());
                jogador.setIcone(Util.AANG);
                //Personagem da tela
                personagensInivisiveis();
                inicialPanel.getMostrarAangLabel().setVisible(true);
                inicialPanel.getNomeJLabel().setText(inicialPanel.getNickTextField().getText());
                inicialPanel.getNomeJLabel().setVisible(true);
                paineisInvisiveis();
            }
            if (e.getSource() == inicialPanel.getKataraButton()) {
                jogador.setNick(inicialPanel.getNickTextField().getText());
                jogador.setIcone(Util.KATARA);
                //Personagem da tela
                personagensInivisiveis();
                inicialPanel.getMostrarKataraLabel().setVisible(true);
                inicialPanel.getNomeJLabel().setText(inicialPanel.getNickTextField().getText());
                inicialPanel.getNomeJLabel().setVisible(true);
                //Personagem da tela
                paineisInvisiveis();
            }
            if (e.getSource() == inicialPanel.getSokkaButton()) {
                jogador.setNick(inicialPanel.getNickTextField().getText());
                jogador.setIcone(Util.SOKKA);
                //Personagem da tela
                personagensInivisiveis();
                inicialPanel.getMostrarSokkaLabel().setVisible(true);
                inicialPanel.getNomeJLabel().setText(inicialPanel.getNickTextField().getText());
                inicialPanel.getNomeJLabel().setVisible(true);
                paineisInvisiveis();
            }
            if (e.getSource() == inicialPanel.getTophButton()) {
                jogador.setNick(inicialPanel.getNickTextField().getText());
                jogador.setIcone(Util.TOPH);
                //Personagem da tela
                personagensInivisiveis();
                inicialPanel.getMostrarTophLabel().setVisible(true);
                inicialPanel.getNomeJLabel().setText(inicialPanel.getNickTextField().getText());
                inicialPanel.getNomeJLabel().setVisible(true);
                paineisInvisiveis();
            }
            if (e.getSource() == inicialPanel.getZucoButton()) {
                jogador.setNick(inicialPanel.getNickTextField().getText());
                jogador.setIcone(Util.ZUCO);
                //Personagem da tela
                personagensInivisiveis();
                inicialPanel.getMostrarZucoLabel().setVisible(true);
                inicialPanel.getNomeJLabel().setText(inicialPanel.getNickTextField().getText());
                inicialPanel.getNomeJLabel().setVisible(true);
                paineisInvisiveis();
            }
            //Botões do panel de musica
            if (e.getSource() == inicialPanel.getMusicaToggleButton()) {
                if (inicialPanel.getMusicaToggleButton().isSelected()) {
                    inicialPanel.getMusicaToggleButton().setText("OFF");
                    Sons.pararDeTocar();
                } else {
                    inicialPanel.getMusicaToggleButton().setText("ON");
                    Sons.tocar(Util.MUSICA_TEMA);
                }
            }
            if (e.getSource() == inicialPanel.getEfeitosToggleButton()) {
                if (inicialPanel.getEfeitosToggleButton().isSelected()) {
                    inicialPanel.getEfeitosToggleButton().setText("OFF");
                    Util.efeitosSonoros = false;
                } else {
                    inicialPanel.getEfeitosToggleButton().setText("ON");
                    Util.efeitosSonoros = true;
                }
            }
            
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            /*
            MOSTRAR PAINEIS
             */
            //Customizar
            if (e.getSource() == inicialPanel.getCustomizarBotaoLabel()) {
                if (inicialPanel.getCustomizarPanel().isVisible()) {
                    paineisInvisiveis();
                } else {
                    paineisInvisiveis();
                    inicialPanel.getCustomizarPanel().setVisible(true);
                }
            }  //Jogar
            else if (e.getSource() == inicialPanel.getJogarBotaoLabel()) {
                if (!(jogador.getIcone() != null && jogador.getNick() != null)) {
                    Mensagens.mensagemExibir("Por favor degite o seu Nick e escolha o personagem.\n Click no botão customizar");
                } else {
                    jogoPanel.inicializar(Util.ESQUERDA, jogador.getIcone());
                    inicialPanel.setVisible(false);
                    iniciarJogo();
                    
                }
            } //Som
            else if (e.getSource() == inicialPanel.getSomLabel()) {
                if (inicialPanel.getSomPanel().isVisible()) {
                    paineisInvisiveis();
                } else {
                    paineisInvisiveis();
                    inicialPanel.getSomPanel().setVisible(true);
                }
            } //Info
            else if (e.getSource() == inicialPanel.getInfoLabel()) {
                if (inicialPanel.getInfoPanel().isVisible()) {
                    paineisInvisiveis();
                } else {
                    paineisInvisiveis();
                    inicialPanel.getInfoPanel().setVisible(true);
                }
            } //Sobre
            else if (e.getSource() == inicialPanel.getSobreLabel()) {
                if (inicialPanel.getSobrePanel().isVisible()) {
                    paineisInvisiveis();
                } else {
                    paineisInvisiveis();
                    inicialPanel.getSobrePanel().setVisible(true);
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent me) {

        }

        @Override
        public void mouseReleased(MouseEvent me) {

        }

        @Override
        public void mouseEntered(MouseEvent me) {

        }

        @Override
        public void mouseExited(MouseEvent me) {

        }

        public void iniciarJogo() {  
//            try {
//                desenhar.start();
//            } catch (Exception e) {
//                desenhar.resume();
//            }
            jogoPanel.setVisible(true);
            jogoPanel.getAtacarButton().setVisible(true);
            jogoPanel.getDefenderButton().setVisible(true);
            jogoPanel.getPerguntasPanel().setVisible(true);
        }

        public void paineisInvisiveis() {
            inicialPanel.getCustomizarPanel().setVisible(false);
            inicialPanel.getSomPanel().setVisible(false);
            inicialPanel.getSobrePanel().setVisible(false);
            inicialPanel.getInfoPanel().setVisible(false);
        }

        public void personagensInivisiveis() {
            inicialPanel.getMostrarAangLabel().setVisible(false);
            inicialPanel.getMostrarKataraLabel().setVisible(false);
            inicialPanel.getMostrarSokkaLabel().setVisible(false);
            inicialPanel.getMostrarTophLabel().setVisible(false);
            inicialPanel.getMostrarZucoLabel().setVisible(false);
        }
    }

    public Desenhar getDesenhar() {
        return desenhar;
    }

    public void setDesenhar(Desenhar desenhar) {
        this.desenhar = desenhar;
    }
    
}
