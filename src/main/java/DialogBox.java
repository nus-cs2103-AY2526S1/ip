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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
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
    /**
     * Creates a dialog box for user input.
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A DialogBox representing user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    public static DialogBox getBettyDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
    // Improved GUI methods by GitHub Copilot
    // Add a method to set dialog style based on sender
    public void setAsUser() {
        this.setAlignment(Pos.TOP_RIGHT);
        this.setStyle("-fx-background-color: #d1e7dd; -fx-background-radius: 15 0 15 15;");
    }

    public void setAsBot() {
        this.setAlignment(Pos.TOP_LEFT);
        this.setStyle("-fx-background-color: #f8d7da; -fx-background-radius: 0 15 15 15;");
    }
}
