package com.cx.Page;

import com.cx.entity.Records;
import com.cx.entity.Task;
import com.cx.utils.File_Date;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class TaskManagement {

    @FXML
    TableView tableView;


    @FXML
    TableColumn name;


    @FXML
    TableColumn type;

    @FXML
    TableColumn blanc;

    @FXML
    TableColumn time;


    public void initialize() {
        name. setCellValueFactory(new PropertyValueFactory("name"));
        type. setCellValueFactory(new PropertyValueFactory("Description"));
        blanc. setCellValueFactory(new PropertyValueFactory("money"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));

    }

    /**
     * 任务通过
     */
    public void Grant_request(){
        Task selectedItem = (Task) tableView.getSelectionModel().getSelectedItem();

        if (selectedItem.getName()==null){
            Alert A=new Alert(Alert.AlertType.ERROR);
            A.setTitle("Pass the task");
            A.setHeaderText("Error, the task was not taken");
            A.showAndWait();
            return;
        }
       selectedItem.setIs(true);

        for (int i=0;i<Children_Login.data1.getChildrenArrayList().size();i++){
            if(Children_Login.data1.getChildrenArrayList().get(i).getUsername().equals(selectedItem.getName())){
                Children_Login.data1.getChildrenArrayList().get(i).setMoney_H(Children_Login.data1.getChildrenArrayList().get(i).getMoney_H()+selectedItem.getMoney());
                com.cx.entity.Records records=new Records();
                records.setMoney(selectedItem.getMoney());
                records.setType("Income");
                records.setDescription("Task settlement:"+selectedItem.getDescription());
                SimpleDateFormat customFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String formattedDate = customFormat.format(new Date());
                records.setTime(formattedDate);
                Children_Login.data1.getChildrenArrayList().get(i).getRecords().add(records);
            }
        }

        File_Date.Writ_File(Children_Login.data1);
        Set_Data(Children_Login.parent.getTasks());

    }

    /**
     * 任务失败
     */
    public void Task_failure(){
        Task selectedItem = (Task) tableView.getSelectionModel().getSelectedItem();
        if(selectedItem.getName()!=null) {
            selectedItem.setIs(true);

            File_Date.Writ_File(Children_Login.data1);
            Set_Data(Children_Login.parent.getTasks());
        }else {
            Alert A=new Alert(Alert.AlertType.ERROR);
            A.setTitle("Pass the task");
            A.setHeaderText("Error, the task was not taken on");
            A.showAndWait();
        }
    }


    /**
     * 添加任务
     */
    public void Add_Task(){
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Publish Task");
        dialog.setHeaderText("Please enter task information");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Publish", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Time");
        TextField password = new TextField();
        password.setPromptText("Reward");

        TextArea textArea=new TextArea();
        grid.add(new Label("Time:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Reward:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(new Label("Description"),0,2);
        grid.add(textArea,0,3);


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
            Task task=new Task();
            task.setDescription(textArea.getText());
            task.setTime(username.getText());
            try {
                task.setMoney(Double.parseDouble(password.getText()));
                Children_Login.parent.getTasks().add(task);
                task.setIs(false);
                File_Date.Writ_File(Children_Login.data1);
                Set_Data(Children_Login.parent.getTasks());

            } catch (NumberFormatException e) {
                Alert A=new Alert(Alert.AlertType.ERROR);
                A.setTitle("Publish");
                A.setHeaderText("Error, please enter the correct information");
                A.showAndWait();
            }




        });
    }

    /**
     * 删除任务
     */
    public void Delete_Task(){
        Task selectedItem = (Task) tableView.getSelectionModel().getSelectedItem();
        if(selectedItem.getName()==null){
            Children_Login.parent.getTasks().remove(selectedItem);
            File_Date.Writ_File(Children_Login.data1);
            Set_Data(Children_Login.parent.getTasks());

        }else {
            Alert A=new Alert(Alert.AlertType.ERROR);
            A.setTitle("Delete");
            A.setHeaderText("Error, the task has already been taken by a child and cannot be deleted");
            A.showAndWait();
        }
    }
    /**
     * 设置数据
     */
    public void Set_Data(ArrayList<Task> apply_fors){
        tableView.getItems().clear();
        for (int i=0;i<apply_fors.size();i++){


            if(apply_fors.get(i).isIs()){

            }else {
                tableView.getItems().add(apply_fors.get(i));

            }
        }

    }

}
