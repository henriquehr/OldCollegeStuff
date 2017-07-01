package Graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

/**
 *
 * @author Henrique
 */
public class GNeuron extends JComponent {

    private int id;
    private int limitUp;
    private int limitDown;
    private int limitLeft;
    private int limitRight;
    private Integer sizeX = 0;
    private Integer sizeY = 0;
    private Integer posX = 0;
    private Integer posY = 0;
    private ArrayList<GSynapse> gsIn;
    private ArrayList<GSynapse> gsOut;
    private PopupMenu pm;

    private int layer;
    private float bias;

    private Color c;
    private Color textC;
    private Graphics2D g;
    private boolean pressed = false;

    public GNeuron() {
        this.gsIn = new ArrayList<>();
        this.gsOut = new ArrayList<>();
        MouseListener ml = new MouseListener();
        this.addMouseListener(ml);
        this.addMouseMotionListener(ml);
        this.addMenu();
        ToolTipManager.sharedInstance().setInitialDelay(0);
    }

    private void move(MouseEvent e) {
        if ((posY >= limitUp && posY <= limitDown) && (posX >= limitLeft && posX <= limitRight)) {
            this.posX = e.getX() + posX - (sizeX / 2);
            this.posY = e.getY() + posY - (sizeY / 2);
        }
        if (posY <= limitUp) {
            this.posY = limitUp + 1;
        }
        if (posY >= limitDown) {
//            posY = limitDown;
        }
        if (posX <= limitLeft) {
            this.posX = limitLeft + 1;
        }
        if (posX >= limitRight) {
//            posX = limitRight;
        }
        if (posY >= limitDown - (sizeY / 2)) {
            this.posY = e.getY() + posY - (sizeY / 2);
            limitDown += sizeY / 2;
            this.getParent().setPreferredSize(new Dimension((int) getParent().getSize().getWidth(), (int) getParent().getSize().getHeight() + (sizeY / 2)));
            this.getParent().revalidate();
        }
        if (posX >= limitRight - (sizeX / 2)) {
            this.posX = e.getX() + posX - (sizeX / 2);
            limitRight += sizeX / 2;
            this.getParent().setPreferredSize(new Dimension((int) getParent().getSize().getWidth() + (sizeX / 2), (int) getParent().getSize().getHeight()));
            this.getParent().revalidate();
        }
        ((JPanel) (this.getParent())).scrollRectToVisible(new Rectangle(getPosX(), getPosY(), getWidth(), getHeight()));
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
//        this.setSize(sizeX, sizeY);
        this.setLocation(posX, posY);
        this.g = (Graphics2D) g;
        this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.g.setColor(c);
        this.g.fillOval(0, 0, sizeX, sizeY);
        this.g.setColor(textC);
        this.g.setFont(new Font("Arial", Font.PLAIN, sizeX / 3));
        this.g.drawString(String.valueOf(id), sizeX / 5, (sizeY / 2) + 3);
    }

    public void setup(int x, int y, int sizeX, int sizeY, Color c, Color textC, int limitRight, int limitDown) {
        this.limitDown = limitDown - 105;
        this.limitRight = limitRight - 85;
        this.limitUp = 5;
        this.limitLeft = 5;
        this.setSize(sizeX, sizeY);
        this.setLocation(x, y);
        this.posX = x;
        this.posY = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.c = c;
        this.textC = textC;
        this.setBackground(null);
    }

    public ArrayList<GSynapse> getGsIn() {
        return gsIn;
    }

    public void addGsIn(GSynapse gsIn) {
        this.gsIn.add(gsIn);
    }

    public ArrayList<GSynapse> getGsOut() {
        return gsOut;
    }

    public void addGsOut(GSynapse gsOut) {
        this.gsOut.add(gsOut);
    }

    public float getBias() {
        return bias;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public Color getColor() {
        return this.c;
    }

    public void setSizeX(Integer sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(Integer sizeY) {
        this.sizeY = sizeY;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBias(float bias) {
        this.bias = bias;
    }

    public Color getTextC() {
        return textC;
    }

    public void setTextC(Color textC) {
        this.textC = textC;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public GNeuron getThis() {
        return this;
    }

    public void setLimitDown(int limitDown) {
        this.limitDown = limitDown - 50;
    }

    public void setLimitRight(int limitRight) {
        this.limitRight = limitRight - 50;
    }

    private void addMenu() {
        pm = new PopupMenu();
        MenuItem mi = new MenuItem("Properties");
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIProperties(getThis()).setVisible(true);
            }
        });
        pm.add(mi);
        this.add(pm);
    }

    class MouseListener extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            setToolTipText("<html>Id: <b>" + getId() + "</b><br>Layer: <b>" + getLayer() + "</b><br>Bias: <b>" + getBias() + "</b></html>");
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                pm.show(getThis(), 20, 15);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            pressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressed = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                move(e);
            }
        }
    }
}
