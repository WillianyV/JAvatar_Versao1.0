/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.JogoPanel;
import view.Mensagens;
import Util.Util;
import view.Tela;

/**
 *
 * @author willi
 */
public class Desenhar extends Thread {

    private JogoPanel jogoPanel;
    private Tela tela;

    public Desenhar(Tela tela) {
        this.jogoPanel = tela.getJogoPanel();
        this.tela = tela;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 / 5);
                if (jogoPanel.getPersonagem1().getHp() <= 0) {
                    jogoPanel.getPersonagem1().animacaoPerdeu();
                    tela.getJogoPanel().atualizarBarraVida(0, 1);
                    Util.flagPerdeu = true;

                } else if (jogoPanel.getPersonagem2().getHp() <= 0) {
                    jogoPanel.getPersonagem2().animacaoPerdeu();
                    tela.getJogoPanel().atualizarBarraVida(0, 2);
                    Util.flagGanhou = true;
                }
                GameLoop.draw(tela);

                jogoPanel.getPersonagem1().animaAtaque();
                jogoPanel.getPersonagem2().animaAtaque();

                if (Util.flagAtacandoPoder1) {
                    
                    jogoPanel.getPersonagem1().elementosAtaques();
                    if (jogoPanel.colide(jogoPanel.getPersonagem1().getAtaqueSprite(), jogoPanel.getPersonagem2().getPersonagem(), 
                            Util.DIREITA)) {
                        Util.flagFerido2 = true;
                        jogoPanel.getPersonagem1().inicializarElementoAtaque();
                    }
                }
                if (Util.flagAtacandoPoder2) {
                    jogoPanel.getPersonagem2().elementosAtaques();
                    if (jogoPanel.colide(jogoPanel.getPersonagem2().getAtaqueSprite(), jogoPanel.getPersonagem1().getPersonagem(), Util.ESQUERDA)) {
                        Util.flagFerido = true;
                        jogoPanel.getPersonagem2().inicializarElementoAtaque();
                    }
                }

                if (Util.flagFerido2) {
                    jogoPanel.getPersonagem2().ferido();
                    if (jogoPanel.getPersonagem2().getPersonagem().aparencia == 28) {
                        int dano = jogoPanel.getPersonagem2().getDefesa() - (jogoPanel.getPersonagem1().getAtaque());
                        if (dano < 0) {
                            jogoPanel.getPersonagem2().setHp(jogoPanel.getPersonagem2().getHp() + dano);
                            jogoPanel.atualizarBarraVida(jogoPanel.getPersonagem2().getHp(), 2);
                        } else {
                            jogoPanel.getPersonagem2().setDefesa(dano);
                        }
                        jogoPanel.getPersonagem1().setAtaque(0);
                        jogoPanel.getPersonagem2().setDefesa(0);
                    }
                }
                if (Util.flagFerido) {
                    jogoPanel.getPersonagem1().ferido();
                    if (jogoPanel.getPersonagem1().getPersonagem().aparencia == 10) {
                        int danoP1 = jogoPanel.getPersonagem1().getDefesa() - (jogoPanel.getPersonagem2().getAtaque() * Util.multiplicador);
                       
                        if (danoP1 < 0) {
                            jogoPanel.getPersonagem1().setHp(jogoPanel.getPersonagem1().getHp() + danoP1);
                            jogoPanel.atualizarBarraVida(jogoPanel.getPersonagem1().getHp(), 1);
                        } else {
                            //jogoPanel.getPersonagem1().setDefesa(danoP1);
                        }
//                        jogoPanel.getPersonagem2().setAtaque(0);
//                        jogoPanel.getPersonagem1().setDefesa(0);
                        System.out.println(jogoPanel.getPersonagem1().getHp() + "HP");
                    }
                }
                if (Util.flagAtacandoPoder2) {

                    if (jogoPanel.colideAtaques(jogoPanel.getPersonagem2().getAtaqueSprite(), jogoPanel.getPersonagem1().getPersonagem(), Util.ESQUERDA)) {

                        if (jogoPanel.getPersonagem1().getDefesa() > 0) {
                            //jogoPanel.getPersonagem1().setDefesa(jogoPanel.getPersonagem1().getDefesa() - 10);

                            Util.flagDefedendo = true;
                            Util.flagAtacandoP1 = true;
                        }

                    }

                }

                if (Util.flagDefedendo) {

                    if (jogoPanel.colide(jogoPanel.getPersonagem1().getAtaqueSprite(), jogoPanel.getPersonagem2().getAtaqueSprite(), Util.DIREITA)) {
                        int calcular = (jogoPanel.getPersonagem1().getDefesa() - (10 * Util.multiplicador));
                        Util.flagAtacandoP1 = false;
                        jogoPanel.getPersonagem1().inicializarElementoAtaque();
                        jogoPanel.getPersonagem2().inicializarElementoAtaque();

                        Util.flagAtacandoPoder2 = false;
                        if(calcular >= 0 ){
                            jogoPanel.getPersonagem1().setDefesa(calcular);
                            
                        }else{
                            Util.flagFerido = true;
                        }
                        Util.flagDefedendo = false;
                        Util.flagAtacandoPoder1 = false;
                        jogoPanel.getPersonagem1().getPersonagem().aparencia = 10;
                        
                    }
                }

            } catch (InterruptedException ex) {
                Mensagens.messagemErro("Erro ao desenhar jogo!\n" + ex);
            }
        }
    }

}
