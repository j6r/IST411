/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import rssitem.Item;

/**
 *
 * @author redjen
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> titleCol;
    @FXML
    private TableColumn<Item, String> linkCol;
    @FXML
    private TableColumn<Item, String> descriptCol;
    @FXML
    private TableColumn<Item, String> dateCol;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
