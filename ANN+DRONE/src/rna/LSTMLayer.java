package rna;

/**
 *
 * @author henrique
 */
public class LSTMLayer {

    private LSTMCell[] neurons;
    private double[] oldOutputs;
    private int neuronsAmount;
    private int inputs;

    public LSTMLayer(int neurons, int inputs) {
        this.neuronsAmount = neurons;
        this.inputs = inputs;
        this.neurons = new LSTMCell[neurons];
        this.oldOutputs = new double[neurons];
    }

    public void initiateWeights() {
        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = new LSTMCell((inputs)+neuronsAmount);
            neurons[i].generateWeights();
        }
    }

    public double[] step(double inputs[]) {
        double[] outputs = new double[neuronsAmount];
        double[] newInputs = new double[inputs.length + outputs.length];
        int inpLe = inputs.length;
        for (int i = 0; i < inputs.length; i++) {
            newInputs[i] = inputs[i];
        }
        for (int i = 0; i < outputs.length; i++) {
            newInputs[inpLe] = oldOutputs[i];
            inpLe++;
        }
        for (int i = 0; i < neurons.length; i++) {
            outputs[i] = neurons[i].step(newInputs);
        }
        oldOutputs = outputs;
        return outputs;
    }
    
    public double nextReward(int index, double[] distances, double distObj) {
        distances[index] -= 0.1;

        return 0;
    }

    public double nextState(int index, double[] distances) {
        distances[index] -= 0.1;
        double o[] = this.step(distances);
        double nextO = o[0];
        for (int i = 0; i < o.length; i++) {
            if (o[i] > nextO) {
                nextO = o[i];
            }
        }
        return nextO;
    }

    public double[] getLastSum(){
       double[] sum = new double[neuronsAmount];
        for (int i = 0; i < neurons.length; i++) {
            sum[i] = neurons[i].getLastSum();
        }
        return sum;
    }
    public double[] getLastOutput() {
        double[] sum = new double[neuronsAmount];
        for (int i = 0; i < neurons.length; i++) {
            sum[i] = neurons[i].getLastOutput();
        }
        return sum;
    }
    public LSTMCell[] getNeurons() {
        return neurons;
    }
}
