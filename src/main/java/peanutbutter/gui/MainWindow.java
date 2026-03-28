package peanutbutter.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import peanutbutter.Juin;
import peanutbutter.commands.ReminderCommand;
import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Represents the main window of the Juin GUI.
 * This class manages the layout, user interactions, and integration
 * with the underlying logic of the application.
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

    private Juin juin;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image juinImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setJuin(Juin d) {
        juin = d;
        Ui ui = new Ui();
        String welcome = ui.welcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getJuinDialog(welcome, juinImage));
        try {
            ReminderCommand reminder = new ReminderCommand();
            reminder.run(juin.getTaskList(), ui);
            String dueMsg = ui.getLastMessage();
            dialogContainer.getChildren().add(DialogBox.getJuinDialog(dueMsg, juinImage));
        } catch (JuinException e) {
            ui.errorMessage(e.getMessage());
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = juin.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJuinDialog(response, juinImage)
        );
        userInput.clear();
    }
}

