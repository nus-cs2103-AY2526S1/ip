package jaiden.ui;

import java.io.IOException;
import java.util.Collections;

import jaiden.command.CommandType;
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
 * Represents a dialog box consisting of an ImageView to represent
 * the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        try {
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            fxmlLoader.<MainWindow>getController().showLoadingError("⚠ The source file is corrupted.");
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Apply different style classes depending on speaker.
     */
    private void applyBaseStyle(boolean isUser) {
        if (isUser) {
            setAlignment(Pos.TOP_RIGHT);
            this.dialog.getStyleClass().add("user-label");
        } else {
            setAlignment(Pos.TOP_LEFT);
            this.dialog.getStyleClass().add("bot-label");
        }
    }

    /**
     * Overlay additional styles depending on command type (errors, add, delete, etc.).
     */
    private void applyCommandStyle(CommandType commandType) {
        if (commandType == null) {
            return;
        }
        switch (commandType) {
        case ADDCOMMAND:
            this.dialog.getStyleClass().add("add-label");
            break;
        case CHANGEMARKCOMMAND:
            this.dialog.getStyleClass().add("marked-label");
            break;
        case DELETECOMMAND:
            this.dialog.getStyleClass().add("delete-label");
            break;
        case LISTCOMMAND:
            this.dialog.getStyleClass().add("list-label");
            break;
        case EXITCOMMAND:
            this.dialog.getStyleClass().add("exit-label");
            break;
        case ERRORCOMMAND:
            this.dialog.getStyleClass().add("error-label");
            break;
        default:
            break;
        }
    }

    /**
     * Gets user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.applyBaseStyle(true);
        return db;
    }

    /**
     * Gets bot (Jaiden) dialog box.
     */
    public static DialogBox getJaidenDialog(String text, Image img, CommandType commandType) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.applyBaseStyle(false);
        db.applyCommandStyle(commandType);
        return db;
    }
}
