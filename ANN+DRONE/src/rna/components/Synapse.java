/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rna.components;

/**
 *
 * @author Henrique Richter
 */
public class Synapse {

    private final int id;
    private double weight;

    public Synapse(double weight, int id) {
        this.id = id;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(ID: " + id + ", Weight: " + weight+")";
    }
}
