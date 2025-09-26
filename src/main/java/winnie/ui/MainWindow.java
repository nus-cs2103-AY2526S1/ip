package winnie.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.uitool.GuiWriter;
import winnie.command.Command;
import winnie.command.VoidCommand;
import winnie.exception.WinnieException;
import winnie.parser.Parser;
import winnie.chatmessage.GreetingMessage;

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

    private TaskList tasks;
    private Storage storage;
    private GuiWriter guiWriter;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image winnieImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the TaskList and Storage instances
     */
    public void setData(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;

        // Initialize GUI components
        this.guiWriter = new GuiWriter(this.dialogContainer, this.winnieImage);

        // Show welcome message
        guiWriter.write(new GreetingMessage());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Winnie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Add user message to dialog
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage));

        // Process command
        Command command = parseCommand(input);
        command.execute(tasks, new GuiProxy(), storage);

        userInput.clear();

        if (command.isExit()) {
            javafx.application.Platform.exit();
        }
    }

    private Command parseCommand(String input) {
        try {
            return Parser.parse(input.trim());
        } catch (WinnieException e) {
            guiWriter.write(new winnie.chatmessage.ErrorMessage(e.getMessage()));
        }
        return new VoidCommand();
    }

    /**
     * Proxy class to handle UI operations within the command execution
     */
    private class GuiProxy implements Ui {
        @Override
        public void showTaskList(TaskList tasks) {
            guiWriter.write(new winnie.chatmessage.TaskListMessage(tasks));
        }

        @Override
        public void showTaskAdded(winnie.task.Task task, int taskCount) {
            guiWriter.write(new winnie.chatmessage.TaskAddedMessage(task, taskCount));
        }

        @Override
        public void showTaskMarked(winnie.task.Task task) {
            guiWriter.write(new winnie.chatmessage.MarkTaskMessage(task));
        }

        @Override
        public void showTaskUnmarked(winnie.task.Task task) {
            guiWriter.write(new winnie.chatmessage.UnmarkTaskMessage(task));
        }

        @Override
        public void showTaskDeleted(winnie.task.Task task, int taskCount) {
            guiWriter.write(new winnie.chatmessage.DeleteTaskMessage(task, taskCount));
        }

        @Override
        public void showError(String errorMessage) {
            guiWriter.write(new winnie.chatmessage.ErrorMessage(errorMessage));
        }

        @Override
        public void showFoundTasks(TaskList foundTasks) {
            guiWriter.write(new winnie.chatmessage.FoundTasksMessage(foundTasks));
        }

        @Override
        public void showGoodbye() {
            guiWriter.write(new winnie.chatmessage.GoodByeMessage());
        }

        @Override
        public Command readCommand() {
            // This method is not used in FXML version
            return new VoidCommand();
        }

        @Override
        public void showWelcome() {
            guiWriter.write(new GreetingMessage());
        }

        @Override
        public void showLoadingError() {
            showError("Error loading tasks from file. Starting with empty task list.");
        }

        @Override
        public void showTaskSnoozed(winnie.task.Task task, java.time.LocalDateTime snoozeUntil) {
            String message = "Nice! I've snoozed this task:\n  " + task.toString() + "\nIt will reappear at: "
                    + winnie.util.DateTimeUtil.formatForDisplay(snoozeUntil);
            guiWriter.write(new winnie.chatmessage.ErrorMessage(message));
        }
    }
}
