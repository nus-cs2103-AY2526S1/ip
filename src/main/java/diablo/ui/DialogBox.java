package diablo.ui;

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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns the DialogBox's dialog
     * @return dialog
     */
    private Label getDialog() {
        return dialog;
    }

    /**
     * Creates a user DialogBox with the corresponding text and image
     * @param text the text of the DialogBox
     * @param img the image of the DialogBox
     * @return a user DialogBox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.getDialog().getStyleClass().add("user-bubble");
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    /**
     * Creates a Diablo DialogBox with the corresponding text and image
     * @param text the text of the DialogBox
     * @param img the image of the DialogBox
     * @return a Diablo DialogBox
     */
    public static DialogBox getDiabloDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.getDialog().getStyleClass().add("bot-bubble");
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }
}

