package com.example.library_acccounting_system_v01;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.ResultSet;

public class MainController {
    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;


    public void adminLogin() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDB();

        try {
            Alert alert;
            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank Space");
                alert.showAndWait();
            }else{
                prepare = (PreparedStatement) connect.prepareStatement(sql);
                prepare.setString(1, username.getText());
                prepare.setString(2,password.getText());

                result = prepare.executeQuery();

                if(result.next()){
//                    THEN PROCEED TO DASHBOARD
                    getData.username = username.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Connected Successfully!");
                    alert.showAndWait();

                    //Hide the form
                    loginBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.initStyle(StageStyle.TRANSPARENT);

                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);

                        stage.setOpacity(.8);
                    });
                    root.setOnMouseReleased((MouseEvent event) -> {
                        stage.setOpacity(1);
                    });


                    stage.setScene(scene);
                    stage.show();

                }else {
//                    SEND ERROR MESSAGE HERE
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username/password");
                    alert.showAndWait();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }
}