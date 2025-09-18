package jackbot.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import jackbot.CommandEngine;
import jackbot.Parser;
import jackbot.Storage;
import jackbot.TaskList;

/**
 * Controller for {@code MainWindow.fxml}.
 *
 * <p>Delegates all command parsing/execution to {@link CommandEngine},
 * keeping behavior/messages in sync with the CLI.</p>
 *
 * @since 1.0
 */
public class MainWindow {

    /** Creates a new {@code MainWindow} controller. */
    public MainWindow() { }

    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private ScrollPane scrollPane;

    /** Shared core components. */
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /** Central engine used by both GUI and CLI. */
    private CommandEngine engine;

    /**
     * Called by the JavaFX runtime after FXML fields are injected.
     * Binds the scroll pane to auto-scroll as new dialog nodes are added.
     */
    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects core components created by the application after FXML load,
     * and builds the shared {@link CommandEngine}.
     *
     * @param storage backing storage for tasks
     * @param tasks   in-memory task list (preloaded if available)
     * @param parser  command parser shared with the CLI
     */
    public void initCore(Storage storage, TaskList tasks, Parser parser) {
        this.storage = storage;
        this.tasks = tasks;
        this.parser = parser;
        this.engine = new CommandEngine(storage, tasks, parser);

        dialog("> Hi! Jackbot at your service. Try: todo read book, find book, list, bye");
    }

    /**
     * Reads text from the input box, echoes it to the dialog area, delegates
     * processing to {@link CommandEngine}, and renders the engine's messages.
     * Disables input if the engine signals exit (i.e., {@code bye}).
     * Bound to the Send button and the TextField’s Enter action.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            return;
        }
        input = input.trim();

        // Echo the user's line
        dialog("> " + input);

        // Single source of truth: parse/execute via the engine
        CommandEngine.Response resp = engine.process(input);

        // Render all messages produced by the engine
        for (String msg : resp.messages) {
            dialog(msg);
        }

        // Handle exit state
        if (resp.exit) {
            userInput.setDisable(true);
        }

        userInput.clear();
    }

    /**
     * Appends a single line of text to the dialog area.
     *
     * @param text line to display
     */
    private void dialog(String text) {
        dialogContainer.getChildren().add(new Label(text));
    }
}
