package MultiThreading;

import java.util.Random;
import java.util.Scanner;

public class ElevatorSim {
    private Building building;
    private int time;
    private Elevator[] Elevators;
    private Thread[] threads;


    public ElevatorSim(int numFloors) {
        building = new Building(numFloors);
        time = 1000;

    }

    public void start(int numElevators,int numOfRequests) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Random gen = new Random();
        Elevators = new Elevator[numElevators];
        threads = new Thread[numElevators];
        // Random requests
        for (int i = 0; i < numOfRequests; i++) {
            int randomFloor = (gen.nextInt(building.getNumberOfFloors()));
            int randomDesti = (gen.nextInt(building.getNumberOfFloors()));
            int randomPassengers = (gen.nextInt(3))+1;
            if (randomFloor == randomDesti) {
                continue;
            }
            building.addFloorRequest(randomFloor, randomDesti, randomPassengers);
        }
        for (int i = 0; i < numElevators; i++) {
            Elevators[i] = new Elevator(i, building, i);
            threads[i] = new Thread(Elevators[i]);
        }
        for (int i = 0; i < numElevators; i++) {
            threads[i].start();
        }
        int choose = 0;
        while (Clock.getTime() <= time && !Thread.interrupted()) {
            System.out.println("1. Pickup");
            System.out.println("2. update");
            System.out.println("3. step");
            System.out.println("4. Elevator status");
            System.out.println("5. Building status");
            System.out.println("6. End simulation");
            choose = sc.nextInt();
            if (choose == 1) {
                System.out.println("Enter ID:");
                int choosenID = sc.nextInt();
                System.out.println("Enter floor number:");
                int choosenTarget = sc.nextInt();
                Elevators[choosenID].setTarget(choosenTarget);
            }

            if (choose == 2) {
                System.out.println("Enter number of floor");
                int floor = sc.nextInt();
                System.out.println("Enter destination floor");
                int destiFloor = sc.nextInt();
                System.out.println("Enter number of passengers");
                int numPassengers = sc.nextInt();
                building.addFloorRequest(floor, destiFloor, numPassengers);
            }

            if (choose == 3) {
                Clock.tick();
            }
            if (choose == 4) {
                System.out.println("Enter Elevator ID");
                int choosenID = sc.nextInt();
                Elevators[choosenID].status();
            }
            if (choose == 5) {
                building.statusBuilding();
            }
            if (choose == 6) {
                break;
            }
        }
        System.out.println("Destroying all elevators");
        for (int i = 0; i < numElevators; i++) {
            threads[i].interrupt();
        }
        building.statusBuilding();

    }
}
