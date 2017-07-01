/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordena;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Henrique & Eldair
 */


//Classe que faz os desenhos das barras
public class Paint extends JPanel {

    int a[];
    int atemp[];
    int c;
    int ctemp;
    int x;
    int xtemp;
    int y;
    int ytemp;
    ArrayList<Bar> ar = new ArrayList<>();
    Graphics g;

    public ArrayList<Bar> getAr() {
        return ar;
    }

    public Graphics getG() {
        return g;
    }

    //Metodo que recebe as caracteristicas da barras
    public JPanel paint(int alt[], int compr, int posx, int posy) {
        a = alt;
        c = compr;
        x = posx;
        y = posy;
        eraseAll();
        paint();
        return this;
    }

    //Método que desenha as barras
    public void paint() {
        g = JOrdena.jPanelGraf.getGraphics();
        for (int m = 0; m < a.length; m++) {
            Bar b = new Bar();
            g.fillRect(x, y - a[m], c, a[m]);
            g.drawString("" + a[m], x - 1, y + 10);
            b.setAltura(a[m]);
            b.setComprimento(c);
            b.setX(x);
            b.setY(y);
            x += 25;
            ar.add(b);
        }
    }

    //Metodo que apaga todas as barras
    private void eraseAll() {
        g = JOrdena.jPanelGraf.getGraphics();
        g.clearRect(0, 0, JOrdena.jPanelGraf.getWidth(), JOrdena.jPanelGraf.getHeight());
        ar.clear();
    }

    //Metodo que redesenha as barras
    public void rePaint() {
        g = JOrdena.jPanelGraf.getGraphics();
        g.clearRect(0, 0, JOrdena.jPanelGraf.getWidth(), JOrdena.jPanelGraf.getHeight());
        ArrayList<Bar> artemp = new ArrayList<>();
        int compr = 10;
        int posx = 0;
        int posy = 150;
        for (int i = 0; i < ar.size(); i++) {
            Bar b = new Bar();
            b.setAltura(ar.get(i).getAltura());
            b.setComprimento(compr);
            b.setX(posx += 25);
            b.setY(posy);
            artemp.add(b);
        }
        ar = artemp;
        for (int i = 0; i < artemp.size(); i++) {
            g.fillRect(artemp.get(i).getX(), artemp.get(i).getY() - artemp.get(i).getAltura(), artemp.get(i).getComprimento(), artemp.get(i).getAltura());
            g.drawString("" + artemp.get(i).getAltura(), artemp.get(i).getX() - 1, artemp.get(i).getY() + 10);
        }
    }

    //Movimenta a barra para baixo
    void moveBarDown(int i) {
        for (int j = 0; j <= 120; j++) {
            g.setColor(Color.gray);
            g.clearRect(ar.get(i).getX()-2, ar.get(i).getY() - ar.get(i).getAltura(), ar.get(i).getComprimento() + 10, ar.get(i).getAltura() + 10);
            ar.get(i).setY(ar.get(i).getY() + 1);
            if (j == 120) {
                g.setColor(Color.black);
            }
            g.fillRect(ar.get(i).getX(), ar.get(i).getY() - ar.get(i).getAltura(), ar.get(i).getComprimento(), ar.get(i).getAltura());
            g.drawString("" + ar.get(i).getAltura(), ar.get(i).getX() - 1, ar.get(i).getY() + 10);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Paint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Movimenta a barra para cima
    void moveBarUp(int i) {
        for (int j = 0; j <= 120; j++) {
            g.setColor(Color.gray);
            g.clearRect(ar.get(i).getX()-2, ar.get(i).getY() - ar.get(i).getAltura(), ar.get(i).getComprimento() + 10, ar.get(i).getAltura() + 10);
            ar.get(i).setY(ar.get(i).getY() - 1);
            if (j == 120) {
                g.setColor(Color.black);
            }
            g.fillRect(ar.get(i).getX(), ar.get(i).getY() - ar.get(i).getAltura(), ar.get(i).getComprimento(), ar.get(i).getAltura());
            g.drawString("" + ar.get(i).getAltura(), ar.get(i).getX() - 1, ar.get(i).getY() + 10);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Paint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Movimenta a barra para a esquerda
    void moveBarLeft(int de, int para) {
        for (; para < ar.get(de).getX();) {
            g.setColor(Color.gray);
            g.clearRect(ar.get(de).getX()-2, ar.get(de).getY() - ar.get(de).getAltura(), ar.get(de).getComprimento() + 10, ar.get(de).getAltura() + 10);
            ar.get(de).setX(ar.get(de).getX() - 1);
            g.fillRect(ar.get(de).getX(), ar.get(de).getY() - ar.get(de).getAltura(), ar.get(de).getComprimento(), ar.get(de).getAltura());
            g.drawString("" + ar.get(de).getAltura(), ar.get(de).getX() - 1, ar.get(de).getY() + 10);
            try {
                Thread.sleep(8);
            } catch (InterruptedException ex) {
                Logger.getLogger(Paint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Movimenta a barra para a direita
    void moveBarRight(int de, int para) {
        for (; ar.get(de).getX() < para;) {
            g.setColor(Color.gray);
            g.clearRect(ar.get(de).getX()-2, ar.get(de).getY() - ar.get(de).getAltura(), ar.get(de).getComprimento() + 10, ar.get(de).getAltura() + 10);
            ar.get(de).setX(ar.get(de).getX() + 1);
            if (ar.get(de).getX() == para) {
                g.setColor(Color.black);
            }
            g.fillRect(ar.get(de).getX(), ar.get(de).getY() - ar.get(de).getAltura(), ar.get(de).getComprimento(), ar.get(de).getAltura());
            g.drawString("" + ar.get(de).getAltura(), ar.get(de).getX() - 1, ar.get(de).getY() + 10);
            try {
                Thread.sleep(8);
            } catch (InterruptedException ex) {
                Logger.getLogger(Paint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Desenha a marcação no metodo Bubble para o usuario acompanhar em qual das barras metodo de ordenação está
    void searchBubble(int i) {
        g.setColor(Color.red);
        if (i < ar.size() - 1) {
            g.drawString("v", ar.get(i).getX(), 20);
            g.drawString("v", ar.get(i + 1).getX(), 20);
        }
        g.setColor(Color.black);
    }

    //Desenha a marcação para o usuario acompanhar em qual das barras metodo de ordenação está
    void search(int i) {
        g.setColor(Color.red);
        g.drawString("v", ar.get(i).getX(), 20);
        g.setColor(Color.black);
    }
    
    //Desenha a marcação para o usuario acompanhar em qual das barras metodo de ordenação está
    void search(int i, Color color) {
        g.setColor(color);
        g.drawString("v", ar.get(i).getX(), 20);
        g.setColor(Color.black);
    }

    //Limpa a marcação
    void clearSearch() {
        g.clearRect(25, 10, JOrdena.jPanelGraf.getWidth(), 10);
    }
}
