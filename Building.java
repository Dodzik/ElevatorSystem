package MultiThreading;

public class Building {
    public Building(int numFloors) {
        floors = new Floor[numFloors];
        numberOfFloors = numFloors;
        for (int i = 0; i < numFloors; i++) {
            floors[i] = new Floor(i, numFloors);
        }
    }

    public synchronized void addFloorRequest(int floor, int destiFloor, int passengers) {
        floors[floor].addRequest(destiFloor, passengers);
    }

    public synchronized void setFloorRequest(int floor, int destiFloor, int passengers) {
        floors[floor].setRequest(destiFloor, passengers);
    }

    public synchronized int getFloorRequest(int floor, int desti) {
        return floors[floor].getDestiReq(desti);
    }

    public synchronized void statusBuilding() {
        for (int i = 0; i < numberOfFloors; i++) {
            floors[i].status_Floor();
            System.out.println();
        }
    }

    public synchronized int getNumberOfFloors() {
        return numberOfFloors;
    }

    public synchronized void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public synchronized int getActNumPassengers(int floor) {
        return floors[floor].getActNumPassengers();
    }

    public synchronized void setActNumPassengers(int floor, int num) {
        floors[floor].setActNumPassengers(num);
    }

    public synchronized int findreq() {
        for (int i = 0; i < getNumberOfFloors(); i++) {
            for (int j = 0; j < getNumberOfFloors(); j++) {
                if (getFloorRequest(i, j) > 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private Floor[] floors;
    private int numberOfFloors;
}
