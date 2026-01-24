package noob.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noob.Noob;
import noob.gui.controller.MainWindow;

public class Main extends Application {
    private static final int MAIN_LAYOUT_HEIGHT = 800;
    private static final int MAIN_LAYOUT_WIDTH = 600;

    private Noob noob = new Noob();

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Initialization
            MainWindow controller = fxmlLoader.getController();
            controller.setNoob(noob);
            controller.initializeComponentFields();

            stage.show();
            setUpStage(stage);

            // Onboarding message
            controller.greet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the stage upon GUI launch
     *
     * @param stage Stage to be set
     */
    private void setUpStage(Stage stage) {
        stage.setTitle("NoobBot");
        stage.setResizable(false);
        stage.setMinHeight(MAIN_LAYOUT_HEIGHT);
        stage.setMinWidth(MAIN_LAYOUT_WIDTH);
    }
}
