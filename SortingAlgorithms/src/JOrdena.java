package ordena;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Henrique & Eldair
 * 
 * 
 * O código ta uma bagunça total, mas compilo e rodo então ta valendo
 */

//Classe que contem os elentos graficos, como, botões e campos de texto e as ações para os mesmos
public class JOrdena extends javax.swing.JFrame {

    //Metodo main que inicia a classe JOrdena
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JOrdena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JOrdena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JOrdena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JOrdena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JOrdena ord = new JOrdena();//intancia a classe JOrdena
                ord.setVisible(true);//mostra a janela
                ord.Help();
            }
        });
    }
    
    //Primeiro metodo a ser chamado ao instanciar a classe
    public JOrdena() {
        this.setTitle("Tipos de Ordenação");//Titulo da janela
        this.setLocationRelativeTo(null);//Posiciona a janela no centro da tela
        this.setResizable(false);//desabilita o botão de maximizar
        initComponents();
        resetList();
    }
    DefaultListModel modelo = new DefaultListModel();
    boolean rodando;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jFormattedTextFieldElementos = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList(modelo);
        jButtonStop = new javax.swing.JButton();
        jComboBoxNew = new javax.swing.JComboBox();
        jButtonNew = new javax.swing.JButton();
        jButtonStart = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelCompara = new javax.swing.JLabel();
        jLabelTroca = new javax.swing.JLabel();
        jPanelGraf = new javax.swing.JPanel();
        jTextAreaAjuda = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Nº de elementos:");

        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bubble sort", "Insertion sort", "Selection sort" }));
        jComboBoxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTypeaction(evt);
            }
        });

        jFormattedTextFieldElementos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextFieldElementos.setText("10");
        jFormattedTextFieldElementos.setToolTipText("Máximo: 15");
        jFormattedTextFieldElementos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                actionElementos(evt);
            }
        });

        jScrollPane1.setBorder(null);

        jList1.setVisibleRowCount(10);
        jList1.setBackground(getBackground());
        jList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jList1);

        jButtonStop.setText("Parar");
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        jComboBoxNew.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aleatório", "Crescente", "Decrescente" }));

        jButtonNew.setText("Novo");
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jButtonStart.setText("Começar");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jLabel2.setText("Comparações:");

        jLabel3.setText("Trocas:");

        jLabelCompara.setText("0");

        jLabelTroca.setText("0");

        jTextAreaAjuda.setBackground(getBackground());
        jTextAreaAjuda.setColumns(20);
        jTextAreaAjuda.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextAreaAjuda.setRows(5);
        jTextAreaAjuda.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextAreaAjuda.setEnabled(false);

        javax.swing.GroupLayout jPanelGrafLayout = new javax.swing.GroupLayout(jPanelGraf);
        jPanelGraf.setLayout(jPanelGrafLayout);
        jPanelGrafLayout.setHorizontalGroup(
            jPanelGrafLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
            .addGroup(jPanelGrafLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTextAreaAjuda, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
        );
        jPanelGrafLayout.setVerticalGroup(
            jPanelGrafLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
            .addGroup(jPanelGrafLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTextAreaAjuda, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelGraf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jFormattedTextFieldElementos, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jComboBoxType, 0, 109, Short.MAX_VALUE)
                            .addComponent(jButtonStop, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jComboBoxNew, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonStart, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelCompara))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelTroca)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldElementos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonNew)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonStart)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelCompara))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelTroca))
                .addGap(18, 18, 18)
                .addComponent(jButtonStop)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelGraf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-822)/2, (screenSize.height-372)/2, 822, 372);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTypeaction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTypeaction
        resetList();
        st.compara = 0;
        st.troca = 0;
        jLabelCompara.setText("0");
        jLabelTroca.setText("0");
        rodando = false;
        st.pauseThread();
   }//GEN-LAST:event_jComboBoxTypeaction

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopActionPerformed
        resetList();
        st.pauseThread();
        rodando = false;
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        if (jFormattedTextFieldElementos.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o campo Nº de elementos", "Aviso", JOptionPane.WARNING_MESSAGE);
            jFormattedTextFieldElementos.setText("0");
        }
        st.pauseThread();
        rodando = false;
        st.compara = 0;
        st.troca = 0;
        jLabelCompara.setText("0");
        jLabelTroca.setText("0");
        resetList();
        New();
    }//GEN-LAST:event_jButtonNewActionPerformed
    int alt[];

    //Metodo que cria a caracteristicas das barras
    private void New() {
        int qtd = Integer.parseInt(jFormattedTextFieldElementos.getText());
        alt = new int[qtd];
        for (int i = 0; i < qtd; i++) {
            alt[i] = (int) (1 + (Math.random() * 100));
        }
        switch (jComboBoxNew.getSelectedItem().toString()) {
            case "Crescente":
                for (int i = 1; i < alt.length; i++) {
                    int a = alt[i];
                    int j;
                    for (j = i - 1; j >= 0 && alt[j] > a; j--) {
                        alt[j + 1] = alt[j];
                        alt[j] = a;
                    }
                }
                break;
            case "Decrescente":
                for (int i = 1; i < alt.length; i++) {
                    int a = alt[i];
                    int j;
                    for (j = i - 1; j >= 0 && alt[j] < a; j--) {
                        alt[j + 1] = alt[j];
                        alt[j] = a;
                    }
                }
                break;
        }
        int compr = 10;
        int posx = 25;
        int posy = 150;
        paint(alt, compr, posx, posy);

    }
    SortTypes st = new SortTypes();

    public void paint(int alt[], int compr, int posx, int posy) {
        jPanelGraf.add(st.paint(alt, compr, posx, posy));
    }
    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        if (alt != null && rodando == false) {
            jTextAreaAjuda.setVisible(false);
            st.compara = 0;
            st.troca = 0;
            jLabelCompara.setText("0");
            jLabelTroca.setText("0");
            rodando = true;
            st.start();
        }
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void actionElementos(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_actionElementos
        if (jFormattedTextFieldElementos.getText().equals("")) {
        } else {
            try {
                if (Integer.parseInt(jFormattedTextFieldElementos.getText()) > 15) {
                    jFormattedTextFieldElementos.setText("15");
                    JOptionPane.showMessageDialog(null, "Máximo de elementos = 15", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Digite apenas números", "Aviso", JOptionPane.ERROR_MESSAGE);
                jFormattedTextFieldElementos.setText("0");
            }
        }
    }//GEN-LAST:event_actionElementos

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JComboBox jComboBoxNew;
    public static javax.swing.JComboBox jComboBoxType;
    private static javax.swing.JFormattedTextField jFormattedTextFieldElementos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabelCompara;
    public static javax.swing.JLabel jLabelTroca;
    public static javax.swing.JList jList1;
    public static javax.swing.JPanel jPanelGraf;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaAjuda;
    // End of variables declaration//GEN-END:variables

    private void resetList() {
        Object[] texto = sortType();
        modelo.clear();
        for (int i = 0; i < texto.length; i++) {
            modelo.addElement(texto[i]);
            jList1.setSelectionBackground(Color.pink);
        }
    }

    //Texto que é mostrado na JList
    private Object[] sortType() {
        if (jComboBoxType.getSelectedItem() == "Bubble sort") {
            Object[] text = new Object[]{"public static void bubbleSort (int[] vetor){",
                "   boolean houveTroca = true;",
                "       while (houveTroca) {",
                "           houveTroca = false;",
                "           for (int i = 0; i < (vetor.length)-1; i++){",
                "               if (vetor[i] > vetor[i+1]){",
                "                   int variavelAuxiliar = vetor[i+1];",
                "                   vetor[i+1] = vetor[i];",
                "                   vetor[i] = variavelAuxiliar;",
                "                   houveTroca = true;",
                "               }",
                "           }",
                "       }",
                "   }"};
            return text;
        }
        if (jComboBoxType.getSelectedItem() == "Insertion sort") {
            Object[] text = new Object[]{"public static void insertionSort(int[] array) {",
                "       int tmp,i,j;",
                "           for (j=1; j<array.length; j++) {",
                "               i =j - 1; ",
                "               tmp = array[j]; ",
                "               while ( (i>=0) && (tmp < array[i]) ) { ",
                "                   array[i+1] = array[i]; ",
                "                   i--; ",
                "               } ",
                "               array[i+1] = tmp;",
                "           }",
                "}"};
            return text;
        }

        if (jComboBoxType.getSelectedItem() == "Selection sort") {
            Object[] text = new Object[]{"public static void SelectionSort(int[] v) {",
                "       int index_min, aux;",
                "       for (int i = 0; i < v.length; i++) {",
                "           index_min = i;",
                "           for (int j = i + 1; j < v.length; j++) {",
                "               if (v[j] < v[index_min]) {",
                "                    index_min = j;",
                "               }",
                "           }",
                "           if (index_min != i) {",
                "               aux = v[index_min];",
                "               v[index_min] = v[i];",
                "               v[i] = aux;",
                "           }",
                "       }",
                "}"};
            return text;
        }

        return null;
    }

    //Texto que é mostrado na Ajuda
    private void Help() {
        jTextAreaAjuda.setText("\n\n                                   AJUDA    \n\n"
                + "             1 - Digite o número de Elementos;\n"
                + "             2 - Escolha Aleatório, Crescente ou Decrescente;\n"
                + "             3 - Clique em Novo;\n"
                + "             4 - Escolha o método de ordenação;\n"
                + "             5 - Clique em Começar;\n"
                + "             6 - Deposite R$100,00 na minha conta(opcional).");
    }
}
