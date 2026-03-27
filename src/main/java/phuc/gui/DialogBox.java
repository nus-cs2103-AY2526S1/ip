package phuc.gui;

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
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with specified text and image.
     * Loads FXML layout and initializes UI components.
     *
     * @param text the message text to display
     * @param img  the avatar image to show
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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        dialog.getStyleClass().add("reply-label");
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a user message dialog box.
     *
     * @param text the message text to display
     * @param img the user avatar image
     * @return a DialogBox configured for user messages
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Factory method to create a Duke (chatbot) message dialog box with flipped orientation.
     *
     * @param text the message text to display
     * @param img Duke's avatar image
     * @return a DialogBox configured for Duke messages
     */
    public static DialogBox getDukeDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    /**
     * Checks the type of command and apply the corresponding design to the command type.
     *
     * @param commandType the command of user in string format.
     */
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "sort":
            dialog.getStyleClass().add("sort-label");
            break;
        case "list":
            dialog.getStyleClass().add("list-label");
            break;
        case "mark":
            dialog.getStyleClass().add("marked-label");
            break;
        case "delete":
            dialog.getStyleClass().add("delete-label");
            break;
        case "greeting":
            dialog.getStyleClass().add("greeting-label");
            break;
        case "todo":
            dialog.getStyleClass().add("add-label");
            break;
        case "deadline":
            dialog.getStyleClass().add("add-label");
            break;
        case "event":
            dialog.getStyleClass().add("add-label");
            break;
        case "unmark":
            dialog.getStyleClass().add("marked-label");
            break;
        case "find":
            dialog.getStyleClass().add("find-label");
            break;
        case "bye":
            dialog.getStyleClass().add("bye-label");
            break;
        default:
            dialog.getStyleClass().add("error-label");
        }
    }
}
