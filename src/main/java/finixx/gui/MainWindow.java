package finixx.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import finixx.Finixx;
import finixx.ui.Ui;

/**
 * Controller for main GUI of the Finixx application.
 * This class handles user interactions and updates the GUI components accordingly.
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

    private Finixx finixx;

    // Icon Credit:
    // "Young" icons created by Freepik - Flaticon
    // https://www.flaticon.com/free-icons/young
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    // Icon Credit:
    // "Phoenix" icons created by Freepik - Flaticon
    // https://www.flaticon.com/free-icons/phoenix
    private final Image finixxImage = new Image(this.getClass().getResourceAsStream("/images/DaFinixx.png"));

    /**
     * Initializes the GUI controller.
     * Sets up the scroll pane to follow the height of the dialog container and allows the dialog container to
     * grow vertically as needed.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        VBox.setVgrow(dialogContainer, Priority.ALWAYS);
    }

    /**
     * Sets the Finixx instance to be used by this controller.
     *
     * @param d The Finixx instance to be used by this controller
     */
    public void setFinixx(Finixx d) {
        finixx = d;

        String botGreeting = Ui.printGreeting();
        String botHelp = Ui.printHelp();
        dialogContainer.getChildren().addAll(
                DialogBox.getFinixxDialog(botGreeting, finixxImage),
                DialogBox.getFinixxDialog(botHelp, null)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = finixx.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFinixxDialog(response, finixxImage)
        );
        userInput.clear();
    }
}
