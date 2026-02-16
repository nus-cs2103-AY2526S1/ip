package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import weewee.Weewee;
import weewee.ui.Ui;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Weewee weewee = new Weewee("./data/weewee.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Weewee Chatbot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Ui ui = new Ui();
            fxmlLoader.<MainWindow>getController().setWeewee(weewee, ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
