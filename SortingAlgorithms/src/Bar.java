/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordena;

/**
 *
 * @author Henrique & Eldair
 */


//Classe que guarda as carateristicas das barras(altura, comprimento, posição X e Y)
public class Bar {
    private int altura;
    private int comprimento;
    private int x;
    private int y;

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getComprimento() {
        return comprimento;
    }

    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
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
