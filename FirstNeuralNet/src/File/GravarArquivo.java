/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Fabricio_Dornelles
 */
public class GravarArquivo {

    public static void gravar(String nameFile, String texto) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(nameFile))) {
            out.write(texto);
        } catch (IOException e) {
            System.out.println("erro: " + e);
        }
    }
}
