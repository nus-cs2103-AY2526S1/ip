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
 * Represents a dialog box in the GUI, consisting of a text label and an image.
 * <p>
 * This class is used for both the user and Baymax (chatbot) dialogs.
 * The style and alignment can be customized depending on the type of command.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    /**
     * Constructs a DialogBox with the given text and image.
     * Loads the FXML layout and initializes the components.
     *
     * @param text the text content of the dialog
     * @param img  the image representing the speaker
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
     * Flips the dialog box so that the image is on the left and text is on the right.
     * This is used for Baymax's dialog to differentiate from the user's dialog.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a DialogBox for the user with the specified text and image.
     *
     * @param text the user's message
     * @param img  the user's avatar image
     * @return a DialogBox representing the user message
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for Baymax (chatbot) with the specified text, image, and command type.
     * The dialog is flipped and styled according to the command type.
     *
     * @param text        the chatbot's message
     * @param img         the chatbot's avatar image
     * @param commandType the type of command (used to style the message)
     * @return a DialogBox representing the Baymax message
     */
    public static DialogBox getBaymaxDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }


    /**
     * Changes the visual style of the dialog text based on the command type.
     *
     * @param commandType the type of command that produced this dialog
     */
    private void changeDialogStyle(String commandType) {
        if (this.getAlignment() == Pos.TOP_LEFT) {
            // Baymax messages: light gray background
            dialog.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-background-radius: 10px;");
        } else {
            // User messages: light blue background
            dialog.setStyle("-fx-background-color: #d0e7ff; -fx-padding: 5px; -fx-background-radius: 10px;");
        }

        switch (commandType) {
        case "TodoCommand":
        case "DeadlineCommand":
        case "EventCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkCommand":
        case "UnmarkCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        case "FindCommand":
            dialog.getStyleClass().add("find-label");
            break;
        case "ListCommand":
            dialog.getStyleClass().add("list-label");
            break;
        case "ByeCommand":
            dialog.getStyleClass().add("bye-label");
            break;
        default:
            dialog.getStyleClass().add("error-label");
            break;
        }
    }

}
