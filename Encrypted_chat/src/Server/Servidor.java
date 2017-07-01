package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eliézer
 */
public class Servidor {

    private List<User> arrayUsuarios;
//    List<List<String>> arrayMensagens;

    public Servidor() {
        arrayUsuarios = new ArrayList<>();
//        arrayMensagens = new ArrayList<>();
        try {
            ServerSocket ss = new ServerSocket(6000);
            System.out.println("Servior TCP rodando.......");

            while (true) {

                Socket socket = ss.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String msg = in.readUTF();

                DataOutputStream writer = new DataOutputStream(socket.getOutputStream());

                String[] elementosMsg;

                elementosMsg = msg.split(";");

                switch (elementosMsg[0]) {
                    case "ola":
                        login(elementosMsg[1]);
                        writer.writeUTF("logado com sucesso!");
                        break;
                    case "tchau":
                        logout(elementosMsg[1]);
                        writer.writeUTF("logoff com sucesso!");
                        break;
                    case "who":
                        writer.writeUTF(getStatus());
                        break;
                    case "listen":
                        ObjectOutputStream writerObject = new ObjectOutputStream(socket.getOutputStream());
                        writerObject.writeObject(getMensagens(elementosMsg[1]));
                        break;
                    case "write":
                        setMensagem(elementosMsg);
                        writer.writeUTF("done");
                        break;
                    default:
                        writer.writeUTF("error!");
                        break;
                }
                writer.close();

            }
        } catch (IOException ex) {
            System.out.println("Erro: " + ex);
        }
    }

    private void login(String user) {
        boolean tem = false;
        for (int i = 0; i < arrayUsuarios.size(); i++) {
            if (arrayUsuarios.get(i).getName().equals(user)) {
                tem = true;
                break;
            }
        }
        if (!tem) {
            arrayUsuarios.add(new User(user, new ArrayList<>()));
        }
    }

    private void logout(String user) {
        for (int i = 0; i < arrayUsuarios.size(); i++) {
            if (arrayUsuarios.get(i).getName().equals(user)) {
                arrayUsuarios.remove(i);
                return;
            }
        }
    }

    private String getStatus() {
        String mensagem = "";
        for (int i = 0; i < arrayUsuarios.size(); i++) {
            mensagem += arrayUsuarios.get(i).getName() + ";";
        }
        return mensagem;
    }

    private List<List<String>> getMensagens(String cli) {
        List<List<String>> mensagem = new ArrayList<>();
        for (int i = 0; i < arrayUsuarios.size(); i++) {
            if (arrayUsuarios.get(i).getName().equals(cli)) {
                mensagem = arrayUsuarios.get(i).getMessages();
                arrayUsuarios.get(i).setMessages(new ArrayList<>());
            }
        }
        return mensagem;
    }

    private void setMensagem(String[] elementosMsg) {
//        String[] elementosMsg;
//        elementosMsg = mensagemRecebida.split(";");
        String mensagem = "";
        mensagem = elementosMsg[3];
        for (int i = 0; i < arrayUsuarios.size(); i++) {
            if (arrayUsuarios.get(i).getName().equals(elementosMsg[1])) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(elementosMsg[2]);
                temp.add(mensagem);
                arrayUsuarios.get(i).getMessages().add(temp);
            }
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
    }
}
