package v.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import v.V;

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

    private V v;
    private BackgroundManager backgroundManager;
    @FXML
    private AnchorPane rootPane; // fx:id bound in FXML

    private Image userImage = AvatarManager.getRandomUserAvatar();
    private final Image dukeImage = AvatarManager.getVAvatar();

    /**
     * Initializes the GUI components and sets up the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Initialize background manager on the real root pane (not the controller instance)
        AnchorPane target = (rootPane != null) ? rootPane : (AnchorPane) this;
        backgroundManager = new BackgroundManager(target);

        // Focus the text field on load so keyboard works immediately
        // Also set up keyboard shortcuts for better user experience
        javafx.application.Platform.runLater(() -> {
            userInput.requestFocus();
            // Enable Enter key to send message (already handled by onAction)
            // Future: Could add Ctrl+L to clear chat, Ctrl+/ for help, etc.
        });

        // Add V's dramatic welcome message with delays - using original CLI text
        addWelcomeMessageWithDelay("Voilà! In view, a voice of the vox populi.", 0);
        addWelcomeMessageWithDelay("Behold—the visage behind the letter.", 1500);
        addWelcomeMessageWithDelay("A humble vessel of verbosity and vigilance.", 3000);
        addWelcomeMessageWithDelay("I am V. Voice for the voiceless. What do you require?", 4500);
    }

    /**
     * Injects the V instance.
     *
     * @param v The V instance to inject.
     */
    public void setV(V v) {
        this.v = v;
    }

    /**
     * Adds a welcome message with a delay for dramatic effect.
     *
     * @param message The message to display.
     * @param delayMs The delay in milliseconds.
     */
    private void addWelcomeMessageWithDelay(String message, int delayMs) {
        javafx.animation.Timeline timeline = new javafx.animation.Timeline();
        timeline.getKeyFrames().add(
            new javafx.animation.KeyFrame(javafx.util.Duration.millis(delayMs), e -> {
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
            })
        );
        timeline.play();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing V's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = v.getResponse(input);
        String commandType = v.getCommandType();
        if (input == null) {
            return;
        }
        // Get a new random user avatar for each message
        userImage = AvatarManager.getRandomUserAvatar();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, commandType)
        );
        userInput.clear();
        // Check if this is an exit command
        if (commandType != null && commandType.equals("ExitCommand")) {
            // Disable input to prevent further commands
            userInput.setDisable(true);
            sendButton.setDisable(true);
            // Use Timeline to delay exit without blocking the UI thread
            javafx.animation.Timeline exitTimeline = new javafx.animation.Timeline();
            exitTimeline.getKeyFrames().add(
                new javafx.animation.KeyFrame(javafx.util.Duration.seconds(4), e -> {
                    javafx.application.Platform.exit();
                })
            );
            exitTimeline.play();
        }
    }

    /**
     * Shows help information with V's dramatic flair.
     *
     * @param topic The help topic to display.
     */
    public void showHelp(String topic) {
        switch (topic.toLowerCase()) {
        case "sort":
            showSortHelp();
            break;
        case "general":
        default:
            showGeneralHelp();
            break;
        }
    }

    /**
     * Shows general help information.
     */
    private void showGeneralHelp() {
        String helpText = "Voilà! The shadows reveal the secrets of this revolutionary tool:\n\n"
                + "📋 TASK MANAGEMENT:\n"
                + "• todo <description> - Add a new task\n"
                + "• deadline <description> /by <date> - Add a deadline\n"
                + "• event <description> /from <start> /to <end> - Add an event\n"
                + "• list - Show all tasks\n"
                + "• mark <number> - Mark task as done\n"
                + "• unmark <number> - Mark task as not done\n"
                + "• delete <number> - Remove a task\n\n"
                + "🔍 SEARCH & ORGANIZE:\n"
                + "• find <keyword> - Search for tasks\n"
                + "• sort [by <criteria>] - Sort tasks (see 'help sort')\n\n"
                + "ℹ️  HELP:\n"
                + "• help - Show this help\n"
                + "• help sort - Show sorting options\n"
                + "• bye - Exit the application\n\n"
                + "The shadows whisper: Use 'help sort' for sorting secrets!";

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(helpText, dukeImage));
    }

    /**
     * Shows sorting help information.
     */
    private void showSortHelp() {
        String sortHelpText = "Voilà! The shadows reveal the art of organizing your revolutionary agenda:\n\n"
                + "🎭 SORTING COMMANDS:\n"
                + "• sort - Show tasks in original order\n"
                + "• sort by date - Sort by date (deadlines/events chronologically)\n"
                + "• sort by type - Group by type (todo, deadline, event)\n"
                + "• sort by status - Pending tasks first, then completed\n"
                + "• sort by name - Sort alphabetically by description\n\n"
                + "📅 DATE SORTING:\n"
                + "- Deadlines and events are sorted by their dates\n"
                + "- Todos appear at the end (no specific date)\n"
                + "- Chronological order: earliest dates first\n\n"
                + "🏷️  TYPE SORTING:\n"
                + "- Todos appear first\n"
                + "- Deadlines appear second\n"
                + "- Events appear third\n\n"
                + "✅ STATUS SORTING:\n"
                + "- Pending (incomplete) tasks appear first\n"
                + "- Completed tasks appear second\n\n"
                + "🔤 NAME SORTING:\n"
                + "- Tasks are sorted alphabetically by description\n"
                + "- Case-insensitive sorting\n\n"
                + "The shadows whisper: Each sort reveals your agenda in a new light!";

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(sortHelpText, dukeImage));
    }

    /**
     * Shows sorted tasks with V's dramatic flair.
     *
     * @param sortedTasks The sorted list of tasks to display.
     * @param criteria The sort criteria used.
     */
    public void showSortedTasks(List<v.task.Task> sortedTasks, String criteria) {
        if (sortedTasks.isEmpty()) {
            String emptyMessage = "The stage is set, but the script is blank. "
                    + "Your revolutionary agenda awaits its first act.";
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(emptyMessage, dukeImage));
            return;
        }

        // V-themed response based on sort criteria
        String dramaticIntro = getDramaticSortIntro(criteria);
        StringBuilder response = new StringBuilder();
        response.append(dramaticIntro).append("\n\n");

        // Use streams to format sorted task list with indices
        String taskList = java.util.stream.IntStream.range(0, sortedTasks.size())
                .mapToObj(i -> (i + 1) + "." + sortedTasks.get(i))
                .reduce("", (acc, task) -> acc + task + "\n");
        response.append(taskList);

        response.append("\nTotal acts of rebellion: ").append(sortedTasks.size()).append(" task(s).");

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response.toString(), dukeImage));
    }

    /**
     * Gets a dramatic V-themed introduction based on sort criteria.
     *
     * @param criteria The sort criteria.
     * @return A dramatic introduction message.
     */
    private String getDramaticSortIntro(String criteria) {
        switch (criteria.toLowerCase()) {
        case "date":
            return "Voilà! Your revolutionary agenda, now perfectly orchestrated by time! "
                    + "The shadows reveal your conspiracies in chronological order.";
        case "type":
            return "Behold! Your tasks, now organized by their nature! "
                    + "The shadows have grouped your conspiracies by type.";
        case "status":
            return "The shadows reveal the status of your revolutionary agenda! "
                    + "Pending missions first, then completed victories.";
        case "name":
            return "Voilà! Your conspiracies, now arranged alphabetically! "
                    + "The shadows have organized your agenda in perfect order.";
        default:
            return "Behold! Your current conspiracies against the mundane! "
                    + "The shadows reveal your revolutionary agenda.";
        }
    }
}
