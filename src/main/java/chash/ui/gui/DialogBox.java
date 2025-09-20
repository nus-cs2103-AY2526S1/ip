package chash.ui.gui;

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

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView profilePic;

    private DialogBox(String text, Image img) {
        //Load view info from fxml file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.dialog.setText(text);
        this.profilePic.setImage(img);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getResponseDialog(String text, Image img) {
        DialogBox db = DialogBox.getUserDialog(text, img);
        db.flip();
        db.markResponse();
        return db;
    }

    public static DialogBox getErrResponseDialog(String text, Image img) {
        DialogBox db = DialogBox.getResponseDialog(text, img);
        db.markErrResponse();
        return db;
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    private void markResponse() {
        this.dialog.getStyleClass().add("reply-label");
        this.dialog.getStyleClass().add("marked-label");
    }

    private void markErrResponse() {
        this.dialog.getStyleClass().add("delete-label");
    }
}
