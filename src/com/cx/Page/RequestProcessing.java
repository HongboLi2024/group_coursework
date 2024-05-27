package com.cx.Page;

import com.cx.entity.Apply_For;
import com.cx.entity.Records;
import com.cx.utils.File_Date;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RequestProcessing {

    @FXML
    TableView tableView;


    @FXML
    TableColumn name;


    @FXML
    TableColumn type;

    @FXML
    TableColumn blanc;

    @FXML
    TableColumn Description;


    public void initialize() {
        name. setCellValueFactory(new PropertyValueFactory("Apply_for_Name"));
        type. setCellValueFactory(new PropertyValueFactory("type"));
        blanc. setCellValueFactory(new PropertyValueFactory("money"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));

    }

    /**
     * 同意请求
     */
    public void Grant_request(){
        Apply_For selectedItem = (Apply_For) tableView.getSelectionModel().getSelectedItem();
      selectedItem.setIs(true);
      selectedItem.setIs_G(true);
      Set_Data(Children_Login.parent.getApply_fors());
      if(selectedItem.getType().equals("Relationship binding")){
          for (int i=0;i<Children_Login.data1.getChildrenArrayList().size();i++){
             if( Children_Login.data1.getChildrenArrayList().get(i).getUsername().equals(selectedItem.getApply_for_Name())){
                 Children_Login.data1.getChildrenArrayList().get(i).setPatriarch(Children_Login.parent);
                 Children_Login.parent.getChildren().add(Children_Login.data1.getChildrenArrayList().get(i));
             }
          }
      }

      if(selectedItem.getType().equals("Purchase Goods")){
          for (int i=0;i<Children_Login.data1.getChildrenArrayList().size();i++){
              if( Children_Login.data1.getChildrenArrayList().get(i).getUsername().equals(selectedItem.getApply_for_Name())){
                  Children_Login.data1.getChildrenArrayList().get(i).setMoney_H(Children_Login.data1.getChildrenArrayList().get(i).getMoney_H()-selectedItem.getMoney());
                  com.cx.entity.Records records=new Records();
                  records.setMoney(selectedItem.getMoney());
                  records.setType("Pay");
                  records.setDescription("Purchase Goods:"+selectedItem.getDescription());
                  SimpleDateFormat customFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                  String formattedDate = customFormat.format(new Date());
                  records.setTime(formattedDate);
                  Children_Login.data1.getChildrenArrayList().get(i).getRecords().add(records);
              }
          }
      }

      if (selectedItem.getType().equals("Withdraw")){
          for (int i=0;i<Children_Login.data1.getChildrenArrayList().size();i++){
              if( Children_Login.data1.getChildrenArrayList().get(i).getUsername().equals(selectedItem.getApply_for_Name())){
                  Children_Login.data1.getChildrenArrayList().get(i).setMoney_H(Children_Login.data1.getChildrenArrayList().get(i).getMoney_H()-selectedItem.getMoney());
                  com.cx.entity.Records records=new Records();
                  records.setMoney(selectedItem.getMoney());
                  records.setType("amount");
                  records.setDescription("Withdraw"+selectedItem.getMoney());
                  SimpleDateFormat customFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                  String formattedDate = customFormat.format(new Date());
                  records.setTime(formattedDate);
                  Children_Login.data1.getChildrenArrayList().get(i).getRecords().add(records);
              }
          }

      }
      File_Date.Writ_File(Children_Login.data1);

    }


    /**
     * 设置数据
     */
    public void Set_Data(ArrayList<Apply_For> apply_fors){
        tableView.getItems().clear();
        for (int i=0;i<apply_fors.size();i++){


                if(apply_fors.get(i).GetIs()){

                }else {
                    tableView.getItems().add(apply_fors.get(i));

                }
            }

    }


}
