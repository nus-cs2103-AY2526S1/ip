package banana.gui;

import banana.main.BananaBot;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    static final String LOGO =
            "                  __,__\n"
                    + "        .--.  .-\"     \"-.  .--.\n"
                    + "       /  .. \\/  .-. .-.  \\/ ..     \\\n"
                    + "      | |   '|  /    Y    \\  |'   | |  |\n"
                    + "      | \\    \\  \\ 0 | 0 /  /    / |  |\n"
                    + "       \\ '- ,\\.-\"````\"-./, -'/\n"
                    + "        `'-' /_   ^ ^   _\\ '-'`\n"
                    + "        .--'|  \\._ _ _./  |'--.\n"
                    + "      /`     \\   \\.-.  /   /    `\\\n"
                    + "     /        '._/  |-' _.'       \\\n"
                    + "    /          ;  /--~'   |       \\\n"
                    + "   /        .'\\|.-\\--.     \\       \\\n"
                    + "  /   .'-. /.-.;\\  |\\|'~'-.|\\       \\\n"
                    + "  \\       `-./`|_\\_/ `     `\\'.      \\\n"
                    + "   '.      ;     ___)        '.`;    /\n"
                    + "     '-.,_ ;     ___)          \\/   /\n"
                    + "      \\   ``'------'\\       \\   `  /\n"
                    + "       '.    \\       '.      |   ;/_\n"
                    + "...  ___>     '.       \\_ _ _/   ,  '--.\n"
                    + "   .'   '.   .-~~~~~-. /     |--'`~~-.  \\\n"
                    + "  // / .---'/  .-~~-._/ / / /---..__.'  /\n"
                    + " ((_(_/    /  /      (_(_(_(---.__    .'\n"
                    + "           | |     _              `~~`\n"
                    + "           | |     \\'.\n"
                    + "            \\ '....' |\n"
                    + "             '.,___.'";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private BananaBot bot = new BananaBot("src/main/data/bot.txt");
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bob.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/stuart.png"));
    /**
     * Initializes the main window.
     */
    @SuppressWarnings("checkstyle:OperatorWrap")
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Hello I'm BananaBot\n" + LOGO + "\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getBotDialog(welcomeMessage, botImage));
    }
    public void setBot(BananaBot b) {
        bot = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}
