package gui;

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
 *
 * Simiar to the one in the tutorial.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser, boolean isError) {
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

        // Default alignment (user on right, bot on left handled by flip())
        if (isUser) {
            // user bubble on right, picture on right
            // keep original order (text, image)
            this.setAlignment(Pos.TOP_RIGHT);
        } else {
            // bot bubble on left, image on left
            flip();
        }

        // Add CSS hooks
        this.getStyleClass().add("dialog-box");
        if (isUser) {
            this.getStyleClass().add("user");
        } else {
            this.getStyleClass().add("bot");
            dialog.getStyleClass().add("reply-label"); // keep your previous styling
        }
        if (isError) {
            this.getStyleClass().add("error");
            dialog.getStyleClass().add("error-text");
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        // note: reply-label is added above for bot bubbles
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true, false);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img, false, false);
    }

    /**
     * New: dedicated factory for error replies
     */
    public static DialogBox getDukeErrorDialog(String text, Image img) {
        return new DialogBox(text, img, false, true);
    }
}