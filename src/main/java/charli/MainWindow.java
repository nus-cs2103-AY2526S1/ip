package charli;

import charli.util.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

/**
 * Controller for the main window of the BurgerBurglar GUI.
 */
public class MainWindow {

    private static final String USER_IMAGE_PATH = "/images/barbieOnFire.png";
    private static final String CHARLI_IMAGE_PATH = "/images/CharliXCX.png";

    private static final String WELCOME =
            "Hi British Vogue, I'm CharliXCX!\n" +
                    "What club classics would you like on your summer rotation?\n";

    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Charli charli;

    private final Image userImage = new Image(
            getClass().getResourceAsStream(USER_IMAGE_PATH)
    );
    private final Image charliImage = new Image(
            getClass().getResourceAsStream(CHARLI_IMAGE_PATH)
    );

    @FXML
    public void initialize() {
        bindAutoScroll();
        userInput.setOnAction(e -> handleUserInput()); // Enter in TextField = send
        Platform.runLater(() -> {
            showWelcome();
            userInput.requestFocus(); // cursor ready to type
        });
    }

    public void setCharli(Charli charli) {
        this.charli = charli;
    }

    private void showWelcome() {
        dialogContainer.getChildren().add(
                DialogBox.getCharliDialog(WELCOME, charliImage)
        );
    }

    /**
     * Handles user input: validates, delegates to backend, displays dialogs.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return; // guard clause
        }

        String response = charli.getResponse(input);
        showDialogs(input, response);
        clearUserInput();
    }

    /**
     * Ensures scroll pane always scrolls to the bottom as new messages are added.
     */
    private void bindAutoScroll() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    private void showDialogs(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCharliDialog(response, charliImage)
        );
    }

    /**
     * Clears the user input field after sending a message.
     */
    private void clearUserInput() {
        userInput.clear();
    }

}
