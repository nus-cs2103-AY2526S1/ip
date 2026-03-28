package cattis;

import java.io.IOException;

import cattis.component.DialogBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main instance of the application
 */
public class Main extends Application {
    private static final String DEFAULT_SCREEN = "/view/ChatScreen.fxml";
    private Cattis cattis = new Cattis();
    @FXML
    private ScrollPane dialogScrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    @FXML
    public void initialize() {
        dialogScrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @Override
    public void start(Stage stage) {
        try {
            var startConfig = new Configuration(
                    DEFAULT_SCREEN, 0.4, 0.7);
            setStage(stage, startConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setStage(Stage stage, Configuration config) throws IOException {
        config.loadConfiguration(stage);
        stage.show();
    }

    /**
     * Handle user input from text field
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        cattis.getUi().resetMessages();
        boolean isExit = cattis.runOneCommand(input);
        if (isExit) {
            Platform.exit();
        }
        String response = cattis.getUi().getLatestMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getCattisDialog(response, cattis.getUi().getUiStatus())
        );
        userInput.clear();
    }

    /**
     * Handle deferred messages in UI
     */
    private void handleDeferredMessages() {
        String response = cattis.getUi().getLatestMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getCattisDialog(response, cattis.getUi().getUiStatus())
        );
        userInput.clear();
    }
}
