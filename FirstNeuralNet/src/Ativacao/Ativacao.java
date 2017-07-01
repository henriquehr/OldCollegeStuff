package Ativacao;


/**
 *
 * @author henrique
 */
public class Ativacao{

    public static int degrau(double sum) {
        return sum >= 0 ? 1 : -1;
    }

    public static double tangenteHiperbolica(double sum) {
        return Math.tanh(sum);
    }
}
