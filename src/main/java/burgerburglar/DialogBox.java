package burgerburglar;

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
import javafx.scene.layout.VBox;


/**
 * A custom control representing a dialog box in the GUI.
 */
public class DialogBox extends HBox {

    private static final String USER_SPEAKER = "YOU";
    private static final String BURGER_SPEAKER = "BURGER";
    private static final double MAX_BUBBLE_WIDTH = 250.0;

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox avatarContainer;

    public DialogBox() {
        loadFxml();
    }

    private void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load DialogBox FXML.", e);
        }
    }

    private void setData(String text, Image img, String speaker, boolean isUser) {
        setDialogText(text);
        setDisplayImage(img);
        addSpeakerLabel(speaker);
        styleDialogBubble(isUser);
    }

    private void setDialogText(String text) {
        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setMaxWidth(MAX_BUBBLE_WIDTH);
    }

    private void setDisplayImage(Image img) {
        displayPicture.setImage(img);
    }

    private void addSpeakerLabel(String speaker) {
        Label speakerLabel = new Label(speaker);
        speakerLabel.getStyleClass().add("speaker-label");
        avatarContainer.getChildren().add(speakerLabel);
    }

    private void styleDialogBubble(boolean isUser) {
        dialog.getStyleClass().add("dialog-bubble");
        if (isUser) {
            dialog.getStyleClass().add("user");
        }
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox();
        db.setData(text, img, USER_SPEAKER, true);
        db.flip();
        return db;
    }

    public static DialogBox getBurgerDialog(String text, Image img) {
        DialogBox db = new DialogBox();
        db.setData(text, img, BURGER_SPEAKER, false);
        return db;
    }
}
