package marvin.gui;

import java.io.IOException;

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

/**
 * A DialogBox object representing a message from a user, containing an ImageView to represent the speaker's
 * face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Instantiates a dialog box representing a message from a user.
     *
     * @param text The text content of the message.
     * @param img  The image representing the user.
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
     * Instantiates a standard user dialog box.
     *
     * @param s The text content of the message.
     * @param i The image representing the user.
     * @return A dialog box representing a message from the user.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Instantiates a dialog box for Marvin, flipped so that the contents are left-aligned.
     *
     * @param s The text content of the message.
     * @param i The image representing the user.
     * @return A dialog box representing a message from the user.
     */
    public static DialogBox getMarvinDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text on the right
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> currentContents = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(currentContents);
        dialog.getStyleClass().add("reply-label");
        this.getChildren().setAll(currentContents);
    }
}
