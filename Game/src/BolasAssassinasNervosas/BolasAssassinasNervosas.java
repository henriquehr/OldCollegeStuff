package trabalhofinal.BolasAssassinasNervosas;

import javax.media.opengl.GL;

/**
 *
 * @author Henrique
 */
public class BolasAssassinasNervosas {

    private float posx = -40;
    private float posy = -0;
    private int lateral = 4;
    private boolean indo = false;
    private float velocidade;

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public boolean indo() {
        return indo;
    }

    public void setIndo(boolean indo) {
        this.indo = indo;
    }

    public void drawBolas(GL gl, float posx, float posy) {
        gl.glLineWidth(1);
        gl.glColor4f(0, 0, 0, 0);
        int i;
        int sections = 20;

        float radius = lateral; //radius
        float twoPi = 2.0f * 3.14159f;

        gl.glBegin(gl.GL_TRIANGLE_FAN);
        gl.glVertex2f(posx, posy); // origin
        for (i = 0; i <= sections; i++) {
            gl.glVertex2d(radius * Math.cos(i * twoPi / sections) + posx, radius * Math.sin(i * twoPi / sections) + posy);
        }
        gl.glEnd();
    }

    public float getPosx() {
        return posx;
    }

    public void setPosx(float posx) {
        this.posx = posx;
    }

    public float getPosy() {
        return posy;
    }

    public void setPosy(float posy) {
        this.posy = posy;
    }

    public float getPosCima() {
        return posy + lateral;
    }

    public float getPosDireita() {
        return posx + lateral;
    }

    public float getPosBaixo() {
        return posy - lateral;
    }

    public float getPosEsquerda() {
        return posx - lateral;
    }

    public float getLateral() {
        return lateral;
    }

    public void setLateral(int lateral) {
        this.lateral = lateral;
    }

}
