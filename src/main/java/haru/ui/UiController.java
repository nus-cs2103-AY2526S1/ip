// ChatGPT helped write the Javadoc

package haru.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML controller exposing chat UI nodes to Haru.
 */
public class UiController {
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox chat;
    @FXML
    private HBox bottom;
    @FXML
    private TextField input;
    @FXML
    private Button send;

    /**
     * Returns the scroll pane containing the chat.
     *
     * @return the ScrollPane for chat
     */
    public ScrollPane getScroll() {
        return scroll;
    }

    /**
     * Returns the chat box container.
     *
     * @return the VBox for chat
     */
    public VBox getChat() {
        return chat;
    }

    /**
     * Returns the bottom input bar.
     *
     * @return the HBox for input and send button
     */
    public HBox getBottom() {
        return bottom;
    }

    /**
     * Returns the input text field.
     *
     * @return the TextField for user input
     */
    public TextField getInput() {
        return input;
    }

    /**
     * Returns the send button.
     *
     * @return the Button to send messages
     */
    public Button getSend() {
        return send;
    }
}
