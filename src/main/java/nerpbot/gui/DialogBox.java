package nerpbot.gui;

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
 * Represents a dialog box with an image and a text message.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            System.out.println("FXML resource found: " + (DialogBox.class.getResource("/view/DialogBox.fxml") != null));
            fxmlLoader.load();
            System.out.println("FXML loaded successfully");

            dialog.setText(text);
            displayPicture.setImage(img);
        } catch (IOException e) {
            System.err.println("ERROR LOADING DIALOGBOX FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getNerpBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    // Used github copilot claude sonnet 4.7 thinking to generate error dialog box styling
    public static DialogBox getNerpBotErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        // Add error styling
        db.setStyle("-fx-background-color: #ffeded; -fx-border-color: #ff6b6b; -fx-border-width: 2px; -fx-border-radius: 5px;");
        db.dialog.setStyle("-fx-text-fill: #d32f2f; -fx-font-weight: bold;");
        return db;
    }

    /**
     * Creates a confirmation dialog box for semantic interpretation.
     *
     * @param text The text to display in the dialog.
     * @param img The image to display alongside the dialog.
     * @return A styled DialogBox for confirmation prompts.
     */
    public static DialogBox getNerpBotConfirmDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        // Add confirmation styling - blue/purple theme for questions
        db.setStyle("-fx-background-color: #e8f4fd; -fx-border-color: #2196f3; -fx-border-width: 2px; -fx-border-radius: 5px;");
        db.dialog.setStyle("-fx-text-fill: #1565c0;");
        return db;
    }
}
