package jackbot.fx;

import jackbot.CommandEngine;
import jackbot.Parser;
import jackbot.Storage;
import jackbot.TaskList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller for {@code MainWindow.fxml}.
 *
 * <p>Delegates all command parsing/execution to {@link CommandEngine},
 * keeping behavior/messages in sync with the CLI. Minimal changes version
 * that renders user/bot lines as chat rows with small avatar images
 * (no custom DialogBox class required).</p>
 */
public class MainWindow {

    /** Shared core components. */
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /** Central engine used by both GUI and CLI. */
    private CommandEngine engine;

    /** JavaFX core components. */
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private ScrollPane scrollPane;

    /**
     * Avatars for right/left chat rows. Ensure these resources exist on classpath:
     * /images/User.png and /images/Jackbot.png
     */
    private Image userImage;
    private Image jackbotImage;

    /** Creates a new {@code MainWindow} controller. */
    public MainWindow() { }

    /**
     * Called by the JavaFX runtime after FXML fields are injected.
     * Binds scroll to auto-scroll as messages are added and loads avatars.
     */
    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
        jackbotImage = new Image(this.getClass().getResourceAsStream("/images/Jackbot.jpg"));
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

        dialogJackbot("Hi! Jackbot at your service. Try: todo read book, find book, list, bye");
    }

    /**
     * Reads text from the input box, displays user message row (right-aligned),
     * processes via {@link CommandEngine}, then displays bot rows (left-aligned).
     * Disables input if engine signals exit (e.g., 'bye').
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            return;
        }
        input = input.trim();

        dialogUser(input);

        CommandEngine.Response resp = engine.process(input);
        for (String msg : resp.messages) {
            dialogJackbot(msg);
        }

        if (resp.exit) {
            userInput.setDisable(true);
        }

        userInput.clear();
    }

    /**
     * Adds a right-aligned HBox row: [Label][User Avatar].
     *
     * @param text message to display
     */
    private void dialogUser(String text) {
        HBox row = new HBox(8);
        row.setAlignment(Pos.TOP_LEFT);

        Label lbl = new Label(text);
        lbl.setWrapText(true);

        ImageView avatar = new ImageView(userImage);
        avatar.setFitWidth(128);
        avatar.setFitHeight(128);
        avatar.setPreserveRatio(true);

        row.getChildren().addAll(avatar, lbl);
        dialogContainer.getChildren().add(row);
    }

    /**
     * Adds a left-aligned HBox row: [Jackbot Avatar][Label].
     *
     * @param text message to display
     */

    private void dialogJackbot(String text) {
        HBox row = new HBox(8);
        row.setAlignment(Pos.TOP_RIGHT);

        ImageView avatar = new ImageView(jackbotImage);
        avatar.setFitWidth(128);
        avatar.setFitHeight(128);
        avatar.setPreserveRatio(true);

        Label lbl = new Label(text);
        lbl.setWrapText(true);

        row.getChildren().addAll(lbl, avatar);
        dialogContainer.getChildren().add(row);
    }
}
