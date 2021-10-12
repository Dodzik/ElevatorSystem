package MultiThreading;

public class Clock {

    private static int time;

    public Clock() {
        time = 0;
    }

    public static void tick() {
        time += 5;
    }

    public static int getTime() {
        return time;
    }
}
