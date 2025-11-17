package beebong.ui;

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

        this.dialog.setText(text);
        this.displayPicture.setImage(img);
    }

    /**
     * Initializes the controller class.
     * This method is automatically called by the FXMLLoader
     * after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        double radius = 32;
        Circle clip = new Circle(radius, radius, radius); // Adjust radius to match image size
        this.displayPicture.setClip(clip);
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

    private void changeDialogStyle(String dialogType) {
        switch(dialogType) {
        case "ErrorResponse":
            dialog.getStyleClass().add("error-label");
            break;
        case "BotResponse":
            dialog.getStyleClass().add("bot-label");
            break;
        case "ExitResponse":
            dialog.getStyleClass().add("exit-label");
            break;
        default:
            // Do nothing
        }
    }

    /**
     * Creates a dialog box representing a user message.
     *
     * @param text the message text to display
     * @param img the image to display alongside the user message
     * @return a DialogBox configured for the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box representing a bot message, applies a flip
     * to position the image and message appropriately, and applies a
     * custom style.
     *
     * @param text the message text to display
     * @param img the image to display alongside the bot message
     * @param style a CSS style string to apply to the dialog box
     * @return a DialogBox configured for the bot with flipped layout and custom style
     */
    public static DialogBox getBotDialog(String text, Image img, String style) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(style);
        return db;
    }
}
