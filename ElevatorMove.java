package MultiThreading;

public class ElevatorMove {
    private int desti;
    private int arrTime;

    public ElevatorMove(int floor, int time) {
        desti = floor;
        arrTime = time;
    }

    public int getDesti() {
        return desti;
    }

    public void setDesti(int desti) {
        this.desti = desti;
    }

    public int getArrTime() {
        return arrTime;
    }

    public void setArrTime(int arrTime) {
        this.arrTime = arrTime;
    }
}
