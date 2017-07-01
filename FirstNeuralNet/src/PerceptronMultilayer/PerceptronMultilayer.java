package PerceptronMultilayer;

import Ativacao.Ativacao;
import Components.Feedforward;
import Components.Layer;
import Components.Neuron;
import File.LerArquivo;
import java.util.Scanner;
import Error.Error;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Henrique
 */
public class PerceptronMultilayer {

    private static double[][] amostras = null;
    private static double[][] saidas = null;

    public static void main(String[] args) {
        int op = -1;
        int qtdEntradas = 0;
        Feedforward nn = null;
        String fileName = "pesosTreinados.ser";
        String arquivoAmostras = "./apendice3.txt";
        do {
            try {
                System.out.println("0 - Sair\n1 - Treinar\n2 - Carregar Rede Neural\n3 - Validar");
                Scanner in = new Scanner(System.in);

                op = in.nextInt();

                System.out.println("");
                if (op == 1) {
                    System.out.println("Quantas camadas?");
                    int qtdCamadas = in.nextInt();
                    if (qtdCamadas < 2) {
                        Error.showError(new Exception(), "Quantidade de camadas invalida. Deve ser 2 ou mais camadas.");
                    }
                    System.out.println("");
                    nn = new Feedforward(qtdCamadas);
                    for (int i = 0; i < qtdCamadas; i++) {
                        System.out.println("Quantos Neurônios na camada " + i + "?");
                        nn.getLayers()[i] = new Layer(in.nextInt());
                        if (nn.getLayers()[i].getNeurons().length < 1) {
                            Error.showError(new Exception(), "Quantidade de neurônios invalida. Deve ser 1 ou mais neurônios.");
                        }
                    }

                    System.out.println("");
                    System.out.println("Última pergunta valendo 1 milhão de reais maaaaa oooeeee\nQuantas entradas na camada inicial?");
                    qtdEntradas = in.nextInt();
                    System.out.println("");
                    for (int i = 0; i < nn.getLayers()[0].getNeurons().length; i++) {
                        nn.getLayers()[0].addNeuron(new Neuron(qtdEntradas + 1), i);
                        nn.getLayers()[0].getNeurons()[i].generateWeight(0, 1);
                    }
                    for (int q = 1; q < nn.getLayers().length; q++) {
                        for (int i = 0; i < nn.getLayers()[q].getNeurons().length; i++) {
                            nn.getLayers()[q].addNeuron(new Neuron(nn.getLayers()[q - 1].getNeurons().length + 1), i);
                            nn.getLayers()[q].getNeurons()[i].generateWeight(0, 1);
                        }
                    }

                    carregarAmostras(arquivoAmostras, qtdEntradas, nn);

                    int epoca = treinar(nn);

                    System.out.println("");

                    System.out.println("epocas: " + epoca);

                    salvarRedeNeural(nn, fileName);
                    
                    System.out.println("Treinamento completo. Épocas: "  + epoca);
                    System.out.println("Rede Neural salvo no arquivo "+ fileName + ".");
                    System.out.println("");
                } else if (op == 2) {
                    try {
                        nn = (Feedforward) carregarRedeNeural(nn, fileName);
                        System.out.println("Rede Neural Carregada.");
                        carregarAmostras(arquivoAmostras, qtdEntradas, nn);
                        System.out.println("");
                    } catch (Exception ex) {
                        Error.showError(ex, "Erro ao carregar a Rede Neural.");
                    }
                } else if (op == 3) {
                    if (nn == null) {
                        Error.showError(new Exception(), "Rede Neural não treinada.");
                    }
                    validar(nn);
                }
            } catch (Exception ex) {
                op = -1;
                ex.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex1) {
                    System.out.println("Erro no Erro.");
                    ex1.printStackTrace();
                }
            }
        } while (op != 0);
    }

    private static void validar(Feedforward nn) {
        double[][] Layers = new double[nn.getLayers().length][];
        double[][] posTanh = new double[nn.getLayers().length][];

        for (int i = 0; i < Layers.length; i++) {
            Layers[i] = new double[nn.getLayers()[i].getNeurons().length];
            for (int j = 0; j < Layers[i].length; j++) {
                posTanh[i] = new double[nn.getLayers()[i].getNeurons().length + 1];
            }
        }

        double[] amostra0 = {0.8622, 0.7101, 0.6236, 0.7894};
        double[] amostra1 = {0.2741, 0.1552, 0.1333, 0.1516};
        double[] amostra2 = {0.6772, 0.8516, 0.6543, 0.7573};

        double[][] amostrasFinal = {amostra0, amostra1, amostra2};

        for (int i = 0; i < amostrasFinal.length; i++) {
            for (int j = 0; j < nn.getLayers()[0].getNeurons().length; j++) {
                for (int k = 0; k < nn.getLayers()[0].getNeurons()[j].getWeights().length - 1; k++) {
                    Layers[0][j] += nn.getLayers()[0].getNeurons()[j].getWeights()[k] * amostrasFinal[i][k];
                }
            }
            posTanh[0] = TanH(Layers[0], nn.getLayers()[1].getNeurons()[0].getWeights().length + 1, i);

            for (int t = 1; t < Layers.length; t++) {
                for (int j = 0; j < nn.getLayers()[t].getNeurons().length; j++) {
                    for (int k = 0; k < nn.getLayers()[t].getNeurons()[j].getWeights().length - 1; k++) {
                        Layers[t][j] += nn.getLayers()[t].getNeurons()[j].getWeights()[k] * posTanh[t - 1][k];
                    }
                }
                posTanh[t] = TanH(Layers[t], nn.getLayers()[t].getNeurons().length + 1, i);
            }

            for (int u = 0; u < posTanh[posTanh.length - 1].length - 1; u++) {
                System.out.print(posTanh[posTanh.length - 1][u] >= 0.5 ? 1 + " " : 0 + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static int treinar(Feedforward nn) {
        double taxaAprendizagem = 0.025;
        double precisao = Math.pow(10, -7);
        int epoca = 0;
        double emAnt = 0, emAtual = 0;
        double[][] Layers = new double[nn.getLayers().length][];
        for (int i = 0; i < nn.getLayers().length; i++) {
            Layers[i] = new double[nn.getLayers()[i].getNeurons().length];
        }
        double[][] posTanh = new double[nn.getLayers().length][];
        for (int i = 0; i < nn.getLayers().length; i++) {
            posTanh[i] = new double[nn.getLayers()[i].getNeurons().length + 1];
        }
        double[] erroLocal;
        erroLocal = new double[amostras.length];
        do {
            emAnt = emAtual;
            for (int i = 0; i < amostras.length; i++) {
                Layers = new double[nn.getLayers().length][];
                for (int y = 0; y < nn.getLayers().length; y++) {
                    Layers[y] = new double[nn.getLayers()[y].getNeurons().length];
                }
                posTanh = new double[nn.getLayers().length][];
                for (int y = 0; y < nn.getLayers().length; y++) {
                    posTanh[y] = new double[nn.getLayers()[y].getNeurons().length + 1];
                }
                for (int j = 0; j < nn.getLayers()[0].getNeurons().length; j++) {
                    Layers[0][j] += nn.getLayers()[0].getNeurons()[j].getSum(amostras[i]);
                }

                posTanh[0] = TanH(Layers[0], nn.getLayers()[0].getNeurons().length + 1, i);

                for (int j = 1; j < nn.getLayers().length; j++) {
                    for (int y = 0; y < nn.getLayers()[j].getNeurons().length; y++) {
                        Layers[j][y] += nn.getLayers()[j].getNeurons()[y].getSum(posTanh[j - 1]);
                    }
                    posTanh[j] = TanH(Layers[j], nn.getLayers()[j].getNeurons().length + 1, i);
                }

                // -----------------------------------------------------------------------
                double[][] derivadaTanH = new double[Layers.length][];
                double[][] gradiente = new double[Layers.length][];
                for (int q = Layers.length - 1; q > 0; q--) {
                    derivadaTanH[q] = new double[nn.getLayers()[q].getNeurons().length];
                    for (int j = 0; j < Layers[q].length; j++) {
                        derivadaTanH[q][j] = derivadaTangH(Layers[q][j]);
                    }
                    gradiente[q] = new double[nn.getLayers()[q].getNeurons().length];
                    for (int k = 0; k < saidas[i].length; k++) {
                        gradiente[q][k] = (saidas[i][k] - posTanh[q][k]) * derivadaTanH[q][k];
                    }
                    for (int j = 0; j < nn.getLayers()[q].getNeurons().length; j++) {
                        for (int u = 0; u < nn.getLayers()[q].getNeurons()[j].getWeights().length; u++) {
                            nn.getLayers()[q].getNeurons()[j].updateWeight(u, taxaAprendizagem * gradiente[q][j] * posTanh[q - 1][u]);
                        }
                    }
                }

                derivadaTanH[0] = new double[nn.getLayers()[0].getNeurons().length];
                gradiente[0] = new double[nn.getLayers()[0].getNeurons().length];

                for (int j = 0; j < Layers[0].length; j++) {
                    derivadaTanH[0][j] = derivadaTangH(Layers[0][j]);
                }

                double temp = 0;

                for (int j = 0; j < nn.getLayers()[1].getNeurons().length; j++) {
                    for (int k = 0; k < nn.getLayers()[1].getNeurons()[j].getWeights().length - 1; k++) {
                        temp += (gradiente[1][j] * nn.getLayers()[1].getNeurons()[j].getWeights()[k]);
                    }
                }
                for (int j = 0; j < gradiente[0].length; j++) {
                    gradiente[0][j] += temp * derivadaTanH[0][j];
                }

                for (int j = 0; j < nn.getLayers()[0].getNeurons().length; j++) {
                    for (int k = 0; k < nn.getLayers()[0].getNeurons()[j].getWeights().length; k++) {
                        nn.getLayers()[0].getNeurons()[j].updateWeight(k, taxaAprendizagem * gradiente[0][j] * amostras[i][k]);
                    }
                }
            }

            for (int i = 0; i < amostras.length; i++) {
                for (int j = 0; j < nn.getLayers()[0].getNeurons().length; j++) {
                    for (int k = 0; k < nn.getLayers()[0].getNeurons()[j].getWeights().length; k++) {
                        Layers[0][j] += nn.getLayers()[0].getNeurons()[j].getWeights()[k] * amostras[i][k];
                    }
                }
                posTanh[0] = TanH(Layers[0], nn.getLayers()[1].getNeurons()[0].getWeights().length + 1, i);
                for (int w = 1; w < Layers.length; w++) {
                    for (int j = 0; j < nn.getLayers()[w].getNeurons().length; j++) {
                        for (int k = 0; k < nn.getLayers()[w].getNeurons()[j].getWeights().length; k++) {
                            Layers[w][j] += nn.getLayers()[w].getNeurons()[j].getWeights()[k] * posTanh[w][j];
                        }
                    }
                    posTanh[w] = TanH(Layers[w], nn.getLayers()[w].getNeurons().length + 1, i);
                }
                erroLocal[i] = emLocal(nn, posTanh[posTanh.length - 1], i);
            }

            emAtual = em(erroLocal);

            epoca++;
            System.out.println(epoca + "----------------------------------------------------" + Math.abs(emAtual - emAnt));
        } while (Math.abs(emAtual - emAnt) >= precisao);
        return epoca;
    }

    public static double derivadaTangH(double x) {
        return 1 - Math.pow(Math.tanh(x), 2);
    }

    public static double[] TanH(double[] SomaLayer, int qtdN, int i) {
        double[] posTanh = new double[qtdN];
        for (int j = 0; j < SomaLayer.length; j++) {
            posTanh[j] = Ativacao.tangenteHiperbolica(SomaLayer[j]);
        }
        posTanh[posTanh.length - 1] = amostras[i][amostras[i].length - 1];
        return posTanh;
    }

    private static double emLocal(Feedforward nn, double[] posTangh, int i) {
        double em = 0;
        for (int j = 0; j < nn.getLayers()[nn.getLayers().length - 1].getNeurons().length; j++) {
            em += Math.pow((saidas[i][j] - posTangh[j]), 2);
        }
        return em / 2;
    }

    private static double em(double[] erroLocal) {
        double temp = 0;
        for (int i = 0; i < erroLocal.length; i++) {
            temp += erroLocal[i];
        }
        return temp / amostras.length;
    }

    private static void carregarAmostras(String arquivoAmostras, int qtdEntradas, Feedforward nn) {
        double[][] a = LerArquivo.lerArquivo(arquivoAmostras);

        amostras = new double[a.length][qtdEntradas + 1];
        saidas = new double[a.length][nn.getLayers()[nn.getLayers().length - 1].getNeurons().length];

        for (int i = 0; i < a.length; i++) {
            System.arraycopy(a[i], 0, amostras[i], 0, qtdEntradas + 1);
            amostras[i][amostras[i].length - 1] = -1;
        }
        int t = nn.getLayers()[nn.getLayers().length - 1].getNeurons().length - 1;
        for (int j = 0; j < a.length; j++) {
            for (int i = a[0].length - 1; i > nn.getLayers()[nn.getLayers().length - 1].getNeurons().length; i--) {
                saidas[j][t--] = a[j][i];
            }
            t = nn.getLayers()[nn.getLayers().length - 1].getNeurons().length - 1;
        }
    }

    private static void salvarRedeNeural(Feedforward nn, String fileName) throws Exception {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File f = new File(fileName);
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(nn);
        } catch (Exception e) {
            Error.showError(e, "Erro ao salvar Rede Neural.");
        } finally {
            oos.close();
            fos.close();
        }
    }

    private static Object carregarRedeNeural(Feedforward nn, String fileName) throws Exception {
        FileInputStream fos = null;
        ObjectInputStream oos = null;
        Object r = null;
        try {
            File f = new File(fileName);
            fos = new FileInputStream(f);
            oos = new ObjectInputStream(fos);
            r = oos.readObject();
        } catch (Exception e) {
            Error.showError(e, "Erro ao carregar arquivo.");
        } finally {
            oos.close();
            fos.close();
        }
        return r;
    }
}
