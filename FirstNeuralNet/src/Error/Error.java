package Error;

/**
 *
 * @author Henrique
 */
public class Error {

    public static void showError(Exception e, String msg) throws Exception {
        throw new Exception(msg, e);
    }
}
