package Class;

public class TrafficLight {
    String id;

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

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public String getStaright() {
        return staright;
    }

    public void setStaright(String staright) {
        this.staright = staright;
    }

    public String getLeftturn() {
        return leftturn;
    }

    public void setLeftturn(String leftturn) {
        this.leftturn = leftturn;
    }

    public String getRightturn() {
        return rightturn;
    }

    public void setRightturn(String rightturn) {
        this.rightturn = rightturn;
    }

    String status;
    int[] position;
    String roadid;
    int path;
    String staright;
    String leftturn;
    String rightturn;


}
