/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rna;

/**
 *
 * @author henrique
 */
public class Neuron {

    private double weights[];
    private int inputsAmount;
    private double sum;
    private double lastOutput;

    public Neuron(int inputsAmount) {
        this.inputsAmount = inputsAmount;
        this.weights = new double[inputsAmount];  // last is bias
    }

    public void generateWeights() {
        double min = -0.9;
        double max = 0.9;
        double range = (max - min);
        for (int i = 0; i < inputsAmount; i++) {
            weights[i] = (double) (Math.random() * range) + min;
        }
    }

    public double sum(double inputs[], double weights[]) {
        double sum = 0.0;
        for (int j = 0; j < inputs.length; j++) {
            sum += inputs[j] * weights[j];
        }
        return sum;
    }

    public void updateWeights(int weightIndex, double newWeight) {
        weights[weightIndex] = newWeight;
    }

    public double step(double inputs[]) {
        double sumInput = this.sum(inputs, weights); // sum inputs
        sum = sumInput;
        double actInputs = Activation.sigmoid(sumInput); // activation function inputs
        lastOutput = actInputs;
        return actInputs;
    }

    public double getLastSum() {
        return sum;
    }

    public double getLastOutput() {
        return lastOutput;
    }

    public double[] getWeights() {
        return weights;
    }
    
    public void setWeights(double weight, int index) {
         weights[index] = weight;
    }
    
    public int getInputsAmount(){
        return inputsAmount;
    }
}
