package haru.ui;

import javafx.scene.layout.VBox;

/**
 * UI handler for displaying chat messages.
 */
public class Ui {
    private final VBox chat;

    /**
     * Creates a Ui with the given chat container.
     *
     * @param chat the VBox container for messages
     */
    public Ui(VBox chat) {
        this.chat = chat;
    }

    /**
     * Shows a user message in the chat box.
     *
     * @param msg the user message text
     */
    public void showUserMessage(String msg) {
        this.chat.getChildren().add(new UserMessage(msg));
    }

    /**
     * Shows a Haru message in the chat box.
     *
     * @param msg the Haru message text
     */
    public void showHaruMessage(String msg) {
        this.chat.getChildren().add(new HaruMessage(msg));
    }
}
