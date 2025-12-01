package candy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

/**
 * A main class where application would start in.
 *
 * Reused from
 * https://se-education.org/guides/tutorials/javaFx.html
 * with minor modifications
 */
public class Main extends Application {

    private Candy candy = new Candy("./data/candyStorage.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            //Font from google font
            //chatGPT to help with the method for loading font:
            Font.loadFont(getClass().getResource("/fonts/Chewy-Regular.ttf").toExternalForm(), 10);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Candy 🍬");
            fxmlLoader.<MainWindow>getController().setCandy(candy);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
