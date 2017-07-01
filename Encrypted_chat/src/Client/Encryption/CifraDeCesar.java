/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Encryption;

/**
 *
 * @author fabricio
 */
public class CifraDeCesar {

    private int chave;

    public CifraDeCesar(int chave) {
        this.chave = chave;        
    }
       
    public void setChave(int chave){
        this.chave =  chave;        
    }
    
    public int getchave(){
        return this.chave;
    }
    
    public  String criptografar(String mensagem) {
        StringBuilder msgCriptografada = new StringBuilder();

        for (int i = 0; i < mensagem.length(); i++) {
            char ch = (char) (mensagem.charAt(i) + chave);
            msgCriptografada.append(ch);
        }

        return msgCriptografada.toString();
    }

    public  String descriptografar(String mensagem) {
        
        StringBuilder msgDescriptografada = new StringBuilder();

        for (int i = 0; i < mensagem.length(); i++) {
            char ch = (char) (mensagem.charAt(i) - chave);
            msgDescriptografada.append(ch);
        }

        return msgDescriptografada.toString();
       
    }

}
