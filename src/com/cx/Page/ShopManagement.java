package com.cx.Page;

import com.cx.entity.Product;
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
    TableColumn Good;


    @FXML
    TableColumn Price;


    public void initialize() {
        Good. setCellValueFactory(new PropertyValueFactory("name"));

        Price. setCellValueFactory(new PropertyValueFactory("money"));
        Set_Data();

    }

    /**
     * 删除商品
     * @param event
     */
    public void Delete_Shop(ActionEvent event) {
        Product product= (Product) tableView.getSelectionModel().getSelectedItem();
        Children_Login.parent.getProductArrayList().remove(product);
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
        dialog.setTitle("Publish Good");
        dialog.setHeaderText("Please enter good's information");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Publish", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Description of Goods");
        TextField password = new TextField();
        password.setPromptText("Price");

        grid.add(new Label("Description of Goods:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Price:"), 0, 1);
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
                Children_Login.parent.getProductArrayList().add(product);
                File_Date.Writ_File(Children_Login.data1);
                Set_Data();
            } catch (NumberFormatException e) {
                Alert A=new Alert(Alert.AlertType.ERROR);
                A.setTitle("Publish Goods");
                A.setHeaderText("Error, please enter the correct information");
                A.showAndWait();
            }




        });

    }



    /**
     * 设置数据
     */
    public void Set_Data(){
        tableView.getItems().clear();
        ArrayList<Product> products = Children_Login.parent.getProductArrayList();
        for (int i=0;i<products.size();i++){
            tableView.getItems().add(products.get(i));
        }
    }

}
