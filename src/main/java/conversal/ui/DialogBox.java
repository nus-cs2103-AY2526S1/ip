package conversal.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A custom control representing a single dialog bubble in the chat window.
 *
 * Each dialog consists of a text label and an avatar image.
 * A dialog can be displayed on the left (Conversal) or flipped to the right (User).
 *
 */
public class DialogBox extends HBox {
    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

    /**
     * Constructs a dialog box with the given text and avatar image.
     * Loads the layout from {@code DialogBox.fxml}.
     *
     * @param text the message text
     * @param img  the avatar image
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxml.setController(this);
            fxml.setRoot(this);
            fxml.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Flips the dialog box so that the text is on the left and the avatar on the right.
     * Used for user messages.
     */
    private void flip() {
        var tmp = javafx.collections.FXCollections.observableArrayList(this.getChildren());
        java.util.Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Factory method to create a dialog box for user messages (aligned to the right).
     *
     * @param text the user’s message text
     * @param img  the user’s avatar image
     * @return a dialog box aligned to the right
     */
    public static DialogBox userDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Factory method to create a dialog box for Conversal messages (aligned to the left).
     *
     * @param text Conversal's message text
     * @param img  Conversal's avatar image
     * @return a dialog box aligned to the left
     */
    public static DialogBox conversalDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
}
