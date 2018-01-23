package hw02;

/**
 * Custom exception class for accounts
 *
 */
public class AccountException extends Exception {

   public AccountException() {
      super();
   }

   public AccountException(String message) {
      super(message);
   }

   public AccountException(String message, Throwable cause) {
      super(message, cause);
   }

}
