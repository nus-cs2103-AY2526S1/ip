package userinteraction;

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
 * This class represents an ImageView with both an image
 * and a label to make the dialogue look more realistic.
 */
public class DialogChatBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private final chatbot.Jocelyn jocelyn = new chatbot.Jocelyn();

    /**
     * Creates a new instance of a dialog chat box that appears on the GUI
     *
     * @param text Text that is passed in by the user.
     * @param img Image that you get that is already provided and taken from Pixabay.
     */
    private DialogChatBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TheMainWindow.class
                    .getResource("/view/DialogChatBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    public static DialogChatBox getUserDialog(String text, Image img) {
        return new DialogChatBox(text, img);
    }

    public static DialogChatBox getJocelynDialog(String text, Image img) {
        var db = new DialogChatBox(text, img);
        db.flip();
        return db;
    }


}
