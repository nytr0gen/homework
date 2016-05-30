import java.util.*;

public class Main
{
    public static void main(String[] args) throws Exception {
        Airport ap = new Airport();
        ap.start();

        final int numAcs = 10;
        Aircraft[] acs = new Aircraft[numAcs];
        for (int i = 0; i < numAcs; i++) {
            acs[i] = new Aircraft(i);
            acs[i].start();
        }
        for (int i = 0; i < numAcs; i++) {
            acs[i].join();
        }

        ap.terminate();
    }
}
