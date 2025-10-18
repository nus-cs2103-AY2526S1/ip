package charli.util;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox avatarContainer;

    public DialogBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setData(String text, Image img, String speaker, boolean isUser) {
        dialog.setText(text);
        displayPicture.setImage(img);

        // Speaker label under avatar
        Label speakerLabel = new Label(speaker);
        speakerLabel.getStyleClass().add("speaker-label");
        avatarContainer.getChildren().add(speakerLabel);

        // Style dialog bubble
        dialog.getStyleClass().add("dialog-bubble");
        if (isUser) {
            dialog.getStyleClass().add("user"); // add 'user' class for user dialog
        }

        // Make label wrap text and adapt width
        dialog.setWrapText(true);
        dialog.setMaxWidth(250); // max bubble width
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox();
        db.setData(text, img, "You", true);
        db.flip();
        return db;
    }

    public static DialogBox getCharliDialog(String text, Image img) {
        DialogBox db = new DialogBox();
        db.setData(text, img, "CharliXCX", false);
        return db;
    }
}