 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class ConnectClientToServer {

    private Socket sock = null;
    private String ip = "127.0.0.1";
    private int port = 6000;

    public void reconnect() {
        try {
            sock = new Socket(ip, port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        sendMessage(sock, message);
    }

    public String getMessage() {
        return getMessage(sock);
    }

    public List<List<String>> getMessageList() {
        return getMessageList(sock);
    }

    private void sendMessage(Socket sock, String message) {
        try {
            DataOutputStream out;
            out = new DataOutputStream(sock.getOutputStream());
            out.writeUTF(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getMessage(Socket sock) {
        String r = "";
        try {
            DataInputStream in = new DataInputStream(sock.getInputStream());
            String msg = in.readUTF();
            r = msg;
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }

    private List<List<String>> getMessageList(Socket sock) {
        List<List<String>> r = null;
        try {
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            r = (List<List<String>>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return r;
    }

    public String getOnline() {
        String r = "";
        sendMessage(sock, "who");
        r = getMessage(sock);
        return r;
    }
}
