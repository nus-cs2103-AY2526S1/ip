package pero;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pero.exception.PeroException;
import pero.ui.GuiUi;

import java.io.IOException;

/**
 * Controller for the main GUI.
 * Receives input from TextField and Button events
 * calls Pero GetResponse(input) to process input command and return relevant string
 * Updates GUI with new chat bubbles
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

    private Pero pero;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image peroImage = new Image(this.getClass().getResourceAsStream("/images/Pero.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    /**
     * Injects the Pero instance
     * @param p Pero instance
     */
    public void setPero(Pero p) {
        pero = p;
        GuiUi guiUi = new GuiUi();
        dialogContainer.getChildren().add(
                DialogBox.getPeroDialog(guiUi.getWelcome(pero.getTasks()), peroImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Pero's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws PeroException, IOException {
        String input = userInput.getText();
        String response;

        try {
            response = pero.getResponse(input);
        } catch (PeroException | IOException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPeroDialog(response, peroImage)
        );
        userInput.clear();
    }
}
