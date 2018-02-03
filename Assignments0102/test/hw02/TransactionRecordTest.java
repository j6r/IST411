package hw02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TransactionRecordTest {

   private static final LocalDateTime EXPECTED_LDT = LocalDateTime.now();
   private static final double EXPECTED_AMOUNT = -500.57;
   private static final double EXPECTED_STARTING_BALANCE = 10000.00;
   private static final double EXPECTED_ENDING_BALANCE = 9499.43;
   private static final String STRING_FORMATTER = "[%s] Amount: %.2f, Start balance: %.2f, End balance: %.2f";
   private String expectedPrintString;
   private TransactionRecord trTest;

   public TransactionRecordTest() {
   }

   @Before
   public void setUp() {
      trTest = new TransactionRecord(EXPECTED_LDT, EXPECTED_AMOUNT, EXPECTED_STARTING_BALANCE, EXPECTED_ENDING_BALANCE);
      expectedPrintString = String.format(STRING_FORMATTER,
              EXPECTED_LDT.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
              EXPECTED_AMOUNT, EXPECTED_STARTING_BALANCE, EXPECTED_ENDING_BALANCE);
   }

   @Test
   public void testConstructor() {
      assertEquals(EXPECTED_LDT, trTest.getTime());
      assertEquals(EXPECTED_AMOUNT, trTest.getAmountTransferred(), 0.0);
      assertEquals(EXPECTED_STARTING_BALANCE, trTest.getStartingBalance(), 0.0);
      assertEquals(EXPECTED_ENDING_BALANCE, trTest.getEndingBalance(), 0.0);
   }

   @Test
   public void testPrintString() {
      assertEquals(expectedPrintString, trTest.toString());
   }

   @Test
   public void testSort() {
      final LocalDateTime trEarlierTime = LocalDateTime.of(2016, 01, 1, 1, 1, 1);
      final LocalDateTime trLaterTime = LocalDateTime.of(2020, 01, 1, 1, 1, 1);

      final ArrayList<TransactionRecord> transactions = new ArrayList<>();
      final TransactionRecord trEarlier = new TransactionRecord(trEarlierTime, EXPECTED_AMOUNT, EXPECTED_AMOUNT, EXPECTED_ENDING_BALANCE);
      final TransactionRecord TrLater = new TransactionRecord(trLaterTime, EXPECTED_AMOUNT, EXPECTED_AMOUNT, EXPECTED_ENDING_BALANCE);

      transactions.add(trTest);
      transactions.add(TrLater);
      transactions.add(trEarlier);
      Collections.sort(transactions);

      assertEquals(trEarlierTime, transactions.get(0).getTime());
      assertEquals(EXPECTED_LDT, transactions.get(1).getTime());
      assertEquals(trLaterTime, transactions.get(2).getTime());
   }

}
