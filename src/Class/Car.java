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
    int position;
    Road currentRoad;
    String nextmove;
    int onlane;

    public Car(String id, Road currentRoad){
        this.id = "Car_" + id;
        this.currentRoad = currentRoad;
        length = 1f;
        breadth = length * 0.5f;
        speed = 0;
        position = 1;
        nextmove = "Straight";
        onlane = 1;
        this.currentRoad.getCarsOnRoad().add(this);
    }

    public Car() {
        id = "000";
        length = 1f;
        breadth = length * 0.5f;
        speed = 0;
        position = 1;
        nextmove = "Straight";
        onlane = 1;
    }
    public void showStatus(){
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(),
                this.getCurrentRoad().getId(), this.getPosition());
    }

    public void move(){
        this.speed =this.currentRoad.getSpeedLimit();
        if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.position == this.currentRoad.getLightsOnRoad().get(0).getPosition()
                && this.currentRoad.getLightsOnRoad().get(0).getStatus().equals("Red")){
            this.speed = 0;
        }else {
            this.speed = this.currentRoad.getSpeedLimit();
            if (this.currentRoad.getLength() == this.getPosition() && !this.currentRoad.getConnectedRoads().isEmpty()) {
                this.currentRoad.getCarsOnRoad().remove(this);
                this.currentRoad = this.currentRoad.getConnectedRoads().get(0);
                this.currentRoad.getCarsOnRoad().add(this);
                this.position = 1;
            } else if (this.currentRoad.getLength() > this.getPosition()) {
                this.position = (this.position + this.speed);
            } else {
                this.speed = 0;
            }
        }
    }

}
