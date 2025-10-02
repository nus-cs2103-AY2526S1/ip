package bobby.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a dialog box consisting of a label for text and an image for the display picture.
 * Used for displaying user, bot, and error messages in the chat window.
 */
public class DialogBox extends HBox{

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text The message to display in the dialog box.
     * @param img The image to display (e.g., user or bot avatar).
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        // Add default style class
        dialog.getStyleClass().add("dialog-label");
        // Load CSS if not already loaded
        if (getStylesheets().isEmpty()) {
            getStylesheets().add(getClass().getResource("/view/dialogbox.css").toExternalForm());
        }
        // Removed circular clip logic as per user request
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * Used for bot and error messages.
     */

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for user messages.
     *
     * @param s The user message.
     * @param i The user's display image.
     * @return A DialogBox styled for user messages.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.dialog.getStyleClass().add("user-dialog");
        db.setAlignment(Pos.TOP_RIGHT);
        db.displayPicture.setFitWidth(72);
        db.displayPicture.setFitHeight(72);
        return db;
    }

    /**
     * Creates a dialog box for bot messages.
     *
     * @param s The bot's message.
     * @param i The bot's display image.
     * @return A DialogBox styled for bot messages.
     */
    public static DialogBox getBobbyDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.dialog.getStyleClass().add("bot-dialog");
        db.flip();
        db.displayPicture.setFitWidth(72);
        db.displayPicture.setFitHeight(72);
        return db;
    }

    /**
     * Creates a dialog box for error messages (displayed in red).
     *
     * @param text The error message.
     * @param img The bot's display image.
     * @return A DialogBox styled for error messages.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("error-dialog");
        db.flip();
        db.displayPicture.setFitWidth(72);
        db.displayPicture.setFitHeight(72);
        return db;
    }
}