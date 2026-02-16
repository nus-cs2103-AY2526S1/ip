package geni;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Geni geni;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Sample_User_Icon.png"));
    private Image geniImage = new Image(this.getClass().getResourceAsStream("/images/chatbot_geni.png"));

    /**
     * Initializes the main window after its FXML has been loaded.
     * <p>
     * Sets up scroll behavior, dialog resizing, stylesheet attachment,
     * and displays an initial greeting message from the assistant.
     * </p>
     */
    @FXML
    public void initialize() {
        // keep scroll at bottom when new messages are added
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Ensure ScrollPane content expands horizontally
        scrollPane.setFitToWidth(true);

        // Respond to width changes so dialog labels wrap nicely.
        dialogContainer.widthProperty().addListener((obs, oldW, newW) -> {
            double maxBubbleWidth = newW.doubleValue() * 0.75; // 75% of container width
            for (var node : dialogContainer.getChildren()) {
                if (node instanceof DialogBox db) {
                    db.setMaxDialogWidth(maxBubbleWidth);
                }
            }
        });

        // Attach stylesheet when the scene becomes available.
        // NOTE: sceneProperty listener provides the new Scene directly (no getScene() call).
        this.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                String css = getClass().getResource("/styles/styles.css").toExternalForm();
                if (!newScene.getStylesheets().contains(css)) {
                    newScene.getStylesheets().add(css);
                }
            }
        });
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        "Hey, I am your personal assistant!\n How May I help you today ?",
                        geniImage
                )
        );
    }

    /** Injects the Duke instance */
    public void setDuke(Geni d) {
        geni = d;
        String greeting = d.getGreetingMessage();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = geni.getResponse(input);

        var userBox = DialogBox.getUserDialog(input, userImage);
        var botBox = DialogBox.getDukeDialog(response, geniImage);

        // Mark botBox as error if response seems to indicate an error (simple heuristic)
        if (response != null) {
            String r = response.toLowerCase();
            if (r.contains("error") || r.contains("unknown command") || r.contains("invalid")) {
                botBox.setAsError();
            }
            boolean isError = response != null && response.startsWith("OOPS!");
            if (isError) {
                botBox.setAsError();
            }
        }

        dialogContainer.getChildren().addAll(userBox, botBox);
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
            System.exit(0);
        }
    }
}
