package geni;

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
        // +++ AI-assisted (ChatGPT): Make avatar circular by clipping and limit image size.
        // This line was suggested/generated with AI to improve appearance of profile pictures.
        {
            double radius = 32.0; // radius in pixels; tweak as desired
            javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(radius, radius, radius);
            displayPicture.setFitWidth(radius * 2);
            displayPicture.setFitHeight(radius * 2);
            displayPicture.setClip(clip);
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
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
    // New helper methods for styling and layout control
    public void setAsUser() {
        this.getStyleClass().add("dialog-user");
    }

    public void setAsBot() {
        this.getStyleClass().add("dialog-bot");
    }

    public void setAsError() {
        // for messages that represent errors
        this.getStyleClass().add("dialog-error");
    }

    /** Allow MainWindow to suggest an upper limit for the dialog text width */
    public void setMaxDialogWidth(double width) {
        dialog.setMaxWidth(width);
        dialog.setWrapText(true);
    }
}

