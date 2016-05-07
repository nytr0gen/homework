import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

public class RMI_Server
{
    public static void main(String[] args) throws Exception {
        final int port = 63000;
        BankOp ob = new BankOp();
        Registry reg = LocateRegistry.createRegistry(port);
        reg.rebind("FoxBank", ob);

        System.out.println("Server is bound..");
    }
}
