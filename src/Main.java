import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Class.Car;
import Class.Road;
import Class.TrafficLight;


public class Main {
    public static void main(String[] args) {
        Scanner simController = new Scanner(System.in);

        int roadSpawns = 2;
        int carSpawns = 1;
        int lightSpawns = 1;

        System.out.println("Object Creation:\n---------------------");
        System.out.println("Roads:");
        ArrayList<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            System.out.println("Please input parameters for road_" + i + "...");
            System.out.print("Length:");
            int lengthInput = simController.nextInt();
            int speedLimitInput = 1;
            roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));
        }
        System.out.println("\nRoads;");
        for (Road road : roads
        ) {
            road.showStatus();
        }

        System.out.println("\nCars;");
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0)));
            cars.get(i).showStatus();
        }

        System.out.println("\nTraffic Lights;");
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight(Integer.toString(i), roads.get(0)));
            lights.get(i).showStatus();
        }
        System.out.println();

        System.out.println("Settings:");
        roads.get(1).setStartLocation(new int[]{roads.get(0).getLength() + 1, 0});
        roads.get(1).showStatus();
        roads.get(0).getConnectedRoads().add(roads.get(1));
        System.out.println();


        //Simulation loop:
        System.out.println("Simulation:");
        Random random = new Random();
        int time = 0;
        System.out.print("Set time scale in milliseconds:");
        int speedOfSim = simController.nextInt();
        int carsFinished = 0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                light.change(random.nextInt());
                light.showStatus();
            }
            for (Car car : cars) {
                car.move();
                car.showStatus();
                if (car.getCurrentRoad().getConnectedRoads().isEmpty() && (car.getSpeed() == 0)) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim);
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
