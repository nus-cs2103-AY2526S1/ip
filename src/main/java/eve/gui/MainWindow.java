package eve.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import eve.ui.Ui;

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

    private eve.Eve eve;

    private Image userImage;
    private Image eveImage;
    private final Ui ui = new Ui();

    @FXML
    public void initialize() {

        assert scrollPane != null : "ScrollPane must be injected by FXML";
        assert dialogContainer != null : "Dialog container must be injected by FXML";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Optional debug logs
        System.out.println("Classpath root -> " + getClass().getResource("/"));
        System.out.println("Check /view/DialogBox.fxml -> " + getClass().getResource("/view/DialogBox.fxml"));
        System.out.println("Check /view/MainWindow.fxml -> " + getClass().getResource("/view/MainWindow.fxml"));

        assert getClass().getResourceAsStream("/images/EVE.PNG") != null : "Eve image resource not found";
        assert getClass().getResourceAsStream("/images/USER.PNG") != null : "User image resource not found";

        // Load user and Eve image from resources
        userImage = new Image(this.getClass().getResourceAsStream("/images/USER.PNG"));
        eveImage = new Image(this.getClass().getResourceAsStream("/images/EVE.PNG"));

        dialogContainer.getChildren().add(
                DialogBox.getEveDialog(ui.renderWelcome(), eveImage));
    }

    /** Injects the Eve instance */
    public void setEve(eve.Eve e) {
        assert e != null : "Injected Eve instance cannot be null";
        eve = e;
    }

    /**
     * Handles user input, shows user and Eve dialog boxes, and clears input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = eve.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEveDialog(response, eveImage));
        userInput.clear();
    }
}
