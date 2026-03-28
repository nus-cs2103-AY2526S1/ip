package gui;

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
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private boolean isUser = true;

    public DialogBox(String text, Image img) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // --- Styling ---
        this.getStyleClass().add("dialog");
        dialog.getStyleClass().addAll("bubble", "bubble-user", "label-wrap");
        displayPicture.getStyleClass().add("avatar");

        dialog.setWrapText(true);
        dialog.setMinHeight(Region.USE_PREF_SIZE);

        // Circular avatar (fixed 99x99)
        double avatarSize = 99;
        displayPicture.setFitWidth(avatarSize);
        displayPicture.setFitHeight(avatarSize);
        Circle clip = new Circle(avatarSize / 2, avatarSize / 2, avatarSize / 2);
        displayPicture.setClip(clip);

        // User on right by default
        setAlignment(Pos.TOP_RIGHT);
    }

    /** Flip for bot: left side + bot bubble CSS */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(children);
        getChildren().setAll(children);

        dialog.getStyleClass().remove("bubble-user");
        if (!dialog.getStyleClass().contains("bubble-bot")) {
            dialog.getStyleClass().add("bubble-bot");
        }
        isUser = false;
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

