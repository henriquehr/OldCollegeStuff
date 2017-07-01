/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordena;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Henrique & Eldair
 */


//Classe com os metodos de ordenção
public class SortTypes extends TimerTask implements Runnable {

    ArrayList<Bar> ar;
    Paint p = new Paint();
    String type;
    public Integer compara = 0;
    public Integer troca = 0;

    SortTypes(ArrayList<Bar> ar, String type, Paint p) {
        this.type = type;
        this.ar = ar;
        this.p = p;
    }

    SortTypes() {
    }
    Thread tr;

    //Metodo que inicia o segundo thread
    public void start() {
        type = (String) JOrdena.jComboBoxType.getSelectedItem();
        tr = new Thread(new SortTypes(ar, type, p));
        tr.start();
    }

    //Pausa o segundo thread
    public void pauseThread() {
        try {
            tr.stop();//Não deve ser usado, mas foi o unico jeito.
            p.rePaint();
        } catch (Exception e) {
        };
    }

    public JPanel paint(int alt[], int compr, int posx, int posy) {
        p.paint(alt, compr, posx, posy);
        return p;
    }

    //Primeiro metodo a ser chamdo ao iniciar o segundo thread
    public void run() {
        p.rePaint();
        p.rePaint();
        p.rePaint();//colocando só um tava bugando o desenho, maldito java
        ar = p.getAr();
        switch (type) {
            case "Bubble sort":
                BubbleSort(ar, p);
                Thread.currentThread().interrupt();
                break;
            case "Insertion sort":
                InsertionSort(ar, p);
                Thread.currentThread().interrupt();
                break;
            case "Selection sort":
                SelectionSort(ar, p);
                Thread.currentThread().interrupt();
                break;
        }
    }

    //Ordenação Bolha
    public void BubbleSort(ArrayList<Bar> vet, Paint p) {
        try {
            JOrdena.jList1.setSelectedIndex(0);
            Thread.sleep(500);
            JOrdena.jList1.setSelectedIndex(1);
            Thread.sleep(500);
            JOrdena.jList1.setSelectedIndex(2);
            Thread.sleep(500);
            JOrdena.jList1.setSelectedIndex(3);
            Thread.sleep(500);
            boolean houveTroca = true;
            while (houveTroca) {
                houveTroca = false;
                JOrdena.jList1.setSelectedIndex(4);
                Thread.sleep(600);
                for (int i = 0; i < vet.size() - 1; i++, Thread.sleep(500), p.searchBubble(i)) {
                    JOrdena.jList1.setSelectedIndex(4);
                    Thread.sleep(500);
                    p.searchBubble(i);
                    JOrdena.jList1.setSelectedIndex(5);
                    Thread.sleep(500);
                    compara++;
                    JOrdena.jLabelCompara.setText(compara.toString());
                    if (vet.get(i).getAltura() > vet.get(i + 1).getAltura()) {
                        Bar variavelAuxiliar = vet.get(i + 1);
                        Thread.sleep(600);
                        JOrdena.jList1.setSelectedIndex(6);
                        Thread.sleep(200);
                        p.moveBarDown(i + 1);
                        troca++;
                        JOrdena.jLabelTroca.setText(troca.toString());
                        Thread.sleep(600);
                        JOrdena.jList1.setSelectedIndex(7);
                        Thread.sleep(200);
                        int a = vet.get(i).getX();
                        p.moveBarRight(i, vet.get(i + 1).getX());
                        troca++;
                        JOrdena.jLabelTroca.setText(troca.toString());
                        Thread.sleep(600);
                        JOrdena.jList1.setSelectedIndex(8);
                        vet.set(i + 1, vet.get(i));
                        vet.set(i, variavelAuxiliar);
                        Thread.sleep(200);
                        p.moveBarLeft(i, a);
                        p.moveBarUp(i);
                        houveTroca = true;
                    }
                    p.clearSearch();
                }

                Thread.sleep(500);
                JOrdena.jList1.setSelectedIndex(13);
                p.clearSearch();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(SortTypes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Ordenação por Inserção
    private void InsertionSort(ArrayList<Bar> vet, Paint p) {
        int i, j, a = 0;
        for (j = 1; j < vet.size(); j++) {
            try {
                i = j - 1;
                JOrdena.jList1.setSelectedIndex(0);
                Thread.sleep(200);
                JOrdena.jList1.setSelectedIndex(1);
                Thread.sleep(200);
                JOrdena.jList1.setSelectedIndex(2);
                Thread.sleep(200);
                p.search(j);
                Bar temp = vet.get(j);
                Thread.sleep(500);
                JOrdena.jList1.setSelectedIndex(4);
                Thread.sleep(500);
                p.moveBarDown(j);
                int pass = 0;
                Thread.sleep(500);
                JOrdena.jList1.setSelectedIndex(5);
                Thread.sleep(500);
                compara++;
                JOrdena.jLabelCompara.setText(compara.toString());
                while ((i >= 0) && (temp.getAltura() < vet.get(i).getAltura())) {
                    JOrdena.jList1.setSelectedIndex(5);
                    Thread.sleep(500);
                    pass = 1;
                    p.search(i,Color.blue);
                    JOrdena.jList1.setSelectedIndex(6);
                    Thread.sleep(500);
                    a = vet.get(i).getX();
                    p.moveBarRight(i, vet.get(i).getX()+25);
                    vet.set(i + 1, vet.get(i));
                    troca++;
                    JOrdena.jLabelTroca.setText(troca.toString());
                    JOrdena.jList1.setSelectedIndex(6);
                    Thread.sleep(500);
                    i--;
                    JOrdena.jList1.setSelectedIndex(7);
                    Thread.sleep(50);
                    compara++;
                    JOrdena.jLabelCompara.setText(compara.toString());
                }
                vet.set(i + 1, temp);
                JOrdena.jList1.setSelectedIndex(9);
                Thread.sleep(500);
                if (pass == 0) {
                    p.moveBarUp(j);
                } else {
                    p.moveBarLeft(i + 1, a);
                    p.moveBarUp(i + 1);
                    troca++;
                    JOrdena.jLabelTroca.setText(troca.toString());
                }
                p.clearSearch();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(SortTypes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOrdena.jList1.setSelectedIndex(11);
    }

    //Ordenação por seleção
    private void SelectionSort(ArrayList<Bar> vet, Paint p) {
        try {
            int index_min;
            JOrdena.jList1.setSelectedIndex(0);
            Thread.sleep(300);
            Bar aux;
            JOrdena.jList1.setSelectedIndex(1);
            for (int i = 0; i < vet.size(); i++) {
                Thread.sleep(600);
                index_min = i;
                p.clearSearch();
                p.search(i);
                JOrdena.jList1.setSelectedIndex(2);
                Thread.sleep(300);
                for (int j = i + 1; j < vet.size(); j++) {
                    JOrdena.jList1.setSelectedIndex(2);
                    Thread.sleep(600);
                    JOrdena.jList1.setSelectedIndex(3);
                    p.search(j, Color.BLUE);
                    Thread.sleep(600);
                    compara++;
                    JOrdena.jLabelCompara.setText(compara.toString());
                    if (vet.get(j).getAltura() < vet.get(index_min).getAltura()) {
                        JOrdena.jList1.setSelectedIndex(4);
                        Thread.sleep(600);
                        index_min = j;
                        JOrdena.jList1.setSelectedIndex(5);
                        Thread.sleep(200);
                        JOrdena.jList1.setSelectedIndex(6);
                        Thread.sleep(200);
                        p.clearSearch();
                        p.search(j);
                        Thread.sleep(600);
                    }
                }

                JOrdena.jList1.setSelectedIndex(9);
                Thread.sleep(200);
                if (index_min != i) {
                    int y = vet.get(i).getX();
                    JOrdena.jList1.setSelectedIndex(9);
                    Thread.sleep(600);
                    aux = vet.get(index_min);
                    JOrdena.jList1.setSelectedIndex(10);
                    Thread.sleep(200);
                    p.moveBarDown(index_min);
                    troca++;
                    JOrdena.jLabelTroca.setText(troca.toString());
                    troca++;
                    JOrdena.jLabelTroca.setText(troca.toString());
                    int x = vet.get(index_min).getX();
                    p.moveBarRight(index_min, x+25);
                    Thread.sleep(600);
                    JOrdena.jList1.setSelectedIndex(11);
                    Thread.sleep(200);
                    p.moveBarDown(i);
                    troca++;
                    JOrdena.jLabelTroca.setText(troca.toString());
                    Thread.sleep(200);
                    p.moveBarRight(i, x);
                    p.moveBarUp(i);
                    Thread.sleep(200);
                    JOrdena.jList1.setSelectedIndex(12);
                    p.moveBarLeft(index_min, y);
                    p.moveBarUp(index_min);
                    vet.set(index_min, vet.get(i));
                    Thread.sleep(200);
                    vet.set(i, aux);
                }
            }
            JOrdena.jList1.setSelectedIndex(15);
        } catch (InterruptedException ex) {
            Logger.getLogger(SortTypes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}