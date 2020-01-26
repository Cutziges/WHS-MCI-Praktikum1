/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Prof.Dr.AndreasM
 */
public class Diagram extends Application {
    
    private static final String APP_TITLE = "Trinkreife";
    private static final String FXML_FILE = "DiagramFXML.fxml";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_FILE));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle(APP_TITLE);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
