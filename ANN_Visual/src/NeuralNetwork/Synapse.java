package NeuralNetwork;

/**
 *
 * @author Henrique
 */
public class Synapse {

    private Neuron in;
    private Neuron out;
    private double weight;

    public Synapse(Neuron in, Neuron out, double weight) {
        this.in = in;
        this.out = out;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Neuron getIn() {
        return in;
    }

    public void setIn(Neuron in) {
        this.in = in;
    }

    public Neuron getOut() {
        return out;
    }

    public void setOut(Neuron out) {
        this.out = out;
    }

    public void setInOut(Neuron in, Neuron out) {
        this.in = in;
        this.out = out;
    }

}
