package Class;

import java.awt.*;
import java.util.Random;

public class Car {
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

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad = currentRoad;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    String id;
    int length;
    int breadth;
    int speed;
    int position;
    Road currentRoad;
    private Color colour;
    private Random random = new Random();

    private Color randomColour() {
        int r = random.nextInt(245 + 1) + 10;
        int g = random.nextInt(245 + 1) + 10;
        int b = random.nextInt(245 + 1) + 10;
        return new Color(r, g, b);
    }

    public Car(String id, Road currentRoad){
        this.id = "Car_" + id;
        this.currentRoad = currentRoad;
        length = 4;
        breadth = 2;
        speed = 0;
        position = 1;
        this.currentRoad.getCarsOnRoad().add(this);
    }
    public Car(Road currentRoad) {
        id = "000";
        length = 4;
        breadth = 2;
        speed = 0;
        this.currentRoad = currentRoad;
        currentRoad.getCarsOnRoad().add(this);
        colour = randomColour();
    }

    public Car() {
        id = "000";
        length = 0;
        breadth = 0;
        speed = 0;
        position = 1;
    }
    public void showStatus(){
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(),
                this.getCurrentRoad().getId(), this.getPosition());
    }

    public void move(){
        Random random = new Random();
        int nextPosition = position + length + speed;
        for (Car nextCar : currentRoad.getCarsOnRoad()) {
            if (nextCar.position > position && nextCar.position <= nextPosition + 4) {
                speed = 0;
                break;
            } else {
                speed = currentRoad.getSpeedLimit();
            }
        }
        if (speed == 0) {
        } else {
            if (!currentRoad.getLightsOnRoad().isEmpty() && nextPosition + 1 >= currentRoad.getLightsOnRoad().get(0).getPosition() && this.currentRoad.getLightsOnRoad().get(0).getStatus().equals("red")) {
                speed = 0;
            } else {
                speed = currentRoad.getSpeedLimit();
                if (currentRoad.getLength() <= nextPosition && !currentRoad.getConnectedRoads().isEmpty()) {
                    currentRoad.getCarsOnRoad().remove(this);
                    int nextRoadIndex = random.nextInt(2);
                    currentRoad = currentRoad.getConnectedRoads().get(nextRoadIndex);
                    currentRoad.getCarsOnRoad().add(this);
                    position = 0;
                } else if (currentRoad.getLength() >= nextPosition) {
                    position = (position + speed);
                } else {
                    speed = 0;
                }
            }
        }
    }

    public void draw(Graphics g, int scale) {
        int xValue = 0;
        int yValue = 1;
        if (currentRoad.getOrient() == Road.Orientation.HORIZONTAL) {
            int[] startLocation = getCurrentRoad().getStartLocation();
            int width = getLength() * scale;
            int height = getBreadth() * scale;
            int x = (getPosition() + startLocation[xValue]) * scale;
            int y = (startLocation[yValue] * scale) + scale;
            g.setColor(colour);
            g.fillRect(x, y, width, height);
        } else if (currentRoad.getOrient() == Road.Orientation.Vertical) {
            int[] startLocation = getCurrentRoad().getStartLocation();
            int width = getBreadth() * scale;
            int height = getLength() * scale;
            int x = (startLocation[xValue] * scale) + ((currentRoad.getWidth() * scale) - (width + scale));
            int y = (getPosition() + startLocation[yValue]) * scale;
            g.setColor(colour);
            g.fillRect(x, y, width, height);
        }
    }

}
