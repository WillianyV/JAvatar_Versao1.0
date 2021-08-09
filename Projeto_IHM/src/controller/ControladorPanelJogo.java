/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Util.Util;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import model.Sons;
import view.InicialPanel;
import view.JogoPanel;
import view.Tela;

/**
 *
 * @author willi
 */
public class ControladorPanelJogo {

    private Tela tela;
    private JogoPanel jogoPanel;
    private ControladorJogo controladorJogo;
    private Color color;
    private ControladorPanelInicial controladorPanelInicial;

    public ControladorPanelJogo(Tela tela, ControladorPanelInicial controladorPanelInicial) {
        this.tela = tela;
        this.controladorPanelInicial = controladorPanelInicial;

        jogoPanel = tela.getJogoPanel();

        controladorJogo = new ControladorJogo();
        color = new Color(227, 229, 224);

        jogoPanel.getPerguntasPanel().getProximoButton().addActionListener(controladorJogo);
        jogoPanel.getAtacarButton().addActionListener(controladorJogo);
        jogoPanel.getDefenderButton().addActionListener(controladorJogo);

        jogoPanel.getPerguntasPanel().getAlternativaALabel().addMouseListener(controladorJogo);
        jogoPanel.getPerguntasPanel().getAlternativaBLabel().addMouseListener(controladorJogo);
        jogoPanel.getPerguntasPanel().getAlternativaCLabel().addMouseListener(controladorJogo);
        jogoPanel.getPerguntasPanel().getAlternativaDLabel().addMouseListener(controladorJogo);
        jogoPanel.getSairIcon().addMouseListener(controladorJogo);

    }

    private class ControladorJogo implements ActionListener, MouseListener, MouseMotionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(Util.flagGanhou || Util.flagPerdeu)) {

                if (e.getSource() == jogoPanel.getPerguntasPanel().getProximoButton()) {
                    jogoPanel.getPerguntasPanel().inicializarPergunta();
                    jogoPanel.getPerguntasPanel().getAlternativaALabel().setBackground(Color.WHITE);
                    jogoPanel.getPerguntasPanel().getAlternativaBLabel().setBackground(Color.WHITE);
                    jogoPanel.getPerguntasPanel().getAlternativaCLabel().setBackground(Color.WHITE);
                    jogoPanel.getPerguntasPanel().getAlternativaDLabel().setBackground(Color.WHITE);
                    jogoPanel.getPerguntasPanel().getCoretoGifjLabel().setVisible(false);
                    jogoPanel.getPerguntasPanel().getErradoGifjLabel().setVisible(false);

                } else if (e.getSource() == jogoPanel.getAtacarButton()) {
                    if (jogoPanel.getPersonagem1().getPontos() > 0) {
                        Util.flagAtacandoP1 = true;
                        jogoPanel.getPersonagem1().setAtaque(jogoPanel.getPersonagem1().getPontos());
                        jogoPanel.getPersonagem1().setPontos(0);
                    }
                } else if (e.getSource() == jogoPanel.getDefenderButton()) {
                    
                    if (!(jogoPanel.getPersonagem1().getPontos() == 0)) {
                        jogoPanel.getPersonagem1().setDefesa(jogoPanel.getPersonagem1().getPontos() + jogoPanel.getPersonagem1().getDefesa());
                        jogoPanel.getPersonagem1().setPontos(0);
                    }
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (!(Util.flagGanhou || Util.flagPerdeu)) {
                if (e.getSource() == jogoPanel.getPerguntasPanel().getAlternativaALabel()) {
                    if (jogoPanel.getPerguntasPanel().getAlternativaBLabel().getBackground() == color
                            || jogoPanel.getPerguntasPanel().getAlternativaCLabel().getBackground() == color
                            || jogoPanel.getPerguntasPanel().getAlternativaDLabel().getBackground() == color) {
                    } else {
                        jogoPanel.getPerguntasPanel().getAlternativaALabel().setBackground(color);
                        if (jogoPanel.getPerguntasPanel().correcao(jogoPanel.getPerguntasPanel().getAlternativaALabel().getText())) {
                            jogoPanel.getPersonagem1().pontuacao();
                        } else {
                            Util.flagAtacandoP2 = true;
                            jogoPanel.getPersonagem2().setAtaque(10);
                        }
                    }

                } else if (e.getSource() == jogoPanel.getPerguntasPanel().getAlternativaBLabel()) {
                    if (jogoPanel.getPerguntasPanel().getAlternativaALabel().getBackground() == color
                            || jogoPanel.getPerguntasPanel().getAlternativaCLabel().getBackground() == color
                            || jogoPanel.getPerguntasPanel().getAlternativaDLabel().getBackground() == color) {
                    } else {
                        jogoPanel.getPerguntasPanel().getAlternativaBLabel().setBackground(color);
                        if (jogoPanel.getPerguntasPanel().correcao(jogoPanel.getPerguntasPanel().getAlternativaBLabel().getText())) {
                            jogoPanel.getPersonagem1().pontuacao();
                        } else {

                            Util.flagAtacandoP2 = true;
                            jogoPanel.getPersonagem2().setAtaque(10);

                        }
                    }
                } else if (e.getSource() == jogoPanel.getPerguntasPanel().getAlternativaCLabel()) {
                    if (jogoPanel.getPerguntasPanel().getAlternativaALabel().getBackground() == color
                            || jogoPanel.getPerguntasPanel().getAlternativaBLabel().getBackground() == color
                            || jogoPanel.getPerguntasPanel().getAlternativaDLabel().getBackground() == color) {
                    } else {
                        jogoPanel.getPerguntasPanel().getAlternativaCLabel().setBackground(color);
                        if (jogoPanel.getPerguntasPanel().correcao(jogoPanel.getPerguntasPanel().getAlternativaCLabel().getText())) {
                            jogoPanel.getPersonagem1().pontuacao();
                        } else {

                            Util.flagAtacandoP2 = true;
                            jogoPanel.getPersonagem2().setAtaque(10);

                        }
                    }

                } else if (e.getSource() == jogoPanel.getPerguntasPanel().getAlternativaDLabel()) {
                    if (jogoPanel.getPerguntasPanel().getAlternativaALabel().getBackground() == color
                            || jogoPanel.getPerguntasPanel().getAlternativaBLabel().getBackground() == color
                            || jogoPanel.getPerguntasPanel().getAlternativaCLabel().getBackground() == color) {
                    } else {
                        jogoPanel.getPerguntasPanel().getAlternativaDLabel().setBackground(color);
                        if (jogoPanel.getPerguntasPanel().correcao(jogoPanel.getPerguntasPanel().getAlternativaDLabel().getText())) {
                            jogoPanel.getPersonagem1().pontuacao();
                        } else {

                            Util.flagAtacandoP2 = true;
                            jogoPanel.getPersonagem2().setAtaque(10);

                        }
                    }
                }
            }
            if (e.getSource() == jogoPanel.getSairIcon()) {
                jogoPanel.setVisible(false);
                tela.getInicialPanel().setVisible(true);

            }

        }

        @Override
        public void mousePressed(MouseEvent me) {

        }

        @Override
        public void mouseReleased(MouseEvent me) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

            if (e.getSource() == jogoPanel.getSairIcon()) {
                jogoPanel.getSairIcon().setCursor(new Cursor(Cursor.HAND_CURSOR));
                jogoPanel.getSairIcon().setToolTipText("Voltar ao menu");

            }
        }

        @Override
        public void mouseExited(MouseEvent me) {

        }

        @Override
        public void mouseDragged(MouseEvent me) {

        }

        @Override
        public void mouseMoved(MouseEvent me) {

        }

    }
}
