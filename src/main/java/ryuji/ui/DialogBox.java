package ryuji.ui;

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

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * <p>The {@code DialogBox} class is a custom UI element used to display a conversation.
 * It is composed of two parts: an image representing the speaker's face and a label that
 * contains the text being spoken. This class is part of a larger chat-based user interface.</p>
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;  // The label displaying the dialog text
    @FXML
    private ImageView displayPicture;  // The image representing the speaker's face

    /**
     * Private constructor that creates a DialogBox with the specified text and image.
     * This constructor loads the FXML for the DialogBox and initializes the components.
     *
     * @param text the dialog text to be displayed
     * @param img  the image to be displayed as the speaker's face
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
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * This method is typically used to show the response from the system (Ryuji) on the left,
     * while the user's input is displayed on the right.
     * <p>The order of the components (image and text) is reversed, and the alignment is adjusted
     * to be on the top left.</p>
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a new user dialog box with the specified text and image.
     * The user dialog is displayed on the right side of the screen.
     *
     * @param text the text to display in the user dialog box
     * @param img  the image to display as the user's profile picture
     * @return a new {@code DialogBox} representing the user's dialog
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new Ryuji dialog box with the specified text and image.
     * The Ryuji dialog is displayed on the left side of the screen.
     *
     * @param text the text to display in the Ryuji dialog box
     * @param img  the image to display as Ryuji's profile picture
     * @return a new {@code DialogBox} representing Ryuji's dialog
     */
    public static DialogBox getRyujiDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
