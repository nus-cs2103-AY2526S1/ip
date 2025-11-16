package tuesday.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tuesday.Tuesday;


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

    private Tuesday tuesday;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/khac.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/xoi.png"));

    @FXML
    public void initialize() {
        this.setId("mainWindow");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setTuesday(Tuesday d) {
        tuesday = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tuesday.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTuesdayDialog(response, dukeImage)
        );
        userInput.clear();
        System.out.println(tuesday.isExit());
        if (tuesday.isExit()) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}
