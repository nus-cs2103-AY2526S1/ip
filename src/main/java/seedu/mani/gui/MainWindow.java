package seedu.mani.gui;

import seedu.mani.ui.Mani;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/*
 * This code was developed with guidance from ChatGPT (OpenAI).
 * ChatGPT provided advice on implementing better UI in JavaFX.
 * Date: 22-Sep-2025
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

    private Mani mani;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setSpacing(5);
        dialogContainer.setPadding(new Insets(10));

        userInput.setOnAction(event -> handleUserInput()); // Enter key

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Mani.\nWhat can I do for you today?", dukeImage)
        );
        dialogContainer.setFillWidth(true);
        dialogContainer.setMaxWidth(450);
    }

    public void setMani(Mani m) {
        mani = m;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) return;

        String response = mani.getResponse(input);

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        if (response.startsWith("Error:")) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, dukeImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        }

        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog("Goodbye! See you next time.", dukeImage));
            Platform.exit();
        }
    }
}