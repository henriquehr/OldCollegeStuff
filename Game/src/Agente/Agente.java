package trabalhofinal.Agente;

import java.util.ArrayList;
import javax.media.opengl.GL;
import trabalhofinal.BolasAssassinasNervosas.BolasAssassinasNervosas;
import trabalhofinal.Mapa.Pixel;

/**
 *
 * @author Henrique
 */
public class Agente {

    private int posx = 0;
    private int posy = 0;
    private int lateral = 3;
    private int setor = 0;

    public void drawAgente(GL gl, int posx, int posy) {
        gl.glLineWidth(1);
        gl.glColor4f(1, 0, 0, 0);
        gl.glBegin(gl.GL_QUADS);
        gl.glVertex2f(posx - lateral, posy + lateral);
        gl.glVertex2f(posx + lateral, posy + lateral);
        gl.glVertex2f(posx + lateral, posy - lateral);
        gl.glVertex2f(posx - lateral, posy - lateral);
        gl.glEnd();
    }

    public boolean mesmaPos(BolasAssassinasNervosas[] ban, int to) {
        for (int i = 0; i < to; i++) {
            if (this.getPosCima()>= ban[i].getPosBaixo() + 1 && this.getPosBaixo()<= ban[i].getPosCima() - 1 && this.getPosDireita() >= ban[i].getPosEsquerda() + 1 && this.getPosEsquerda() <= ban[i].getPosDireita() - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaFinal(ArrayList<Pixel> setor) {
        Pixel p = new Pixel(posx, posy);
        return p.contains(setor);
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getPosCima() {
        return posy + lateral;
    }

    public int getPosDireita() {
        return posx + lateral;
    }

    public int getPosBaixo() {
        return posy - lateral;
    }

    public int getPosEsquerda() {
        return posx - lateral;
    }

    public int getLateral() {
        return lateral;
    }

    public void setLateral(int lateral) {
        this.lateral = lateral;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

}
