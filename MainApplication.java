package com.example.library_acccounting_system_v01;

import javafx.application.Application;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {

    private double x = 0;
    private double y = 0;
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);



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


        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}