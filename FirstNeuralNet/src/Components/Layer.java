package Components;

import java.io.Serializable;

/**
 *
 * @author henrique
 */
public class Layer  implements Serializable{

    private Neuron neurons[];
    private double weights[][];

    public Layer(int qtdNeurons) {
        neurons = new Neuron[qtdNeurons];
    }

    public void addNeuron(Neuron neuron, int index) {
        this.neurons[index] = neuron;
    }

    public Neuron[] getNeurons() {
        return this.neurons;
    }

    public double[][] getMatrixWeights() {
        weights = new double[neurons.length][neurons[0].getWeights().length];
        for (int i = 0; i < weights.length; i++) {
                weights[i] = neurons[i].getWeights();
        }
        return weights;
    }
}
