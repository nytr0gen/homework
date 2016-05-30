import java.util.*;
import java.util.concurrent.*;

public class Aircraft extends Thread
{
    public int mId;
    public Airport.OpType op;

    public Semaphore mWaiting;
    public Aircraft(int id) {
        mId = id;
        mWaiting = new Semaphore(0);

        // Choose randomly wether to land or depart
        if (Math.random() < 0.5) {
            op = Airport.OpType.LAND;
        } else {
            op = Airport.OpType.DEPART;
        }
    }

    private void delay(int milisecs) {
        try {
            Thread.sleep(milisecs);
        } catch(InterruptedException e) {
            System.out.println(e);
        }
    }

    private void log(String msg) {
        String time = String.format("%1$tH:%1$tM:%1$tS", new Date());
        System.out.printf("Aircraft %d (%s): %s\n", mId, time, msg);
    }

    public void run() {
        try {
            Airport.delay((int)(Math.random() * 2000));

            switch (op) {
            case LAND:
                log("Born to land");
                break;
            case DEPART:
                log("Born to depart");
                break;
            }

            Airport.open(this);
            log("Runway acquired");
            Airport.close(this);
            log("Runway released");
        } catch(InterruptedException e) {
            System.out.println(e);
        }
    }
}
