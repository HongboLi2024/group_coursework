package com.cx.Page;

import com.cx.entity.Children;
import com.cx.entity.Data;
import com.cx.entity.Patriarch;
import com.cx.utils.File_Date;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class Children_Login {

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    ChoiceBox<String> id;

    public static Children children;
    public static Patriarch patriarch;
    public static Data data1;

    public void initialize() {

        id.getItems().add("Children");
        id.getItems().add("Parent");

        id.getSelectionModel().select(0);
    }
    /**
     * 登录
     * @param e
     * @throws IOException
     */
    public void Login(Event e) throws IOException {
        Data data= File_Date.Read_File();

        if(id.getSelectionModel().getSelectedIndex()==1){
            //家长

            for (int i=0;i<data.getPatriarchArrayList().size();i++){
                if(data.getPatriarchArrayList().get(i).getUsername().equals(username.getText())){
                    if(data.getPatriarchArrayList().get(i).getPassword().equals(password.getText())){
                        patriarch=data.getPatriarchArrayList().get(i);
                        data1=data;
                        Button source = (Button) e.getSource();
                        Stage window = (Stage) source.getScene().getWindow();
                        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("patriarch.fxml"))));
                        window.setTitle("Parents End");

                        return;
                    }
                }
            }

        }else if(id.getSelectionModel().getSelectedIndex()==0){
            //儿童

            for (int i=0;i<data.getChildrenArrayList().size();i++){
                if(data.getChildrenArrayList().get(i).getUsername().equals(username.getText())){
                    if(data.getChildrenArrayList().get(i).getPassword().equals(password.getText())){
                        children=data.getChildrenArrayList().get(i);
                        data1=data;
                        Button source = (Button) e.getSource();
                        Stage window = (Stage) source.getScene().getWindow();
                        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("Children_Main.fxml"))));
                        window.setTitle("Children End");

                        return;
                    }
                }
            }
        }

        Alert A=new Alert(Alert.AlertType.ERROR);
        A.setTitle("Welcome to Children Bank");
        A.setHeaderText("Error, account does not exist or password is incorrect");
        A.showAndWait();
//        Button source = (Button) e.getSource();
//        Stage stage= (Stage) source.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Children_Main.fxml")))));

    }

    /**
     * 注册
     * @param e
     * @throws IOException
     */
    public void Register(Event e) throws IOException {
        Label source = (Label) e.getSource();
        Stage stage= (Stage) source.getScene().getWindow();
        stage.setTitle("Register");
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")))));
    }
}
