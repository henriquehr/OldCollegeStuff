package Components;

import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author henrique
 */
public class Neuron  implements Serializable{

    private double weight[];

    public Neuron(int connectionsWeight) {
        this.weight = new double[connectionsWeight];
    }

    public double[] getWeights() {
        return weight;
    }

    public void setWeights(double[] weight) {
        this.weight = weight;
    }

    public void generateWeight(int min, int max) {
        for (int i = 0; i < weight.length; i++) {
            weight[i] = (Math.random() * (max - min)) + min;
        }
    }

    public void updateWeight(int index, double value) {
        weight[index] += value;
    }

    public double getSum(double[] input) {
        double sum;
        sum = input[input.length-1] * weight[weight.length - 1];
        for (int i = 0; i < weight.length - 1; i++) {
            sum += input[i] * weight[i];
        }
        return sum;
    }
}
