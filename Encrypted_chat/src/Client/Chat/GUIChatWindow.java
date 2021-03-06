/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Chat;

import Client.Encryption.CifraDeCesar;
import Client.Encryption.SaveLoadChat.CarregadorDeChave;
import Client.Encryption.SaveLoadChat.Criptografia;
import Client.Encryption.SaveLoadChat.File_io;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Calendar;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

/**
 *
 * @author Henrique
 */
public class GUIChatWindow extends javax.swing.JFrame {

    private String user;
    private String myself;
    private Thread th;
    private CifraDeCesar cc;

    /**
     * Creates new form ChatWindow
     *
     *
     */
    public GUIChatWindow() {
        initComponents();
        jTextAreaSend.requestFocus();
        cc = new CifraDeCesar(616);
        th = new Thread();
    }

    public void setUser(String name) {
        this.user = name;
        this.setTitle("Chat com: " + name);
    }

    public String getUser() {
        return user;
    }

    void setText(String message, String from) {
//        message = cc.descriptografar(message);
        if (!message.isEmpty()) {
            Calendar c = Calendar.getInstance();
            String date = "\n" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + " - " + from + ": ";
            jTextAreaReceived.append(date + message + "\n");
            jTextAreaReceived.setCaretPosition(jTextAreaReceived.getDocument().getLength());
        }
    }

    void setMyself(String myself) {
        this.myself = myself;
    }

    private void sendMessage(String messageO) {
        if (!messageO.trim().isEmpty()) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    th = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            synchronized (GUIChatWindow.this) {
                                String message = cc.criptografar(messageO);
                                setText(messageO, myself);
                                ConnectClientToServer thr = new ConnectClientToServer();
                                thr.reconnect();
                                thr.sendMessage("write;" + user + ";" + myself + ";" + message);
                                thr.getMessage();
                            }
                        }
                    });
                    while (th.isAlive()) {
                    }
                    th.start();
                }
            }).start();

        }
    }

    private void saveFile() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        fc.setMultiSelectionEnabled(false);
        fc.setCurrentDirectory(new File("."));

        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                CarregadorDeChave cdc = new CarregadorDeChave();

                String chavePub = "chaves/chave.publica";
                String conversa = jTextAreaReceived.getText();
                byte[] textoClaro = conversa.getBytes("ISO-8859-1");

                PublicKey pub = cdc.carregarChavePublica(new File(chavePub));
                Criptografia criptografia = new Criptografia();
                byte[][] criptografado = criptografia.criptografar(pub, textoClaro);

                File_io.gravarBin(fc.getSelectedFile().getAbsolutePath(), criptografado);

            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButtonSend = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSplitPaneCenter = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReceived = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSend = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(398, 199));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jButtonSave.setText("Salvar Conversa");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonSave);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jButtonSend.setText("Enviar");
        jButtonSend.setPreferredSize(new java.awt.Dimension(63, 43));
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonSend, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.EAST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jSplitPaneCenter.setDividerLocation(351);
        jSplitPaneCenter.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setAutoscrolls(true);

        jTextAreaReceived.setEditable(false);
        jTextAreaReceived.setColumns(20);
        jTextAreaReceived.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jTextAreaReceived.setLineWrap(true);
        jTextAreaReceived.setRows(5);
        jTextAreaReceived.setWrapStyleWord(true);
        jTextAreaReceived.setFocusable(false);
        jTextAreaReceived.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jTextAreaReceived);

        jSplitPaneCenter.setTopComponent(jScrollPane1);

        jScrollPane2.setAutoscrolls(true);

        jTextAreaSend.setColumns(20);
        jTextAreaSend.setLineWrap(true);
        jTextAreaSend.setRows(5);
        jTextAreaSend.setWrapStyleWord(true);
        jTextAreaSend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextAreaSendKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaSend);

        jSplitPaneCenter.setBottomComponent(jScrollPane2);

        jPanel2.add(jSplitPaneCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(837, 514));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        sendMessage(jTextAreaSend.getText());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
//                    Thread.sleep(100);
                    jTextAreaSend.setText("");
                    jTextAreaSend.requestFocus();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void jTextAreaSendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaSendKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage(jTextAreaSend.getText());
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    try {
//                        Thread.sleep(100);
                        jTextAreaSend.setText("");
                        jTextAreaSend.requestFocus();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }//GEN-LAST:event_jTextAreaSendKeyPressed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveFile();
    }//GEN-LAST:event_jButtonSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPaneCenter;
    private javax.swing.JTextArea jTextAreaReceived;
    private javax.swing.JTextArea jTextAreaSend;
    // End of variables declaration//GEN-END:variables

}
