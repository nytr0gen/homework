import java.util.*;
import java.util.concurrent.locks.*;

public class Airport extends Thread
{
    enum OpType { LAND, DEPART };
    public static final int WAIT_LAND = 2000;
    public static final int WAIT_DEPART = 1000;

    private static Lock mInternalLock = new ReentrantLock();
    private static LinkedList<Aircraft> mAircraftQueue = new LinkedList<Aircraft>();
    private static Boolean mStopQueue = false;
    private static Condition mRunwayFree = mInternalLock.newCondition();
    private static int mOnRunway = 0;

    public static void open(Aircraft aircraft) throws InterruptedException {
        mInternalLock.lock();
        mAircraftQueue.add(aircraft);
        mInternalLock.unlock();

        // Block here until Airport will give us access
        try {
            aircraft.mWaiting.acquire();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void close(Aircraft aircraft) throws InterruptedException {
        // Sleep for the desired time
        switch (aircraft.op) {
        case LAND:
            delay(WAIT_LAND);
            break;
        case DEPART:
            delay(WAIT_DEPART);
            break;
        }

        mInternalLock.lock();

        mOnRunway--;
        if (mOnRunway == 0) {
            mRunwayFree.signal();
        }

        mInternalLock.unlock();
    }

    public static void delay(int milisecs) {
        try {
            Thread.sleep(milisecs);
        } catch(InterruptedException e) {
            System.out.println(e);
        }
    }

    public void terminate() {
        mStopQueue = true;
    }

    public void run() {
        while (true) {
            // Stop Aircraft Queue
            if (mStopQueue) {
                break;
            }

            mInternalLock.lock();

            // Continue if aircraft queue is empty
            if (mAircraftQueue.isEmpty()) {
                mInternalLock.unlock();
                delay(20);
                continue;
            }

            // Get top aircraft from queue
            Aircraft aircraft = mAircraftQueue.removeFirst();

            while (mOnRunway > 0) {
                try { mRunwayFree.await(); } catch(Exception e) {}
            }

            try {
                aircraft.mWaiting.release(); // Let him run now
            } catch(Exception e) {
                System.out.println(e);
            }
            mOnRunway++;

            mInternalLock.unlock();
        }
    }
}
