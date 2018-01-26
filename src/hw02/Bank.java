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
   private Account account;

   /**
    * default constructor
    */
   public Bank() {}

   /**
    *
    * @param amount initial balance
    * @throws AccountException if the initial balance is less than $0.00
    */
   public void addAccount(double amount) throws AccountException {
      int id = this.createtCustomerID();
      this.bankAccounts.put(id, account = new Account(id, amount));

   }

   private int createtCustomerID() {

      int randomNumber;
      while (true) {
         Random r = new Random();
         randomNumber = r.nextInt(100) + 1;

         for (int i = 0; i < this.bankAccounts.size(); i++) {
            if (randomNumber == this.bankAccounts.get(i).getId()) {
               
            }

         }
      }

   }

   /**
    *
    * @param Cust_In customer identifier
    * @throws AccountException if an account does exist
    */
   public void  deleteAccont(int Cust_In) throws AccountException {
      if (this.queryCustAccount(Cust_In)) {
         this.bankAccounts.remove(Cust_In);
      } else {
         throw new AccountException("The account does not exist");
      }

   }

   /**
    * Adds the specified amount of money to the account balance
    * @param Cust_In customer identifier
    * @param amount the amount to add
    * @return the new account balance
    * @throws AccountException if an account does exist
    *  AccountException if the amount is less than $0.00
    */
   public double Deposit(int Cust_In, double amount) throws AccountException {

       if (this.queryCustAccount(Cust_In)) {
         return this.bankAccounts.get(Cust_In).deposit(amount);
      } else {
         throw new AccountException("The account does not exist");
      }
      
   }
    /**
    *
    * @param Cust_In customer identifier
    * @param amount the amount to withdraw
    * @return the new balance
    * @throws AccountException if an account does not exist
    * if the amount to withdraw is less than $0.00 or if allowOverdraft is
    * false and the withdrawal amount is greater than the available balance
    * */
   public double withdraw(int Cust_In, double amount) throws AccountException{
       if (this.queryCustAccount(Cust_In)) {
        return this.bankAccounts.get(Cust_In).withdraw(amount, false);    
      } else {
         throw new AccountException("The account does not exist");
      }
        
  }
    /**
    *
    * @param Cust_In customer account identifier
    * @return the account balance
    * @throws AccountException if an account does not exist;
    * AccountException if the amount is less than $0.00
    */
   public double getBalance(int Cust_In) throws AccountException {
       if (this.queryCustAccount(Cust_In)) {
         return this.bankAccounts.get(Cust_In).getBalance();   
      } else {
         throw new AccountException("The account does not exist");
      }
      
   }
   /**
    *
    * @param Cust_From the account to withdraw from
    * @param Cust_To the account to deposit into
    * @param amount1 the amount for the transaction
    * @throws AccountException if an account does not exist;
    * if the amount to withdraw is less than $0.00 or if allowOverdraft is
    * false and the withdrawal amount is greater than the available balance;
    * AccountException if the amount is less than $0.00
    */
   public void transferfunds(int Cust_From, int Cust_To, double amount1) throws AccountException {
      if (this.queryCustAccount(Cust_From)) {
          this.bankAccounts.get(Cust_From).withdraw(amount1, false);
          this.bankAccounts.get(Cust_To).deposit(amount1);     
      } else {
        throw new AccountException("One or both account(s) does not exist");
      }
   }
    /**
    *
    * @param Cust_From the account to withdraw from
    * @returns bankAccount - False if the key does not exist; True if the key does exist
    */
   private boolean queryCustAccount(int inCustID) {

      return bankAccounts.containsKey(inCustID);

   }
   /**
    * queries all Customers 
    */
   public void printCustIDs() {

      for (int key : bankAccounts.keySet()) {

         System.out.println(this.bankAccounts.get(key));

      }
   }

}
