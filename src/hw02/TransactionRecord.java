package hw02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TransactionRecord contains the metadata associated with a unique transaction for an account.
 *
 * Objects of this class are expected to be used as a historical record and all fields are
 * immutable.
 */
public class TransactionRecord implements Comparable<TransactionRecord> {

   private final LocalDateTime time;
   private final double amountTransferred;
   private final double startingBalance;
   private final double endingBalance;

   private static final String STRING_FORMATTER = "[%s] Amount: %.2f, Start balance: %.2f, End balance: %.2f";

   public TransactionRecord(LocalDateTime time, double amountTransferred, double initialBalance, double endingBalance) {
      this.time = time;
      this.amountTransferred = amountTransferred;
      this.startingBalance = initialBalance;
      this.endingBalance = endingBalance;
   }

   public LocalDateTime getTime() {
      return time;
   }

   public double getAmountTransferred() {
      return amountTransferred;
   }

   public double getStartingBalance() {
      return startingBalance;
   }

   public double getEndingBalance() {
      return endingBalance;
   }

   @Override
   public String toString() {
      return String.format(STRING_FORMATTER, time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
              amountTransferred, startingBalance, endingBalance);
   }

   /**
    * Compares two TransactionRecord objects by time
    *
    * @param o the TransactionRecord to be compared against this one
    * @return 0 if the timestamps match, less than 1 if this TransactionRecord occurs before the
 other, and greater than 1 if it occurs after the other
    */
   @Override
   public int compareTo(TransactionRecord o) {
      return this.time.compareTo(o.time);
   }

}
