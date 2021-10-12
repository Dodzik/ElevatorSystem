package MultiThreading;

import java.util.Scanner;


public class Test {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of elevators:");
        int numberOfElevators = sc.nextInt();
        while (numberOfElevators < 1) {
            System.out.println("The number of elevators must be greater than 0:");
            numberOfElevators = sc.nextInt();
        }
        System.out.println("Enter number of floors:");
        int numberOfFloors = sc.nextInt();
        while (numberOfFloors <= numberOfElevators) {
            System.out.println("The number of floors must be greater than number of elevators:");
            numberOfFloors = sc.nextInt();
        }
        System.out.println("Enter number of requestes:");
        int numberOfRequests = sc.nextInt();
        ElevatorSim simulation = new ElevatorSim(numberOfFloors);
        simulation.start(numberOfElevators, numberOfRequests);
    }
}
