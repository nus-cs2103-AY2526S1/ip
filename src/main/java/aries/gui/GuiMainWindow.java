package aries.gui;

import aries.Aries;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class GuiMainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Aries aries;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/mrbean.png"));
    private Image ariesImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));

    /** Initializes the GUI components */
    @FXML
    public void initialize() {
        scrollPane.setFitToWidth(true);
        scrollPane.setFocusTraversable(true);

        dialogContainer.heightProperty().addListener((o, a, b) -> scrollPane.setVvalue(1.0));

        Platform.runLater(() -> scrollPane.requestFocus());

        scrollPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
            case UP -> scrollBy(-40);
            case DOWN -> scrollBy(40);
            case PAGE_UP -> scrollBy(-scrollPane.getHeight());
            case PAGE_DOWN -> scrollBy(scrollPane.getHeight());
            case HOME -> scrollPane.setVvalue(0);
            case END -> scrollPane.setVvalue(1);
            default -> { }
            }
        });
    }

    /** Injects the Aries instance */
    public void setAries(Aries aries) {
        this.aries = aries;
        dialogContainer.getChildren().add(
                DialogBox.getAriesDialog(aries.getWelcomeMessage(), ariesImage, ""));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Aries's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aries.getResponse(input);
        String commandType = aries.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAriesDialog(response, ariesImage, commandType));
        userInput.clear();

        if (aries.isExitCommand(input)) {
            // Delay exit to allow user to read the goodbye message
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.exit();
            }).start();
        }
    }

    /**
     * Scrolls the scroll pane by a specified number of pixels.
     * @param dy The number of pixels to scroll vertically. Positive values scroll down, negative values scroll up.
     */
    private void scrollBy(double dy) {
        double contentH = scrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportH = scrollPane.getViewportBounds().getHeight();
        double max = Math.max(1e-9, contentH - viewportH);
        double dv = dy / max;
        scrollPane.setVvalue(Math.min(1.0, Math.max(0.0, scrollPane.getVvalue() + dv)));
    }
}
