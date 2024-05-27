package com.cx.Page;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Records {

    @FXML
    TableView tableView;


    @FXML
    TableColumn time;


    @FXML
    TableColumn type;

    @FXML
    TableColumn blanc;

    @FXML
    TableColumn Description;
    public void initialize() {
       time. setCellValueFactory(new PropertyValueFactory("time"));
        type. setCellValueFactory(new PropertyValueFactory("type"));
        blanc. setCellValueFactory(new PropertyValueFactory("money"));
        Description. setCellValueFactory(new PropertyValueFactory("Description"));

    }


    public void setDate(ArrayList<com.cx.entity.Records> records){
        tableView.setItems(FXCollections.observableList(records));
    }
}
