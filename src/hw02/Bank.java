package hw02;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Single-eye
 */
/**
 * Bank class replicating the function of an actual bank: its functions includes to open, modify ,
 * and deletes accounts. *
 */
public class Bank extends Thread {

   String id;
   private Account from;
   private Account to;
   private double amount = 0;
   private Map<Integer, Account> bankAccounts = new HashMap<>();
   private Account account;
   int customer = 1;
   //testing 
   public void transfer(String id, Account Cust_From, Account to, double amount) throws AccountException {
      this.id = id;
      this.from = from;
      this.to = to;
      this.amount = amount;
   }

   public Bank() {

   }

   public void addAccount(double amount) throws AccountException {
      int id = this.createCustomerID();
      //account = (new Account(id, amount));
      this.bankAccounts.put(id, new Account(id, amount));

   }

   public void deleteAccont(int Cust_In) throws AccountException {
      if (this.queryCustAccount(Cust_In)) {
         this.bankAccounts.remove(Cust_In);
      } else {
         throw new AccountException("The account does not exist");
      }

   }

   public double Deposit(int Cust_In, double amount) throws AccountException {
 
        if (this.queryCustAccount(Cust_In)) {
          return this.bankAccounts.get(Cust_In).deposit(amount);
       } else {
          throw new AccountException("The account does not exist");
       }
       
    }

   public double withdraw(int Cust_In, double amount) throws AccountException {
      if (this.queryCustAccount(Cust_In)) {
         return this.bankAccounts.get(Cust_In).withdraw(amount, false);
      } else {
         throw new AccountException("The account does not exist");
      }

   }

   private int createCustomerID() {
      return this.customer++;
   }
   // Testing at the moment
   @Override
   public void run() {
      synchronized (from) {
         try {
            from.withdraw(amount);
         } catch (AccountException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
            Thread.sleep(500);
         } catch (InterruptedException e) {
         }

         synchronized (to) {
            try {
               to.deposit(amount);
            } catch (AccountException ex) {
               Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      }
      System.out.println(amount + "is transfered from " + from + " to " + to);
   }

   private boolean queryCustAccount(int inCustID) {

      return bankAccounts.containsKey(inCustID);

   }
// Holds customers accounts with the CustomerID as the key

   /**
    * default constructor
    */

   /**
    * add a customer account after checking if an account exist or not
    *
    * @param in_Cust_In
    * @param inAccount
    */

   /**
    *
    * @param in_Cust_In
    * @return
    */
//   public boolean deleteAccont(int in_Cust_In) {
//
//      if (this.queryCustAccount(in_Cust_In)) {
//
//         this.bankAccounts.remove(in_Cust_In);
//         return true;
//      } else {
//
//         return false;
//      }
//
//   }

   /**
    *
    * @param in_Cust_In
    * @param amount
    * @return
    * @throws AccountException
    */
//   public Boolean Deposit(int in_Cust_In, double amount) throws AccountException {
//
//      if (this.queryCustAccount(in_Cust_In)) {
//
//         this.bankAccounts.get(in_Cust_In).withdraw(amount);
//         return true;
//      } else {
//
//         return false;
//      }
//   }

   /**
    * Adds the specified amount of money to the account balance
    *
    * @param amount the amount to add
    * @return the new account balance
    * @throws AccountException if the amount is less than $0.00
    */

   public Boolean Deposit(int in_Cust_In, double amount, boolean overdraft) throws AccountException {

      if (this.queryCustAccount(in_Cust_In)) {

         this.bankAccounts.get(in_Cust_In).withdraw(amount, overdraft);
         return true;
      } else {

         return false;
      }

   }
   //retrieves the balance

   /**
    * retreives an account balance
    *
    * @param in_Cust
    */
   public void getBalance(int in_Cust) {

      double balance = this.bankAccounts.get(in_Cust).getBalance();
      System.out.println(amount);

   }

//   /**
//    * Transfer funds from one account to another
//    *
//    * @param Cust_From
//    * @param Cust_To
//    * @param amount1
//    * @return
//    * @throws AccountException
//    */
   public void transferfunds(int Cust_From, int Cust_To, double amount1) throws AccountException {
       if (this.queryCustAccount(Cust_From)) {
           this.bankAccounts.get(Cust_From).withdraw(amount1, false);
           this.bankAccounts.get(Cust_To).deposit(amount1);     
       } else {
         throw new AccountException("One or both account(s) does not exist");
       }
    }
//
//   // Checks for duplicate accounts
//   private boolean queryCustAccount(int inCustID) {
//
//      return bankAccounts.containsKey(inCustID);
//
//   }
//
//   /**
//    * queries all Customers
//    */
//   public void queryCustIDs() {
//
//      for (int key : bankAccounts.keySet()) {
//
//         System.out.println(this.bankAccounts.get(key));
//
//      }
//   }
}
