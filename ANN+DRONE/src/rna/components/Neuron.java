
package rna.components;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henrique Richter
 */
public class Neuron {

    private final int id;

    private double bias;

    private List<Synapse> in;
    private List<Synapse> out;

    public Neuron(double bias, int id) {
        this.id = id;
        this.in = new ArrayList<>();
        this.out = new ArrayList<>();
        this.bias = bias;
    }

    public double getSum(double[] inputs) {
        double sum = 0.0;
        for (int i = 0; i < this.in.size(); i++) {
            sum += inputs[i] * this.in.get(i).getWeight();
        }
        sum *= this.bias;
        return sum;
    }

    public void updateWeight(int index, double value) {
        in.get(index).setWeight(in.get(index).getWeight() + value);
    }


    public void addIn(Synapse s) {
        in.add(s);
    }

    public void addOut(Synapse s) {
        out.add(s);
    }

    public int getId() {
        return id;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public List<Synapse> getIn() {
        return in;
    }

    public void setIn(List<Synapse> in) {
        this.in = in;
    }

    public List<Synapse> getOut() {
        return out;
    }

    public void setOut(List<Synapse> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "(ID: " + id + ", Bias: " + bias + ")";
    }

}
