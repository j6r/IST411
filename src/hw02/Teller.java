package hw02;

/**
 *
 * @author timothyowens
 */
public class Teller {
   
    public static void main(String[] args) throws AccountException {
       
       Bank b1 = new Bank();
       Bank b2 = new Bank();
       b1.addAccount(200);
       b2.addAccount(500.00);
       b1.withdraw(1,5.00 );
       
          
       
       
       System.out.println(b1.toString());
//        final Account accA = new Account(1);
//        final Account accB = new Account(2);
//        accA.deposit(1000.00);
//        accB.deposit(1000.00);
       
//       Account checking = new Account(1,50.00);
//       Account savings = new Account(2, 60.00);
//       
////       
//       Bank b1 = new Bank("transfer1", accA, accB, 10.00);
//       Bank b2 = new Bank("tranfer2", accB, accA, 20.00);
//       b1.start();
//       b2.start();
//       System.out.println(b1.toString());
//       System.out.println(b2.toString());
//      

       
       
       //bank1.transferfunds(1, 2, 50.00);
       //bank1.transferfunds(2, 1, 10.00);
       //bank1.Deposit(1, 100.00);
       
    }
       
      
    }

