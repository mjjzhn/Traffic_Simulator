package Class;

import java.awt.*;
import java.util.Random;

public class TrafficLight {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    String id;
    String status;
    int position;
    Road road;

    public TrafficLight (String id, Road road){
        this.id = "TrafficLight_" + id;
        this.status = "Red";
        this.road = road;
        this.position = road.getLength();
        road.getLightsOnRoad().add(this);
    }

    public void change(int num){
        Random rand = new Random(num);
        double prob = rand.nextDouble();
        if (prob > 0.5){
            this.setStatus("Green");
        }else {
            this.setStatus("Red");
        }
    }

    public void showStatus(){
        System.out.printf("%s is:%s on %s at position:%s%n", this.getId(), this.getStatus(), this.getRoad().getId(), this.getPosition());
    }

    public void draw(Graphics g, int scale) {
        if (road.getOrient() == Road.Orientation.HORIZONTAL) {
            switch (status) {
                case "red":
                    g.setColor(Color.red);
                    break;
                case "green":
                    g.setColor(Color.green);
            }
            int[] startLocation = getRoad().startLocation;
            int x = (getPosition() + startLocation[0]) * scale;
            int y = startLocation[1] * scale;
            int height = (getRoad().getWidth() / 2) * scale;
            g.fillRect(x, y, scale, height);
        }
        if (road.getOrient() == Road.Orientation.Vertical) {
            switch (status) {
                case "red":
                    g.setColor(Color.red);
                    break;
                case "green":
                    g.setColor(Color.green);
            }
            int[] startLocation = getRoad().getStartLocation();
            int x = (startLocation[0] + (getRoad().getWidth() / 2)) * scale;
            int y = (getPosition() + startLocation[1]) * scale;
            int width = (getRoad().getWidth() / 2) * scale;
            g.fillRect(x, y, width, scale);
        }
    }
}
