package com.cx.Page;

import com.cx.entity.Task;
import com.cx.utils.File_Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Task_List {

    @FXML
    TableView tableView;


    @FXML
    TableColumn time;


    @FXML
    TableColumn type;

    @FXML
    TableColumn blanc;


    public void initialize() {
        time. setCellValueFactory(new PropertyValueFactory("time"));
        type. setCellValueFactory(new PropertyValueFactory("Description"));
        blanc. setCellValueFactory(new PropertyValueFactory("money"));
        tableView.setItems(FXCollections.observableList(Children_Login.children.getPatriarch().getTasks()));
    }

    /**
     * 接受任务
     */
    public void Accept_Task(){
        Task task= (Task) tableView.getSelectionModel().getSelectedItem();
        if(task!=null){

            for (int i=0;i<Children_Login.children.getPatriarch().getTasks().size();i++){
                if(Children_Login.children.getPatriarch().getTasks().get(i).getDescription().equals(task.getDescription())){
                    Children_Login.children.getPatriarch().getTasks().get(i).setName(Children_Login.children.getUsername());
                    File_Date.Writ_File(Children_Login.data1);
                    Update();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("接受任务");
                    alert.setHeaderText(null);
                    alert.setContentText("接受任务成功");

                    alert.showAndWait();

                }
            }


        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("接受任务");
            alert.setHeaderText(null);
            alert.setContentText("错误,请从任务列表选择任务");

            alert.showAndWait();
        }
    }

    public void Update(){
        ArrayList<Task> tasks=new ArrayList<>();
        for (int i=0;i<Children_Login.children.getPatriarch().getTasks().size();i++){
            if(Children_Login.children.getPatriarch().getTasks().get(i).getName()==null){
                tasks.add(Children_Login.children.getPatriarch().getTasks().get(i));
            }
        }
        tableView.setItems(FXCollections.observableList(tasks));
    }
}
