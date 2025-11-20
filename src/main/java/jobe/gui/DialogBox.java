package jobe.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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
            Parent dialogRoot = fxmlLoader.load();
            Circle clip = new Circle(50, 50, 40);
            displayPicture.setClip(clip);
            dialogRoot.getStylesheets().add(getClass().getResource("/css/dialog-box.css").toExternalForm());
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
    
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    
    public static DialogBox getJobeDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
    
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "TodoCommand":
            dialog.getStyleClass().add("todo-label");
            break;
        case "DeadlineCommand":
            dialog.getStyleClass().add("deadline-label");
            break;
        case "EventCommand":
            dialog.getStyleClass().add("event-label");
            break;
        case "MarkCommand":
            dialog.getStyleClass().add("mark-label");
            break;
        case "UnmarkCommand":
            dialog.getStyleClass().add("unmark-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        case "ListCommand":
            dialog.getStyleClass().add("list-label");
            break;
        case "FindCommand":
            dialog.getStyleClass().add("find-label");
            break;
        case "HelloCommand":
            dialog.getStyleClass().add("hello-label");
            break;
        case "ErrorCommand":
            dialog.getStyleClass().add("error-label");
            break;
        case "StorageCommand":
            dialog.getStyleClass().add("storage-label");
        default:
        
        }
    }
}

