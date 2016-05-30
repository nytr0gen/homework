import java.util.*;
import java.util.concurrent.locks.*;

class Airport
{
    public static final int WAIT_LAND = 2000;
    public static final int WAIT_DEPART = 1000;
    public static Lock mRunwayControl;

    public static void Create() {
        mRunwayControl = new ReentrantLock();
    }
}

class Aircraft extends Thread
{
    public int mId;
    public Aircraft(int id) {
        mId = id;
        log("Born");
    }

    private void delay(int milisecs) {
        try {
            Thread.sleep(milisecs);
        } catch(InterruptedException e) {
            System.out.println(e);
        }
    }

    private void log(String msg) {
        System.out.printf("Thread %d: %s\n", mId, msg);
    }

    private void land() throws InterruptedException {
        log("Attempting to land");
        Airport.mRunwayControl.lock();

        log("Runway acquired for landing");
        delay(Airport.WAIT_LAND);

        Airport.mRunwayControl.unlock();
        log("Runway released");
    }

    private void depart() throws InterruptedException {
        log("Attempting to depart");
        Airport.mRunwayControl.lock();

        log("Runway acquired for departure");
        delay(Airport.WAIT_DEPART);

        Airport.mRunwayControl.unlock();
        log("Runway released");
    }

    public void run() {
        try {
            land();
            depart();
        } catch(InterruptedException e) {
            System.out.println(e);
        }
    }
}

public class Main
{
    public static void main(String[] args) throws Exception {
        final int numAcs = 5;
        Airport.Create();
        Aircraft[] acs = new Aircraft[numAcs];
        for (int i = 0; i < numAcs; i++) {
            acs[i] = new Aircraft(i);
            acs[i].start();
        }
    }
}
