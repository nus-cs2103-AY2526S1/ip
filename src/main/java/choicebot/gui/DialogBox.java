package choicebot.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box in the chat interface.
 * <p>
 * Each dialog box consists of a text label and an optional avatar image.
 * It supports two types of dialogs:
 * <ul>
 *     <li>User dialog: right-aligned, green bubble, optionally no avatar.</li>
 *     <li>ChoiceBot dialog: left-aligned, white bubble, with avatar.</li>
 * </ul>
 * </p>
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox by loading the FXML layout and setting the text and image.
     *
     * @param text The message text to display in the dialog box.
     * @param img  The image representing the speaker (avatar).
     */
    private DialogBox(String text, Image img) {
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
    }

    /**
     * Creates a dialog box for the user.
     * <p>
     * The dialog is right-aligned, uses a green bubble style, and hides the avatar image.
     * </p>
     *
     * @param text The message text entered by the user.
     * @param img  The user's image (not displayed for chat bubble).
     * @return A DialogBox instance representing the user message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.displayPicture.setVisible(false); // no avatar for user
        db.dialog.setStyle("-fx-background-color: #DCF8C6; -fx-background-radius: 15;"
                + "-fx-padding: 8 12 8 12; -fx-font-size: 14px; -fx-text-fill: #303030;");
        return db;
    }

    /**
     * Creates a dialog box for the ChoiceBot.
     * <p>
     * The dialog is left-aligned, uses a white bubble style, and displays the bot avatar.
     * </p>
     *
     * @param text The message text from the bot.
     * @param img  The bot's avatar image.
     * @return A DialogBox instance representing the bot message.
     */
    public static DialogBox getChoiceBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_LEFT);
        db.dialog.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 15;"
                + "-fx-padding: 8 12 8 12; -fx-font-size: 14px; -fx-text-fill: #303030;");
        return db;
    }
}
