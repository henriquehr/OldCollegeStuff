package Robot;

import coppelia.BoolW;
import coppelia.FloatWA;
import coppelia.IntW;
import coppelia.remoteApi;

/**
 *
 * @author henri
 */
public class API {

    private remoteApi vrep;
    private int clientID;
    private double xObjective, yObjective, xDrone, yDrone;
    private float firstHeight = 0.0f;

    public API() {
        vrep = new remoteApi();
        this.connect();
    }

    public void locateObj() {
        IntW objective = new IntW(0);
        FloatWA pos = new FloatWA(3);
        vrep.simxGetObjectHandle(clientID, "OBJ", objective, vrep.simx_opmode_oneshot_wait);
        vrep.simxGetObjectPosition(clientID, objective.getValue(), -1, pos, vrep.simx_opmode_oneshot_wait);
        xObjective = pos.getArray()[0];
        yObjective = pos.getArray()[1];
    }

    public void move(int direction) {
        IntW drone = new IntW(0);
        vrep.simxGetObjectHandle(clientID, "Quadricopter", drone, vrep.simx_opmode_oneshot_wait);
        IntW target = drone;
        FloatWA pos = new FloatWA(3);
        vrep.simxGetObjectPosition(clientID, drone.getValue(), -1, pos, vrep.simx_opmode_oneshot_wait);
        switch (direction) { // move | 0=Front,1=Back,2=Right,3=Left
            case 0:
                    pos = Move.moveFront(pos, firstHeight);
                    vrep.simxSetObjectPosition(clientID, target.getValue(), -1, pos, vrep.simx_opmode_oneshot);                
                break;
            case 1:
                    pos = Move.moveLeft(pos, firstHeight);
                    vrep.simxSetObjectPosition(clientID, target.getValue(), -1, pos, vrep.simx_opmode_oneshot);
                break;
            case 2:
                    pos = Move.moveBack(pos, firstHeight);
                    vrep.simxSetObjectPosition(clientID, target.getValue(), -1, pos, vrep.simx_opmode_oneshot);
                
                break;
            case 3:
                    pos = Move.moveRight(pos, firstHeight);
                    vrep.simxSetObjectPosition(clientID, target.getValue(), -1, pos, vrep.simx_opmode_oneshot);
                break;
            case 6:
                break;
        }
        xDrone = pos.getArray()[0];
        yDrone = pos.getArray()[1];
    }
    
    public int[] directionObj() {
        double x;
        double y;
        double z;
        double x2;
        double y2;
        double z2;
        double[] distance = new double[4];
        IntW objective = new IntW(0);
        FloatWA pos = new FloatWA(3);
        vrep.simxGetObjectHandle(clientID, "OBJ", objective, vrep.simx_opmode_oneshot_wait);
        vrep.simxGetObjectPosition(clientID, objective.getValue(), -1, pos, vrep.simx_opmode_oneshot_wait);
        x = pos.getArray()[0];
        y = pos.getArray()[1];
        z = pos.getArray()[2];
        xObjective = pos.getArray()[0];
        yObjective = pos.getArray()[1];
        if (firstHeight == 0) {
            firstHeight = 0.4f;
        }
        IntW propeller1 = new IntW(0);
        FloatWA propeller1Pos = new FloatWA(3);
        vrep.simxGetObjectHandle(clientID, "Proximity_sensor_Front", propeller1, vrep.simx_opmode_oneshot_wait);
        vrep.simxGetObjectPosition(clientID, propeller1.getValue(), -1, propeller1Pos, vrep.simx_opmode_oneshot_wait);
        x2 = propeller1Pos.getArray()[0];
        y2 = propeller1Pos.getArray()[1];
        z2 = propeller1Pos.getArray()[2];
        distance[0] = Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2) + Math.pow((z2 - z), 2));
        IntW propeller2 = new IntW(0);
        FloatWA propeller2Pos = new FloatWA(3);
        vrep.simxGetObjectHandle(clientID, "Proximity_sensor_Left", propeller2, vrep.simx_opmode_oneshot_wait);
        vrep.simxGetObjectPosition(clientID, propeller2.getValue(), -1, propeller2Pos, vrep.simx_opmode_oneshot_wait);
        x2 = propeller2Pos.getArray()[0];
        y2 = propeller2Pos.getArray()[1];
        z2 = propeller2Pos.getArray()[2];
        distance[1] = Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2) + Math.pow((z2 - z), 2));
        IntW propeller3 = new IntW(0);
        FloatWA propeller3Pos = new FloatWA(3);
        vrep.simxGetObjectHandle(clientID, "Proximity_sensor_Back", propeller3, vrep.simx_opmode_oneshot_wait);
        vrep.simxGetObjectPosition(clientID, propeller3.getValue(), -1, propeller3Pos, vrep.simx_opmode_oneshot_wait);
        x2 = propeller3Pos.getArray()[0];
        y2 = propeller3Pos.getArray()[1];
        z2 = propeller3Pos.getArray()[2];
        distance[2] = Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2) + Math.pow((z2 - z), 2));
        IntW propeller4 = new IntW(0);
        FloatWA propeller4Pos = new FloatWA(3);
        vrep.simxGetObjectHandle(clientID, "Proximity_sensor_Right", propeller4, vrep.simx_opmode_oneshot_wait);
        vrep.simxGetObjectPosition(clientID, propeller4.getValue(), -1, propeller4Pos, vrep.simx_opmode_oneshot_wait);
        x2 = propeller4Pos.getArray()[0];
        y2 = propeller4Pos.getArray()[1];
        z2 = propeller4Pos.getArray()[2];
        distance[3] = Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2) + Math.pow((z2 - z), 2));
        int[] index = new int[2];
        double nearest = distance[0];
        for (int i = 0; i < distance.length; i++) {
            if (nearest > distance[i]) {
                nearest = distance[i];
                index[0] = i;
            }
        }
        distance[index[0]] = 99;
        nearest = distance[0];
        for (int i = 0; i < distance.length; i++) {
            if (nearest > distance[i]) {
                nearest = distance[i];
                index[1] = i;
            }
        }
        return index;
    }

    public double[] readSensorsWalls() {
        double[] distances = new double[4];
        distances[0] = detect("Proximity_sensor_Front");
        distances[1] = detect("Proximity_sensor_Left");
        distances[2] = detect("Proximity_sensor_Back");
        distances[3] = detect("Proximity_sensor_Right");
        return distances;
    }

    public double[] readInputs() {
        double[] distances = new double[9];
        distances[0] = xDrone;
        distances[1] = yDrone;
        distances[2] = xObjective;
        distances[3] = yObjective;
        double[] distacesWalls = readSensorsWalls();
        distances[4] = distacesWalls[0];
        distances[5] = distacesWalls[1];
        distances[6] = distacesWalls[2];
        distances[7] = distacesWalls[3];
        distances[8] = 1; // bias
        return distances;
    }

    private double detect(String sensorName) {
        IntW sensor = new IntW(0);
        vrep.simxGetObjectHandle(clientID, sensorName, sensor, vrep.simx_opmode_oneshot_wait);
        BoolW ds = new BoolW(false);
        FloatWA detectPoint = new FloatWA(0);
        IntW detectedObjectHandle = new IntW(0);
        FloatWA detectedSurfaceVector = new FloatWA(0);
        vrep.simxReadProximitySensor(clientID, sensor.getValue(), ds, detectPoint, detectedObjectHandle, detectedSurfaceVector, vrep.simx_opmode_oneshot_wait);
        if (ds.getValue() == false) {
            return 0;
        }
        return 1;
    }

    private void connect() {
        vrep.simxFinish(-1); // close all opened connections
        int clientID = vrep.simxStart("127.0.0.1", 19999, true, true, 5000, 5);
        if (clientID != -1) {
            System.out.println("Connected to remote API server");
        } else {
            System.out.println("Failed connecting to remote API server");
            System.exit(0);
        }
    }

    public void disconnect() {
        if (clientID != -1 && vrep != null) {
            IntW pingTime = new IntW(0);
            vrep.simxGetPingTime(clientID, pingTime);
            // close the connection to V-REP:
            vrep.simxFinish(clientID);
            System.out.println("Connection to server closed");
        }
    }
}
