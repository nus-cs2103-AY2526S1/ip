package seedu.haru;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * Represents a custom dialog box in a chat interface, containing a text label and an image (e.g., user or bot avatar).
 * The dialog box can be flipped to differentiate between user messages and bot messages.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param s the text to display in the dialog box
     * @param i the image to display in the dialog box
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        text.setWrapText(true);
        text.setMaxWidth(180.0);

        text.setStyle("-fx-background-color: lightblue; "
                + "-fx-padding: 10; "
                + "-fx-background-radius: 15; "
                + "-fx-border-radius: 15;");

        displayPicture = new ImageView(i);
        displayPicture.setFitWidth(60.0);
        displayPicture.setFitHeight(60.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);

        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box horizontally, switching the position of the text and image.
     * Used to display messages from a bot or the other party in a conversation.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a DialogBox representing a user's message.
     *
     * @param s the text of the user's message
     * @param i the user's image
     * @return a DialogBox representing the user's message
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a DialogBox representing Haru's message.
     * The dialog box is flipped so that the image appears on the left and text on the right.
     *
     * @param s the text of the bot's message
     * @param i Haru's image
     * @return a DialogBox representing the bot's message
     */
    public static DialogBox getHaruDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }

}
