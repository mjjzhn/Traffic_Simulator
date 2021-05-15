package Class;

import java.awt.*;
import java.util.ArrayList;

public class Road {

    public enum Orientation{
        HORIZONTAL, Vertical
    }


    private Orientation orient;
    String id;
    int length;
    int speedLimit;
    int width;
    int[] startLocation;
    int[] endLocation;
    ArrayList<Car> carsOnRoad = new ArrayList<>();
    ArrayList<TrafficLight> lightsOnRoad = new ArrayList<>();
    ArrayList<Road> connectedRoads = new ArrayList<>();

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Orientation getOrient() {
        return orient;
    }

    public void setOrient(Orientation orient) {
        this.orient = orient;
    }

    public Road(String id, int speedLimit, int length, int[] startLocation, Orientation orient){
        this.id = "road_" + id;
        this.speedLimit = speedLimit;
        this.length = length;
        width = 8;
        this.orient = orient;
        this.startLocation = startLocation;
        this.endLocation = new int[]{this.length + this.startLocation[0], 0};
        setEndLocation();
    }

    public void showStatus(){
        System.out.printf("%s limit of:%dm/s is %dm long at location:%s to %s%n", this.getId(), this.getSpeedLimit(), this.getLength(), this.getStartLocation(), this.getEndLocation());
    }
    private void setEndLocation() {
        if (orient == Orientation.HORIZONTAL) {
            this.endLocation = new int[]{this.length + this.startLocation[0], this.startLocation[1]};
        } else if (orient == Orientation.Vertical) {
            this.endLocation = new int[]{this.startLocation[1], this.length + this.startLocation[0]};
        }
    }

    public void draw(Graphics g, int scale){
        if (orient == Orientation.Vertical){
            int[] startLocation = this.startLocation;
            int x = startLocation[0] * scale;
            int y = startLocation[1] * scale;
            int width = this.width * scale;
            int height = length * scale;
            g.setColor(Color.darkGray);
            g.fillRect(x, y, width, height);
            g.setColor(Color.white);
            g.fillRect(x + (width / 2) - scale / 6, y, scale / 6, height);
            g.fillRect(x + (width / 2) + scale / 6, y, scale / 6, height);

        }else if(orient == Orientation.HORIZONTAL){
            int[] startLocation = this.startLocation;
            int x = startLocation[0] * scale;
            int y = startLocation[1] * scale;
            int width = length * scale;
            int height = this.width * scale;
            g.setColor(Color.darkGray);
            g.fillRect(x, y, width, height);
            g.setColor(Color.white);
            g.fillRect(x, y + (height / 2) - scale / 6, width, scale / 6);
            g.fillRect(x, y + (height / 2) + scale / 6, width, scale / 6);
        }
    }
}
