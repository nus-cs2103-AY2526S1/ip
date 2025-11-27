
package mikey.ui;

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
import mikey.main.MainWindow;

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

        // Apply error styling if needed
        if (isError) {
            applyErrorStyling();
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * Also applies Mikey-specific styling.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);

        // Apply Mikey styling (bot responses)
        dialog.setStyle(dialog.getStyle()
                + "-fx-background-color: white; -fx-text-fill: #333333; "
                + "-fx-border-color: #e0e0e0; -fx-border-width: 1; -fx-border-radius: 15;");
    }

    /**
     * Applies styling for user messages
     */
    //Claude AI was used for implementing this method
    private void applyUserStyling() {
        dialog.setStyle(dialog.getStyle()
                + "-fx-background-color: #007AFF; -fx-text-fill: white;");
    }

    /**
     * Applies styling for error messages
     */
    //Claude AI was used for implementing this method
    private void applyErrorStyling() {
        dialog.setStyle(dialog.getStyle()
                + "-fx-background-color: #FF3B30; -fx-text-fill: white; "
                + "-fx-border-color: #FF1744; -fx-border-width: 1; -fx-border-radius: 15;");
    }

    //Claude AI was used for improving this method
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialog = new DialogBox(text, img, false);
        dialog.applyUserStyling();
        return dialog;
    }

    //Claude AI was used for improving this method
    public static DialogBox getMikeyDialog(String text, Image img) {
        // Check if this is an error message based on common error indicators
        boolean isError = text.contains("Invalid input!")
                || text.contains("cannot be empty!")
                || text.contains("Please")
                || text.contains("Provide")
                || text.contains("does not exist!");

        DialogBox db = new DialogBox(text, img, isError);
        db.flip();
        return db;
    }
}