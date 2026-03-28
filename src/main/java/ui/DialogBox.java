package ui;

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
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
     * Flips the dialog box such that the ImageView is on the left and text on the right
     * and applys the correct message type.
     */
    private void flip(Message.Type type) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");

        applyType(type);

    }

    /**
     * Adds the respective css class to the message type.
     */
    private void applyType(Message.Type type) {
        //AI generated switch statements
        switch (type) {
        case ERROR:
            dialog.getStyleClass().add("error"); // applies the 'error' class
            break;
        case INFO:
            dialog.getStyleClass().add("info"); // applies the 'info' class
            break;
        case SUCCESS:
            dialog.getStyleClass().add("success"); // applies the 'success' class
            break;
        case WARNING:
            dialog.getStyleClass().add("warning"); // applies the 'warning' class
            break;
        default:
            break;
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getMarkDialog(Message text, Image img) {
        var db = new DialogBox(text.getMessage(), img);
        db.flip(text.getType());
        return db;
    }
}

