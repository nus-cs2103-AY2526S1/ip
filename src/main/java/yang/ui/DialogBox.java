package yang.ui;

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
 * A custom control that represents a dialog box with text and an image.
 * Used for displaying user and Yang messages in the GUI.
 */
public class DialogBox extends HBox {
    @FXML private Label text;
    @FXML private ImageView displayPicture;

    private DialogBox(String msg, Image img) {
        try {
            FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        text.setText(msg);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> kids = FXCollections.observableArrayList(getChildren());
        Collections.reverse(kids);
        getChildren().setAll(kids);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    public static DialogBox getYangDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
