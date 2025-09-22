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

/**
 * A custom JavaFX control representing a dialog box for user and chatbot messages.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView profilePic;

    private DialogBox(String text, Image img) {
        assert text != null;
        assert img != null;

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

    /**
     * Creates a dialog box displaying the user's input.
     *
     * @param text the text to display
     * @param img  the profile image for the user
     * @return a {@code DialogBox} representing the user's message
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null;
        assert img != null;

        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box displaying the CHASH's response.
     *
     * @param text the response text
     * @param img  the CHASH's profile image
     * @return a {@code DialogBox} representing the CHASH's response
     */
    public static DialogBox getResponseDialog(String text, Image img) {
        assert text != null;
        assert img != null;

        DialogBox db = DialogBox.getUserDialog(text, img);
        db.flip();
        db.markResponse();
        return db;
    }

    /**
     * Creates a dialog box displaying an error response.
     *
     * @param text the error text
     * @param img  the error profile image
     * @return a {@code DialogBox} representing the error response
     */
    public static DialogBox getErrResponseDialog(String text, Image img) {
        assert text != null;
        assert img != null;

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
