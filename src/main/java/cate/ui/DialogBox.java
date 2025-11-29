package cate.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Represents a dialog box in the chat GUI.
 * Each dialog box consists of a text label and optionally an avatar image.
 * Supports different styles for user messages, Cate messages, and error messages.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * If the image is null, the avatar is hidden and space is not reserved.
     *
     * @param text the message text to display
     * @param img  the avatar image; null if no image should be shown
     */
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
        dialog.setFont(Font.font("Segoe UI", 13));
        dialog.setPadding(new Insets(6, 10, 6, 10));
        dialog.setMaxWidth(400);

        if (img != null) {
            displayPicture.setImage(img);
            displayPicture.setFitWidth(60);
            displayPicture.setFitHeight(60);
            Circle clip = new Circle(30, 30, 25);
            displayPicture.setClip(clip);
            displayPicture.setVisible(true);
            displayPicture.setManaged(true);
        } else {
            displayPicture.setVisible(false);
            displayPicture.setManaged(false);
        }

        setSpacing(6);
    }

    /**
     * Flips the dialog box so that the avatar is on the left and text on the right.
     * Used for Cate messages and error messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for a user message.
     * User messages do not display an avatar and are right-aligned.
     *
     * @param text the user message text
     * @return a DialogBox styled for the user
     */
    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox(text, null);
        db.setAlignment(Pos.TOP_RIGHT);
        db.dialog.setStyle(
                "-fx-background-color: #e0e0e0;"
                        + "-fx-text-fill: black;"
                        + "-fx-background-radius: 12;"
                        + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2,0,0,1);"
        );
        db.setPadding(new Insets(0, 10, 0, 0));
        return db;
    }

    /**
     * Creates a dialog box for Cate messages.
     * Cate messages display the avatar on the left and are left-aligned.
     *
     * @param text the Cate message text
     * @param img  the Cate avatar image
     * @return a DialogBox styled for Cate
     */
    public static DialogBox getCateDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(
                "-fx-background-color: #1e88e5;"
                        + "-fx-text-fill: white;"
                        + "-fx-background-radius: 12;"
        );
        return db;
    }

    /**
     * Creates a dialog box for error messages.
     * Error messages display the avatar on the left and are left-aligned,
     * and use a distinct style to highlight errors.
     *
     * @param text the error message text
     * @param img  the avatar image to display with the error
     * @return a DialogBox styled for errors
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(
                "-fx-background-color: #fff4e5;"
                        + "-fx-text-fill: #b34700;"
                        + "-fx-font-weight: normal;"
                        + "-fx-background-radius: 12;"
        );
        return db;
    }
}
