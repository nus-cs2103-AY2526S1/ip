package buddy.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/* Re-use policy
 * Code was modified from JavaFX tutorial.
 */


public class Main extends Application {

    // Not the CLI Buddy
    private final BuddyLogic logic = new BuddyLogic();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            MainWindow controller = fxmlLoader.getController();
            controller.setBuddyLogic(logic);           // inject instance of BuddyLogic
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
