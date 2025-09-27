package eve.gui;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's
 * face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Loading DialogBox.fxml returns an error");
        }

        dialog.setText(text);
        dialog.setMaxWidth(350);
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Creates a {@code DialogBox} representing the user's message.
     * <p>
     * The dialog box is flipped so that the text appears on the left
     * and the user's image on the right. A custom style is applied
     * (greenish background, rounded corners, Segoe UI font).
     *
     * @param text The text content of the dialog.
     * @param img  The user avatar image to display beside the dialog.
     * @return A {@code DialogBox} styled as a user message bubble.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        // User bubble style
        db.dialog.setStyle(
                "-fx-font-family: 'Segoe UI';"
                        + "-fx-font-size: 13px;"
                        + "-fx-padding: 8 12 8 12;"
                        + "-fx-background-color: #b7f56cff;"
                        + "-fx-background-radius: 12;"
                        + "-fx-text-fill: #222;");
        return db;
    }

    /**
     * Creates a {@code DialogBox} representing Eve's (the bot's) message.
     * <p>
     * The dialog box is styled with a bluish background, rounded corners,
     * Segoe UI Semilight font, and line spacing adjustments.
     *
     * @param text The text content of the dialog.
     * @param img  The bot avatar image to display beside the dialog.
     * @return A {@code DialogBox} styled as an Eve message bubble.
     */
    public static DialogBox getEveDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        // Bot bubble style
        db.dialog.setStyle(
                "-fx-font-family: 'Segoe UI Semilight';"
                        + "-fx-font-size: 13px;"
                        + "-fx-padding: 8 12 8 12;"
                        + "-fx-background-color: #aecef5ff;"
                        + "-fx-background-radius: 12;"
                        + "-fx-text-fill: #0c0c0cff;"
                        + "-fx-line-spacing: 3px;");
        return db;
    }
}
