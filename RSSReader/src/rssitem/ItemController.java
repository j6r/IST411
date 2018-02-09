package rssitem;

import javafx.collections.ObservableList;
import javafx.collections.*;
import apiclient.*;

/**
 *
 * @author Adam
 */
public class ItemController {
    
    private ObservableList<Item> articleList;
    private RSSClient client;
    
    public ItemController() {
        client  = new RSSClient();
        articleList = FXCollections.observableArrayList(client.getItems());
    }
    
    public ObservableList<Item> getObservableList() {
        return articleList;
    }
    
}
