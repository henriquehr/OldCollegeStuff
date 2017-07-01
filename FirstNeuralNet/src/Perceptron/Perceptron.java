package Perceptron;

import Ativacao.Ativacao;
import Components.Feedforward;
import Components.Layer;
import Components.Neuron;
import File.LerArquivo;

/**
 *
 * @author henrique
 */
public class Perceptron {

    private static double[][] amostras = null;
    private static double[] saidas = null;

    public static void main(String[] args) {
        Feedforward nn = new Feedforward(1);
        nn.getLayers()[0] = new Layer(1);
        nn.getLayers()[0].addNeuron(new Neuron(4), 0);
        nn.getLayers()[0].getNeurons()[0].generateWeight(0, 1);

        for (int i = 0; i < nn.getLayers()[0].getNeurons()[0].getWeights().length; i++) {
            System.out.println(nn.getLayers()[0].getNeurons()[0].getWeights()[i]);
        }

        double[][] a = LerArquivo.lerArquivo("./apendice1.txt");

        amostras = new double[a.length][a[0].length];
        saidas = new double[a.length];

        for (int i = 0; i < a.length; i++) {
            System.arraycopy(a[i], 0, amostras[i], 0, a[i].length -1);
            amostras[i][amostras[i].length-1] = -1;
            saidas[i] = a[i][a[i].length - 1];
        }


        int epoca = treinar(nn);

        System.out.println("\nEpoca: " + epoca + "\n");

        for (int i = 0; i < nn.getLayers()[0].getNeurons()[0].getWeights().length; i++) {
            System.out.println(nn.getLayers()[0].getNeurons()[0].getWeights()[i]);
        }

        System.out.println("----------------------------------------------------");

        validar(nn);

    }

    private static void validar(Feedforward nn) {

        double[] amostra = {-0.3665, 0.0620, 5.9891};
        double[] amostra2 = {-0.7842, 1.1267, 5.5912};
        double[] amostra3 = {0.3012, 0.5611, 5.8234};
        double[] amostra4 = {0.7757, 1.0648, 8.0677};
        double[] amostra5 = {0.1570, 0.8028, 6.3040};
        double[] amostra6 = {-0.7014, 1.0316, 3.6005};
        double[] amostra7 = {0.3748, 0.1536, 6.1537};
        double[] amostra8 = {-0.6920, 0.9404, 4.4058};
        double[] amostra9 = {-1.3970, 0.7141, 4.9263};
        double[] amostra10 = {-1.8842, -0.2805, 1.2548}; 
        
        double[][] classificarTotal = {amostra, amostra2, amostra3, amostra4, amostra5, amostra6, amostra7, amostra8, amostra9, amostra10};
       


        for (int i = 0; i < classificarTotal.length; i++) {
            double u = nn.getLayers()[0].getNeurons()[0].getSum(classificarTotal[i]);
            double y = Ativacao.degrau(u);
            if (y == -1) {
                System.out.println("Amostra " + i + " = Classe A");
            } else if (y == 1) {
                System.out.println("Amostra " + i + " = Classe B");
            }
        }
    }

    private static int treinar(Feedforward nn) {

        double taxaAprendizagem = 0.01;

        boolean erro = true;
        int epoca = 0;
        while (erro == true) {
            erro = false;
            for (int k = 0; k < amostras.length; k++) {
                double u = nn.getLayers()[0].getNeurons()[0].getSum(amostras[k]);
                double y = Ativacao.degrau(u);
//                System.out.println("Desejado " + k + ": " + y + " Saida: " + saidas[k]);
                if (y != saidas[k]) {
                    for (int l = 0; l < nn.getLayers()[0].getNeurons()[0].getWeights().length; l++) {
                        nn.getLayers()[0].getNeurons()[0].updateWeight(l,(taxaAprendizagem * (saidas[k] - y) * amostras[k][l]));
//                            System.out.println(" Novo Peso: " + nn.getLayers()[0].getNeurons()[0].getWeights()[l]);
                    }
                    erro = true;
                }
            }
            epoca++;
        }
        return epoca;
    }
}
