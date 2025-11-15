package nami.ui;

import nami.NamiGUI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Controller for the main GUI. */
public class MainWindow {

    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private NamiGUI nami;

    private final Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image namiImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        // Auto-scroll to bottom when new dialogs are added
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Nami bot instance and shows a welcome message. */
    public void setNami(NamiGUI bot) {
        this.nami = bot;
        String welcome =
                "_______________________________________\n"
                        + " Hello! I'm Nami\n\n"
                        + "What can I do for you?\n"
                        + "_____________________________________\n";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcome, namiImage));
    }

    /** Handles user input: echo bubble (right) + Nami reply bubble (left). */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) return;

        String response = nami.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, namiImage)
        );
        userInput.clear();

        if (nami.shouldExit()) {
            // let the goodbye render, then close
            new Thread(() -> {
                try { Thread.sleep(250); } catch (InterruptedException ignored) {}
                Platform.runLater(() -> {
                    Stage stage = (Stage) dialogContainer.getScene().getWindow();
                    stage.close();
                });
            }).start();
        }
    }
}
