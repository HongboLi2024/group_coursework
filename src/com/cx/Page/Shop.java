package com.cx.Page;

import com.cx.entity.Apply_For;
import com.cx.entity.Patriarch;
import com.cx.entity.Product;
import com.cx.entity.Task;
import com.cx.utils.File_Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Shop {
    @FXML
    TableView tableView;




    @FXML
    TableColumn type;

    @FXML
    TableColumn blanc;


    public void initialize() {

        type. setCellValueFactory(new PropertyValueFactory("name"));
        blanc. setCellValueFactory(new PropertyValueFactory("money"));
        Set_Data();
    }


    /**
     * 购买
     */
    public void Shop_Apply_For(){
        Product selectedItem = (Product) tableView.getSelectionModel().getSelectedItem();

        if(selectedItem!=null){

            if(selectedItem.getMoney()<Children_Login.children.getMoney_H()) {
                Apply_For apply_for = new Apply_For();
                apply_for.setMoney(selectedItem.getMoney());
                apply_for.setApply_for_Name(Children_Login.children.getUsername());
                apply_for.setType("Purchase Goods");
                apply_for.setDescription("Purchase Goods: " + selectedItem.getName());
                Children_Login.children.getPatriarch().getApply_fors().add(apply_for);

                File_Date.Writ_File(Children_Login.data1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Purchase Goods");
                alert.setHeaderText(null);
                alert.setContentText("Purchase request sent");

                alert.showAndWait();


            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Purchase Goods");
                alert.setHeaderText(null);
                alert.setContentText("Error, insufficient available balance in account");

                alert.showAndWait();
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Purchase Goods");
            alert.setHeaderText(null);
            alert.setContentText("Error, please select a good from the shop");

            alert.showAndWait();
        }
    }
    /**
     * 设置数据
     */
    public void Set_Data(){
        ArrayList<Product> products = Children_Login.children.getPatriarch().getPatriarchArrayList();
        tableView.getItems().clear();
        for (int i=0;i<products.size();i++){
                tableView.getItems().add(products.get(i));

            }
        }


}
