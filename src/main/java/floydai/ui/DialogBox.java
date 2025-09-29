package floydai.ui;

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
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

/**
 * Represents a dialog box in the UI, consisting of a {@link Label} for text
 * and an {@link ImageView} representing the speaker's face.
 * <p>
 * This class is backed by an FXML layout file {@code DialogBox.fxml}.
 * </p>
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the given text and image.
     * The FXML layout is loaded, and the provided values are set.
     *
     * @param text the dialog text to display
     * @param img  the image representing the speaker
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setTextAlignment(TextAlignment.JUSTIFY);
        displayPicture.setImage(img);
        displayPicture.setFitWidth(64);
        displayPicture.setFitHeight(64);
        displayPicture.setPreserveRatio(true);

        Circle clip = new Circle(32, 32, 32);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box so that the {@link ImageView} appears on the left
     * and the text {@link Label} appears on the right.
     * <p>
     * This is used to visually distinguish different speakers in the UI.
     * </p>
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Flips the dialog box so that the {@link ImageView} appears on the left
     * and the text {@link Label} appears on the right
     * and add error styles to the label.
     * <p>
     * This is used to visually distinguish different speakers in the UI.
     * </p>
     */
    private void error() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
        dialog.getStyleClass().add("error");
    }

    /**
     * Creates a dialog box representing the user.
     *
     * @param text the text of the user's message
     * @param img  the user's display image
     * @return a {@code DialogBox} for the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box representing Floyd (the system).
     * The box is flipped so the image appears on the left side.
     *
     * @param text the text of Floyd's message
     * @param img  the system's display image
     * @return a {@code DialogBox} for Floyd
     */
    public static DialogBox getFloydDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Creates a dialog box representing error messages
     * The box is flipped so the image appears on the left side.
     *
     * @param text the text of Floyd's message
     * @param img  the system's display image
     * @return a {@code DialogBox} for Floyd
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.error();
        return db;
    }
}
