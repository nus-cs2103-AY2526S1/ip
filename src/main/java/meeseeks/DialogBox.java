package meeseeks;
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
 * Represents a dialog box with different message types for asymmetric conversation design.
 * Supports user messages, app messages, and error messages with distinct styling.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    public enum MessageType {
        USER, APP, ERROR
    }

    private DialogBox(String text, Image img, MessageType type) {
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
        
        // Apply different styling based on message type for asymmetric design
        applyMessageStyling(type);
    }

/**
 * Applies different styling based on message type for asymmetric design
 * @param type the message type
 */
    private void applyMessageStyling(MessageType type) {
        switch (type) {
            case USER:
                // User messages - right-aligned, compact design
                dialog.setStyle("-fx-background-color: #4CAF50; " +
                        "-fx-background-radius: 18; " +
                        "-fx-padding: 8 16; " +
                        "-fx-font-size: 13; " +
                        "-fx-font-weight: 500; " +
                        "-fx-text-fill: white; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2, 0, 0, 1);");
                displayPicture.setFitWidth(32);
                displayPicture.setFitHeight(32);
                break;
            case APP:
                // App messages - left-aligned, more spacious
                dialog.setStyle("-fx-background-color: #F5F5F5; " +
                        "-fx-background-radius: 18; " +
                        "-fx-padding: 12 16; " +
                        "-fx-font-size: 14; " +
                        "-fx-text-fill: #333333; " +
                        "-fx-border-color: #E0E0E0; " +
                        "-fx-border-radius: 18; " +
                        "-fx-border-width: 1; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 1, 0, 0, 0);");
                displayPicture.setFitWidth(36);
                displayPicture.setFitHeight(36);
                break;
            case ERROR:
                // Error messages - prominent red styling to catch attention
                dialog.setStyle("-fx-background-color: #FFEBEE; " +
                        "-fx-background-radius: 18; " +
                        "-fx-padding: 12 16; " +
                        "-fx-font-size: 14; " +
                        "-fx-font-weight: 500; " +
                        "-fx-text-fill: #D32F2F; " +
                        "-fx-border-color: #FFCDD2; " +
                        "-fx-border-radius: 18; " +
                        "-fx-border-width: 2; " +
                        "-fx-effect: dropshadow(gaussian, rgba(211,47,47,0.2), 3, 0, 0, 1);");
                displayPicture.setFitWidth(36);
                displayPicture.setFitHeight(36);
                break;
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

    /**
     * Creates a user dialog box
     * @param text the text to display
     * @param img the image to display
     * @return the user dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, MessageType.USER);
    }

    /**
     * Creates an app dialog box
     * @param text the text to display
     * @param img the image to display
     * @return the app dialog box
     */
    public static DialogBox getAppDialog(String text, Image img) {
        var db = new DialogBox(text, img, MessageType.APP);
        db.flip();
        return db;
    }

    /**
     * Creates an error dialog box
     * @param text the text to display
     * @param img the image to display
     * @return the error dialog box
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img, MessageType.ERROR);
        db.flip();
        return db;
    }

    // Keep backward compatibility
    public static DialogBox getDukeDialog(String text, Image img) {
        return getAppDialog(text, img);
    }
}
