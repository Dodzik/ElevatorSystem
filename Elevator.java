package MultiThreading;

import java.util.ArrayList;

public class Elevator implements Runnable {
    private int ID;
    private Building building;
    private int numPassengers;
    private int positionFloor;
    private int[] requests;
    private ArrayList<ElevatorMove> moveQue;
    private int target;
    private boolean up;

    public Elevator(int num, Building build, int firstPosition) {
        ID = num;
        numPassengers = 0;
        positionFloor = firstPosition;
        building = build;
        target = -1;
        up = true;
        moveQue = new ArrayList<ElevatorMove>();
        requests = new int[building.getNumberOfFloors()];
        System.out.println("Creating elevetor ID: " + ID);
    }

    public void run() {
        while (!Thread.interrupted()) {
            if (moveQue.isEmpty() && numPassengers == 0) next();
            if (!moveQue.isEmpty()) {
                if (moveQue.get(0).getArrTime() == Clock.getTime()) {
                    positionFloor = moveQue.get(0).getDesti();
                    if (requests[positionFloor] > 0) {
                        System.out.println("Time: " + Clock.getTime() + " Elevator: " + ID + " unloaded: " + requests[positionFloor] + " passengers at floor: " + positionFloor);
                        unloadPassengers(requests[positionFloor]);
                        moveQue.remove(0);
                    } else {
                        if (up == true) {
                            searchPassengersUP(10);
                        } else {
                            searchPassengersDOWN(10);
                        }
                        moveQue.remove(0);

                        target = -1;
                    }
                }

            }

        }
    }

    public void loadPassengers(int floor, int num) {
        numPassengers += num;
        requests[floor] += num;
        building.setActNumPassengers(positionFloor, building.getActNumPassengers(positionFloor) - num);
        building.setFloorRequest(positionFloor, floor, 0);
        System.out.println("Time: " + Clock.getTime() + " Elevator: " + ID + " loaded: " + requests[floor] + " passengers at floor: " + positionFloor);
    }

    public void unloadPassengers(int num) {
        numPassengers -= num;
        requests[positionFloor] -= num;
    }

    public void searchPassengersUP(int delay) {
        int count = 0;
        for (int i = positionFloor + 1; i < building.getNumberOfFloors(); i++) {
            if (building.getFloorRequest(positionFloor, i) > 0) {
                count++;
                up = true;
                loadPassengers(i, building.getFloorRequest(positionFloor, i));
                delay = delay + 10 * Math.abs(i - positionFloor);
                addMoveQue(new ElevatorMove(i, Clock.getTime() + delay));

            }
        }
        if (count == 0 && numPassengers == 0) up = false;
    }

    public void searchPassengersDOWN(int delay) {
        int count = 0;
        for (int i = positionFloor - 1; i >= 0; i--) {

            if (building.getFloorRequest(positionFloor, i) > 0) {
                count++;
                up = false;
                loadPassengers(i, building.getFloorRequest(positionFloor, i));
                delay = delay + 10 * Math.abs(i - positionFloor);
                addMoveQue(new ElevatorMove(i, Clock.getTime() + delay));
            }
        }
        if (count == 0 && numPassengers == 0) up = true;
    }

    public void next() {
        target = building.findreq();

        if (target != -1) {
            int delay = 10 * Math.abs(target - positionFloor);
            addMoveQue(new ElevatorMove(target, Clock.getTime() + delay));
        }
    }

    public void addMoveQue(ElevatorMove move) {
        moveQue.add(move);
    }

    public int getID() {
        return ID;
    }

    public void status() {
        System.out.println("ID: " + getID() + " actual floor: " + getPositionFloor() + " destination floor: " + getTarget() + " number of passengers inside " + getNumPassengers());
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public int getPositionFloor() {
        return positionFloor;
    }

    public void setPositionFloor(int positionFloor) {
        this.positionFloor = positionFloor;
    }


    public int[] getRequests() {
        return requests;
    }

    public void setRequests(int[] requests) {
        this.requests = requests;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public ArrayList<ElevatorMove> getMoveQue() {
        return moveQue;
    }

    public void setMoveQue(ArrayList<ElevatorMove> moveQue) {
        this.moveQue = moveQue;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
