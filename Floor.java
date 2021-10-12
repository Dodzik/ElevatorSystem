package MultiThreading;

public class Floor {
    Floor(int num,int num_floors){
        floorNum = num+1;
        destiReq =new int[num_floors];
        actNumPassengers =0;
        numberOfFloors = num_floors;
    }
    public void addRequest(int destiFloor,int num){
        if (destiFloor!= floorNum) {
            destiReq[destiFloor] += num;
            actNumPassengers += num;
        }
    }
    public void setRequest(int destiFloor,int num){
            destiReq[destiFloor] = num;
    }

    public int getActNumPassengers() {
        return actNumPassengers;
    }

    public void setActNumPassengers(int actNumPassengers) {
        this.actNumPassengers = actNumPassengers;
    }

    public int getDestiReq(int floor) {
        return destiReq[floor];
    }

    public void setDestiReq(int[] destiReq) {
        this.destiReq = destiReq;
    }

    public int getFloorNum() {
        return floorNum;
    }
    public void status_Floor(){
        System.out.println("Floor number: " + floorNum);
        System.out.println("Passengers on floor: "  + actNumPassengers);
        System.out.println("Number of request to certain floors: ");
        for (int i = 0; i < numberOfFloors; i++) {
            System.out.print("Floor nr " + (i+1)+": "+ destiReq[i]);
            System.out.println();
        }
    }
    private int[] destiReq;
    private int actNumPassengers;
    private int floorNum;
    private int numberOfFloors;
}
