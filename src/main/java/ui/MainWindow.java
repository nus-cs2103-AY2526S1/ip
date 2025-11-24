package ui;

import cheryl.Cheryl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Cheryl cheryl;

    private final Image userImage = new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/images/DaUser.png"),
                    "Missing resource: /images/DaUser.png"));
    private final Image cherylImage = new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/images/DaCheryl.png"),
                    "Missing resource: /images/DaCheryl.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the main Cheryl logic instance into this controller. */
    public void setCheryl(Cheryl cheryl) {
        this.cheryl = cheryl;

        // Display welcome message
        dialogContainer.getChildren().add(
                DialogBox.getCherylDialog("Hello! I'm Cheryl\nWhat can I do for you?", cherylImage)
        );
    }

    /** Handles one line of user input: echoes user text and shows Cheryl's reply. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        String response;
        try {
            response = cheryl.getResponse(input);
        } catch (Exception e) {
            response = normalizeError(e);
            e.printStackTrace();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCherylDialog(response, cherylImage)
        );

        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye") || input.trim().equalsIgnoreCase("exit")) {
            dialogContainer.getChildren().add(
                    DialogBox.getCherylDialog("Bye! Hope to see you again soon!", cherylImage)
            );
            Platform.exit();
        }
    }

    /** Ensures errors are not prefixed with multiple OOPS!!! messages. */
    private String normalizeError(Exception e) {
        String msg = e.getMessage() == null ? e.toString() : e.getMessage();
        // Remove duplicate OOPS!!! if already present
        msg = msg.replaceAll("(?i)\\bOOPS!!!\\s*", "");
        return "OOPS!!! " + msg;
    }
}

