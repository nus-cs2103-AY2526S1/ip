package jinbot.gui;

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
        dialog.setWrapText(true);
        dialog.setMaxWidth(250);

        displayPicture.setImage(img);

        displayPicture.setFitHeight(80);
        displayPicture.setFitWidth(80);

        displayPicture.setPreserveRatio(true);
        double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2;
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);
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

    /**
     * Returns dialog box on user side.
     *
     * @param text text from user.
     * @param img Image represents user's avatar.
     * @return Dialog box on user side.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.dialog.getStyleClass().add("user-bubble");
        return db;
    }

    /**
     * Returns dialog box on jinbot side.
     *
     * @param text Text form jinbot
     * @param img Represents jinbot's avatar.
     * @return Dialog box containing error message.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("duke-bubble");
        return db;
    }

    /**
     * Returns dialog box containing error message.
     *
     * @param text EError text.
     * @param img
     * @return
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("error-bubble");
        return db;
    }
}

