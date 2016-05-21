import java.rmi.*;
import java.util.*;
import java.net.*;
import java.rmi.registry.*;

public class RMI_Client
{
    public static void main(String[] args) throws Exception {
        final String IP = "localhost";
        final int port = 63000;
		Registry registry = LocateRegistry.getRegistry(IP, port);
        BankOpInterface op = (BankOpInterface) registry.lookup("FoxBank");

        Scanner sc = new Scanner(System.in);
        int accountId;
        int amount;
        while (true) {
            String request = sc.next();
            try {
                switch (request) {
                case "deposit": case "d":
                    accountId = sc.nextInt();
                    amount = sc.nextInt();
                    op.deposit(accountId, amount);
                    System.out.printf("A fost depozitata suma %d in contul %d\n", amount, accountId);
                break;
                case "withdraw": case "w":
                    accountId = sc.nextInt();
                    amount = sc.nextInt();
                    op.withdraw(accountId, amount);
                    System.out.printf("Au fost scoasa suma %d din contul %d\n", amount, accountId);
                break;
                case "inquiry": case "i":
                    accountId = sc.nextInt();
                    amount = op.inquiry(accountId);
                    System.out.printf("Inquiry for accountId %d is %d\n", accountId, amount);
                break;
                default:
                    System.out.println("No command of mine");
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
