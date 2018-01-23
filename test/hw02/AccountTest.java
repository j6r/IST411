package hw02;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author redjen
 */
public class AccountTest {

   private static final int EXPECTED_ID_1 = 100;
   private static final double EXPECTED_INITIAL_BALANCE_1 = 1234.56;
   private static final String EXPECTED_TRANSACTION_HISTORY_1 = "Transaction history for account %d:\n"
           + "[%s] Amount: 500.00, Start balance: 1234.56, End balance: 1734.56\n"
           + "[%s] Amount: 1000.00, Start balance: 1734.56, End balance: 734.56\n";
   private Account account1;

   public AccountTest() {

   }

   @Before
   public void setUp() throws Exception {
      account1 = new Account(EXPECTED_ID_1, EXPECTED_INITIAL_BALANCE_1);
   }

   /*
    * Constructor tests
    */
   @Test
   public void testContructorWithInitialBalance() throws Exception {
      assertEquals(EXPECTED_ID_1, account1.getId());
      assertEquals(EXPECTED_INITIAL_BALANCE_1, account1.getBalance(), 0.00);
   }

   @Test
   public void testContructorNoInitialBalance() throws Exception {
      final Account account2 = new Account(EXPECTED_ID_1);
      assertEquals(EXPECTED_ID_1, account2.getId());
      assertEquals(0.00, account2.getBalance(), 0.00);
   }

   @Test(expected = AccountException.class)
   public void testConstructorNegativeBalance() throws Exception {
      final Account negativeAccount = new Account(2000, -600.00);
   }

   /*
    * Deposit tests 
    *
    */
   @Test
   public void testDeposit() throws Exception {
      final double newBalance1 = account1.deposit(100.00);
      assertEquals(EXPECTED_INITIAL_BALANCE_1 + 100.00, newBalance1, 0.00);
      assertEquals(EXPECTED_INITIAL_BALANCE_1 + 100.00, account1.getBalance(), 0.00);
   }

   @Test
   public void testDepositNegativeAmount() {
      try {
         account1.deposit(-5000.00);

         // exception should be thrown, fail if it is not
         fail("AccountException was not thrown");
      } catch (AccountException e) {
         assertEquals(EXPECTED_INITIAL_BALANCE_1, account1.getBalance(), 0.00);
      }
   }

   /*
    * Withdrawal tests 
    *
    */
   @Test
   public void testWithdrawal() throws Exception {
      final double newBalance1 = account1.withdraw(1000.00);
      assertEquals(EXPECTED_INITIAL_BALANCE_1 - 1000.00, newBalance1, 0.00);
      assertEquals(EXPECTED_INITIAL_BALANCE_1 - 1000.00, account1.getBalance(), 0.00);
   }

   @Test
   public void testWithdrawalOverdraftAllowed() throws Exception {
      final double newBalance1 = account1.withdraw(10000.00, true);
      assertEquals(EXPECTED_INITIAL_BALANCE_1 - 10000.00, newBalance1, 0.00);
      assertEquals(EXPECTED_INITIAL_BALANCE_1 - 10000.00, account1.getBalance(), 0.00);
   }

   @Test
   public void testWithdrawalNegativeAmount() {
      try {
         account1.withdraw(-5000.00);

         // exception should be thrown, fail if it is not
         fail("AccountException was not thrown");
      } catch (AccountException e) {
         assertEquals(EXPECTED_INITIAL_BALANCE_1, account1.getBalance(), 0.00);
      }
   }

   /*
    * Print transaction history tests
    * Requires manual verification
    */
   @Test
   public void testPrintTransactionHistory() throws Exception {
      account1.deposit(500.00); // old balance: 1234.56, new balance: 1734.56
      account1.withdraw(1000.00); // old balance 1734.56, new balance: 734.56

      account1.printTransactionHistory();
   }
}
