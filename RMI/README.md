
ATM/bank system.

 A client (unique accountId) can go to an ATM and use 3 operations:

     - deposit(accountId, int amount)
     - withdraw(accountId, int amount)
     - int inquiry(int accountId)

 Also, consider that multiple clients with the same accountId can request withdraw/ operations.
