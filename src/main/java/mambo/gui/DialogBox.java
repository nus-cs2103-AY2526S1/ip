package mambo.gui;

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
 *
 * @author kentalim2
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
        makeImageCircular();
    }

    /**
     * Applies a circular clip to the displayPicture ImageView.
     * DeepSeek AI was used in guiding through the proper implementation of this function.
     */
    private void makeImageCircular() {
        Circle clip = new Circle();

        double radius = displayPicture.getFitWidth() / 2.2;
        clip.setRadius(radius);

        clip.setCenterX(radius);
        clip.setCenterY(radius);

        displayPicture.setClip(clip);
    }

    /**
     * Changes the dialog box colour based on the type of command that is being passed through.
     *
     * @param commandType The command type that is being passed into the chatbot as a string
     */
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "ToDoCommand", "EventCommand", "DeadlineCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getMamboDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

}
