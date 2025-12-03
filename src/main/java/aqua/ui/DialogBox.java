package aqua.ui;

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
import javafx.scene.shape.Rectangle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox textBoxContainer;
    @FXML
    private HBox imageContainer;

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
     * Initializes the dialog box, setting up the clipping for the display picture.
     */
    @FXML
    public void initialize() {
        Rectangle clip = new Rectangle(displayPicture.getFitHeight(), displayPicture.getFitHeight());
        clip.setArcHeight(90);
        clip.setArcWidth(90);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.textBoxContainer.getChildren());
        Collections.reverse(tmp);
        this.textBoxContainer.getChildren().setAll(tmp);
        tmp = FXCollections.observableArrayList(this.imageContainer.getChildren());
        Collections.reverse(tmp);
        this.imageContainer.getChildren().setAll(tmp);
        this.textBoxContainer.setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Creates a dialog box for the user.
     *
     * @param text The text to be displayed
     * @param img The image to be displayed
     * @return A dialog box for the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-dialog");
        db.flip();
        return db;
    }

    /**
     * Creates a dialog box for Aqua.
     *
     * @param text The text to be displayed
     * @param img The image to be displayed
     * @return A dialog box for Aqua
     */
    public static DialogBox getAquaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("aqua-dialog");
        return db;
    }
}
