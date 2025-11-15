package nami.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloFxProbe extends Application {
    @Override
    public void start(Stage s) {
        s.setScene(new Scene(new Label("JavaFX OK!"), 240, 120));
        s.show();
    }
    public static void main(String[] args) { launch(args); }
}