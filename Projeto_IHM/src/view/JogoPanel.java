/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Util.Util;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;
import model.Jogador;
import model.Personagem;
import model.Sprite;

/**
 *
 * @author willi
 */
public class JogoPanel extends JPanel {

    private ImageIcon jogador1Icon, jogador2Icon, imagemDeFundo, vsEsquerda, vsDireita, atacarIcon, vcPerdeuIcon, vcGanhouIcon;
    private static ImageIcon imagemPerguntas;
    private JLabel nomeLabel, nome2Label, defenderIcon, sairIcon;
    private PerguntasPanel perguntasPanel;

    private Personagem personagem1, personagem2;
    private boolean flagColidir = false;
    private JButton defenderButton, atacarButton;
    private Sprite hpJogador1Sprite, hpJogador2Sprite;

    private ArrayList<String> vsPersonagens;

    private BufferedImage buffer;

    public JogoPanel() {
        setLayout(null);
        buffer = new BufferedImage(Util.LARGURA, Util.ALTURA, BufferedImage.TYPE_INT_ARGB);

        vsPersonagens = new ArrayList<String>();

        vsPersonagens.add(Util.AANG);
        vsPersonagens.add(Util.KATARA);
        vsPersonagens.add(Util.SOKKA);
        vsPersonagens.add(Util.TOPH);
        vsPersonagens.add(Util.ZUCO);

        personagem1 = new Personagem(Util.AANG);
        personagem1.inicializarSprite();
        personagem2 = new Personagem(Util.TOPH);
        personagem2.setOrientacao(Util.DIREITA);
        personagem2.inicializarSprite();

        inicializarIconPersonagens(Util.AANG, Util.TOPH);

        try {
            hpJogador1Sprite = new Sprite("Arquivos\\Imagens\\Telas\\barra.png", 9, 171, 37, 20, 1, 344, 335);
            hpJogador2Sprite = new Sprite("Arquivos\\Imagens\\Telas\\barra.png", 10, 171, 37, 20, 1, 850, 335);
        } catch (IOException e) {
            Mensagens.messagemErro("Erro ao inicializar sprites da barra de vida.");
        }

        defenderButton = new JButton();
        defenderButton.setBackground(new Color(223, 200, 158));
        defenderButton.setIcon(new ImageIcon("Arquivos\\Imagens\\Telas\\defender.png"));
        defenderButton.setBorder(null);

        atacarButton = new JButton();
        atacarButton.setBackground(new Color(223, 200, 158));
        atacarButton.setIcon(new ImageIcon("Arquivos\\Imagens\\Telas\\atacar.png"));
        atacarButton.setBorder(null);

        vcPerdeuIcon = new ImageIcon("Arquivos\\Imagens\\Telas\\you_lose.png");
        vcGanhouIcon = new ImageIcon("Arquivos\\Imagens\\Telas\\you_win.png");
        sairIcon = new JLabel(new ImageIcon(("Arquivos\\Imagens\\Telas\\sair.png")));

        imagemDeFundo = new ImageIcon("Arquivos\\Imagens\\Telas\\MapaJogo.png");

        perguntasPanel = new PerguntasPanel();

        perguntasPanel.setVisible(false);
        atacarButton.setVisible(false);
        defenderButton.setVisible(false);

        add(sairIcon).setBounds(1250, 335, 37, 37);
        add(atacarButton).setBounds(37, 210, 145, 55);
        add(defenderButton).setBounds(203, 210, 145, 55);
        add(perguntasPanel).setBounds(440, 40, 485, 240);

        setSize(Util.LARGURA, Util.ALTURA);
        setVisible(false);
    }

    public void inicializar(String orientacao, String nomeLutador) {
        int index = sortearPersonagem2();

        personagem1.inicializarPersonagem(orientacao, nomeLutador);
        personagem1.inicializarSprite();
        personagem2.inicializarPersonagem(Util.DIREITA, vsPersonagens.get(index));
        personagem2.inicializarSprite();

        inicializarIconPersonagens(nomeLutador, vsPersonagens.get(index));

        personagem1.setHp(100);
        personagem2.setHp(100);
        personagem1.setPontos(0);
        personagem1.setDefesa(0);
        personagem1.setAtaque(0);
        atualizarBarraVida(100, 1);
        atualizarBarraVida(100, 2);
        Util.flagGanhou = false;
        Util.flagPerdeu = false;
        perguntasPanel.getPerguntasSorteadas().clear();

        perguntasPanel.inicializarPergunta();
        perguntasPanel.getAlternativaALabel().setBackground(Color.WHITE);
        perguntasPanel.getAlternativaBLabel().setBackground(Color.WHITE);
        perguntasPanel.getAlternativaCLabel().setBackground(Color.WHITE);
        perguntasPanel.getAlternativaDLabel().setBackground(Color.WHITE);
        perguntasPanel.getCoretoGifjLabel().setVisible(false);
        perguntasPanel.getErradoGifjLabel().setVisible(false);

    }

    public int sortearPersonagem2() {
        Random r = new Random();
        return r.nextInt(vsPersonagens.size());
    }

    public void inicializarIconPersonagens(String nomeP1, String nomeP2) {
        if (nomeP1.equals(Util.AANG)) {
            jogador1Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\aang.png");
            vsEsquerda = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_ang_left.png");
        } else if (nomeP1.equals(Util.KATARA)) {
            jogador1Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\katara.png");
            vsEsquerda = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_katara_left.png");
        } else if (nomeP1.equals(Util.SOKKA)) {
            jogador1Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\sokka.png");
            vsEsquerda = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_sokka_left.png");
        } else if (nomeP1.equals(Util.TOPH)) {
            jogador1Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\toph.png");
            vsEsquerda = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_toph_left.png");
        } else if (nomeP1.equals(Util.ZUCO)) {
            jogador1Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\zuco.png");
            vsEsquerda = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_zuko_left.png");
        }

        if (nomeP2.equals(Util.AANG)) {
            jogador2Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\aang.png");
            vsDireita = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_ang_right.png");
        } else if (nomeP2.equals(Util.KATARA)) {
            jogador2Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\katara.png");
            vsDireita = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_katara_right.png");
        } else if (nomeP2.equals(Util.SOKKA)) {
            jogador2Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\sokka.png");
            vsDireita = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_sokka_right.png");
        } else if (nomeP2.equals(Util.TOPH)) {
            jogador2Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\toph.png");
            vsDireita = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_toph_right.png");
        } else if (nomeP2.equals(Util.ZUCO)) {
            jogador2Icon = new ImageIcon("Arquivos\\Imagens\\Telas\\zuco.png");
            vsDireita = new ImageIcon("Arquivos\\Imagens\\Telas\\vs_zuko_right.png");
        }
    }

    //verificar o mvc com Rycharlisson
    public boolean colide(Sprite sprite1, Sprite sprite2, String orientacao) {
        Rectangle r1;
        Rectangle r2;
        if (orientacao.equals(Util.DIREITA)) {
            r1 = new Rectangle(sprite1.posX, sprite1.posY, sprite1.width, sprite1.height);
            r2 = new Rectangle(sprite2.posX + 80, sprite2.posY, sprite2.width, sprite2.height);
        } else {
            r1 = new Rectangle(sprite1.posX, sprite1.posY, sprite1.width, sprite1.height);
            r2 = new Rectangle(sprite2.posX - 80, sprite2.posY, sprite2.width, sprite2.height);
        }

        if (r1.intersects(r2)) {
            return true;
        }

        return false;
    }

    public boolean colideAtaques(Sprite sprite1, Sprite sprite2, String orientacao) {
        Rectangle r1;
        Rectangle r2;
        if (orientacao.equals(Util.DIREITA)) {
            r1 = new Rectangle(sprite1.posX, sprite1.posY, sprite1.width, sprite1.height);
            r2 = new Rectangle(sprite2.posX - 80, sprite2.posY, sprite2.width, sprite2.height);
        } else {
            r1 = new Rectangle(sprite1.posX, sprite1.posY, sprite1.width, sprite1.height);
            r2 = new Rectangle(sprite2.posX + 160, sprite2.posY, sprite2.width, sprite2.height);
        }

        if (r1.intersects(r2)) {
            return true;
        }

        return false;
    }

    public boolean colideDefesa(Sprite sprite1, Sprite sprite2) {
        Rectangle r1 = new Rectangle(sprite1.posX, sprite1.posY, sprite1.width, sprite1.height);
        Rectangle r2 = new Rectangle(sprite2.posX, sprite2.posY, sprite2.width, sprite2.height);

        if (r1.intersects(r2)) {
            return true;
        }

        return false;
    }

    public void atualizarBarraVida(int hp, int personagem) {
        if (personagem == 1) {
            if (hp == 100) {
                hpJogador1Sprite.aparencia = 9;
            } else if (hp == 90) {
                hpJogador1Sprite.aparencia = 8;
            } else if (hp == 80) {
                hpJogador1Sprite.aparencia = 7;
            } else if (hp == 70) {
                hpJogador1Sprite.aparencia = 6;
            } else if (hp == 60) {
                hpJogador1Sprite.aparencia = 5;
            } else if (hp == 50) {
                hpJogador1Sprite.aparencia = 4;
            } else if (hp == 40) {
                hpJogador1Sprite.aparencia = 3;
            } else if (hp == 30) {
                hpJogador1Sprite.aparencia = 2;
            } else if (hp == 20) {
                hpJogador1Sprite.aparencia = 1;
            } else if (hp == 10) {
                hpJogador1Sprite.aparencia = 1;
            } else if (hp == 0) {
                hpJogador1Sprite.aparencia = 0;
            }
        } else {
            if (hp == 100) {
                hpJogador2Sprite.aparencia = 10;
            } else if (hp == 90) {
                hpJogador2Sprite.aparencia = 11;
            } else if (hp == 80) {
                hpJogador2Sprite.aparencia = 12;
            } else if (hp == 70) {
                hpJogador2Sprite.aparencia = 13;
            } else if (hp == 60) {
                hpJogador2Sprite.aparencia = 14;
            } else if (hp == 50) {
                hpJogador2Sprite.aparencia = 15;
            } else if (hp == 40) {
                hpJogador2Sprite.aparencia = 16;
            } else if (hp == 30) {
                hpJogador2Sprite.aparencia = 17;
            } else if (hp == 20) {
                hpJogador2Sprite.aparencia = 18;
            } else if (hp == 10) {
                hpJogador2Sprite.aparencia = 18;
            } else if (hp == 0) {
                hpJogador2Sprite.aparencia = 19;
            }
        }
    }

    public static void carregarImagemPerguntas(String url) {
        imagemPerguntas = new ImageIcon(url);
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public ImageIcon getImagemDeFundo() {
        return imagemDeFundo;
    }

    public void setImagemDeFundo(ImageIcon imagemDeFundo) {
        this.imagemDeFundo = imagemDeFundo;
    }

    public PerguntasPanel getPerguntasPanel() {
        return perguntasPanel;
    }

    public void setPerguntasPanel(PerguntasPanel perguntasPanel) {
        this.perguntasPanel = perguntasPanel;
    }

    public static ImageIcon getImagemPerguntas() {
        return imagemPerguntas;
    }

    public static void setImagemPerguntas(ImageIcon imagemPerguntas) {
        JogoPanel.imagemPerguntas = imagemPerguntas;
    }

    public Personagem getPersonagem1() {
        return personagem1;
    }

    public void setPersonagem1(Personagem personagem1) {
        this.personagem1 = personagem1;
    }

    public ImageIcon getJogador1Icon() {
        return jogador1Icon;
    }

    public void setJogador1Icon(ImageIcon jogador1Icon) {
        this.jogador1Icon = jogador1Icon;
    }

    public ImageIcon getAtacarIcon() {
        return atacarIcon;
    }

    public void setAtacarIcon(ImageIcon atacarIcon) {
        this.atacarIcon = atacarIcon;
    }

    public JLabel getDefenderIcon() {
        return defenderIcon;
    }

    public void setDefenderIcon(JLabel defenderIcon) {
        this.defenderIcon = defenderIcon;
    }

    public ImageIcon getVsEsquerda() {
        return vsEsquerda;
    }

    public void setVsEsquerda(ImageIcon vsEsquerda) {
        this.vsEsquerda = vsEsquerda;
    }

    public Personagem getPersonagem2() {
        return personagem2;
    }

    public void setPersonagem2(Personagem personagem2) {
        this.personagem2 = personagem2;
    }

    public boolean isFlagColidir() {
        return flagColidir;
    }

    public void setFlagColidir(boolean flagColidir) {
        this.flagColidir = flagColidir;
    }

    public JButton getDefenderButton() {
        return defenderButton;
    }

    public void setDefenderButton(JButton defenderButton) {
        this.defenderButton = defenderButton;
    }

    public JButton getAtacarButton() {
        return atacarButton;
    }

    public void setAtacarButton(JButton atacarButton) {
        this.atacarButton = atacarButton;
    }

    public ImageIcon getJogador2Icon() {
        return jogador2Icon;
    }

    public void setJogador2Icon(ImageIcon jogador2Icon) {
        this.jogador2Icon = jogador2Icon;
    }

    public ImageIcon getVsDireita() {
        return vsDireita;
    }

    public void setVsDireita(ImageIcon vsDireita) {
        this.vsDireita = vsDireita;
    }

    public JLabel getNomeLabel() {
        return nomeLabel;
    }

    public void setNomeLabel(JLabel nomeLabel) {
        this.nomeLabel = nomeLabel;
    }

    public JLabel getNome2Label() {
        return nome2Label;
    }

    public void setNome2Label(JLabel nome2Label) {
        this.nome2Label = nome2Label;
    }

    public Sprite getHpJogador1Sprite() {
        return hpJogador1Sprite;
    }

    public void setHpJogador1Sprite(Sprite hpJogador1Sprite) {
        this.hpJogador1Sprite = hpJogador1Sprite;
    }

    public Sprite getHpJogador2Sprite() {
        return hpJogador2Sprite;
    }

    public void setHpJogador2Sprite(Sprite hpJogador2Sprite) {
        this.hpJogador2Sprite = hpJogador2Sprite;
    }

    public ImageIcon getVcPerdeuIcon() {
        return vcPerdeuIcon;
    }

    public void setVcPerdeuIcon(ImageIcon vcPerdeuIcon) {
        this.vcPerdeuIcon = vcPerdeuIcon;
    }

    public ImageIcon getVcGanhouIcon() {
        return vcGanhouIcon;
    }

    public void setVcGanhouIcon(ImageIcon vcGanhouIcon) {
        this.vcGanhouIcon = vcGanhouIcon;
    }

    public JLabel getSairIcon() {
        return sairIcon;
    }

    public void setSairIcon(JLabel sairIcon) {
        this.sairIcon = sairIcon;
    }

}
