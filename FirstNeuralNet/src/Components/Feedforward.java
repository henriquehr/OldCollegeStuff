package Components;

import java.io.Serializable;




/**
 *
 * @author henrique
 */
public class Feedforward implements Serializable{
    private Layer layers[];

    public Feedforward(int qtdLayer) {
        layers = new Layer[qtdLayer];
    }

    public Layer[] getLayers() {
        return layers;
    }

}
