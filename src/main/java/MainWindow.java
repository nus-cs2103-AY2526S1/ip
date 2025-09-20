
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rafayel.Rafayel;
import rafayel.RafayelException;
import rafayel.command.RemindCommand;
import rafayel.ui.Ui;

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

    private Runnable exitHandler;

    private Rafayel rafayel;

    private Image userIcon = new Image(this.getClass().getResourceAsStream("/images/UserIcon.jpeg"));
    private Image rafayelIcon = new Image(this.getClass().getResourceAsStream("/images/RafayelIcon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rafayel instance */
    public void setRafayel(Rafayel r) {
        rafayel = r;

        // Show welcome message
        Ui ui = new Ui();
        String welcomeMessage = ui.showWelcome();
        dialogContainer.getChildren().addAll(DialogBox.getRafayelDialog(welcomeMessage, rafayelIcon));
    }

    public void setExitHandler(Runnable exitHandler) {
        this.exitHandler = exitHandler;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rafayel's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws RafayelException {
        String input = userInput.getText();
        String response = rafayel.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userIcon),
                DialogBox.getRafayelDialog(response, rafayelIcon)
        );
        userInput.clear();

        if (rafayel.shouldExit() && exitHandler != null) {
            exitHandler.run();
        }
    }

    /**
     * Retrieves and shows the reminders with a deadline.
     *
     * @throws RafayelException if encountered error during retrieval of reminders.
     */
    public void checkAndShowReminders() {
        String reminderText = rafayel.getReminders();
        if (RemindCommand.NO_TASK_FOR_REMINDERS.equals(reminderText)
                || "No upcoming deadlines nor overdue tasks! :D".equals(reminderText)) {
            return;
        }
        dialogContainer.getChildren().addAll(DialogBox.getRafayelDialog(
                "Reminders:\n\n" + reminderText, rafayelIcon));
    }
}
