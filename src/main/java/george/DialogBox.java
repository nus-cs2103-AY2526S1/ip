package george;

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

    private DialogBox(String text, Image img, boolean isError) {
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

        // This improvement in GUI was done by AI (Deepseek)
        // Apply error styling if this is an error message
        if (isError) {
            applyErrorStyle();
        }
    }

    /**
     * Applies error styling to the dialog box.
     */
    private void applyErrorStyle() {
        // Clear any existing style
        dialog.setStyle("");

        // Apply error styling
        dialog.setStyle(
                "-fx-text-fill: #d32f2f;"
                + "-fx-font-weight: bold;"
                + "-fx-background-color: #ffebee;"
                + "-fx-background-radius: 10;"
                + "-fx-padding: 10;"
                + "-fx-border-color: #d32f2f;"
                + "-fx-border-radius: 10;"
                + "-fx-border-width: 2;"
        );
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
        return new DialogBox(text, img, false);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }

    /**
     * Creates an error dialog box with special styling.
     *
     * @param text The error message text
     * @param img The image to display
     * @return A styled error dialog box
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}
