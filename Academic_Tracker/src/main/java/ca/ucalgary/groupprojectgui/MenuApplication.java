package ca.ucalgary.groupprojectgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Amy Wiebe, Tutorial 13
 * @author Cassandra Rodberg, Tutorial 17
 * @author Devanshi Iragavarapu, Tutorial 08
 * @date April 15th, 2024
 * @presenting Tutorial 13
 */

/**
 * MenuApplication starts the program and creates the JavaFX scene
 */
public class MenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader( MenuApplication.class.getResource( "PGUI.fxml" ) );
        Scene scene = new Scene( fxmlLoader.load(), 645, 720  );
        stage.setTitle( "Academic Tracker" );
        stage.setResizable( false );
        stage.setScene( scene );
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}