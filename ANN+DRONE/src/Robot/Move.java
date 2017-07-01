package Robot;

import coppelia.FloatWA;

/**
 * Created by hr on 09/09/2015.
 */
public class Move {

    private static float step = 0.1f;

    public static FloatWA moveLeft(FloatWA pos, float firstHeight) {
        pos.getArray()[1] = pos.getArray()[1] + step;
        pos.getArray()[2] = firstHeight;
        return pos;
    }

    public static FloatWA moveRight(FloatWA pos, float firstHeight) {
        pos.getArray()[1] = pos.getArray()[1] - step;
        pos.getArray()[2] = firstHeight;
        return pos;
    }

    public static FloatWA moveFront(FloatWA pos, float firstHeight) {
        pos.getArray()[0] = pos.getArray()[0] + step;
        pos.getArray()[2] = firstHeight;
        return pos;
    }

    public static FloatWA moveBack(FloatWA pos, float firstHeight) {
        pos.getArray()[0] = pos.getArray()[0] - step;
        pos.getArray()[2] = firstHeight;
        return pos;
    }
}
