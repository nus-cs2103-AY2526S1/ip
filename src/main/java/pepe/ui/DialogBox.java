package pepe.ui;

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
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text the text to display in the dialog
     * @param img the image representing the speaker
     */
    private DialogBox(String text, Image img) {
        assert text != null : "Dialog text should not be null";
        assert img != null : "Image should not be null";
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
     * Updates the style of the dialog label based on the type of command.
     * <p>
     * Applies one of several CSS classes:
     * <ul>
     *     <li>{@code warning-label} if commandType is null</li>
     *     <li>{@code delete-label} for DeleteCommand</li>
     *     <li>{@code mark-label} for MarkCommand or UnmarkCommand</li>
     *     <li>{@code find-label} for FindCommand</li>
     *     <li>{@code add-label} for DeadlineCommand, TodoCommand, or EventCommand</li>
     *     <li>{@code reply-label} for all other types</li>
     * </ul>
     *
     * @param commandType the type of command (class name) or {@code null} for warnings
     */
    private void changeDialogStyle(String commandType) {
        if (commandType == null) {
            dialog.getStyleClass().add("warning-label");
            return;
        }
        switch (commandType) {
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        case "MarkCommand", "UnmarkCommand":
            dialog.getStyleClass().add("mark-label");
            break;
        case "DeadlineCommand", "TodoCommand", "EventCommand":
            dialog.getStyleClass().add("add-label");
            break;
        default:
            dialog.getStyleClass().add("reply-label");
        }
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
     * Creates a DialogBox representing the user's input.
     *
     * @param text the user's input text
     * @param img the user's avatar image
     * @return a DialogBox representing the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("label");
        return db;
    }

    /**
     * Creates a DialogBox representing Pepe's response.
     * <p>
     * The dialog box is flipped so that the image appears on the left.
     * Optionally changes the style based on the command type.
     *
     * @param text the response text from Pepe
     * @param img Pepe's avatar image
     * @param commandType the type of command, or {@code null} for warnings
     * @return a DialogBox representing Pepe
     */
    public static DialogBox getPepeDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
