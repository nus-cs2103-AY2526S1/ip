package eureka;

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
 * Represents a custom dialog box consisting of a text label and an image.
 * <p>
 * The dialog box is used in the GUI to display conversations between the user
 * and the chatbot (Eureka). It can be flipped to distinguish between the user
 * and Eureka's messages.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a {@code DialogBox} with the specified text and image.
     * <p>
     * Loads the FXML layout for the dialog box and sets the message text
     * and speaker image.
     *
     * @param text the message text to display
     * @param img  the speaker's display picture
     */
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
     * Flips the dialog box layout such that the {@link ImageView} is on the left
     * and the text is on the right. This is typically used for Eureka's responses.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Factory method to create a dialog box for user messages.
     *
     * @param text the user's message text
     * @param img  the user's display picture
     * @return a {@code DialogBox} representing the user's message
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Factory method to create a dialog box for Eureka's messages.
     * <p>
     * The dialog box is flipped so that the image appears on the left side.
     *
     * @param text the chatbot's response text
     * @param img  the chatbot's display picture
     * @return a {@code DialogBox} representing Eureka's message
     */
    public static DialogBox getEurekaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
