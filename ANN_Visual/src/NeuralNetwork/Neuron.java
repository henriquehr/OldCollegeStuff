package NeuralNetwork;

/**
 *
 * @author henrique
 */
public class Neuron {

    private Synapse connectionsIn[];
    private Synapse connectionsOut[];
    private int layer;
    private float bias;

    public Neuron(int connectionsInAmount, int connectionsOutAmount, float bias, int layer) {
        this.connectionsIn = new Synapse[connectionsInAmount];
        this.connectionsOut = new Synapse[connectionsOutAmount];
        this.bias = bias;
        this.layer = layer;
    }

    public Neuron(float bias, int layer) {
        this.bias = bias;
        this.layer = layer;
    }

    public Synapse[] getConnectionsIn() {
        return connectionsIn;
    }

    public Synapse[] getConnectionsOut() {
        return connectionsIn;
    }

//    public double[] getWeights() {
//        double weights[] = new double[connections.length];
//        for (int i = 0; i < weights.length; i++) {
//            weights[i] = connections[i].getWeight();
//        }
//        return weights;
//    }
    public void setConnectionIn(int index, Neuron in, double weight) {
        this.connectionsIn[index] = new Synapse(in, this, weight);
    }

    public void setConnectionOut(int index, Neuron out, double weight) {
        this.connectionsOut[index] = new Synapse(this, out, weight);
    }

    public void generateWeights(int min, int max) {
        for (Synapse connection : connectionsIn) {
            connection.setWeight((Math.random() * (max - min)) + min);
        }
    }

    public void updateWeight(int index, double value) {
        connectionsIn[index].setWeight(connectionsIn[index].getWeight() + value);
    }

    public double getSum(double[] input) {
        double sum;
        sum = input[input.length - 1] * connectionsIn[connectionsIn.length - 1].getWeight();
        for (int i = 0; i < connectionsIn.length - 1; i++) {
            sum += input[i] * connectionsIn[i].getWeight();
        }
        return sum;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public float getBias() {
        return bias;
    }

    public void setBias(float bias) {
        this.bias = bias;
    }
}
