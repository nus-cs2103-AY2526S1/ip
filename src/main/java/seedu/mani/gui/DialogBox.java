package seedu.mani.gui;

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

/*
 * This code was developed with guidance from ChatGPT (OpenAI).
 * ChatGPT provided advice on implementing better UI in JavaFX.
 * Date: 22-Sep-2025
 */
/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isError, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Text wrapping
        dialog.setWrapText(true);

        // Style based on type of message
        if (isError) {
            dialog.setStyle("-fx-background-color: #FFD2D2; -fx-text-fill: red; -fx-padding: 8; -fx-background-radius: 8;");
        } else if (isUser) {
            dialog.setStyle("-fx-background-color: #DCF8C6; -fx-padding: 8; -fx-background-radius: 8;");
        } else {
            dialog.setStyle("-fx-background-color: #E8E8E8; -fx-padding: 8; -fx-background-radius: 8;");
        }

        dialog.setText(text);

        // Profile picture setup
        displayPicture.setImage(img);
        displayPicture.setFitWidth(40);
        displayPicture.setFitHeight(40);
        Circle clip = new Circle(20, 20, 20);
        displayPicture.setClip(clip);
        dialog.setMaxWidth(400); // adjust as needed
        dialog.setWrapText(true);

        if (isUser) {
            flip();
        }
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    public Label getDialogLabel() {
        return dialog;
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false, true);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img, false, false);
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        return new DialogBox(text, img, true, false);
    }
}