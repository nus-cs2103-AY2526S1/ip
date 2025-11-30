package dyke.ui;

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
import javafx.scene.shape.Circle;

/**
 * Houses methods for creating the dialog boxes in GUI.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Clip the ImageView into a circle (Enlisted the help of ChatGPT
        * to crop the display pictures into a circle) */
        double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2;

        Circle clip = new Circle(
                displayPicture.getFitWidth() / 2,
                displayPicture.getFitHeight() / 2,
                radius
        );
        displayPicture.setClip(clip);
        displayPicture.setImage(img);
        dialog.setText(text);
    }

    /**
     * Flips the dialog box such that the ImageView is
     * on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp =
                FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    private void setUserStyle() {
        this.dialog.getStyleClass().add("user-bubble");
    }

    private void setDykeStyle() {
        this.dialog.getStyleClass().add("message-bubble");
    }

    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox d = new DialogBox(s, i);
        d.setUserStyle();
        return d;
    }

    public static DialogBox getDykeDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.setDykeStyle();
        db.flip();
        return db;
    }

}
