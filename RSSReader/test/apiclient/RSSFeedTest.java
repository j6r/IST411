package apiclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class provides unit/integration tests for the RSSFeed class.
 *
 * This classes will be instantiated from RSSClient by parsing RSS feeds. Thus,
 * normal unit tests would not be adequate.
 *
 */
public class RSSFeedTest {

   private static final String RSS_PATH = "resources/alerts.xml";
   private RSSFeed feed;

   // Feed metadata
   private static final String EXPECTED_TITLE = "ITS Alerts";
   private static final String EXPECTED_LINK = "http://alerts.its.psu.edu";
   private static final String EXPECTED_LANGUAGE = "en-us";
   private static final String EXPECTED_COPYRIGHT = "Copyright2018 The Pennsylvania State University";
   private static final String EXPECTED_LAST_BUILD_DATE = "Wed, 31 Jan 2018 22:10:01 -0500";
   private static final String EXPECTED_DESCRIPTION = "The Information Technology "
           + "Services (ITS) Alerts System is a rapid-delivery method used to distribute "
           + "timely information on major ITS services, unexpected outages, and maintenance "
           + "schedules to the Penn State community.";

   // Items
   private static final int EXPECTED_ITEM_COUNT = 8;

   public RSSFeedTest() throws Exception {

   }

   @Before
   public void setUp() throws Exception {
      JAXBContext jaxbContext = JAXBContext.newInstance(RSSFeed.class);
      Unmarshaller jaxbUnmarshaller = null;

      try (BufferedReader in = new BufferedReader(new FileReader(new File(RSS_PATH)))) {
         jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         feed = (RSSFeed) jaxbUnmarshaller.unmarshal(in);
      }
   }

   @Test
   public void testGetTitle() throws Exception {
      assertEquals(EXPECTED_TITLE, feed.getTitle());
   }

   @Test
   public void testGetDescription() {
      assertEquals(EXPECTED_DESCRIPTION, feed.getDescription());
   }

   @Test
   public void testGetLink() {
      assertEquals(EXPECTED_LINK, feed.getLink());
   }

   @Test
   public void testGetLanguage() {
      assertEquals(EXPECTED_LANGUAGE, feed.getLanguage());
   }

   @Test
   public void testGetCopyright() {
      assertEquals(EXPECTED_COPYRIGHT, feed.getCopyright());
   }

   @Test
   public void testGetLastBuildDate() {
      assertEquals(EXPECTED_LAST_BUILD_DATE, feed.getLastBuildDate());
   }

   @Test
   public void testGetItemCount() {
      assertEquals(EXPECTED_ITEM_COUNT, feed.getItems().size());
   }

   // TODO integration tests for Item?
}
