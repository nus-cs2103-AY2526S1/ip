package gui;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Represents a dialog box with an avatar and text bubble.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Text styling
        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setMaxWidth(300);
        dialog.setFont(Font.font("Segoe UI", 14));
        dialog.setPadding(new Insets(10, 14, 10, 14));

        // Avatar
        displayPicture.setFitWidth(40);
        displayPicture.setFitHeight(40);
        Circle clip = new Circle(20, 20, 20);
        displayPicture.setClip(clip);
        displayPicture.setImage(img);

        this.setSpacing(10);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
    /** User message (right-aligned, blue bubble) */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        //Add in this setStyle using AI
        db.dialog.setStyle("""
                -fx-background-color: linear-gradient(to bottom right, #90caf9, #42a5f5);
                -fx-background-radius: 16;
                -fx-text-fill: white;
                """);
        return db;
    }

    /** Bot message (left-aligned, grey bubble) */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        //Add in this setStyle using AI
        db.dialog.setStyle("""
                -fx-background-color: linear-gradient(to bottom right, #eeeeee, #cccccc);
                -fx-background-radius: 16;
                -fx-text-fill: black;
                """);
        return db;
    }

    //Add this error handling using AI
    /** Error message (left-aligned, red bubble) */
    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.setText("⚠️ " + text);
        db.dialog.setTextFill(Color.RED);
        db.dialog.setStyle("""
                -fx-background-color: #ffebee;
                -fx-border-color: #f44336;
                -fx-border-width: 1.5;
                -fx-background-radius: 16;
                -fx-text-fill: #b71c1c;
                """);
        return db;
    }
}
