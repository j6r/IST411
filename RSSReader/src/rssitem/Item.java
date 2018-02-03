package rssitem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Item for an RSS feed. This is a POJO model class providing the attributes and
 * methods for displaying an RSS feed item.
 *
 * This class is generally created by the rss client and its fields populated
 * with data via JAXB
 *
 */
@XmlRootElement
public class Item {

   private String title;
   private String link;
   private String description;
   private String date;

   /**
    * Constructs a new item
    */
   public Item() {
   }

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

   public String getDate() {
      return date;
   }

   @XmlElement
   public void setDate(String date) {
      this.date = date;
   }

   @Override
   public String toString() {
      return title;
   }

}
