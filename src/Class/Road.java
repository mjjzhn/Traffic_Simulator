package Class;

import java.util.ArrayList;

public class Road {
    String id;
    int length;
    int speedLimit;
    int[] startLocation;
    int[] endLocation;
    ArrayList<Car> carsOnRoad;
    ArrayList<TrafficLight> lightsOnRoad;
    ArrayList<Road> connectedRoads;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int[] getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
    }

    public int[] getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(int[] endLocation) {
        this.endLocation = endLocation;
    }

    public ArrayList<Car> getCarsOnRoad() {
        return carsOnRoad;
    }

    public void setCarsOnRoad(ArrayList<Car> carsOnRoad) {
        this.carsOnRoad = carsOnRoad;
    }

    public ArrayList<TrafficLight> getLightsOnRoad() {
        return lightsOnRoad;
    }

    public void setLightsOnRoad(ArrayList<TrafficLight> lightsOnRoad) {
        this.lightsOnRoad = lightsOnRoad;
    }

    public ArrayList<Road> getConnectedRoads() {
        return connectedRoads;
    }

    public void setConnectedRoads(ArrayList<Road> connectedRoads) {
        this.connectedRoads = connectedRoads;
    }

    public Road(String id, int speedLimit, int length, int[] startLocation){
        this.id = "road_" + id;
        this.speedLimit = speedLimit;
        this.length = length;
        this.startLocation = startLocation;
        this.endLocation = new int[]{this.length + this.startLocation[0], 0};
    }

    public void showStatus(){
        System.out.printf("%s limit of:%dm/s is %dm long at location:%s to %s%n", this.getId(), this.getSpeedLimit(), this.getLength(), this.getStartLocation(), this.getEndLocation());
    }
}
