/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Fabricio_Dornelles
 */
public class LerArquivo {

    private static double amostras[][];

    public static double[][] lerArquivo(String pathNomeArquivo) {
        try {
            String s, nomeFx;
            // atribuir a nomeFx o nome do ficheiro 
            nomeFx = pathNomeArquivo;
            // declarar BufferedReader para leitura do txt
            BufferedReader fileAux = null;
            try {
                fileAux = new BufferedReader(new FileReader(nomeFx));
                //chamar o metodo para contar as linhas e colunas validas do arquivo
                contaLinhasCol(fileAux);
            } catch (Exception e) {
                // ocorreu um erro na abertura do arquivo, logo:
                System.out.println("Impossível abrir o ficheiro " + nomeFx);
                System.out.println("fim do programa...");
                return null;
            }

            fileAux.close();// fecha fileAux

            // varivel bufferredReader utilizada para ler o arquivo e passar para matriz de amostras          
            BufferedReader f = null;
            String[] linha = null;
            try {
                // abrir o ficheiro de texto
                f = new BufferedReader(new FileReader(nomeFx));
            } catch (Exception e) {
                // ocorreu um erro na abertura do arquivo, logo:
                System.out.println("Impossível abrir o ficheiro " + nomeFx);
                System.out.println("fim do programa...");
                return null;
            }
            // string linhas[] = texto.split("\n");
            s = f.readLine();   // ler a 1ª linha
            s = f.readLine();// ler a 2ª linha, a primeira é descartada, pois é apenas o cabeçalho
            int line = 0;
            int coluna = 0;

            while (s != null) {  // o readLine devolve null no fim ficheiro 
                linha = s.split(" ");

                for (int j = 0; j < linha.length; j++) {
                    if (!linha[j].equals("")) {
                        amostras[line][coluna] = Double.parseDouble(linha[j]);
                        coluna++;
                    }
                }
                coluna = 0;
                line++;
                s = f.readLine(); // ler a linha seguinte
            }
            // fechar o ficheiro
            f.close();
        } catch (IOException ex) {
            System.out.println("erro: " + ex);
        }
        return amostras;
    }

    public static void contaLinhasCol(BufferedReader f) throws IOException {
        int nrLinhas = 0;
        int nrColunas = 0;
        String s;
        s = f.readLine();   // ler a 1ª linha
        String linha[] = null;
        // verifica a quantidade de coluna
        linha = s.split(" ");
        //  System.out.println("\nlinha");
        for (int i = 0;
                i < linha.length;
                i++) {
            if (!linha[i].equals("")) {
                nrColunas++;
            }
        }
        //fim da contagem de colunas
        // verifica a quantidade de linhas
        while (s
                != null) {
            s = f.readLine();
            nrLinhas++;
        }
        nrLinhas = nrLinhas - 1; // descontado a linha do cabeçalho que é descartada

        amostras = new double[nrLinhas][nrColunas];
    }
}
