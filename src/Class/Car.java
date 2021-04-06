package Class;

public class Car {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        this.breadth = breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setPosition(int a, int b) {
        this.position = new int[]{a,b};
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad = currentRoad;
    }

    public String getNextmove() {
        return nextmove;
    }

    public void setNextmove(String nextmove) {
        this.nextmove = nextmove;
    }

    public int getOnlane() {
        return onlane;
    }

    public void setOnlane(int onlane) {
        this.onlane = onlane;
    }

    String id;
    float length;
    float breadth;
    int speed;
    int[] position;
    Road currentRoad;
    String nextmove;
    int onlane;

    public Car(String id, Road currentRoad){
        this.id = "Car_" + id;
        this.currentRoad = currentRoad;
        length = 1f;
        breadth = length * 0.5f;
        speed = 0;
        position = currentRoad.getStartLocation();
        nextmove = "Straight";
        onlane = 1;
        this.currentRoad.getCarsOnRoad().add(this);
    }

    public Car() {
        id = "000";
        length = 1f;
        breadth = length * 0.5f;
        speed = 0;
        position = new int[]{0,0};
        nextmove = "Straight";
        onlane = 1;
    }

}
