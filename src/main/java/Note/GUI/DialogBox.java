package Note.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * A custom control representing a single dialog message.
 * Can display a message from the user (right) or bot (left).
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     * @param text Message text
     * @param img Avatar image
     * @param isUser true if user, false if bot
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT); // user messages on the right
        } else {
            this.setAlignment(Pos.TOP_LEFT); // bot messages on the left
        }

        this.setSpacing(10);
        this.setPadding(new Insets(5, 10, 5, 10));
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    public static DialogBox getBotDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }
}
