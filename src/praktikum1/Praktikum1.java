
package praktikum1;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sarah Grugiel
 */
public class Praktikum1 extends Application {
    
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

