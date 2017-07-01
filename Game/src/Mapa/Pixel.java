
package trabalhofinal.Mapa;

import java.util.ArrayList;

/**
 *
 * @author Henrique
 */
public class Pixel {
    private int x;
    private int y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object pixel){
        Pixel p = (Pixel) pixel;
        return p.getX() == this.getX() && p.getY() == this.getY();
    }
    
    public boolean contains(ArrayList<Pixel> a){
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).equals(this)){
                return true;
            }
        }
        return false;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
