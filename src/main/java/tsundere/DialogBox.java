package tsundere;

import java.io.IOException;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private Image angryTsundereImage = new Image(this.getClass().getResourceAsStream("/images/angryTohsaka.jpg"));

    private DialogBox(VBox parent, String text, Image img, boolean isUser) {
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
        if (!isUser) {
            displayPicture.setOnMouseClicked(event -> parent.getChildren().add(
                    DialogBox.getTsundereDialog(parent, "Hey! Stop it!", angryTsundereImage)
            ));
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        dialog.pseudoClassStateChanged(new PseudoClass() {
            @Override
            public String getPseudoClassName() {
                return "rin";
            }
        }, true);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(VBox parent, String text, Image img) {
        return new DialogBox(parent, text, img, true);
    }

    public static DialogBox getTsundereDialog(VBox parent, String text, Image img) {
        var db = new DialogBox(parent, text, img, false);
        db.flip();
        return db;
    }
}
