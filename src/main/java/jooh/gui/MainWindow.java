package jooh.gui;

import java.io.IOException;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import javafx.util.Duration;
import jooh.exception.JoohException;
import jooh.parser.Parser;
import jooh.storage.Storage;
import jooh.task.*;
import jooh.ui.Ui;

public class MainWindow {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    // Reuse your existing components
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();
    private final Ui ui = new Ui();

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image joohImage = new Image(this.getClass().getResourceAsStream("/images/DaJooh.png"));

    @FXML
    public void initialize() {
        // Auto-scroll
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Load tasks and show welcome line (using Ui formatters)
        StringBuilder bootMsg = new StringBuilder();
        bootMsg.append(ui.formatWelcomeMsg());

        try {
            List<Task> loaded = storage.load();
            loaded.forEach(taskList::addTask);
            if (!loaded.isEmpty()) {
                bootMsg.append("\n\n").append("Loaded ").append(loaded.size()).append(" tasks.");
            }
        } catch (IOException e) {
            // show IO message but continue
            bootMsg.append("\n\n").append(e.getMessage());
        }

        // Show initial Jooh bubble
        dialogContainer.getChildren().add(
                DialogBox.getJoohDialog(bootMsg.toString(), joohImage)
        );
    }

    /** Handles Enter on the text field and clicking the Send button (wired in FXML). */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) return;

        // Always show the user's bubble first
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        // Compute reply via your real Parser + TaskList + Ui
        String reply;
        try {
            Parser.Parsed p = Parser.Parsed.parse(input);
            switch (p.type) {
                case BYE:
                    reply = ui.formatGoodbyeMsg();
                    dialogContainer.getChildren().add(DialogBox.getJoohDialog(reply, joohImage));
                    userInput.clear();
                    // Save before exit
                    try { storage.save(taskList.getTaskList()); } catch (IOException ignored) {}
                    // Close after a short tick to let UI render the last message
                    PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 2 second delay
                    delay.setOnFinished(event -> Platform.exit());
                    delay.play();
                    return;

                case LIST:
                    reply = ui.formatListTasksMsg(taskList.getTaskList());
                    break;

                case TODO: {
                    Task t = new Todo(p.desc, false);
                    taskList.addTask(t);
                    reply = ui.formatAddTaskMsg(t, taskList.getSize());
                    break;
                }

                case DEADLINE: {
                    Task t = new Deadline(p.desc, p.by, false);
                    taskList.addTask(t);
                    reply = ui.formatAddTaskMsg(t, taskList.getSize());
                    break;
                }

                case EVENT: {
                    Task t = new Event(p.desc, p.from, p.to, false);
                    taskList.addTask(t);
                    reply = ui.formatAddTaskMsg(t, taskList.getSize());
                    break;
                }

                case MARK: {
                    int n = p.index;
                    if (n <= 0 || n > taskList.getSize()) throw new JoohException("Task doesn't exist...");
                    Task t = taskList.getTask(n - 1);
                    taskList.markTaskDone(n - 1);
                    reply = ui.formatTaskMarkedDoneMsg(t);
                    break;
                }

                case UNMARK: {
                    int n = p.index;
                    if (n <= 0 || n > taskList.getSize()) throw new JoohException("Task doesn't exist...");
                    Task t = taskList.getTask(n - 1);
                    taskList.markTaskUndone(n - 1);
                    reply = ui.formatTaskMarkedUndoneMsg(t);
                    break;
                }

                case DELETE: {
                    int n = p.index;
                    if (n <= 0 || n > taskList.getSize()) throw new JoohException("Task doesn't exist...");
                    String rmv = taskList.getTaskAsString(n - 1);
                    taskList.removeTask(n - 1);
                    reply = ui.formatTaskRemovedMsg(rmv);
                    break;
                }

                case FIND: {
                    List<Task> matches = taskList.findTasks(p.desc);
                    reply = ui.formatFindTasksMsg(matches);
                    break;
                }

                case FIXED: {
                    Task t = new Fixed(p.desc, false, p.duration);
                    taskList.addTask(t);
                    reply = ui.formatAddTaskMsg(t, taskList.getSize());
                    break;
                }

                default:
                    throw new JoohException("Unknown command type: " + p.type);
            }
        } catch (JoohException e) {
            reply = e.getMessage();
        }

        // Show Jooh reply
        dialogContainer.getChildren().add(DialogBox.getJoohDialog(reply, joohImage));

        // Persist after each command
        try {
            storage.save(taskList.getTaskList());
        } catch (IOException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getJoohDialog("Save error: " + e.getMessage(), joohImage)
            );
        }

        userInput.clear();
    }
}

