import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import joe.Joe;
import joe.ui.Ui;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Image joeImage = new Image(this.getClass().getResourceAsStream("/images/joe.png"));
    private Joe joe = new Joe();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Joe Chat Bot");
            fxmlLoader.<MainWindow>getController().setJoe(joe); // inject the Duke instance

            fxmlLoader.<MainWindow>getController().getDialogContainer().getChildren()
                    .add(DialogBox.getJoeDialog(Ui.welcomeText(), joeImage));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
