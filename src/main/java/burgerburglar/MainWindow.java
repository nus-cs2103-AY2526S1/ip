package burgerburglar;

import java.io.InputStream;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



/**
 * Controller for the main window of the BurgerBurglar GUI.
 */
public class MainWindow {

    private static final String USER_IMAGE_PATH = "/images/user.jpg";
    private static final String BURGER_IMAGE_PATH = "/images/burger.png";

    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BurgerBurglar burgerburglar;

    private final Image userImage = new Image(
            assertNotNull(getClass().getResourceAsStream(USER_IMAGE_PATH),
                    "User image resource not found: " + USER_IMAGE_PATH)
    );

    private final Image burgerImage = new Image(
            assertNotNull(getClass().getResourceAsStream(BURGER_IMAGE_PATH),
                    "Burger image resource not found: " + BURGER_IMAGE_PATH)
    );

    /**
     * Helper method to assert that a resource stream is not null.
     * Throws AssertionError with descriptive message if null.
     */
    private InputStream assertNotNull(InputStream stream, String errorMessage) {
        assert stream != null : errorMessage;
        return stream;
    }

    /**
     * Greet the user upon entry.
     */
    @FXML
    public void initialize() {
        setupEnterKeyHandler();
        forwardScrollEventsToScrollPane();
    }

    public void setBurgerBurglar(BurgerBurglar burgerburglar) {
        this.burgerburglar = burgerburglar;
        showGreeting();
        scrollToBottom();
    }

    /**
     * Show the greeting message from BurgerBurglar.
     */
    private void showGreeting() {
        dialogContainer.getChildren().add(
                DialogBox.getBurgerDialog(burgerburglar.showGreeting(), burgerImage)
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

        String response = burgerburglar.getResponse(input);
        showDialogs(input, response);
        clearUserInput();

        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
            System.exit(0);
        }
    }


    /**
     * Displays both user and burger dialogs.
     */
    private void showDialogs(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBurgerDialog(response, burgerImage)
        );
        scrollToBottom();
    }

    /**
     * Sets up the Enter key handler for the text field.
     */
    private void setupEnterKeyHandler() {
        userInput.setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case ENTER:
                handleUserInput();
                break;
            default:
                break;
            }
        });
    }

    /**
     * Handle scroll events directly on the dialog container.
     * This should work for trackpad and mouse wheel.
     */
    private void forwardScrollEventsToScrollPane() {
        dialogContainer.setOnScroll(event -> {
            double deltaY = event.getDeltaY(); // Vertical scroll amount
            double currentVValue = scrollPane.getVvalue();
            double contentHeight = dialogContainer.getHeight();
            double viewportHeight = scrollPane.getViewportBounds().getHeight();

            // Calculate new scroll position
            double newVValue = currentVValue - deltaY / contentHeight;

            // Clamp between 0 and 1
            newVValue = Math.max(0, Math.min(1, newVValue));

            // Set new scroll position
            scrollPane.setVvalue(newVValue);

            event.consume(); // Prevent default handling
        });
    }

    /**
     * Scrolls to the bottom of the scroll pane only if the user is near the bottom.
     * This allows manual scrolling without interruption.
     */
    private void scrollToBottom() {
        double threshold = 0.95; // 95% from the top (i.e., near the bottom)
        double currentScroll = scrollPane.getVvalue();

        // If the user is near the bottom, scroll to the bottom when new message is added
        if (currentScroll >= threshold) {
            scrollPane.setVvalue(1.0); // Scroll to bottom
        }
    }

    /**
     * Clears the user input field after sending a message.
     */
    private void clearUserInput() {
        userInput.clear();
    }
}
