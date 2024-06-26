package com.cx.Page;

import com.cx.entity.Children;
import com.cx.entity.Records;
import com.cx.utils.File_Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MyChild {

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

    @FXML
    TextField textField;//攒钱目标

    @FXML
    TextField textField1;//定期利率

    @FXML
    ChoiceBox comboBox;//选择孩子

    @FXML
    Label label;//定期余额

    @FXML
    Label label1;//活期余额

    Children children;

    public void initialize() {
        for (int i = 0; i<Children_Login.parent.getChildren().size(); i++){
            comboBox.getItems().add(Children_Login.parent.getChildren().get(i).getUsername());
        }
        time. setCellValueFactory(new PropertyValueFactory("time"));
        type. setCellValueFactory(new PropertyValueFactory("type"));
        blanc. setCellValueFactory(new PropertyValueFactory("money"));
        Description. setCellValueFactory(new PropertyValueFactory("Description"));

        comboBox.setOnAction(event -> {
           String name= (String) comboBox.getSelectionModel().getSelectedItem();
           for (int i = 0; i<Children_Login.parent.getChildren().size(); i++){
               if (Children_Login.parent.getChildren().get(i).getUsername().equals(name)){
                   label.setText("Fixed account balance: "+Children_Login.parent.getChildren().get(i).getMoney_D());
                   label1.setText("Current account balance: "+Children_Login.parent.getChildren().get(i).getMoney_H());
                   children=Children_Login.parent.getChildren().get(i);
                   setDate(Children_Login.parent.getChildren().get(i).getRecords());
               }
           }
        });
    }


    public void setDate(ArrayList<Records> records){
        tableView.setItems(FXCollections.observableList(records));
    }

    /**
     * 设置定期利率
     */
    public void Set_Money(){
        try {
            double s= Double.parseDouble(textField1.getText());
            Children_Login.parent.setCurrent(s);
            File_Date.Writ_File(Children_Login.data1);
        } catch (NumberFormatException e) {
            Alert A=new Alert(Alert.AlertType.ERROR);
            A.setTitle("Change interest rates");
            A.setHeaderText("Error, please enter the correct interest rate");
            A.showAndWait();
        }
    }
    /**
     * 设置mubiao
     */
    public void Set_goal(){
        try {
            double s= Double.parseDouble(textField.getText());
           children.setGoal(s);
            File_Date.Writ_File(Children_Login.data1);
        } catch (NumberFormatException e) {
            Alert A=new Alert(Alert.AlertType.ERROR);
            A.setTitle("Set a Goal");
            A.setHeaderText("Error, please enter a number!");
            A.showAndWait();
        }
    }

}
