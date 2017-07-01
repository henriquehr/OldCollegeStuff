/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Encryption.SaveLoadChat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author fabricio
 */
public class CarregadorDeChave {
    
    
     public PrivateKey carregarChavePrivada(File fPvk) throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fPvk));
        PrivateKey ret = (PrivateKey) ois.readObject();
        ois.close();

        return ret;
    }
     
     public PublicKey carregarChavePublica(File fPub) throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fPub));
        PublicKey ret = (PublicKey) ois.readObject();
        ois.close();

        return ret;
    }
    
}
