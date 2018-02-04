package apiclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import rssitem.Item;

/**
 * RSSClient is a generic client for RSS feed APIs. It fetches the feed and
 * provides methods for extracting feed metadata and a list of article items.
 *
 * Fetching a list of RSS article items requires two steps: 1) creating an
 * instance of RSSClient with an RSS feed URL (or using the default); 2) calling
 * getItems().
 *
 * RSSClient client = new RSSClient(feedURL);
 * List<Item> articleList = client.getItems().
 *
 * If the feedURL is not specified the client will fetch items from the default
 * feed for ITS Alerts.
 *
 * RSSClient also provides information about the feed itself, in addition to a
 * list of the items. Each feed provides its title, description, language,
 * copyright statement, URL, and date it was last updated. To get this
 * information, create an instance of RSSClient then call the appropriate
 * getters.
 *
 * RSSClient client = new RSSClient));
 * String title = client.getTitle();
 * String description = client.getDescription();
 * etc.
 *
 */
public class RSSClient {

   private static final String DEFAULT_FEED_URL = "http://alerts.its.psu.edu/alerts.rss";
   private final String feedUrl;
   private final RSSFeed feed;

   /**
    * Constructs a new RSSClient using the default ITS Alerts feed URL
    */
   public RSSClient() {
      this(DEFAULT_FEED_URL);
   }

   /**
    * Constructs a new RSSClient using the specified RSS feed URL
    *
    * @param feedUrl
    */
   public RSSClient(String feedUrl) {
      this.feedUrl = feedUrl;
      // TODO handling for null feed
      this.feed = getFeed();
   }

   /**
    * Returns the feed's title
    *
    * @return the feed's title
    */
   public String getTitle() {
      return feed.getTitle();
   }

   /**
    * Returns the feed's URL
    *
    * @return the feed's URL as a string
    */
   public String getLink() {
      return feed.getLink();
   }

   /**
    * Returns the feed's description
    *
    * @return the feed's description
    */
   public String getDescription() {
      return feed.getDescription();
   }

   /**
    * Returns the feed's language
    *
    * The language is represented as a language tag (e.g. en-us or pt-br).
    * {@link https://en.wikipedia.org/wiki/Language_localisation#Language_tags_and_codes}
    *
    * @return the feed's language tag
    */
   public String getLanguage() {
      return feed.getLanguage();
   }

   /**
    * Returns the feed's copyright statement
    *
    * @return the feed's copyright statement
    */
   public String getCopyright() {
      return feed.getCopyright();
   }

   /**
    * Returns the last time the feed was updated
    * The date may be in a variety for formats. PSU Alerts uses
    * DateTimeFormatter.RFC_1123_DATE_TIME
    *
    * @return the last updated time as a string
    */
   public String getLastBuildDateString() {
      return feed.getLastBuildDate();
   }

   /**
    * Returns the last time the feed was updated as a LocalDateTime object
    *
    * @return a LocalDateTime object representing the last update
    */
   public LocalDateTime getLastBuildDate() {
      return LocalDateTime.parse(feed.getLastBuildDate(), DateTimeFormatter.RFC_1123_DATE_TIME);
   }

   /**
    * Returns a list of the items in the feed.
    *
    * @see Item
    * @return a list of the items in the feed
    */
   public ArrayList<Item> getItems() {
      return (ArrayList<Item>) feed.getItems();
   }

   /**
    * Connects to the specified host, pulls the RSS feed, and parses it.
    *
    * @return an RSSFeed object containing the feed metadata and items
    */
   private RSSFeed getFeed() {
      // TODO better exception handling, including nulls
      URLConnection conn = null;
      JAXBContext jaxbContext = null;
      Unmarshaller jaxbUnmarshaller = null;
      RSSFeed rf = null;

      try {

         conn = getConnection();

         if (conn == null) {
            throw new RSSClientException("An error occurred while fetching the feed");
         }

         jaxbContext = JAXBContext.newInstance(RSSFeed.class);

         try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            rf = (RSSFeed) jaxbUnmarshaller.unmarshal(in);
         } catch (IOException ex) {
            Logger.getLogger(RSSClient.class.getName()).log(Level.SEVERE, null, ex);
         }

      } catch (RSSClientException | JAXBException ex) {
         Logger.getLogger(RSSClient.class.getName()).log(Level.SEVERE, null, ex);
      }

      return rf;
   }

   /**
    * Gets a connection to the RSS feed
    *
    * @return a URLConnection object for the RSS feed
    * @throws RSSClientException
    */
   private URLConnection getConnection() throws RSSClientException {
      // TODO better exception handling, including nulls
      URL url = null;
      URLConnection conn = null;

      try {
         url = new URL(feedUrl);
         conn = url.openConnection();

      } catch (IOException ex) {
         Logger.getLogger(RSSClient.class.getName()).log(Level.SEVERE, null, ex);
         throw new RSSClientException("Connection error occurred", ex);
      }
      return conn;
   }

}
