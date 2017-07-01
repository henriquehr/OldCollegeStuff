
package trabalhofinal.Mapa;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Henrique
 */
public class Tempo implements Serializable{

    private String nome;
    private String dificuldade;
    private ArrayList<String> tempos;

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getTempos() {
        return tempos;
    }

    public void setTempos(ArrayList<String> tempos) {
        this.tempos = tempos;
    }


}
