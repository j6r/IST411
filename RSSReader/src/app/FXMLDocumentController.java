/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import rssitem.ItemController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import rssitem.Item;
import java.util.ArrayList;

/**
 *
 * @author redjen
 */
public class FXMLDocumentController implements Initializable {
    //ArrayList <Item> newslist = new ArrayList();
    
    @FXML
    private TableView<Item> rssTable; 
    @FXML
    private TableColumn<Item, String> titleCol;
    @FXML
    private TableColumn<Item, String> descriptCol;
    @FXML
    private TableColumn<Item, String> dateCol;
    @FXML
    private TableColumn<Item, String> linkCol;
    private ItemController rss;
    
    

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rss = new ItemController();
        populateTable();
      
    }
    private void populateTable(){
        
        titleCol.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
        descriptCol.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        linkCol.setCellValueFactory(cellData -> cellData.getValue().getLinkProperty());
        
        rssTable.setItems(rss.getObservableList());
        
    }

}
