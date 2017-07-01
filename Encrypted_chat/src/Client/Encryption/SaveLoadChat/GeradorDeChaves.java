/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Encryption.SaveLoadChat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.RSAKeyGenParameterSpec;

/**
 *
 * @author fabricio
 */
public class GeradorDeChaves {

    private static final int RSAKEYSIZE = 1024;
    PrivateKey priv = null;
    PublicKey pub = null;
    KeyPair kpr = null;
    KeyPairGenerator kpg = null;
    ObjectOutputStream oos = null;

    public void gerarChaves() throws Exception {

        GeradorDeChaves geradorDeChave = new GeradorDeChaves();

        File dir = new File("chaves");
        dir.mkdir();
        
        geradorDeChave.gerarChavePublica(new File("chaves/chave.publica"));
        geradorDeChave.gerarChavePrivate(new File("chaves/chave.privada"));

    }

    public GeradorDeChaves() throws IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, KeyStoreException {
        kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(new RSAKeyGenParameterSpec(RSAKEYSIZE, RSAKeyGenParameterSpec.F4));
        kpr = kpg.generateKeyPair();
    }

    public void gerarChavePublica(File fPub) throws IOException {
        pub = kpr.getPublic();
        oos = new ObjectOutputStream(new FileOutputStream(fPub));
        oos.writeObject(pub);
        oos.close();
    }

    public void gerarChavePrivate(File fPvk) throws IOException {
        priv = kpr.getPrivate();
        oos = new ObjectOutputStream(new FileOutputStream(fPvk));
        oos.writeObject(priv);
        oos.close();
    }
}
