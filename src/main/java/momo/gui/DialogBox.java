package momo.gui;

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
 * Represents a dialog box containing a text label and an image.
 *
 * <p>The dialog box is used to display both user input and Momo's responses.
 * User dialogs are aligned with the image on the right, while Momo's dialogs
 * are flipped with the image on the left and styled according to the type of
 * command executed.</p>
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
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Applies a CSS style to the dialog label based on the command type.
     *
     * @param commandType Type of command that triggered the response.
     */
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "AddDeadlineCommand":
        case "AddEventCommand":
        case "AddTodoCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "UnmarkCommand":
            dialog.getStyleClass().add("unmarked-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        case "FindCommand":
            dialog.getStyleClass().add("find-label");
            break;
        case "Error":
            dialog.getStyleClass().add("error-label");
            break;
        default:
            // No style applied for unrecognized command types
        }
    }

    /**
     * Creates a dialog box representing user input.
     *
     * <p>The dialog displays the text with the image on the right side and
     * no additional styling applied.</p>
     *
     * @param text Text entered by the user.
     * @param img  User's profile image.
     * @return A dialog box containing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box representing Momo's response.
     *
     * <p>The dialog is flipped horizontally with the image on the left side
     * and styled according to the type of command executed. This distinguishes
     * Momo's dialogs from the user's.</p>
     *
     * @param text Response text from Momo.
     * @param img Momo's profile image.
     * @param commandType Type of command that triggered the response.
     * @return A dialog box containing Momo's styled dialog.
     */
    public static DialogBox getMomoDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
