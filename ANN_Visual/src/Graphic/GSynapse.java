package Graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 *
 * @author Henrique
 */
public class GSynapse extends JComponent {
    
    private int id;
    
    private GNeuron neuronFrom;
    private GNeuron neuronTo;
    private double weight;
    
    private Color c;
    private Graphics2D g;
    
    public GSynapse(GNeuron neuronTo, GNeuron neuronFrom) {
        this.neuronTo = neuronTo;
        this.neuronFrom = neuronFrom;
    }
    
    public GSynapse() {
        
    }
    
    @Override
    public void paint(Graphics g) {
        this.setSize(neuronFrom.getX() + neuronTo.getX() + neuronFrom.getSizeX() + neuronTo.getSizeX(), neuronFrom.getY() + neuronTo.getY() + neuronFrom.getSizeY() + neuronTo.getSizeY());;
        this.g = (Graphics2D) g;
        this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.g.setStroke(new BasicStroke(1));
        this.g.setColor(c);
        this.g.drawLine(neuronFrom.getX() + (neuronFrom.getSizeX() / 2), neuronFrom.getY() + (neuronFrom.getSizeY() / 2), neuronTo.getX() + (neuronTo.getSizeX() / 2), neuronTo.getY() + (neuronTo.getSizeY() / 2));
        this.g.setFont(new Font("Arial", Font.PLAIN, neuronFrom.getSizeX() / 2));
        this.g.drawString(String.valueOf(weight), (neuronFrom.getX() + neuronTo.getX()) / 2, ((neuronFrom.getY() + neuronTo.getY()) / 2) + (neuronFrom.getSizeY() / 2));
    }
    
    public void setup(Color c) {
        if (neuronFrom != null && neuronTo != null) {
            this.setSize(neuronFrom.getX() + neuronTo.getX(), neuronFrom.getY() + neuronTo.getY());
            this.setLocation(0, 0);
            this.c = c;
            this.setBackground(null);
        }
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Integer getXFrom() {
        return neuronFrom.getX();
    }
    
    public Integer getYFrom() {
        return neuronFrom.getY();
    }
    
    public Integer getXTo() {
        return neuronTo.getX();
    }
    
    public Integer getYTo() {
        return neuronTo.getY();
    }
    
    public Color getC() {
        return c;
    }
    
    public void setC(Color c) {
        this.c = c;
    }
    
    public Graphics2D getG() {
        return g;
    }
    
    public void setG(Graphics2D g) {
        this.g = g;
    }
    
    public GNeuron getNeuronFrom() {
        return neuronFrom;
    }
    
    public void setNeuronFrom(GNeuron neuronFrom) {
        this.neuronFrom = neuronFrom;
    }
    
    public GNeuron getNeuronTo() {
        return neuronTo;
    }
    
    public void setNeuronTo(GNeuron neuronTo) {
        this.neuronTo = neuronTo;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
}
