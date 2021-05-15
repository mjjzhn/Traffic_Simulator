package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import Class.Road;
import Class.TrafficLight;
import Class.Car;
import Class.Bus;
import Class.Motorbike;

public class Simulator extends JPanel {
    public enum Status{
        STOP,Running,Finsh
    }
    private Status state = Status.STOP;
    private int scale;
    private ArrayList<Road> roads;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<TrafficLight> lights;
    private Timer timer;
    private Boolean stop = true;
    private Random random = new Random();
    private static int cycle = 0;
    private int carsToSpawn = 2;
    private int carsSpawned = 0;
    private int carsRemoved = 0;
    private int numberOfCycles = 20;
    private int updateRate = 1000;

    public void loadMap(ArrayList<Road> roads, ArrayList<TrafficLight> lights) {
        this.roads = roads;
        this.lights = lights;
    }
    public void setcarspawn(int spawns) {
        this.carsToSpawn = spawns - 1;
        createcar();
    }
    public void setcarspawnRate(int rate) {
        this.numberOfCycles = rate;
    }

    private void createcar() {
        int randomcar = random.nextInt(3);
        switch (randomcar) {
            case 0:
                cars.add(new Car(Integer.toString(cycle), roads.get(0)));
                break;
            case 1:
                cars.add(new Bus(Integer.toString(cycle), roads.get(0)));
                break;
            case 2:
                cars.add(new Motorbike(Integer.toString(cycle), roads.get(0)));
                break;
        }
    }
    public void setScale(int scale) {
        this.scale = scale;
    }

    public void simulate() {
        setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 0));
        infoPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JLabel carLabel = new JLabel("cars: ");
        infoPanel.add(carLabel);
        JLabel averageSpeedLabel = new JLabel("Average Speed: ");
        infoPanel.add(averageSpeedLabel);
        JLabel stateLabel = new JLabel("State: " + state);
        infoPanel.add(stateLabel);
        add(infoPanel, BorderLayout.SOUTH);

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(updateRate / 60, e -> {
            cycle++;
            if (cars.size() == 0) {
                state = Status.Finsh;
            } else if (stop) {
                state = Status.STOP;
            } else {
                state = Status.Running;
            }
            stateLabel.setText("State: " + state);
            carLabel.setText("cars: " + getTotalcars());
            averageSpeedLabel.setText("Average Speed:" + getAverageSpeed());
            if (cars.size() == 0 || stop) {
                timer.stop();
            }
            if (cycle % 30 == 0) { //light operates every x tics
                for (TrafficLight light : lights) {
                    light.change(random.nextInt());
                    light.showStatus();
                }
            }
            for (Iterator<Car> iterator = cars.iterator(); iterator.hasNext(); ) {
                Car car = iterator.next();
//                car.setLane(Model.car.Lane.LEFT);
                car.move();
                car.showStatus();
                if (car.getPosition() + car.getLength() + car.getSpeed() >= car.getCurrentRoad().getLength() && car.getCurrentRoad().getConnectedRoads().isEmpty() && (car.getSpeed() == 0)) {
                    car.getCurrentRoad().getCarsOnRoad().remove(car);
                    iterator.remove();
                    carsRemoved++;
                }
            }

            if (cycle % numberOfCycles == 0 && carsSpawned < carsToSpawn) {
                createcar();
                carsSpawned++;
            }
            repaint();
        });
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Road road : roads
        ) {
            road.draw(g, scale);
        }

        if (!cars.isEmpty()) {
            for (Car car : cars
            ) {
                car.draw(g, scale);
            }
        }

        for (TrafficLight light : lights
        ) {
            light.draw(g, scale);
        }
    }

    private int getTotalcars() {
        return carsSpawned + 1 - carsRemoved;
    }

    private String getAverageSpeed() {
        int totalSpeed = 0;
        for (Car car : cars) {
            totalSpeed = totalSpeed + car.getSpeed();
        }
        if (!cars.isEmpty()) {
            int average = totalSpeed / cars.size();
            return average * 3.6 + "km/h";
        } else {
            return "0";
        }
    }

    public void setUpdateRate(int updateRate) {
        this.updateRate = updateRate;
    }


    public void setStopSim(Boolean stop) {
        this.stop = stop;
    }

}
