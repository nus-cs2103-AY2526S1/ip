package moon.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

/**
 * A chat bubble with an avatar and a text label.
 * <p>
 * Loaded via FXML and used by {@link MainWindow} for both user and bot messages.
 * The user bubble keeps the avatar on the right; the bot bubble is flipped so the avatar is on the left.
 */
public class DialogBox extends HBox {
    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

    /**
     * Creates a dialog bubble showing the given text and image.
     * The FXML is loaded and injected before UI properties are set.
     *
     * @param text message text for the bubble
     * @param img  avatar image
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.maxWidthProperty().bind(this.widthProperty().subtract(100)); // leave room for avatar/padding

        // avatar
        displayPicture.setImage(img);

        // make avatar circular
        // Note: this was created with the help of AI
        Circle clip = new Circle();
        clip.centerXProperty().bind(displayPicture.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(displayPicture.fitHeightProperty().divide(2));
        clip.radiusProperty().bind(Bindings.min(
                displayPicture.fitWidthProperty(),
                displayPicture.fitHeightProperty()
        ).divide(2));
        displayPicture.setClip(clip);

        // base bubble class (shared styles)
        dialog.getStyleClass().add("bubble");
    }

    /**
     * Flips order so avatar is on the left and text on the right; also applies a reply style.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Factory for a user dialog bubble.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-bubble");
        return db;
    }

    /**
     * Factory for a bot dialog bubble. The bubble is flipped so the avatar appears on the left.
     */
    public static DialogBox getMoonDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("bot-bubble");
        return db;
    }
}
