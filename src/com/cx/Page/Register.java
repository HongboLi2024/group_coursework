package com.cx.Page;

import com.cx.entity.Children;
import com.cx.entity.Data;
import com.cx.entity.Parent;
import com.cx.utils.File_Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Register {


    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    ChoiceBox<String> id;



    public void initialize() {


        id.getItems().add("Parent");
        id.getItems().add("Children");
        id.getSelectionModel().select(0);
    }

    /**
     * 注册
     * @param event
     */
    public void Register(ActionEvent event) throws IOException {

        Data data = File_Date.Read_File();


        boolean c=true;//判断当前用户名是否被注册

        for (int i=0;i<data.getChildrenArrayList().size();i++){
            if(data.getChildrenArrayList().get(i).getUsername().equals(username.getText())){
                c=false;
            }
        }

        for (int i=0;i<data.getPatriarchArrayList().size();i++){
            if(data.getPatriarchArrayList().get(i).getUsername().equals(username.getText())){
                c=false;
            }
        }

        if(c) {
            if (id.getSelectionModel().getSelectedIndex() == 0) {
                //家长账户
                Parent parent = new Parent();
                parent.setUsername(username.getText());
                parent.setPassword(password.getText());
                data.getPatriarchArrayList().add(parent);
                File_Date.Writ_File(data);


            } else {
                //儿童账户

                Children children = new Children();
                children.setUsername(username.getText());
                children.setPassword(password.getText());
                children.setMoney_H(0);
                children.setMoney_D(0);
                data.getChildrenArrayList().add(children);
                File_Date.Writ_File(data);

            }

            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Registration successful, return to login");
            alert.showAndWait();
            Login(event);
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Registration failed, the account has already been registered");
            alert.showAndWait();
        }


    }


    /**
     * 返回登录
     * @param event
     * @throws IOException
     */
    public void Login(ActionEvent event) throws IOException {


        Button source = (Button) event.getSource();
        Stage stage= (Stage) source.getScene().getWindow();
        stage.setTitle("Log in");
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")))));
    }
}
