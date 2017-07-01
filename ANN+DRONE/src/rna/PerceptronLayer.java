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
public class PerceptronLayer {

    private Neuron[] neurons;
    private int neuronsAmount;
    private int inputs;

    public PerceptronLayer(int neurons, int inputs) {
        this.neuronsAmount = neurons;
        this.inputs = inputs;
        this.neurons = new Neuron[neurons];
    }

    public void initiateWeights() {
        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = new Neuron(inputs);
            neurons[i].generateWeights();
        }
    }

    public double[] step(double inputs[]) {
        double[] outputs = new double[neuronsAmount];
        for (int i = 0; i < neurons.length; i++) {
            outputs[i] = neurons[i].step(inputs);
        }
        return outputs;
    }

    public double[] getLastSum() {
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

    public Neuron[] getNeurons() {
        return neurons;
    }
}
