package Class;

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

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public String getRoadid() {
        return roadid;
    }

    public void setRoadid(String roadid) {
        this.roadid = roadid;
    }

    String id;
    String status;
    int[] position;
    String roadid;

    public TrafficLight (String id, Road road){
        this.id = "TrafficLight_" + id;
        this.status = "Red";
        this.roadid = road.getId();
        this.position = road.getEndLocation();
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
        System.out.printf("%s is:%s on %s at position:%s%n", this.getId(), this.getStatus(), this.getRoadid(), this.getPosition());
    }


}
