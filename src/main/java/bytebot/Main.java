package bytebot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for the JavaFX app.
 */
public class Main extends Application {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image byteImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setTitle("ByteBot");

        MainWindow controller = fxmlLoader.getController();
        controller.setAvatarImages(userImage, byteImage);
        controller.setByteBot(new ByteBot(true));

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


