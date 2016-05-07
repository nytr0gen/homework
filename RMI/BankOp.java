import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;

public class BankOp extends UnicastRemoteObject implements BankOpInterface
{
    private HashMap<Integer, Integer> bank = new HashMap<Integer, Integer>();

    BankOp() throws RemoteException {
        super();
    }

    synchronized public void deposit(int accountId, int amount) throws RemoteException {
        int currentAmount = this.inquiry(accountId);
        bank.put(accountId, currentAmount + amount);
    }

    synchronized public void withdraw(int accountId, int amount) throws RemoteException {
        int currentAmount = this.inquiry(accountId);
        bank.put(accountId, currentAmount - amount);
    }

    public int inquiry(int accountId) throws RemoteException {
        if (bank.containsKey(accountId)) {
            return bank.get(accountId);
        } else {
            bank.put(accountId, 0);
            return 0;
        }
    }
}
