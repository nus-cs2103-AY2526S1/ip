
// import java.util.Objects;

import java.util.ArrayList;
// import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rafayel.Rafayel;
import rafayel.RafayelException;
import rafayel.reminder.ReminderManager;
// import rafayel.storage.Storage;
import rafayel.task.Task;
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

    private Rafayel rafayel;
    private ReminderManager reminderManager;

    private Image userIcon = new Image(this.getClass().getResourceAsStream("/images/UserIcon.jpeg"));
    private Image rafayelIcon = new Image(this.getClass().getResourceAsStream("/images/RafayelIcon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rafayel instance */
    public void setRafayel(Rafayel r) {
        rafayel = r;
        this.reminderManager = new ReminderManager(r.getAll());

        // Show welcome message
        Ui ui = new Ui();
        String welcomeMessage = ui.showWelcome();
        dialogContainer.getChildren().addAll(DialogBox.getRafayelDialog(welcomeMessage, rafayelIcon));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rafayel's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws RafayelException {
        String input = userInput.getText();
        String response = rafayel.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userIcon),
                DialogBox.getRafayelDialog(response, rafayelIcon));
        userInput.clear();
    }

    /**
     * Retrieves and shows the reminders with a deadline.
     */
    public void checkAndShowReminders() {
        ArrayList<Task> reminders = reminderManager.getUpcomingReminders();
        if (!reminders.isEmpty()) {
            String reminderText = reminderManager.formatReminders(reminders);

            // Add the reminder to the chat dialog instead of using an Alert
            dialogContainer.getChildren()
                    .addAll(DialogBox.getRafayelDialog("⏰ Reminder!\n\n" + reminderText, rafayelIcon));
        }
    }

}
