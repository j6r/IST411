package hw02;

import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 * Account provides basic functionality associated with a bank account including maintaining a
 * balance, making deposits, making withdrawals, and keeping a history of transactions.
 *
 */
public class Account {

   private final int id;
   private double balance;
   private TreeSet<TransactionRecord> transactionHistory;

   /**
    * Creates a new Account with specified metadata
    *
    * @param id the account ID
    * @param initialBalance the initial balance
    * @throws hw02.AccountException if the initial balance is less than $0.00
    */
   public Account(int id, double initialBalance) throws AccountException {

      if (initialBalance < 0.00) {
         throw new AccountException("Initial balance must be greater than or equal to $0.00");
      }

      this.id = id;
      this.balance = initialBalance;
      this.transactionHistory = new TreeSet<>();
   }

   /**
    * Creates a new Account with the specified ID and $0.00 balance
    *
    * @param id the account ID
    * @throws hw02.AccountException
    */
   public Account(int id) throws AccountException {
      this(id, 0.00);
   }

   /**
    * Adds the specified amount of money to the account balance
    *
    * @param amount the amount to add
    * @return the new account balance
    * @throws AccountException if the amount is less than $0.00
    */
   public double deposit(double amount) throws AccountException {
      if (amount < 0.00) {
         throw new AccountException("Deposit amount must be greater than $0.00");
      }

      final double previousBalance = balance;
      balance += amount;
      transactionHistory.add(new TransactionRecord(LocalDateTime.now(), amount, previousBalance, balance));

      return balance;
   }

   /**
    * Withdraws the specified amount
    *
    * @param amount the amount to withdraw
    * @param allowOverdraft allows the account balance to go negative if true
    * @return the new balance
    * @throws AccountException if the amount to withdraw is less than $0.00 or if allowOverdraft is
    * false and the withdrawal amount is greater than the available balance
    */
   public double withdraw(double amount, boolean allowOverdraft) throws AccountException {
      if (!allowOverdraft && amount > balance) {
         throw new AccountException("Withdrawal amount greater than available balance");
      }

      if (amount <= 0.00) {
         throw new AccountException("Withdrawal amount must be greater than $0.00");
      }

      final double previousBalance = balance;
      balance -= amount;
      transactionHistory.add(new TransactionRecord(LocalDateTime.now(), amount, previousBalance, balance));

      return balance;
   }

   /**
    * Withdraws the specified amount if funds are available
    *
    * @param amount the amount to withdraw
    * @return the new balance
    * @throws AccountException if the amount to withdraw is less than $0.00 or greater than the
    * balance
    */
   public double withdraw(double amount) throws AccountException {
      return withdraw(amount, false);
   }

   /**
    * Prints the account's transaction history to standard out
    */
   public void printTransactionHistory() {
      System.out.printf("Transaction history for account %d:%n", id);
      for (TransactionRecord transactionRecord : transactionHistory) {
         System.out.println(transactionRecord.toString());
      }
   }

   public double getBalance() {
      return balance;
   }

   public int getId() {
      return id;
   }

   public TreeSet<TransactionRecord> getTransactionHistory() {
      return (TreeSet<TransactionRecord>) transactionHistory.clone();
   }

   @Override
   public String toString() {
      return String.format("Account ID: %d, Balance: %.2f", id, balance);
   }
}
