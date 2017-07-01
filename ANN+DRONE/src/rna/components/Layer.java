/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rna.components;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrique Richter
 */
public class Layer {

    private List<Neuron> neurons;
    private int layerID;

    public Layer(int layer) {
        this.neurons = new ArrayList<>();
        this.layerID = layer;
    }

    public void addNeuron(Neuron neuron) {
        this.neurons.add(neuron);
    }

    public int getAmountNeurons() {
        return neurons.size();
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public int getLayer() {
        return layerID;
    }

    public void setLayer(int layer) {
        this.layerID = layer;
    }

}
