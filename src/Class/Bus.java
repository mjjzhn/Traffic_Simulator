package Class;

public class Bus extends Car{
    public Bus(String id){
        this.id = "bus_" + id;
        this.length = super.getLength() * 3;
    }
}
