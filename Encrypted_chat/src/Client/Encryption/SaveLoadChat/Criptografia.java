/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Encryption.SaveLoadChat;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author fabricio
 */
public class Criptografia {

    public byte[][] criptografar(PublicKey pubKey, byte[] texto) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        byte[] textoCifrado = null;
        byte[] chaveCifrada = null;

        // Gerar uma chave simétrica de 128 bits  
        KeyGenerator geradorDeChave = KeyGenerator.getInstance("AES");
        geradorDeChave.init(128);
        SecretKey sk = geradorDeChave.generateKey();

        byte[] chave = sk.getEncoded();

        // Cifrar o texto com a chave gerada  
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivspec = new IvParameterSpec(new byte[16]);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"), ivspec);
        textoCifrado = cipher.doFinal(texto);

        // Cifrar a chave com a chave pública  
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.ENCRYPT_MODE, pubKey);
        chaveCifrada = rsaCipher.doFinal(chave);

        return new byte[][]{textoCifrado, chaveCifrada};
    }

    public byte[] descriptografar(PrivateKey privKey, byte[] textoCript, byte[] chaveCript) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        byte[] textoDecifrado = null;

        // Decifrar a chave simétrica com a chave privada  
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] chaveDecifrada = rsaCipher.doFinal(chaveCript);

        // Decifrar o texto com a chave decifrada  
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivspec = new IvParameterSpec(new byte[16]);
        aesCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chaveDecifrada, "AES"), ivspec);
        textoDecifrado = aesCipher.doFinal(textoCript);

        return textoDecifrado;
    }

}
