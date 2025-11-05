package uxie.interfaces.ui.fxelements;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

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
import javafx.scene.text.Font;
import uxie.interfaces.ui.GuiMain;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    /** Dialog text. */
    @FXML
    private Label dialog;

    /** Avatar image. */
    @FXML
    private ImageView displayPicture;

    /**
     * Creates new DialogBox with supplied dialog text and avatar.
     */
    private DialogBox(String dialogText, Image avatarImage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(
                    "/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace(); // can't output to GUI coz. well. can't make a dialog box
        }

        assert dialogText != null : "DialogBox: dialogText is null";
        dialog.setText(dialogText);
        Optional<Font> dialogFont = GuiMain.getFont();
        dialogFont.ifPresent(font -> dialog.setFont(font));

        displayPicture.setImage(avatarImage);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Returns new DialogBox in style of user.
     *
     * @param text dialog spoken by user (a command string).
     * @param img avatar image of user.
     * @return DialogBox in style of user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns new DialogBox in style of Uxie.
     *
     * @param text dialog spoken by Uxie.
     * @param img avatar image of Uxie.
     * @return DialogBox in style of Uxie.
     */
    public static DialogBox getUxieDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
