package Class;

import java.util.ArrayList;

public class Road {
    String id;
    float length;
    int speedLimit;
    int[] startLocation;
    int[] endLocation;
    ArrayList<Car> carsOnRoad;
    ArrayList<TrafficLight> lightsOnRoad;
    ArrayList<Road> connectedRoads;
}
