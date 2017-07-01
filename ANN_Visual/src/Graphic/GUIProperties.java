package Graphic;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Henrique
 */
public class GUIProperties extends JDialog {

    GUIProperties(GNeuron source) {
        this.setFocusable(true);
        this.setSize(200, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Neuron: " + source.getId() + ", Layer: " + source.getLayer());
        this.setLayout(null);

        StringBuilder sb = new StringBuilder();
        sb.append("<b>Layer</b>: <b>").append(String.valueOf(source.getLayer())).append("</b><br><b>Bias</b>: <b>").append(String.valueOf(source.getBias())).append("</b>");
        sb.append("<br><b>Weights In</b>: ");
        for (GSynapse gSynapse : source.getGsIn()) {
            if (gSynapse.getNeuronFrom() != null && gSynapse.getNeuronTo() != null) {
                sb.append("<br>").append(" <i>From ").append(String.valueOf(gSynapse.getNeuronFrom().getId())).append("</i>: <b>").append(String.valueOf(gSynapse.getWeight())).append("</b>");
            }
        }
        sb.append("<br><b>Weights Out</b>: ");
        for (GSynapse gSynapse : source.getGsOut()) {
            if (gSynapse.getNeuronFrom() != null && gSynapse.getNeuronTo() != null) {
                sb.append("<br>").append(" <i>To ").append(String.valueOf(gSynapse.getNeuronTo().getId())).append("</i>: <b>").append(String.valueOf(gSynapse.getWeight())).append("</b>");
            }
        }

        JEditorPane display = new JEditorPane("text/html", "");
        JScrollPane scroll = new JScrollPane(display);
        JPanel middlePanel = new JPanel();
        display.setEditable(false);
        display.setBackground(middlePanel.getBackground());
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setWheelScrollingEnabled(true);
        middlePanel.add(scroll);
        middlePanel.setLocation(0, -5);
        middlePanel.setSize((int) this.getSize().getWidth() - 7, (int) this.getSize().getHeight() - 23);
        scroll.setSize((int) middlePanel.getSize().getWidth(), (int) middlePanel.getSize().getHeight());
        display.setSize((int) middlePanel.getSize().getWidth() - 18, (int) middlePanel.getSize().getHeight());
        scroll.setPreferredSize(new Dimension((int) middlePanel.getSize().getWidth(), (int) middlePanel.getSize().getHeight() - 4));
        scroll.setBorder(null);
        this.add(middlePanel);
        display.setText(sb.toString());
        display.setCaretPosition(0);
        KeyListener kl = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        display.addKeyListener(kl);
        middlePanel.addKeyListener(kl);
        scroll.addKeyListener(kl);
        this.addKeyListener(kl);
    }

}
