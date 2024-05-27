package com.cx.Page;

import com.cx.entity.Apply_For;
import com.cx.entity.Data;
import com.cx.utils.File_Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ChildrenMain {

    @FXML
    Label patriarch;


    public void initialize() {

        if (Children_Login.children.getPatriarch() != null) {
            patriarch.setText("家长:" + Children_Login.children.getPatriarch().getUsername());
        } else {
            patriarch.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    TextInputDialog dialog = new TextInputDialog("用户名");
                    dialog.setTitle("账户绑定");
                    dialog.setContentText("请输入你需要绑定的用户名:");

// Traditional way to get the response value.
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        Data data = File_Date.Read_File();
                        for (int i=0;i<data.getPatriarchArrayList().size();i++){
                            if(data.getPatriarchArrayList().get(i).getUsername().equals(result.get())){
                                Apply_For apply_for=new Apply_For();
                                apply_for.setApply_for_Name(Children_Login.children.getUsername());
                                apply_for.setType("关系绑定");
                                data.getPatriarchArrayList().get(i).getApply_fors().add(apply_for);
                                Children_Login.children.getApply_fors().add(apply_for);
                                File_Date.Writ_File(data);

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("关系绑定");
                                alert.setHeaderText(null);
                                alert.setContentText("已成功发送绑定请求，等待家长同意");

                                alert.showAndWait();
                                return;
                            }
                        }

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("关系绑定");
                        alert.setHeaderText(null);
                        alert.setContentText("错误，该账户不存在");

                        alert.showAndWait();
                    }


                }
            });
        }

    }
    /**
     * 余额
     * @param event
     * @throws IOException
     */
    public void Balance(ActionEvent event) throws IOException {


        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Balance.fxml"))));
        stage.setTitle("余额");
        stage.show();
    }


    /**
     * 流水
     */
    public void Running_water() throws IOException {
        Stage stage=new Stage();


        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Records.fxml"));
        AnchorPane anchorPane=fxmlLoader.load();
        Records records= fxmlLoader.getController();
        records.setDate(Children_Login.children.getRecords());
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("流水");
        stage.show();
    }

    /**
     * 任务列表
     */
    public void Task_List() throws IOException {
        if(Children_Login.children.getPatriarch()!=null){
            Stage stage=new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Task_List.fxml"))));
            stage.setTitle("任务列表");
            stage.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("任务列表");
            alert.setHeaderText(null);
            alert.setContentText("错误,请先绑定家长");
            alert.showAndWait();
        }
    }


    /**
     * 商品
     * @throws IOException
     */
    public void Shop() throws IOException {
        if(Children_Login.children.getPatriarch()!=null){
            Stage stage=new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Shop.fxml"))));
            stage.setTitle("商品列表");
            stage.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("任务列表");
            alert.setHeaderText(null);
            alert.setContentText("错误,请先绑定家长");
            alert.showAndWait();
        }
    }

}
