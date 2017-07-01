/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.Mapa;

import java.util.ArrayList;
import javax.media.opengl.GL;

/**
 *
 * @author henrique
 */
public class Mapa {
    private ArrayList<Ponto> mp;

    public Mapa() {
        this.mp = new ArrayList<>();
    }

    public int pointsSize(){
        return mp.size();
    }
    
    public ArrayList<Ponto> getMapa() {
        return mp;
    }
    
    public void addPoint(Ponto p){
        mp.add(p);
    }
    
    public Ponto getPoint(int index){
        return mp.get(index);
    }
    
    public void drawMap(GL gl, int mode, float r, float g, float b){
        gl.glLineWidth(2);
        gl.glColor4f(r, g, b, 0);
        gl.glBegin(mode);
        for (int i = 0; i < mp.size(); i++) {
            gl.glVertex2f(mp.get(i).getPosx(), mp.get(i).getPosy());
        }
        gl.glEnd();
    }
    
}
