package rna;

/**
 *
 * @author henrique
 */
public class LSTMCell {

    private double weightsInput[];
    private double weightsInputGate[];
    private double weightsForgetGate[];
    private double weightsOutputGate[];
    private double oldValue;
    private int inputsAmount;
    private double sum;
    private double lastOutput;

    public LSTMCell(int inputsAmount) {
        this.oldValue = 0.0;
        this.inputsAmount = inputsAmount;
        this.weightsInput = new double[inputsAmount];  // last is bias
        this.weightsInputGate = new double[inputsAmount];  // last is bias
        this.weightsForgetGate = new double[inputsAmount];  // last is bias
        this.weightsOutputGate = new double[inputsAmount];  // last is bias
    }

    public void generateWeights() {
        for (int i = 0; i < inputsAmount; i++) {
            weightsInput[i] = (double) Math.random();
            weightsInputGate[i] = (double) Math.random();
            weightsForgetGate[i] = (double) Math.random();
            weightsOutputGate[i] = (double) Math.random();
        }
    }

    public double sum(double inputs[], double weights[]) {
        double sum = 0.0;
        for (int j = 0; j < inputs.length; j++) {
            sum += inputs[j] * weights[j];
        }
        return sum;
    }

    public void updateWeightsInput(int weightIndex, double newWeight) {
        weightsInput[weightIndex] = +newWeight;
    }

    public void updateWeightsInputGate(int weightIndex, double newWeight) {
        weightsInputGate[weightIndex] = +newWeight;
    }

    public void updateWeightsForgetGate(int weightIndex, double newWeight) {
        weightsForgetGate[weightIndex] = +newWeight;
    }

    public void updateWeightsOutputGate(int weightIndex, double newWeight) {
        weightsOutputGate[weightIndex] = +newWeight;
    }

    public double step(double inputs[]) {
        //input
        double sumInput = this.sum(inputs, weightsInput); // sum inputs
        double actInputs = Activation.tanH(sumInput); // activation function inputs
        double sumInputGate = this.sum(inputs, weightsInputGate); // sum inputs
        double actInputGate = Activation.sigmoid(sumInputGate); // activation function input gate
        double multInpXInpGate = actInputs * actInputGate; // multiply input * input gate
        //forget
        double sumForgetGate = this.sum(inputs, weightsForgetGate); // sum inputs
        double actForgetGate = Activation.sigmoid(sumForgetGate); // activation function forget gate
        double forget = actForgetGate * this.oldValue; // multiply forget gate * past value
        double newValue = multInpXInpGate + forget;
        this.oldValue = newValue;
        //output
        double actOutput = Activation.tanH(newValue); // activation function output
        double sumOutputGate = this.sum(inputs, weightsOutputGate); // sum inputs
        sum = sumOutputGate;
        double actOutputGate = Activation.sigmoid(sumOutputGate); // activation function output gate
        double out = actOutput * actOutputGate; // multiply output gate * output
        lastOutput = out;
        return out;
    }

    public double[] getWeights() {
        return weightsInput;
    }

    public double[] getWeightsInput() {
        return weightsInput;
    }

    public double[] getWeightsInputGate() {
        return weightsInputGate;
    }

    public double[] getWeightsForgetGate() {
        return weightsForgetGate;
    }

    public double[] getWeightsOutputGate() {
        return weightsOutputGate;
    }

    public double getLastSum() {
        return sum;
    }

    public double getLastOutput() {
        return lastOutput;
    }

}
