import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;


class BankClient
{
    public int mAccountId;
    public int mDeposit = 0;
    public BankClient(int accountId) {
        mAccountId = accountId;
    }

    synchronized void deposit(int amount) {
        mDeposit += amount;
    }

    synchronized void withdraw(int amount) {
        mDeposit -= amount;
    }
}

Bank : unicastrei, BankInter
{
    BankOp getMyAccount(int id);

}

public class BankOp extends UnicastRemoteObject implements BankOpInterface
{
x
    BankOp() throws RemoteException {
        super();
    }

    private BankClient get(int accountId) {
        if (clients.containsKey(accountId)) {
            return clients.get(accountId);
        } else {
            BankClient b = new BankClient(accountId);
            clients.put(accountId, b);
            return b;
        }
    }

    public void deposit(int accountId, int amount) throws RemoteException {
        BankClient b = get(accountId);
        b.deposit(amount);
    }

    public void withdraw(int accountId, int amount) throws RemoteException {
        BankClient b = get(accountId);
        b.withdraw(amount);
    }

    public int inquiry(int accountId) throws RemoteException {
        BankClient b = get(accountId);
        return b.mDeposit;
    }
}
