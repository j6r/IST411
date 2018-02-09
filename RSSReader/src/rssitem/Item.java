package rssitem;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.*;

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

   private StringProperty title;
   private StringProperty link;
   private StringProperty description;
   private StringProperty date;

   /**
    * Constructs a new item
    */
   public Item() {
   }
   
   /**
    * @return the StringProperty title
    */
   public StringProperty getTitleProperty() {
      return title;
   }
   
   /**
    * @return the title
    */
   public String getTitle() {
       return title.get();
   }
   
   /**
    * @param title the title to set
    */
   @XmlElement
   public void setTitle(String title) {
      this.title.set(title);
   }
   
   /**
    * @return the StringProperty link
    */
   public StringProperty getLinkProperty() {
      return link;
   }
   
   /**
    * @return the link
    */
   public String getLink() {
       return link.get();
   }
   
   /**
    * @param link the link to set
    */
   @XmlElement
   public void setLink(String link) {
      this.link.set(link);
   }
   
   /**
    * @return the StringProperty description
    */
   public StringProperty getDescriptionProperty() {
      return description;
   }
   
   /**
    * @return the description
    */
   public String getDescription() {
       return description.get();
   }
   
   /**
    * @param description the description to set
    */
   @XmlElement
   public void setDescription(String description) {
      this.description.set(description);
   }
   
   /**
    * @return the StringProperty date
    */
   public StringProperty getDateProperty() {
      return date;
   }
   
   /**
    * @return the date
    */
   public String getDate() {
       return date.get();
   }
   
   /**
    * @param date the date to set
    */
   @XmlElement
   public void setDate(String date) {
      this.date.set(date);
   }

   @Override
   public String toString() {
      return title.toString();
   }

}
