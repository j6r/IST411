package hw02;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Single-eye
 */
public class BankTest {
   

   private Account account1,account2;
   private Bank bank1;
   
   
   public BankTest() throws Exception {
      
       account1 = new Account(1,1453);
       account2 = new Account(2, 6546.66);
       bank1= new Bank();

   }
   
   /**
    * Test of addAccount method, of class Bank.
    */
   @Test
   public void testAddAccount() throws Exception {
      bank1.addAccount(11, account1);
      bank1.addAccount(33, account2);
     
   
      bank1.queryCustIDs();
      bank1.transferfunds(11, 33, 1000.11);
      bank1.getBalance(11);
      bank1.getBalance(33);
      
      
      
   }

   /**
    * Test of deleteAccont method, of class Bank.
    */
   @Test
   public void testDeleteAccont() {
   }

   /**
    * Test of queryCustIDs method, of class Bank.
    */
   @Test
   public void testQueryCustIDs() {
   }
   
}
