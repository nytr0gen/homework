import java.rmi.*;

public interface BankOpInterface extends Remote {
    public void deposit(int accountId, int amount) throws RemoteException;
    public void withdraw(int accountId, int amount) throws RemoteException;
    public int inquiry(int accountId) throws RemoteException;
}
