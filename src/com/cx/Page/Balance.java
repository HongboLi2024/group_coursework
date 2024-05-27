package com.cx.Page;

import com.cx.entity.Apply_For;
import com.cx.utils.File_Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class Balance {

    @FXML
    Label D;

    @FXML
    Label H;

    @FXML
    ProgressBar progressBar;//目标

    public void initialize() {

        D.setText("定期余额:"+Children_Login.children.getMoney_D());
        H.setText("定期余额:"+Children_Login.children.getMoney_H());



        progressBar.setProgress(Children_Login.children.getD_goal()/Children_Login.children.getGoal());

    }


    /**
     * 提取现金
     */
    public void Withdraw(){
        TextInputDialog dialog = new TextInputDialog("金额");
        dialog.setTitle("提取现金");
        dialog.setHeaderText("输入你要提取的金额");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try {
                double i= Double.parseDouble(result.get());

                if(i<=Children_Login.children.getMoney_H()){
                    Apply_For apply_for=new Apply_For();
                    apply_for.setApply_for_Name(Children_Login.children.getUsername());
                    apply_for.setType("提取现金");
                    apply_for.setMoney(i);
                    Children_Login.children.getPatriarch().getApply_fors().add(apply_for);
                    File_Date.Writ_File(Children_Login.data1);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("提取余额");
                    alert.setHeaderText(null);
                    alert.setContentText("已发送请求至家长，等待家长审批");

                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("提取失败");
                    alert.setContentText("余额不足");

                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("提取失败");
                alert.setContentText("请输入数字");

                alert.showAndWait();
            }
        }

    }

    /**
     * 目标
     */
    public void goal(){
        TextInputDialog dialog = new TextInputDialog("金额");
        dialog.setTitle("存入目标");
        dialog.setHeaderText("输入你要存入的金额");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try {
                double i= Double.parseDouble(result.get());

                if(i<=Children_Login.children.getMoney_H()){

                    Children_Login.children.setD_goal(Children_Login.children.getD_goal()+i);
                    File_Date.Writ_File(Children_Login.data1);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("存入目标");
                    alert.setHeaderText(null);
                    alert.setContentText("存入成功");

                    alert.showAndWait();
                    progressBar.setProgress(Children_Login.children.getD_goal()/Children_Login.children.getGoal());
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("存入失败");
                    alert.setContentText("余额不足");

                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("提取失败");
                alert.setContentText("请输入数字");

                alert.showAndWait();
            }
        }
    }
    /**
     * 定期
     */
    public void intervals(){
        TextInputDialog dialog = new TextInputDialog("金额");
        dialog.setTitle("存入定期");
        dialog.setHeaderText("输入你要存入的金额");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try {
                double i= Double.parseDouble(result.get());

                if(i<=Children_Login.children.getMoney_H()){

                    Children_Login.children.setMoney_D(Children_Login.children.getMoney_D()+i);
                    Children_Login.children.setMoney_H(Children_Login.children.getMoney_H()-i);
                    D.setText("定期余额:"+Children_Login.children.getMoney_D());
                    File_Date.Writ_File(Children_Login.data1);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("存入定期");
                    alert.setHeaderText(null);
                    alert.setContentText("存入成功");

                    alert.showAndWait();

                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("存入失败");
                    alert.setContentText("余额不足");

                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("提取失败");
                alert.setContentText("请输入数字");

                alert.showAndWait();
            }
        }
    }

    }
