package trabalho2;

import java.util.ArrayList;

/**
 *
 * @author henrique
 */
public class Caixeiro implements Runnable {

    private static Integer[][] arestas;
    private Rota[] arestasConstrutiva;
    private int inicio;
    private int tamanhoPop;
    private int maxEpocas;
    private int qtdCidades;

    public Caixeiro(Integer[][] arestas, Rota[] arestasConstrutiva, int inicio, int qtdCidades, int tamanhoPop, int maxEpocas) {
        Caixeiro.arestas = arestas;
        this.inicio = inicio;
        this.tamanhoPop = tamanhoPop;
        this.maxEpocas = maxEpocas;
        this.qtdCidades = qtdCidades;
        this.arestasConstrutiva = arestasConstrutiva;
    }

    private void inicio() {
        ArrayList<Rota> heuristicaConstrutiva = heuristicaConstrutiva();
        Integer[] solInicial = new Integer[heuristicaConstrutiva.size()+2];
        for (int i = 0; i < heuristicaConstrutiva.size(); i++) {
            solInicial[i] = heuristicaConstrutiva.get(i).getA().getId();
        }
        solInicial[solInicial.length-2] = heuristicaConstrutiva.get(heuristicaConstrutiva.size()-1).getB().getId();
        int distancia = 0;
        for (int i = 0; i < heuristicaConstrutiva.size(); i++) {
            distancia += heuristicaConstrutiva.get(i).getDistancia();
        }
        solInicial[solInicial.length-1] = distancia;
        
//        for (int i = 0; i < heuristicaConstrutiva.size(); i++) {
//            System.out.println(heuristicaConstrutiva.get(i).toString());
//        }
//        
//        for (int i = 0; i < solInicial.length; i++) {
//            System.out.print(solInicial[i] + " - ");
//        }
//        System.out.println("");
        
        AG ag = new AG(inicio, solInicial, qtdCidades, tamanhoPop, maxEpocas);
        Integer[] melhor = ag.start();
    }

    public static Integer getCusto(int posA, int posB) {
        return arestas[posA][posB];
    }

    @Override
    public void run() {
        inicio();
    }

    private ArrayList<Rota> heuristicaConstrutiva() {
        int size;
        boolean essaVaiSerUmaVariavelComUmNomeMuitoComprido = true, terminou = false;
        Rota rota = null;
        ArrayList<Integer> passou = new ArrayList<>();
        ArrayList<Rota> circuitoResultado = new ArrayList<>();

        size = qtdCidades;

        //acha a primeira rota apartir da cidade inicial
        for (int i = 0; i < arestasConstrutiva.length; i++) {
            if (arestasConstrutiva[i].getA().getId() != arestasConstrutiva[i].getB().getId()) {//evita A-A
                if (arestasConstrutiva[i].getA().getId() == inicio) {//A-B
                    rota = new Rota(arestasConstrutiva[i]);
                    break;
                } else if (arestasConstrutiva[i].getB().getId() == inicio) {//B-A
                    rota = new Rota(arestasConstrutiva[i]);
                    rota.setA(arestasConstrutiva[i].getB());
                    rota.setB(arestasConstrutiva[i].getA());
                    break;
                }
            }
        }
        //verifica se existe uma rota melhor que a primeira encontrada
        for (int i = 0; i < arestasConstrutiva.length; i++) {
            if (arestasConstrutiva[i].getA().getId() != arestasConstrutiva[i].getB().getId()) {//evita A-A
                if (arestasConstrutiva[i].getA().getId() == rota.getA().getId()) {//A-B
                    if (arestasConstrutiva[i].getA().getPrioridade() <= rota.getB().getPrioridade() && arestasConstrutiva[i].getDistancia() < rota.getDistancia()) {
                        rota = new Rota(arestasConstrutiva[i]);
                    }
                } else if (arestasConstrutiva[i].getB().getId() == rota.getA().getId()) {//B-A
                    if (arestasConstrutiva[i].getB().getPrioridade() <= rota.getB().getPrioridade() && arestasConstrutiva[i].getDistancia() < rota.getDistancia()) {
                        rota = new Rota(arestasConstrutiva[i]);
                        rota.setA(arestasConstrutiva[i].getB());
                        rota.setB(arestasConstrutiva[i].getA());
                    }
                }
            }
        }
        //atualiza a prioridade da cidade B
        for (int i = 0; i < arestasConstrutiva.length; i++) {
            if (arestasConstrutiva[i].getB().getId() == rota.getB().getId()) {
                arestasConstrutiva[i].getB().setPrioridade(rota.getB().getPrioridade() + 1);
            } else if (arestasConstrutiva[i].getA().getId() == rota.getB().getId()) {
                arestasConstrutiva[i].getA().setPrioridade(rota.getB().getPrioridade() + 1);
            }
        }
        //atualiza a prioridade da cidade A
        for (int i = 0; i < arestasConstrutiva.length; i++) {
            if (arestasConstrutiva[i].getB().getId() == rota.getA().getId()) {
                arestasConstrutiva[i].getB().setPrioridade(rota.getA().getPrioridade() + 1);
            } else if (arestasConstrutiva[i].getA().getId() == rota.getA().getId()) {
                arestasConstrutiva[i].getA().setPrioridade(rota.getA().getPrioridade() + 1);
            }
        }

        passou.add(rota.getA().getId());
        passou.add(rota.getB().getId());
        circuitoResultado.add(rota);

        //loop principal, encontra uma rota -> encontra uma rota maior prioridade -> encontra uma rota com menor distancia
        do {
            //acha a primeira rota
            for (int j = 0; j < arestasConstrutiva.length; j++) {
                if (arestasConstrutiva[j].getA().getId() != arestasConstrutiva[j].getB().getId()) {//evita A-A
                    if (arestasConstrutiva[j].getA().getId() == rota.getB().getId()) {//A-B
                        rota = new Rota(arestasConstrutiva[j]);
                        if (passou.size() >= size && rota.getB().getId() == inicio) {//verificador de saida
                            terminou = true;
                            break;
                        }
                        break;
                    } else if (arestasConstrutiva[j].getB().getId() == rota.getB().getId()) {//B-A
                        rota = new Rota(arestasConstrutiva[j]);
                        rota.setA(arestasConstrutiva[j].getB());
                        rota.setB(arestasConstrutiva[j].getA());
                        if (passou.size() >= size && rota.getB().getId() == inicio) {//verificador de saida
                            terminou = true;
                            break;
                        }
                        break;
                    }
                }
            }

            if (!terminou) {
                //acha a rota com a menor prioridade que a anterior
                for (int j = 0; j < arestasConstrutiva.length; j++) {
                    if (arestasConstrutiva[j].getA().getId() != arestasConstrutiva[j].getB().getId()) {//evita A-A
                        if (arestasConstrutiva[j].getA().getId() == rota.getA().getId()) {//A-B
                            if (arestasConstrutiva[j].getB().getPrioridade() <= rota.getB().getPrioridade()) {
                                rota = new Rota(arestasConstrutiva[j]);
                                if (passou.size() >= size && rota.getB().getId() == inicio) {//verificador de saida
                                    terminou = true;
                                    break;
                                }
                            }
                        } else if (arestasConstrutiva[j].getB().getId() == rota.getA().getId()) {//B-A
                            if (arestasConstrutiva[j].getA().getPrioridade() <= rota.getB().getPrioridade()) {
                                rota = new Rota(arestasConstrutiva[j]);
                                rota.setA(arestasConstrutiva[j].getB());
                                rota.setB(arestasConstrutiva[j].getA());
                                if (passou.size() >= size && rota.getB().getId() == inicio) {//verificador de saida
                                    terminou = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (!terminou) {
                // acha a rota com menor distancia que a anterior
                for (int i = 0; i < arestasConstrutiva.length; i++) {
                    if (arestasConstrutiva[i].getA().getId() != arestasConstrutiva[i].getB().getId()) {//evita A-A
                        if (arestasConstrutiva[i].getA().getId() == rota.getA().getId()) { //A-B
                            if (arestasConstrutiva[i].getB().getPrioridade() <= rota.getB().getPrioridade() && arestasConstrutiva[i].getDistancia() < rota.getDistancia()) {
                                rota = new Rota(arestasConstrutiva[i]);
                                if (passou.size() >= size && rota.getB().getId() == inicio) {//verificador de saida
                                    terminou = true;
                                    break;
                                }
                            }
                        } else if (arestasConstrutiva[i].getB().getId() == rota.getA().getId()) {//B-A
                            if (arestasConstrutiva[i].getA().getPrioridade() <= rota.getB().getPrioridade() && arestasConstrutiva[i].getDistancia() < rota.getDistancia()) {
                                rota = new Rota(arestasConstrutiva[i]);
                                rota.setA(arestasConstrutiva[i].getB());
                                rota.setB(arestasConstrutiva[i].getA());
                                if (passou.size() >= size && rota.getB().getId() == inicio) {//verificador de saida
                                    terminou = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            //atualiza a prioridade da cidade B
            for (int i = 0; i < arestasConstrutiva.length; i++) {
                if (arestasConstrutiva[i].getB().getId() == rota.getB().getId()) {
                    arestasConstrutiva[i].getB().setPrioridade(rota.getB().getPrioridade() + 1);
                } else if (arestasConstrutiva[i].getA().getId() == rota.getB().getId()) {
                    arestasConstrutiva[i].getA().setPrioridade(rota.getB().getPrioridade() + 1);
                }
            }
            if (!passou.contains(rota.getB().getId())) {
                passou.add(rota.getB().getId());
            }
            circuitoResultado.add(rota);

            //zera as prioridades
            if (passou.size() >= size && essaVaiSerUmaVariavelComUmNomeMuitoComprido) {
                for (int i = 0; i < arestasConstrutiva.length; i++) {
                    arestasConstrutiva[i].getA().setPrioridade(0);
                    arestasConstrutiva[i].getB().setPrioridade(0);
                }
                essaVaiSerUmaVariavelComUmNomeMuitoComprido = false;
            }
        } while (!terminou);
        return circuitoResultado;
    }
}
