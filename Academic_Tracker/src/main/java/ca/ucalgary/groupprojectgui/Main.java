package ca.ucalgary.groupprojectgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import static javafx.application.Application.*;

/**
 * @author Amy Wiebe, Tutorial 13
 * @author Cassandra Rodberg, Tutorial 17
 * @author Devanshi Iragavarapu, Tutorial 08
 * @date April 15th, 2024
 * @presenting Tutorial 13
 */

public class Main extends Application {
    public static void main(String[] args){
        if(args.length > 1){
            System.err.println("Expected one command line argument for file to load from");
        }
        if (args.length == 1) {
            String fileName = args[0];
            file = new File( fileName );
            System.out.println( "hi" );
            if (!file.exists() || !file.canRead()) {
                System.err.println( "Cannot load from the file " + fileName );
                System.exit( 1 );
            }
        }
        launch(  );
    }
    private static File file = null;
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 645, 720);
        MenuController cont = fxmlLoader.getController();
        cont.retrieveData(file);
        stage.setTitle("Academic Tracker");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
