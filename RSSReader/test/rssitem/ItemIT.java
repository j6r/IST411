package rssitem;

import apiclient.RSSFeed;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author redjen
 */
public class ItemIT {

   private static final String RSS_PATH = "resources/alerts.xml";
   private RSSFeed feed;
   private static final int EXPECTED_ITEM_COUNT = 8;
   
   
   private static final String EXPECTED_TITLE_1 = "Enterprise VPN Maintenance ";
   private static final String EXPECTED_DESCRIPTION_1 = "On Friday 2/2, starting at 05:00 and lasting roughly till 07:00, Data Centers Services will be upgrading the Enterprise VPN. During this work, there is no outage expected of the VPN, however some connections could be disrupted.  <br /> <br />We appreciate your patience and assistance as we work to improve security and reliability of services in Enterprise IT. This change is being tracked via CHG0046488. <br />";
   private static final String EXPECTED_LINK_1 = "http://alerts.its.psu.edu/alert-4936";
   private static final String EXPECTED_DATE_1 = "Wed, 31 Jan 2018 08:45:54 EST";

   public ItemIT() {
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
   public void testGetItemCount() {
      assertEquals(EXPECTED_ITEM_COUNT, feed.getItems().size());
   }

   @Test
   public void testGetItems() {
      Item item = feed.getItems().get(0);
      assertEquals(EXPECTED_TITLE_1, item.getTitle());
      assertEquals(EXPECTED_DESCRIPTION_1, item.getDescription());
      assertEquals(EXPECTED_LINK_1, item.getLink());
      assertEquals(EXPECTED_DATE_1, item.getDate());
   }

}
