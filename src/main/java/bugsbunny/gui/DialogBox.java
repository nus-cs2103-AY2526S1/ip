package bugsbunny.gui;

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
 * A dialog box which consists of an ImageView to represent the speaker's face
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

        // Used ChatGPT to make the avatar images slightly smaller with a rounded crop
        displayPicture.setImage(img);
        displayPicture.setFitWidth(72); // reduced from ~99 (FXML default)
        displayPicture.setFitHeight(72);
        displayPicture.setSmooth(true);

        // Make avatar round
        javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(36, 36, 36);
        displayPicture.setClip(clip);
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

    // Used ChatGPT to change the User's and Bot's dialog to have a chat bubble with their background colours.
    // Blue for user, gray for bot, and red for errors.
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle(
                "-fx-background-color: #DBEAFE;" // blue-100
                        + "-fx-text-fill: #1E3A8A;" // blue-900
                        + "-fx-padding: 8 10 8 10;"
                        + "-fx-background-radius: 10;"
                        + "-fx-border-color: #93C5FD;" // blue-300
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
        );
        db.dialog.setWrapText(true);
        return db;
    }

    public static DialogBox getBugsBunnyDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(
                "-fx-background-color: #F9FAFB;" // neutral gray-50
                        + "-fx-text-fill: #111827;" // gray-900
                        + "-fx-padding: 8 10 8 10;"
                        + "-fx-background-radius: 10;"
                        + "-fx-border-color: #E5E7EB;" // gray-200
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
        );
        db.dialog.setWrapText(true);
        return db;
    }

    // Used ChatGPT to output this error box for error messages.
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(); // bot-style (left)
        // Inline styles for a light-red bubble with border
        db.dialog.setStyle(
                "-fx-background-color: #FEE2E2;" // red-100
                        + "-fx-text-fill: #7F1D1D;" // red-900
                        + "-fx-padding: 8 10 8 10;"
                        + "-fx-background-radius: 10;"
                        + "-fx-border-color: #FCA5A5;" // red-300
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
        );
        db.dialog.setWrapText(true);
        return db;
    }
}
