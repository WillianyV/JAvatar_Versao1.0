/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.Util;
import java.awt.Graphics;
import java.awt.Graphics2D;
import view.JogoPanel;
import view.Tela;

/**
 *
 * @author willi
 */
public class GameLoop {

    public static void draw(Tela tela) {
        JogoPanel jogoPanel = tela.getJogoPanel();

        Graphics2D graficos = (Graphics2D) jogoPanel.getGraphics();
        Graphics desenharBuffer = jogoPanel.getBuffer().getGraphics();
        graficos.drawImage(jogoPanel.getBuffer(), 0, 0, null);

        if (jogoPanel.isVisible()) {
            //imagem de fundo
            desenharBuffer.drawImage(jogoPanel.getImagemDeFundo().getImage(), 0, 0, null);

            //mostrar textos
            desenharBuffer.setFont(Util.chewy);
            desenharBuffer.setColor(Util.color);
            desenharBuffer.drawString("Pontuação: " + jogoPanel.getPersonagem1().getPontos(), 10, 365);
            desenharBuffer.drawString("Defesa: " + jogoPanel.getPersonagem1().getDefesa(), 200, 365);
            desenharBuffer.drawString(tela.getJogador().getNick() , 140, 195);
            

            //icones do jogadores
            desenharBuffer.drawImage(jogoPanel.getJogador1Icon().getImage(), 130, 40, jogoPanel);
            desenharBuffer.drawImage(jogoPanel.getJogador2Icon().getImage(), 1110, 40, jogoPanel);
            //sprites
            //barra HP
            desenharBuffer.drawImage(jogoPanel.getHpJogador1Sprite().sprites[jogoPanel.getHpJogador1Sprite().aparencia],
                    jogoPanel.getHpJogador1Sprite().posX,
                    jogoPanel.getHpJogador1Sprite().posY, null);
            desenharBuffer.drawImage(jogoPanel.getHpJogador2Sprite().sprites[jogoPanel.getHpJogador2Sprite().aparencia],
                    jogoPanel.getHpJogador2Sprite().posX,
                    jogoPanel.getHpJogador2Sprite().posY, null);
            //icones de VS
            desenharBuffer.drawImage(jogoPanel.getVsEsquerda().getImage(), 510, 335, jogoPanel);
            desenharBuffer.drawImage(jogoPanel.getVsDireita().getImage(), 800, 335, jogoPanel);
            //sprites
            //Jogador 1
            desenharBuffer.drawImage(jogoPanel.getPersonagem1().getPersonagem().sprites[jogoPanel.getPersonagem1().getPersonagem().aparencia],
                    jogoPanel.getPersonagem1().getPersonagem().posX,
                    jogoPanel.getPersonagem1().getPersonagem().posY, null);
            if (Util.flagAtacandoPoder1) {
                desenharBuffer.drawImage(jogoPanel.getPersonagem1().getAtaqueSprite().sprites[jogoPanel.getPersonagem1().getAtaqueSprite().aparencia],
                        jogoPanel.getPersonagem1().getAtaqueSprite().posX,
                        jogoPanel.getPersonagem1().getAtaqueSprite().posY, null);
            }
            //Jogador 2
            desenharBuffer.drawImage(jogoPanel.getPersonagem2().getPersonagem().sprites[jogoPanel.getPersonagem2().getPersonagem().aparencia],
                    jogoPanel.getPersonagem2().getPersonagem().posX,
                    jogoPanel.getPersonagem2().getPersonagem().posY, null);
            if (Util.flagAtacandoPoder2) {
                desenharBuffer.drawImage(jogoPanel.getPersonagem2().getAtaqueSprite().sprites[jogoPanel.getPersonagem2().getAtaqueSprite().aparencia],
                        jogoPanel.getPersonagem2().getAtaqueSprite().posX,
                        jogoPanel.getPersonagem2().getAtaqueSprite().posY, null);
            }
            //Panel de Perguntas          
            jogoPanel.getPerguntasPanel().repaint();
            //botoes de atacar edefender
            jogoPanel.getAtacarButton().repaint();
            jogoPanel.getDefenderButton().repaint();
            //Imagens das perguntas
            if (jogoPanel.getImagemPerguntas() != null) {
                desenharBuffer.drawImage(jogoPanel.getImagemPerguntas().getImage(), 1020, 40, null);
            }

            jogoPanel.getSairIcon().repaint();
            //
            if (Util.flagGanhou) {
                desenharBuffer.drawImage(jogoPanel.getVcGanhouIcon().getImage(), 600, 400, null);
            } else if (Util.flagPerdeu) {
                desenharBuffer.drawImage(jogoPanel.getVcPerdeuIcon().getImage(), 600, 400, null);
            }

        } else if (tela.getInicialPanel().isVisible()) {
            desenharBuffer.drawImage(tela.getInicialPanel().getVcGanhouIcon().getImage(), 600, 400, null);
            desenharBuffer.drawImage(tela.getInicialPanel().getBackIcon().getImage(), 0, 0, null);
            //tela.getInicialPanel().getImagemDeFundojLabel().repaint();
            tela.getInicialPanel().getCustomizarBotaoLabel().repaint();
            tela.getInicialPanel().getJogarBotaoLabel().repaint();
            tela.getInicialPanel().getSomLabel().repaint();
            tela.getInicialPanel().getInfoLabel().repaint();
            tela.getInicialPanel().getSobreLabel().repaint();

            if (tela.getInicialPanel().getSomPanel().isVisible()) {
                tela.getInicialPanel().getSomPanel().repaint();
            } else if (tela.getInicialPanel().getSobrePanel().isVisible()) {
                tela.getInicialPanel().getSobrePanel().repaint();
            } else if (tela.getInicialPanel().getInfoPanel().isVisible()) {
                tela.getInicialPanel().getInfoPanel().repaint();
            } else if (tela.getInicialPanel().getCustomizarPanel().isVisible()) {
                tela.getInicialPanel().getCustomizarPanel().repaint();
            }

            if ((tela.getJogador().getIcone() != null)) {
                if (tela.getJogador().getIcone().equals(Util.AANG)) {
                    tela.getInicialPanel().getMostrarAangLabel().repaint();
                } else if (tela.getJogador().getIcone().equals(Util.KATARA)) {
                    tela.getInicialPanel().getMostrarKataraLabel().repaint();
                } else if (tela.getJogador().getIcone().equals(Util.SOKKA)) {
                    tela.getInicialPanel().getMostrarSokkaLabel().repaint();
                } else if (tela.getJogador().getIcone().equals(Util.TOPH)) {
                    tela.getInicialPanel().getMostrarTophLabel().repaint();
                } else if (tela.getJogador().getIcone().equals(Util.ZUCO)) {
                    tela.getInicialPanel().getMostrarZucoLabel().repaint();
                }
                tela.getInicialPanel().getNomeJLabel().repaint();
            }
            

        }

    }

}
