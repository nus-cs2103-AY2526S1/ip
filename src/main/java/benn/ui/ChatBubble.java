package benn.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Custom control for showing a chat bubble with text and an avatar.
 */
public class ChatBubble extends HBox {
    @FXML
    private Label messageLabel;
    @FXML
    private ImageView avatarView;

    /**
     * Private constructor that loads FXML and initializes the bubble.
     *
     * @param text the message to show
     * @param icon the avatar image
     */
    private ChatBubble(String text, Image icon) {
        try {
            FXMLLoader loader = new FXMLLoader(ChatBubble.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        messageLabel.setText(text);
        avatarView.setImage(icon);

        // make avatar circular
        Circle mask = new Circle(20, 20, 20);
        avatarView.setClip(mask);
    }

    /**
     * Rearranges the layout so that the avatar is on the left and the text is on the right.
     */
    private void invert() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        Collections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a bubble representing the user’s input.
     *
     * @param text content of the message
     * @param icon user avatar image
     * @return a ChatBubble for the user
     */
    public static ChatBubble getUserDialog(String text, Image icon) {
        return new ChatBubble(text, icon);
    }

    /**
     * Creates a bubble representing the bot’s reply.
     *
     * @param text content of the reply
     * @param icon bot avatar image
     * @return a ChatBubble for the bot
     */
    public static ChatBubble getDukeDialog(String text, Image icon) {
        ChatBubble bubble = new ChatBubble(text, icon);
        bubble.invert();
        return bubble;
    }
}
