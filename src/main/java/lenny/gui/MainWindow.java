package lenny.gui;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lenny.logic.Lenny;


/**
 * Controller for {@code MainWindow.fxml}.
 * <p>
 * Manages the main chat UI: reads user input, asks {@link lenny.logic.Lenny}
 * for a response, and appends {@link DialogBox} nodes for both user and bot
 * messages. Also keeps the scroll pane pinned to the latest message.
 * </p>
 */
public class MainWindow {

    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Lenny lenny;

    // preload avatars from resources
    private final Image userImage =
            new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image lennyImage =
            new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/DaLenny.png")));

    /**
     * Called automatically by the FXML loader after the view is constructed.
     * Binds the scroll position to the dialog container height and may add an
     * initial greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.setFitToWidth(true);
        dialogContainer.setFillWidth(true);
        dialogContainer.heightProperty().addListener((obs, ov, nv) ->
                javafx.application.Platform.runLater(() -> scrollPane.setVvalue(1.0))
        );
        dialogContainer.getChildren().add(
                DialogBox.getLennyDialog("Hello! I'm Lenny. What can I do for you?", lennyImage)
        );
    }

    /**
     * Injects the backend logic object that processes user input.
     *
     * @param l the {@link lenny.logic.Lenny} instance to use
     */

    public void setLenny(Lenny l, boolean GuiMode) {
        this.lenny = l;
    }



    /**
     * Handles the Send button (and Enter key) action: reads the text field,
     * obtains a response from {@link lenny.logic.Lenny}, appends user and bot
     * dialog boxes to the conversation, and clears the input field.
     */
    @FXML
    private void handleUserInput() {
        Integer p = null;
        String response = "";
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }

        // If it's an add-type command and user didn’t include /p, ask for it like the CLI.
        if (needsPriorityPrompt(input) && extractInlinePriority(input) == null) {
            p = promptPriorityDialog();
            if (p == null) {
                // User cancelled: do nothing (no command sent, no message added)
                return;
            }
        }

        if (p == null) {
            response = lenny.getResponse(input);
        } else {
            response = lenny.getResponse(input, p);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLennyDialog(response, lennyImage)
        );
        if (response.equals("Powering down... see you soon \uD83D\uDC4B.")) {
            Stage stage = (Stage) userInput.getScene().getWindow();

            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 2-second delay
            delay.setOnFinished(event -> stage.close());
            delay.play();
        }
        userInput.clear();
    }
    /** Returns true if this command typically needs a priority. */
    private boolean needsPriorityPrompt(String cmd) {
        if (cmd == null) {
            return false;
        }
        String s = cmd.strip().toLowerCase();
        // Add/adjust prefixes based on your actual add commands
        return s.startsWith("todo ") || s.startsWith("deadline ") || s.startsWith("event ");
    }

    /** Returns the inline /p N priority if present and valid (1–5); otherwise null. */
    private Integer extractInlinePriority(String cmd) {
        if (cmd == null) {
            return null;
        }
        Matcher m = Pattern.compile("(?i)(?:^|\\s)\\/(?:p|priority)\\s+(\\d)").matcher(cmd);
        if (!m.find()) {
            return null;
        }
        int p = Integer.parseInt(m.group(1));
        return (p >= 1 && p <= 5) ? p : null;
    }

    /** Shows a GUI dialog that asks for priority. */
    private Integer promptPriorityDialog() {
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(3, 1, 2, 3, 4, 5);
        dialog.setTitle("Set Priority");
        dialog.setHeaderText("What is the priority of the task?(1-5)");
        dialog.setContentText("Priority:");
        Optional<Integer> res = dialog.showAndWait();
        return res.orElse(null); // null if user cancelled
    }
}
