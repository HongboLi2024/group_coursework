package com.cx.Page;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class parent {

    public void initialize() {


    }


    /**
     * 处理请求
     * @throws IOException
     */
    public void RequestProcessing() throws IOException {
        Stage stage=new Stage();


        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Request_Processing.fxml"));
        AnchorPane anchorPane=fxmlLoader.load();
        RequestProcessing records= fxmlLoader.getController();
        records.Set_Data(Children_Login.parent.getApply_fors());
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Processing Requirement");
        stage.show();
    }


    /**
     * 任务管理
     * @throws IOException
     */
    public void Task() throws IOException {
        Stage stage=new Stage();


        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Task_Management.fxml"));
        AnchorPane anchorPane=fxmlLoader.load();
        TaskManagement records= fxmlLoader.getController();
        records.Set_Data(Children_Login.parent.getTasks());
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Task Management");
        stage.show();
    }


    /**
     * 商品管理
     */
    public void Shop() throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Shop_Management.fxml"))));
        stage.setTitle("Goods Management");
        stage.show();
    }
    /**
     * 我的孩子管理
     */
    public void Child() throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("My_Child.fxml"))));
        stage.setTitle("My Child");
        stage.show();
    }
}
