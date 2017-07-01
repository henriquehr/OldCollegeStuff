/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rna;

/**
 *
 * @author Henrique Richter
 */
public class Activation {

    public static int degrau(double x) {
        return x >= 0 ? 1 : -1;
    }

    public static double tanH(double x) {
        return Math.tanh(x);
    }

    public static double sigmoid(double x) {
        return (1 / (1 + Math.exp(-x)));
    }
    
    public static double tanHDeriv(double x){
        return (1.0 - Math.pow(Math.tanh(x), 2.0));
    }
    
    public static double sigmoidDeriv(double x){
        return Math.exp(-x)/Math.pow(1+Math.exp(-x),2);
    }
    
}
