package apiclient;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import rssitem.Item;

/**
 * RSSFeed is a POJO model for the content of an RSS feed.
 *
 * It is constructed by the RSSClient.
 *
 * @author j6r
 *
 */
@XmlRootElement(name = "rss")
public class RSSFeed {

   private Channel channel;

   public String getTitle() {
      return channel.getTitle();
   }

   public String getLink() {
      return channel.getLink();
   }

   public String getDescription() {
      return channel.getDescription();
   }

   public String getLanguage() {
      return channel.getLanguage();
   }

   public String getCopyright() {
      return channel.getCopyright();
   }

   public String getLastBuildDate() {
      return channel.getLastBuildDate();
   }

   public List<Item> getItems() {
      return channel.getItems();
   }

   private Channel getChannel() {
      return channel;
   }

   @XmlElement(name = "channel")
   private void setChannel(Channel channel) {
      this.channel = channel;
   }

   @XmlRootElement
   private static class Channel {

      private String title;
      private String link;
      private String description;
      private String language;
      private String copyright;
      private String lastBuildDate;
      private List<Item> items;

      public String getTitle() {
         return title;
      }

      @XmlElement
      public void setTitle(String title) {
         this.title = title;
      }

      public String getLink() {
         return link;
      }

      @XmlElement
      public void setLink(String link) {
         this.link = link;
      }

      public String getDescription() {
         return description;
      }

      @XmlElement
      public void setDescription(String description) {
         this.description = description;
      }

      public String getLanguage() {
         return language;
      }

      @XmlElement
      public void setLanguage(String language) {
         this.language = language;
      }

      public String getCopyright() {
         return copyright;
      }

      @XmlElement
      public void setCopyright(String copyright) {
         this.copyright = copyright;
      }

      public String getLastBuildDate() {
         return lastBuildDate;
      }

      @XmlElement
      public void setLastBuildDate(String lastBuildDate) {
         this.lastBuildDate = lastBuildDate;
      }

      public List<Item> getItems() {
         return items;
      }

      @XmlElement(name = "item")
      public void setItems(List<Item> items) {
         this.items = items;
      }

   }
}
