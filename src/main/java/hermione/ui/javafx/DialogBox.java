package hermione.ui.javafx;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's
 * face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayCircle;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the dialog text
        dialog.setText(text);

        // Set up circular clipping for the display picture
        displayCircle.setStroke(Color.BLACK);
        displayCircle.setFill(new ImagePattern(img));
        displayCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.BLACK));
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img);
        db.flip();
        if (isError) {
            db.dialog.getStyleClass().add("error");
        }
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        dialog.getStyleClass().add("reply-label");
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}
