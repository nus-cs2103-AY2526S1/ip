package bestbot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * DialogBox: Represents a single chat dialog bubble in the GUI.
 *
 * <p>This class is used for both user and bot messages.
 * Provides factory methods to construct dialog boxes with proper alignment and styling.</p>
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox.
     *
     * @param text Text to display in the dialog.
     * @param img  Image avatar associated with the dialog.
     */
    private DialogBox(String text, Image img) {
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
    }

    /**
     * Creates a DialogBox for user messages.
     *
     * @param text User message text
     * @param img  User avatar
     * @return DialogBox aligned to the right with green background
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #d0f0c0; -fx-padding: 8; -fx-background-radius: 8;");
        db.setNodeOrientation(javafx.geometry.NodeOrientation.RIGHT_TO_LEFT);
        return db;
    }

    /**
     * Creates a DialogBox for bot messages.
     *
     * @param text Bot message text
     * @param img  Bot avatar
     * @return DialogBox aligned to the left with grey background
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8; -fx-background-radius: 8;");
        db.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        return db;
    }
}
