package gui;

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
    private Image madScientist = new Image(this.getClass().getResourceAsStream("/images/Mad Scientist.jpg"));

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
     * Returns a new dialog box containing user image and user input text.
     */
    public static DialogBox getUserDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.changeDialogPicture(commandType);
        return db;
    }

    /**
     * Returns a new dialog box containing tsundereChan's image and reply text.
     */
    public static DialogBox getTsundereDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Returns a new dialog box containing tsundereChan's image and reply text,
     * with a different dialog style depending on commandType.
     */
    public static DialogBox getTsundereDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

    /**
     * Changes the style of the dialog box depending on the command
     */
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "AddDeadlineCommand", "AddEventCommand", "AddTodoCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkCommand", "DeleteCommand", "UnmarkCommand", "FindCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "InvalidCommand":
            dialog.getStyleClass().add("error-label");
            break;
        default:
            // Do nothing
        }
    }

    /**
     * Changes the picture of the dialog box depending on the command
     */
    private void changeDialogPicture(String commandType) {
        switch (commandType) {
        case "ElPsyCongrooCommand", "MadScientistCommand", "SteinsGateCommand", "WorldIsEndingCommand":
            displayPicture.setImage(madScientist);
            break;
        default:
            // Do nothing
        }
    }
}
