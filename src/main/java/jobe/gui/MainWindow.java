package jobe.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jobe.Jobe;
import jobe.ui.Ui;

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
    
    private Jobe jobe;
    private Ui ui;
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image jobeImage = new Image(this.getClass().getResourceAsStream("/images/robot4.jpg"));
    
    /**
     * Initialises the Jobe app.
     */
    @FXML
    public void initialize() {
        this.ui = new Ui();
        this.ui.showHelloResponse();
        userInput.setFocusTraversable(false);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getJobeDialog(this.ui.getResponse(), jobeImage, "HelloCommand")
        );
    }
    
    /**
     * Injects the Jobe instance
     */
    public void setJobe(Jobe jobe) {
        this.jobe = jobe;
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jobe.getResponse(input);
        String commandType = jobe.getCommandType() != null ? jobe.getCommandType() : "ErrorCommand";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJobeDialog(response, jobeImage, commandType)
        );
        userInput.clear();
    }
    
    /**
     * Shows messages relating to loading of files to the user upon startup.
     */
    public void showStartupMessages() {
        String storageMessages = jobe.getStorage().getStatusMessages();
        
        if (storageMessages.isBlank()) {
            return;
        }
        
        dialogContainer.getChildren().add(
                DialogBox.getJobeDialog(storageMessages, jobeImage, "StorageCommand")
        );
    }
}
