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
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        this.makeImageCircular();

        // Set default styling for user messages (right side)
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(8.0);
    }

    /** Makes the profile picture circular by applying a circular clip. */
    private void makeImageCircular() {
        // Create a circle with radius equal to half the image width
        double radius = displayPicture.getFitWidth() / 2;
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right. Used for
     * bot/Dwight messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);

        // Add reply styling to the label
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a user dialog box with message on the left and avatar on the right. Messages appear
     * as blue bubbles (Telegram style).
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a bot/Dwight dialog box with avatar on the left and message on the right. Messages
     * appear as gray bubbles (Telegram style).
     */
    public static DialogBox getDwightDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
