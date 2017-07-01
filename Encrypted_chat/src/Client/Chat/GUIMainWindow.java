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
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 *
 * @author Henrique
 */
public class GUIMainWindow extends javax.swing.JFrame {

    private ConnectClientToServer tServer;
    private String myself;
    private List<GUIChatWindow> chats;
    private CifraDeCesar cc;

    /**
     * Creates new form GUIMainWindow
     *
     * @param myself
     */
    public GUIMainWindow(String myself) {
        this.myself = myself;
        initComponents();
        this.setTitle(myself + " - Chaterino");
        login();
        getOnline();
        getMessage();
        jLabelUser.setText(myself);
        chats = new ArrayList<>();
        cc = new CifraDeCesar(616);
    }

    private void getOnline() {
        DefaultListModel<String> model = (DefaultListModel<String>) jListUsersOnline.getModel();
        Thread thr = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!isActive()) {
                }
                while (true) {
                    tServer = new ConnectClientToServer();
                    tServer.reconnect();
                    String on = ((ConnectClientToServer) tServer).getOnline();
                    String[] ons = on.split(";");
                    model.clear();
                    for (String on1 : ons) {
                        if (!on1.equals(jLabelUser.getText()) && !model.contains(on1) && !on1.isEmpty()) {
                            model.addElement(on1);
                        }
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thr.setPriority(Thread.MIN_PRIORITY);
        thr.start();
    }

    private void getMessage() {
        ConnectClientToServer thr = new ConnectClientToServer();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        thr.reconnect();
                        thr.sendMessage("listen;" + myself);
                        List<List<String>> m = thr.getMessageList();
                        for (List<String> user : m) {
                            String u = user.get(0);
                            String message = "";
                            for (int i = 1; i < user.size(); i++) {
                                message += cc.descriptografar(user.get(i));
                            }
                            startChat(u, message);
                        }
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        ).start();
    }

    private void startChat(String user, String message) {
        boolean open = false;
        int index = 0;
        for (int i = 0; i < chats.size(); i++) {
            if (chats.get(i).getUser().equals(user)) {
                if (!chats.get(i).isDisplayable()) {
                    chats.remove(i);
                } else {
                    open = true;
                    index = i;
                    break;
                }
            }
        }
        if (open) {
            chats.get(index).setText(message, user);
        } else {
            chats.add(new GUIChatWindow());
            chats.get(chats.size() - 1).setUser(user);
            chats.get(chats.size() - 1).setMyself(myself);
            chats.get(chats.size() - 1).setVisible(true);
            chats.get(chats.size() - 1).setText(message, user);
        }
    }

    private void login() {
        ConnectClientToServer thr = new ConnectClientToServer();
        thr.reconnect();
        thr.sendMessage("ola;" + myself);
    }

    private void sendOff() {
        ConnectClientToServer thr = new ConnectClientToServer();
        thr.reconnect();
        thr.sendMessage("tchau;" + myself);
        System.out.println(thr.getMessage());
    }

    private void openFile() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setMultiSelectionEnabled(false);
        fc.setCurrentDirectory(new File("."));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            CarregadorDeChave cdc = new CarregadorDeChave();
            String chavePriv = "chaves/chave.privada";
            Criptografia criptografia = new Criptografia();
            String path = file.getAbsolutePath();
            byte[][] readFileCript = File_io.lerBin(path);
            try {
                PrivateKey pvk = cdc.carregarChavePrivada(new File(chavePriv));
                byte[] descriptografado = criptografia.descriptografar(pvk, readFileCript[0], readFileCript[1]);
                String message = new String(descriptografado, "ISO-8859-1");
                new GUIOpenMessage(message).setVisible(true);
            } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException ex) {
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

        jPanelBack = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanelName = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonOpenFile = new javax.swing.JButton();
        jButtonStartChat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListUsersOnline = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chaterino");
        setMinimumSize(new java.awt.Dimension(593, 146));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelBack.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Usuarios Online");
        jPanelBack.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(190);
        jSplitPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanelName.setLayout(new javax.swing.BoxLayout(jPanelName, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText("  Seu Nome:");
        jPanel1.add(jLabel2);
        jPanel1.add(jLabelUser);

        jPanelName.add(jPanel1);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonOpenFile.setText("Abrir uma Conversa");
        jButtonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenFileActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonOpenFile);

        jButtonStartChat.setText("Iniciar Chat");
        jButtonStartChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartChatActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonStartChat);

        jPanelName.add(jPanel2);

        jSplitPane1.setRightComponent(jPanelName);

        jListUsersOnline.setModel(new DefaultListModel<String>());
        jListUsersOnline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListUsersOnlineMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListUsersOnline);

        jSplitPane1.setLeftComponent(jScrollPane2);

        jPanelBack.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelBack, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(548, 470));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartChatActionPerformed
        if (jListUsersOnline.getSelectedIndex() != -1) {
            startChat((String) jListUsersOnline.getSelectedValue(), "");
        }
    }//GEN-LAST:event_jButtonStartChatActionPerformed

    private void jListUsersOnlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListUsersOnlineMouseClicked
        if (evt.getClickCount() == 2) {
            if (jListUsersOnline.getSelectedIndex() != -1) {
                startChat((String) jListUsersOnline.getSelectedValue(), "");
            }
        } else {
            jButtonStartChat.requestFocus();
        }
    }//GEN-LAST:event_jListUsersOnlineMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        sendOff();
    }//GEN-LAST:event_formWindowClosing

    private void jButtonOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenFileActionPerformed
        openFile();
    }//GEN-LAST:event_jButtonOpenFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOpenFile;
    private javax.swing.JButton jButtonStartChat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JList jListUsersOnline;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBack;
    private javax.swing.JPanel jPanelName;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables

}
