package fish;
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

    private static final String DIALOG_STYLE_TEMPLATE =
            "-fx-background-color: %s; -fx-background-radius: 12; -fx-padding: 10;";
    private static final String USER_DIALOG_COLOR = "#E6D8FF";
    private static final String FISH_DIALOG_COLOR = "#FFF5B1"; // Assisted by Codex

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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.applyDialogStyling(USER_DIALOG_COLOR);
        return db;
    }

    public static DialogBox getFishDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.applyDialogStyling(FISH_DIALOG_COLOR);
        db.flip();
        return db;
    }

    private void applyDialogStyling(String backgroundColor) {
        dialog.setStyle(String.format(DIALOG_STYLE_TEMPLATE, backgroundColor));
    }
}
