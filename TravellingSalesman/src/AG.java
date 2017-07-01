package trabalho2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author henrique
 */
public class AG {

    private int maxEpocas;
    private int tamanhoPop;
    private int tamanhoCromo;
    private Integer[] melhorCromo;
    private Integer[][] popAtual;
    private int melhorCusto;
    private int inicio;

    public AG(int inicio, Integer[] solInicial, int tamanhoCromo, int tamanhoPop, int maxEpocas) {
        this.inicio = inicio;
        this.melhorCromo = solInicial;
        this.melhorCusto = solInicial[solInicial.length - 1];
        this.tamanhoCromo = tamanhoCromo - 1;
        this.tamanhoPop = tamanhoPop;
        this.maxEpocas = maxEpocas;
        popAtual = new Integer[tamanhoPop][this.tamanhoCromo + 3];
    }

    public Integer[] start() {
        int i = 0;
        geraCromossomo(melhorCromo);
        int apAnt = melhorCusto;
        int cont = 0;
        avaliaPop();
        ordenaPop();
        mostrar();
//        for (int j = 0; j < tamanhoPop; j++) {
//            for (int k = 0; k < tamanhoCromo + 3; k++) {
//                System.out.print(popAtual[j][k] + " - ");
//            }
//            System.out.println("");
//        }
        System.out.println("---------------------------------------");
        do {
            trocaAleatoria();
            crossOverMultiponto();
            if (Math.random() < 0.05) {
                mutacao();
            }
            crossOver1Ponto();
            avaliaPop();
            ordenaPop();
            System.out.println(i + " - Melhor: " + popAtual[0][tamanhoCromo + 2] + " - Segundo Melhor: " + popAtual[1][tamanhoCromo + 2]);
//            mostrar();
//            if (melhorCusto == apAnt) {
//                cont++;
//            } else {
//                apAnt = melhorCusto;
//                cont = 0;
//            }
//            if ((cont > 100 && popAtual[1][tamanhoCromo + 2] > melhorCusto + 3000) || popAtual[1][tamanhoCromo + 2] > melhorCusto + 5000) {
//                geraCromossomo(melhorCromo);
//                cont = 0;
//            }
        } while (i++ < maxEpocas);
        
        ordenaPop();
        for (int j = 0; j < tamanhoPop; j++) {
            for (int k = 0; k < tamanhoCromo + 3; k++) {
                System.out.print(popAtual[j][k] + " - ");
            }
            System.out.println("");
        }
        return melhorCromo;
    }

    private void geraCromossomo(Integer[] melhorCromossomo) {
        System.arraycopy(melhorCromossomo, 0, popAtual[0], 0, tamanhoCromo + 3);
        ArrayList<Integer> a = new ArrayList<>();
        int inserir, rnd;
        for (int i = 1; i < tamanhoPop; i++) {
//            System.arraycopy(melhorCromossomo, 0, popAtual[i], 0, tamanhoCromo + 3);
            for (int h = 0; h < tamanhoCromo + 1; h++) {
                a.add(h);
            }
            popAtual[i][0] = this.inicio;
            popAtual[i][popAtual[i].length - 2] = this.inicio;
            for (int j = 1; j < tamanhoCromo + 1; j++) {
                rnd = (int) (Math.random() * a.size());
                inserir = a.remove(rnd);
                if (inserir != this.inicio) {
                    popAtual[i][j] = inserir;
                } else {
                    j--;
                }
            }
        }
    }

    private int avaliaPop() {
        int avalia = 0;
        for (int i = 0; i < tamanhoPop; i++) {
            avalia = funcaoObjetivo(popAtual[i]);
            popAtual[i][tamanhoCromo + 2] = avalia;
            if (avalia < melhorCusto) {
                melhorCusto = avalia;
                melhorCromo = popAtual[i];
            }
        }
        return avalia;
    }

    private void crossOver1Ponto() {
        Integer[][] popNova = new Integer[tamanhoPop][tamanhoCromo + 3];
        popNova[0] = melhorCromo;
        for (int t = 0; t < tamanhoPop - 2; t++) {
            Integer[] temp = Arrays.copyOfRange(popAtual[t], 1, (tamanhoCromo / 2) + 1);
            Integer[] temp2 = new Integer[(tamanhoCromo / 2) + 1];
            boolean tem = false;
            int t2 = 0;
            for (int i = 1; i < tamanhoCromo + 2 && t2 < temp2.length; i++) {
                for (int j = 0; j < temp.length; j++) {
                    if (popAtual[t + 1][i] == temp[j]) {
                        tem = true;
                    }
                }
                if (!tem) {
                    temp2[t2++] = popAtual[t + 1][i];
                }
                tem = false;
            }
            System.arraycopy(temp2, 0, popNova[t + 1], 1, temp2.length);
            System.arraycopy(temp, 0, popNova[t + 1], (tamanhoCromo / 2) + 2, temp.length);
            popNova[t + 1][0] = this.inicio;
            popNova[t + 1][tamanhoCromo + 1] = this.inicio;

            temp = new Integer[(tamanhoCromo / 2)];
            temp2 = Arrays.copyOfRange(popAtual[t], (tamanhoCromo / 2) + 1, tamanhoCromo + 1);
            t2 = 0;
            for (int i = 1; i < tamanhoCromo + 2 && t2 < temp.length; i++) {
                for (int j = 0; j < temp2.length; j++) {
                    if (popAtual[t + 1][i] == temp2[j]) {
                        tem = true;
                    }
                }
                if (!tem) {
                    temp[t2++] = popAtual[t + 1][i];
                }
                tem = false;
            }

            System.arraycopy(temp2, 0, popNova[t + 2], 1, temp2.length);
            System.arraycopy(temp, 0, popNova[t + 2], (tamanhoCromo / 2) + 2, temp.length);
            popNova[t + 2][0] = this.inicio;
            popNova[t + 2][tamanhoCromo + 1] = this.inicio;
        }
//        for (int i = 0; i < tamanhoPop; i++) {
//            for (int j = 0; j < tamanhoCromo + 3; j++) {
//                System.out.print(popAtual[i][j] + " - ");
//            }
//            System.out.println("");
//        }

//        for (int i = 0; i < tamanhoPop; i++) {;;
//            for (int j = 0; j < tamanhoCromo + 3; j++) {
//                System.out.print(popNova[i][j] + " - ");
//            }
//            System.out.println("");
//        }
        swapPop(popNova);
    }

    private void mutacao() {
        for (int i = 1; i < tamanhoPop; i++) {
            int A = (int) (1 + Math.random() * tamanhoCromo);
            int B = (int) (1 + Math.random() * tamanhoCromo);
            int aux = popAtual[i][A];
            popAtual[i][A] = popAtual[i][B];
            popAtual[i][B] = aux;
        }
    }

    private void trocaAleatoria() {
        Integer[] escolha = new Integer[(int) (20 + Math.random() * ((tamanhoCromo - 10) - 20))];
        Integer[] escolhaFinal = new Integer[escolha.length];
        Arrays.fill(escolha, -1);
        for (int t = 0; t < tamanhoPop; t++) {
            Arrays.fill(escolha, -1);
            int rnd = (int) (1 + Math.random() * 10);
            int custoAnt = Integer.MAX_VALUE;
            for (int i = 0; i < rnd; i++) {
                for (int j = 0; j < escolha.length; j++) {
                    int escolhido = (int) (1 + Math.random() * (tamanhoCromo));
                    boolean tem = false;
                    for (int k = 0; k < escolha.length; k++) {
                        if (popAtual[t][escolhido] == escolha[k]) {
                            tem = true;
                            break;
                        }
                    }
                    if (!tem) {
                        escolha[j] = popAtual[t][escolhido];
                    } else {
                        j--;
                    }
                }
                int custo = 0;
                for (int j = 0; j < escolha.length - 1; j++) {
                    custo += getCusto(escolha[j], escolha[j + 1]);
                }
                if (custo < custoAnt) {
                    custoAnt = custo;
                    System.arraycopy(escolha, 0, escolhaFinal, 0, escolha.length);
                }
            }
            int custo2 = 0;
            for (int i = 1; i > escolhaFinal.length; i++) {
                custo2 += popAtual[t][i];
            }

            if (custoAnt < custo2) {
                for (int i = 0; i < tamanhoCromo; i++) {
                    for (int j = 0; j < escolhaFinal.length; j++) {
                        if (popAtual[t][i] == escolhaFinal[j]) {
                            popAtual[t][i] = null;
                        }
                    }
                }

                int h = 1;
                for (int j = 0; j < escolha.length; j++) {
                    escolha[j] = popAtual[t][h++];
                }
                int o = 0;
                for (int i = tamanhoCromo; o < escolha.length; i--) {
                    if (popAtual[t][i] == null) {
                        if (escolha[o] != null) {
                            popAtual[t][i] = escolha[o];
                        } else {
                            i++;
                        }
                        o++;
                    }
                }

                int y = 0;
                for (int i = 1; y < escolhaFinal.length; i++) {
                    popAtual[t][i] = escolhaFinal[y++];
                }
            }
        }
    }

    public void crossOverMultiponto() {
        Integer[][] popNova = new Integer[tamanhoPop][tamanhoCromo + 3];
        popNova[0] = melhorCromo;
        Integer[] escolha = new Integer[tamanhoCromo + 3];
        int index2 = 1;
        for (int t = 0; t < tamanhoPop - 1; t += 1) {
            Arrays.fill(escolha, null);
            escolha[0] = popAtual[t][0];
            escolha[escolha.length - 2] = popAtual[t][tamanhoCromo + 1];
            int index = 1;
            for (int i = 1; index < escolha.length - 2;) {
                boolean tem = false;
                for (int r = 0; r < 5 && index < escolha.length - 2 && i < escolha.length - 1; r++) {
                    for (int j = 0; j < escolha.length; j++) {
                        if (escolha[j] == null) {
                            break;
                        }
                        if (escolha[j] == popAtual[t][i]) {
                            tem = true;
                            break;
                        }
                    }
                    if (!tem) {
                        escolha[index] = popAtual[t][i];
                        index++;
                    }
                    tem = false;
                    i++;
                }
                i -= 5;
                for (int r = 0; r < 5 && index < escolha.length - 2 && i < escolha.length - 1; r++) {
                    for (int j = 0; j < escolha.length; j++) {
                        if (escolha[j] == null) {
                            break;
                        }
                        if (escolha[j] == popAtual[t + 1][i]) {
                            tem = true;
                            break;
                        }
                    }
                    if (!tem) {
                        escolha[index] = popAtual[t + 1][i];
                        index++;
                    }
                    tem = false;
                    i++;
                }
                i -= 4;
            }
            popNova[index2++] = Arrays.copyOf(escolha, escolha.length);
        }
//        for (int i = 0; i < tamanhoPop; i++) {
//            for (int j = 0; j < tamanhoCromo + 3; j++) {
//                System.out.print(popNova[i][j] + " - ");
//            }
//            System.out.println("");
//        }
    }

    private void swapPop(Integer[][] popNova) {
        System.arraycopy(popNova, 0, popAtual, 0, tamanhoPop);
    }

    private void ordenaPop() {
        Arrays.sort(popAtual, 0, tamanhoPop, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return ((Integer) o1[o1.length - 1]).compareTo(o2[o2.length - 1]);
            }
        });
    }

    private int funcaoObjetivo(Integer[] cromossomo) {
        int fitness = 0;
        for (int i = 0; i < tamanhoCromo + 1; i++) {
            fitness += getCusto(cromossomo[i], cromossomo[i + 1]);
        }
        return fitness;
    }

    private Integer getCusto(int posA, int posB) {
        Integer custoAB = Caixeiro.getCusto(posA, posB);
        if (custoAB == null) {
            Integer custoBA = Caixeiro.getCusto(posB, posA);
            return custoBA;
        }
        return custoAB;
    }

    private void mostrar() {
        System.out.print("Melhor indivíduo: ");
        for (int i = 0; i < tamanhoCromo + 3; i++) {
            System.out.print(melhorCromo[i] + " - ");
        }
        System.out.println("");
//        System.out.println(melhorCromo[tamanhoCromo + 2]);
        System.out.println("Custo: " + melhorCusto);
    }
}
