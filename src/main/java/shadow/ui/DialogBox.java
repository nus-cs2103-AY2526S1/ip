package shadow.ui;

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
 * Represents a custom dialog box for user interaction in a GUI-based application.
 * This class extends the HBox layout and uses FXML for its structure and appearance.
 * A DialogBox contains a label for the dialog text and an image view for a display picture.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox with the specified text and image.
     * Loads the associated FXML layout, initializes the DialogBox, and sets its content.
     *
     * @param text the text to be displayed in the dialog
     * @param i the image to be displayed in the dialog
     */
    public DialogBox(String text, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(i);
    }

    /**
     * Reverses the order of the child nodes within the DialogBox and sets its alignment to the top-left.
     *
     * @return the current DialogBox instance with reversed child nodes and updated alignment
     */
    private DialogBox flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(children);
        this.getChildren().setAll(children);
        this.setAlignment(Pos.TOP_LEFT);
        return this;
    }

    /**
     * Creates a user dialog box with the specified dialog text and image.
     *
     * @param s the string to be displayed in the dialog box
     * @param i the image to be displayed in the dialog box
     * @return a new instance of {@code DialogBox} containing the provided text and image
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a Duke dialog box with the specified text and image.
     * The dialog box is flipped such that the image is on the left and the text is on the right.
     *
     * @param s the string to be displayed in the dialog box
     * @param i the image to be displayed in the dialog box
     * @return a new instance of {@code DialogBox} containing the provided text and image,
     *     flipped for Duke's perspective
     */
    public static DialogBox getDukeDialog(String s, Image i) {
        return (new DialogBox(s, i)).flip();
    }
}
