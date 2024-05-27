package com.cx.Page;

import com.cx.entity.Product;
import com.cx.entity.Task;
import com.cx.utils.File_Date;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Optional;

public class ShopManagement {

    @FXML
    TableView tableView;
    @FXML
    TableColumn name;
    @FXML
    TableColumn price;


    public void initialize() {
        name. setCellValueFactory(new PropertyValueFactory("name"));

        price. setCellValueFactory(new PropertyValueFactory("money"));
        Set_Data();

    }

    /**
     * 删除商品
     * @param event
     */
    public void Delete_Shop(ActionEvent event) {
        Product product= (Product) tableView.getSelectionModel().getSelectedItem();
        Children_Login.patriarch.getPatriarchArrayList().remove(product);
        File_Date.Writ_File(Children_Login.data1);
        Set_Data();
    }

    /**
     * 添加商品
     * @param event
     */
    public void Add_Shop(ActionEvent event) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("添加商品");
        dialog.setHeaderText("请输入商品信息");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("添加", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("商品说明");
        TextField password = new TextField();
        password.setPromptText("金额");

        grid.add(new Label("商品说明:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("金额:"), 0, 1);
        grid.add(password, 1, 1);


// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            Product product=new Product();
            product.setName(username.getText());


            try {
             product.setMoney(Double.parseDouble(password.getText()));
                Children_Login.patriarch.getPatriarchArrayList().add(product);
                File_Date.Writ_File(Children_Login.data1);
                Set_Data();
            } catch (NumberFormatException e) {
                Alert A=new Alert(Alert.AlertType.ERROR);
                A.setTitle("添加任务");
                A.setHeaderText("错误,请输入正确信息");
                A.showAndWait();
            }
        });

    }



    /**
     * 设置数据
     */
    public void Set_Data(){
        tableView.getItems().clear();
        ArrayList<Product> products = Children_Login.patriarch.getPatriarchArrayList();
        for (int i=0;i<products.size();i++){
            tableView.getItems().add(products.get(i));
        }
    }

}
