package hw02;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//  @author Single-eye
/**
 * Bank class replicating the function of an actual bank: its functions includes to open, modify ,
 * and deletes accounts. *
 */
public class Bank {

   // Holds customers accounts with the CustomerID as the key
   private Map<Integer, Account> bankAccounts = new HashMap<>();

   /**
    * default constructor
    */
   public Bank() {

   }

   /**
    * add a customer account after checking if an account exist or not
    *
    * @param in_Cust_In
    * @param inAccount
    */
   public void addAccount(double inAccount) {

   }

   private void createtCustomerID() {

      int randomNumber;
      while (true) {
         Random r = new Random();
         randomNumber = r.nextInt(100) + 1;

         for (int i = 0; i < this.bankAccounts.size(); i++) {
            if(randomNumber == this.bankAccounts.get(i).getId()){
               
               
            }
            
            
         }
      }

   }

   /**
    *
    * @param in_Cust_In
    * @return
    */
   public boolean deleteAccont(int in_Cust_In) {

      if (this.queryCustAccount(in_Cust_In)) {

         this.bankAccounts.remove(in_Cust_In);
         return true;
      } else {

         return false;
      }

   }

   /**
    *
    * @param in_Cust_In
    * @param amount
    * @return
    * @throws AccountException
    */
   public Boolean Deposit(int in_Cust_In, double amount) throws AccountException {

      if (this.queryCustAccount(in_Cust_In)) {

         this.bankAccounts.get(in_Cust_In).withdraw(amount);
         return true;
      } else {

         return false;
      }
   }

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
      System.out.println(balance);

   }

   /**
    * Transfer funds from one account to another
    *
    * @param Cust_From
    * @param Cust_To
    * @param amount1
    * @return
    * @throws AccountException
    */
   public boolean transferfunds(int Cust_From, int Cust_To, double amount1) throws AccountException {
      boolean check1 = this.queryCustAccount(Cust_From);
      boolean check2 = this.queryCustAccount(Cust_To);
      if (check1 && check2 == true) {
         this.bankAccounts.get(Cust_From).withdraw(amount1);
         this.bankAccounts.get(Cust_To).deposit(amount1);
         return true;
      } else {

         return false;
      }
   }

   // Checks for duplicate accounts
   private boolean queryCustAccount(int inCustID) {

      return bankAccounts.containsKey(inCustID);

   }

   /**
    * queries all Customers
    */
   public void queryCustIDs() {

      for (int key : bankAccounts.keySet()) {

         System.out.println(this.bankAccounts.get(key));

      }
   }

}
