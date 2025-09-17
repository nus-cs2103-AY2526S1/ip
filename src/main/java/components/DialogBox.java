package components;

import java.io.IOException;

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
 * JavaFX class that represents a Dialog
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String message, Image image, String color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(message);
        dialog.setStyle("-fx-text-fill: %s;".formatted(color));
        displayPicture.setImage(image);
    }

    /**
     * Creates the dialog box with black text.
     * @param message to display
     * @param image to display
     * @return DialogBox with black text and image
     */
    public static DialogBox getDialogBox(String message, Image image) {
        return new DialogBox(message, image, "black");
    }


    /**
     * Creates the dialog box with red text to display errors better.
     * @param message to display
     * @param image to display
     * @return DialogBox with red text and image
     */
    public static DialogBox getErrorDialogBox(String message, Image image) {
        return new DialogBox(message, image, "red");
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method to create a DialogBox for the User.
     * @param message that the user sent
     * @param image representing the user
     * @return the DialogBox representing the User
     */
    public static DialogBox getUserDialog(String message, Image image) {
        return DialogBox.getDialogBox(message, image);
    }


    /**
     * Factory method to create a DialogBox for Pepe.
     * @param message that Pepe sent
     * @param image representing Pepe
     * @return the DialogBox representing Pepe
     */
    public static DialogBox getPepeDialog(String message, Image image, boolean hasError) {
        DialogBox db;
        if (hasError) {
            db = DialogBox.getErrorDialogBox(message, image);
        } else {
            db = DialogBox.getDialogBox(message, image);
        }
        db.flip();
        return db;
    }
}
