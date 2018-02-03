package apiclient;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * Fetch the live feed and compare expected metadata against what RSSClient
 * parsed out.
 *
 * The last build date field cannot be tested as the feed is constantly updating
 * this.
 *
 * Note that this test will fail if ITS changes the metadata in the feed, so
 * be sure to check that case if the test starts failing.
 */
public class RSSClientIT {

   private static RSSClient client;

   // Feed metadata
   private static final String EXPECTED_TITLE = "ITS Alerts";
   private static final String EXPECTED_LINK = "http://alerts.its.psu.edu";
   private static final String EXPECTED_LANGUAGE = "en-us";
   private static final String EXPECTED_COPYRIGHT = "Copyright2018 The Pennsylvania State University";
   private static final String EXPECTED_DESCRIPTION = "The Information Technology "
           + "Services (ITS) Alerts System is a rapid-delivery method used to distribute "
           + "timely information on major ITS services, unexpected outages, and maintenance "
           + "schedules to the Penn State community.";

   public RSSClientIT() {

   }

   @BeforeClass
   public static void setUp() throws Exception {
      // new client using default ITS alerts URL
      client = new RSSClient();
   }

   @Test
   public void testGetLiveFeedMetadata() {

      assertEquals(EXPECTED_TITLE, client.getTitle());
   }

   @Test
   public void testGetDescription() {
      assertEquals(EXPECTED_DESCRIPTION, client.getDescription());
   }

   @Test
   public void testGetLink() {
      assertEquals(EXPECTED_LINK, client.getLink());
   }

   @Test
   public void testGetLanguage() {
      assertEquals(EXPECTED_LANGUAGE, client.getLanguage());
   }

   @Test
   public void testGetCopyright() {
      assertEquals(EXPECTED_COPYRIGHT, client.getCopyright());
   }

}
