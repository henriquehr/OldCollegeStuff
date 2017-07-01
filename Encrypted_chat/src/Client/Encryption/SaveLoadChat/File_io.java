/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Encryption.SaveLoadChat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 *
 * @author fabricio
 */
public class File_io {

    public static void gravarBin(String path, byte[][] data) {

        try {
            FileOutputStream out = new FileOutputStream(path + "data");
            out.write(data[0]);
            out.close();
        } catch (Exception e) {
            System.out.println("Deu pau no salvar : : " + e.getMessage());
        }

        try {
            FileOutputStream out = new FileOutputStream(path + "chave");
            out.write(data[1]);
            out.close();
        } catch (Exception e) {
            System.out.println("Deu pau no salvar : : " + e.getMessage());
        }

        try {

            File arquivo = new File(path);
            if (!arquivo.exists()) {
                //cria um arquivo (vazio)
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivo, true);

            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("para ler esta coversar abra ela pelo chaterino");

            bw.newLine();

            bw.close();
            fw.close();

        } catch (Exception e) {
            System.out.println("Deu pau no salvar : : " + e.getMessage());
        }

    }

    public static byte[][] lerBin(String path) {

        try {

            File ficheiroDados = new File(path + "data");
            File ficheiroChave = new File(path + "chave");

            int sizeDados = (int) ficheiroDados.length();
            int sizeChave = (int) ficheiroChave.length();

            byte[] textoCifrado = new byte[sizeDados];
            byte[] chaveCifrada = new byte[sizeChave];

            FileInputStream inDados = new FileInputStream(ficheiroDados);
            inDados.read(textoCifrado);//passa toda o conteudo para o resultado
            inDados.close();

            FileInputStream inChave = new FileInputStream(ficheiroChave);
            inChave.read(chaveCifrada);//passa toda o conteudo para o resultado
            inChave.close();

            return new byte[][]{textoCifrado, chaveCifrada};//bytesRetorno;

        } catch (Exception e) {
            System.out.println("Deu pau no ler: " + e.getMessage());
            return null;
        }

    }

}
