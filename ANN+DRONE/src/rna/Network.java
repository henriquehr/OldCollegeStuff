/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rna;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author henrique
 */
public class Network {

    private PerceptronLayer hidden;
    private PerceptronLayer output;
    private int inputsCount = 9; //0=xObj, 1=yObj, 2=xDrone, 3=yDrone, 4-7=walls 8=bias  //0=x,1=y,2=Front,3=Back,4=Right,5=Left,6=Up,7=Down, 8= distanceObj, 9=Bias
    private double[] inputs;
    private double[] lastOutputs;
    private int lastBiggest;
    private int directionObj[];
    private int[] lastMoves;
    private int lastMoveCount;

    public Network() {
        hidden = new PerceptronLayer(256, inputsCount);
        output = new PerceptronLayer(4, hidden.getNeurons().length + 1); //+1 = bias
        lastMoves = new int[5];
        lastMoveCount = 0;
    }

    public void initiateWeights() {
        hidden.initiateWeights();
        output.initiateWeights();
    }

    public void setDirectionObj(int[] directionObj) {
        this.directionObj = directionObj;
    }

    public int step(double[] inputs) {
        this.inputs = inputs;
        double[] outHidden = hidden.step(inputs);
        double[] outHiddenBias = new double[outHidden.length + 1];
        for (int i = 0; i < outHidden.length; i++) {
            outHiddenBias[i] = outHidden[i];
        }
        outHiddenBias[outHiddenBias.length - 1] = 1;
        double[] out = output.step(outHiddenBias);
        lastOutputs = out;
        lastBiggest = 0;
        double b = out[0];
        for (int i = 0; i < out.length; i++) {
            if (out[i] > b) {
                b = out[i];
                lastBiggest = i;
            }
            System.out.print(" OUTPUT " + i + ": " + lastOutputs[i]);
        }
        return lastBiggest;
    }

    public void updateWeights(double[] inputs) {
        this.inputs = inputs;
        back();
    }

    private void back() {
        double learningRate = 0.02;
        double[] errorOutput = new double[output.getNeurons().length];
        double[] errorHidden = new double[hidden.getNeurons().length];
        double sum = 0.0;
        double targetObj[] = target();
        for (int i = 0; i < output.getNeurons().length; i++) {
            errorOutput[i] = lastOutputs[i] * Activation.sigmoidDeriv(lastOutputs[i]) * (targetObj[i] - lastOutputs[i]);
        }
        for (int i = 0; i < hidden.getNeurons().length; i++) {
            for (int j = 0; j < output.getNeurons().length; j++) {
                sum += output.getNeurons()[j].getWeights()[i] * errorOutput[j];
            }
            errorHidden[i] = hidden.getLastOutput()[i] * Activation.sigmoidDeriv(hidden.getLastOutput()[i]) * sum;

            sum = 0.0;
        }
        for (int j = 0; j < output.getNeurons().length; j++) {
            for (int i = 0; i < hidden.getNeurons().length; i++) {
                output.getNeurons()[j].getWeights()[i] += learningRate * errorOutput[j] * hidden.getLastOutput()[i];
            }
        }
        for (int i = 0; i < output.getNeurons().length; i++) { // bias
            output.getNeurons()[i].getWeights()[output.getNeurons()[i].getWeights().length - 1] += learningRate * errorOutput[i] * 1;
        }
        for (int j = 0; j < hidden.getNeurons().length; j++) {
            for (int i = 0; i < inputs.length; i++) {
                hidden.getNeurons()[j].getWeights()[i] += learningRate * errorHidden[j] * inputs[i];
            }
        }
    }

    private double[] target() {
        double values[] = {0.0, 0.0, 0.0, 0.0};
        double addOBJ = 0.4;
        double addWALL = 0.6;
        System.out.println("Dir: " + directionObj[0] + " & " + directionObj[1]);
        
        for (int i = 0; i < values.length; i++) {
            if(inputs[i+4] == 0){
                values[i] = addWALL;
            }
        }
        
        if (inputs[directionObj[0] + 4] == 0) {
            values[directionObj[0]] += addOBJ;
        }
        if (inputs[directionObj[1] + 4] == 0) {
            values[directionObj[1]] += 0.1;
        }


        System.out.println("VALUES: " + values[0] + " - " + values[1] + " - " + values[2] + " - " + values[3]);
        return values;
    }

    public void showNetwork() {
        System.out.println("Neurons hidden: " + hidden.getNeurons().length);
        for (int i = 0; i < hidden.getNeurons().length; i++) {
            for (int j = 0; j < hidden.getNeurons()[i].getWeights().length; j++) {
                System.out.println("Hidden " + i + ":" + j + ": " + hidden.getNeurons()[i].getWeights()[j]);
            }
        }
        System.out.println("Neurons output: " + output.getNeurons().length);
        for (int i = 0; i < output.getNeurons().length; i++) {
            for (int j = 0; j < output.getNeurons()[i].getWeights().length; j++) {
                System.out.println("Output " + i + ":" + j + ": " + output.getNeurons()[i].getWeights()[j]);
            }
        }
        for (int i = 0; i < output.getLastOutput().length; i++) {
            System.out.print("Output>> " + output.getLastOutput()[i] + " -- ");
        }
        System.out.println("");
        System.out.println("----------------------------------------");
    }

    public void save(String loc) {
        PrintWriter out = null;
        String txt = "";
        try {
            out = new PrintWriter(loc);
            for (int i = 0; i < hidden.getNeurons().length; i++) {
                for (int j = 0; j < hidden.getNeurons()[i].getWeights().length; j++) {
                    txt += String.valueOf(hidden.getNeurons()[i].getWeights()[j]) + "\n";
                }
            }
            txt += "---\n";
            for (int i = 0; i < output.getNeurons().length; i++) {
                for (int j = 0; j < output.getNeurons()[i].getWeights().length; j++) {
                    txt += String.valueOf(output.getNeurons()[i].getWeights()[j]) + "\n";
                }
            }
            out.println(txt);
            System.out.println("FILE SAVED");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR SAVING FILE " + ex.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public Network load(String loc) {
        Network nn = new Network();
        nn.initiateWeights();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(loc))) {
                String line;
                for (int i = 0; i < nn.hidden.getNeurons().length; i++) {
                    for (int j = 0; j < nn.hidden.getNeurons()[i].getWeights().length; j++) {
                        if ((line = br.readLine()) != null) {
                            nn.hidden.getNeurons()[i].setWeights(Double.parseDouble(line), j);
                        }
                    }
                }
                line = br.readLine();
                for (int i = 0; i < nn.output.getNeurons().length; i++) {
                    for (int j = 0; j < nn.output.getNeurons()[i].getWeights().length; j++) {
                        if ((line = br.readLine()) != null) {
                            nn.output.getNeurons()[i].setWeights(Double.parseDouble(line), j);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR LOADING FILE " + ex.getMessage());
        } finally {

        }
        return nn;
    }

}
