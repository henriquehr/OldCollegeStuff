package Control;

import Graphic.GNeuron;
import Graphic.GSynapse;
import NeuralNetwork.Neuron;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

/**
 * @author Henrique
 */
public class GUIMain extends JFrame {

    private GNeuron neurons[] = new GNeuron[40];
    private GSynapse connections[] = new GSynapse[429];
    private Neuron n[] = new Neuron[40];
    private JPanel jpFundo;
    private JPanel jpFrente;
    private JScrollPane jsp;
    private JButton jb;
    private int size = 30;
    private ThreadMouseWheel thmw;
    private int sizeW;
    private int sizeH;
    private Thread th;
    private Thread th2;
    private Runnable rn;
    private Runnable rn2;
    private Dimension tamOrig;

    public GUIMain() {
        for (int i = 0; i < 40; i++) {
            if (i < 10) {
                n[i] = new Neuron(6, 10, (float) Math.round(Math.random() * 100) / 100.0f, 1);
            } else if (i < 30) {
                n[i] = new Neuron(10, 20, (float) Math.round(Math.random() * 100) / 100.0f, 2);
            } else if (i < 40) {
                n[i] = new Neuron(20, 10, (float) Math.round(Math.random() * 100) / 100.0f, 3);
            }
        }
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(640, 480));
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(null);
        this.addComponets();
        this.addMouseL();
        this.addWindowL();
        this.threadDraw();
        this.setVisible(true);

//        this.jsp.setSize((int) (this.getSize().getWidth() - 6), (int) (this.getSize().getHeight() - 75));
//        this.jpFrente.setPreferredSize(new Dimension((int) (jsp.getSize().getWidth()), (int) (jsp.getSize().getHeight())));
//        this.jpFundo.setSize((int) this.getSize().getWidth(), (int) this.getSize().getHeight());
        for (int i = 0; i < this.neurons.length; i++) {
            this.neurons[i] = new GNeuron();
            this.neurons[i].setId(i);
            this.neurons[i].setBias(n[i].getBias());
        }
        for (int i = 0; i < this.connections.length; i++) {
            this.connections[i] = new GSynapse();
            this.connections[i].setId(i);
            this.connections[i].setWeight(Math.round(Math.random() * 100) / 100.0);
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                tamOrig = jpFrente.getSize();
                sizeH = (int) getSize().getHeight();
                sizeW = (int) getSize().getWidth();
            }
        });
    }

    public static void main(String[] args) {
        new GUIMain();
    }

    private void calcPosition(Neuron neurons[], int size) {
        Arrays.parallelSort(neurons, new Comparator<Neuron>() {

            @Override
            public int compare(Neuron o1, Neuron o2) {
                return o1.getLayer() > o2.getLayer() ? 1 : -1;
            }
        });

        ArrayList<Integer> ar = new ArrayList<>();
        for (Neuron neuron : neurons) {
            if (ar.size() > neuron.getLayer() - 1) {
                ar.set(neuron.getLayer() - 1, ar.get(neuron.getLayer() - 1) + 1);
            } else {
                ar.add(neuron.getLayer() - 1, 1);
            }
        }

        int qtdLayer = ar.size();
        int segH = (sizeW / qtdLayer) / 2;
        int segV = sizeH;
        int meio = ar.get(0) / 2;

        int y = (segV / meio) - (ar.get(0) / 2) - size, x = segH;
        int layerAnt = 1;
        this.size = size;
        for (int i = 0; i < neurons.length; i++) {
            if (neurons[i].getLayer() != layerAnt) {
                layerAnt = neurons[i].getLayer();
                meio = ar.get(layerAnt - 1) / 2;
                x += (layerAnt + segH * 2);
                y = (segV / meio) - (ar.get(0) / 2) - size;
            }
            this.neurons[i].setLayer(layerAnt);
            this.neurons[i].setSizeX(size);
            this.neurons[i].setSizeY(size);
            this.neurons[i].setLocation(x, y);
            y += size + ar.get(layerAnt - 1) / meio;
        }
        int i = -1;
        for (GNeuron neuron1 : this.neurons) {
            i++;
            if (i > connections.length) {
                break;
            }
            this.connections[i].setNeuronFrom(neuron1);
            neuron1.addGsOut(this.connections[i]);
            for (int j = 0; j < this.neurons.length; j++) {
                if (this.connections[i].getNeuronFrom().getLayer() + 1 == neurons[j].getLayer()) {
                    this.connections[i].setNeuronTo(this.neurons[j]);
                    this.neurons[j].addGsIn(this.connections[i]);
                    i++;
                    if (i >= connections.length) {
                        break;
                    }
                    this.connections[i].setNeuronFrom(neuron1);
                    neuron1.addGsOut(this.connections[i]);
                }
            }
        }
    }

    private void calcPositionZoom(GNeuron GNeurons[], int size, int dir) {
        for (GNeuron GNeuron : GNeurons) {
            GNeuron.setSizeX(size);
            GNeuron.setSizeY(size);
            GNeuron.setPosX((GNeuron.getPosX() + dir) - (dir / 2));
            GNeuron.setPosY((GNeuron.getPosY() + dir) - (dir / 2));
        }
    }

    private void drawNeuron(GNeuron n) {
        n.setup(n.getX(), n.getY(), n.getSizeX(), n.getSizeY(), Color.black, Color.white, this.getWidth(), this.getHeight());
        jpFrente.add(n);
    }

    private void drawLine(GSynapse c) {
        c.setup(new Color((int) (Math.random() * 253), (int) (Math.random() * 252), (int) (Math.random() * 252)));
        jpFrente.add(c);
    }

    public final void threadDraw() {
        rn = new Runnable() {
            @Override
            public void run() {
                for (GNeuron neuron : neurons) {
                    drawNeuron(neuron);
                }
            }
        };
        rn2 = new Runnable() {
            @Override
            public void run() {
                for (GSynapse connection : connections) {
                    drawLine(connection);
                }
            }
        };
    }

    public void draw() {
        th = new Thread(rn);
        th.start();
        try {
            th.join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        th2 = new Thread(rn2);
        th2.start();
        try {
            th2.join();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        jpFrente.repaint();
    }

    private void addWindowL() {
        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                try {
                    for (GNeuron gNeuron : neurons) {
                        gNeuron.setLimitDown((int) jpFrente.getSize().getHeight());
                        gNeuron.setLimitRight((int) jpFrente.getSize().getWidth());
                    }
                    jpFrente.setPreferredSize(tamOrig);
                    jpFrente.revalidate();
                } catch (Exception ex) {
                }
            }
        });
    }

    private void addComponets() {
        jpFundo = new JPanel();
        jpFundo.setBackground(null);
        jpFundo.setLayout(new BorderLayout());
//        jpFundo.setLayout(null);

        jb = new JButton("Load File");
        jb.setLocation(1, 1);
        jb.setSize(100, 30);
        jb.setVisible(true);
        jb.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    size = 30;
                    calcPosition(n, size);
                    draw();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        jpFrente = new JPanel();
        jpFrente.setBackground(null);
        jpFrente.setName("frente");
//        jpFrente.setLayout(new BorderLayout());
        jpFrente.setLayout(null);
        jpFrente.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                try {
                    for (GNeuron gNeuron : neurons) {
                        gNeuron.setLimitDown((int) jpFrente.getSize().getHeight());
                        gNeuron.setLimitRight((int) jpFrente.getSize().getWidth());
                        
                        tamOrig = jpFrente.getSize();
                    }
                } catch (Exception ex) {
                }
            }
        });

        jsp = new JScrollPane();
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jsp.setWheelScrollingEnabled(true);
//        jsp.setLayout(null);

        JPanel jpUp = new JPanel();
        jpUp.setLayout(new BoxLayout(jpUp, BoxLayout.LINE_AXIS));
        jpUp.add(jb);

        this.jsp.setViewportView(jpFrente);
        this.jpFundo.add(jpUp, BorderLayout.NORTH);
        this.jpFundo.add(jsp, BorderLayout.CENTER);
        this.setContentPane(jpFundo);
    }

    class ThreadMouseWheel {

        private MouseWheelEvent e;

        public void run() {
            if (e.isControlDown()) {
                int direcao = e.getWheelRotation();
                size -= direcao > 0 ? +2 : -2;
                if (size > 100) {
                    size = 100;
                    return;
                }
                if (size < 10) {
                    size = 10;
                    return;
                }
                calcPositionZoom(neurons, size, direcao);
                for (GNeuron gNeuron : neurons) {
                    gNeuron.setup((int) gNeuron.getPosX(), (int) gNeuron.getPosY(), gNeuron.getSizeX(), gNeuron.getSizeY(), gNeuron.getColor(), gNeuron.getTextC(), getWidth(), getHeight());
                }
            }
        }

        public void setE(MouseWheelEvent e) {
            this.e = e;
        }

    }

    private void addMouseL() {
        thmw = new ThreadMouseWheel();
        jpFrente.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e) {
                thmw.setE(e);
                thmw.run();
            }
        });
    }

}
