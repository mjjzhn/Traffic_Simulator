package Class;

public class Bus extends Car{
    public Bus(String id, Road currentRoad){
        super(currentRoad);
        this.id = ("bus_" + id);
        setLength(super.getLength() * 3);
        position = -length;
    }
}
