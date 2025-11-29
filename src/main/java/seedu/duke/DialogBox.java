package seedu.duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A dialog row consisting of the speaker image and a text bubble.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog box using the {@code DialogBox.fxml} definition.
     *
     * @param text Message shown in the bubble.
     * @param img  Avatar image.
     */
    private DialogBox(String text, Image img) {
        try {
            final FXMLLoader fxmlLoader =
                    new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            // In a UI control constructor, printing is acceptable for debugging in this project scope.
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Bubble readability & avatar size.
        setSpacing(8);
        dialog.setWrapText(true);
        dialog.maxWidthProperty().bind(this.widthProperty().subtract(100));

        displayPicture.setFitWidth(56);
        displayPicture.setFitHeight(56);
        displayPicture.setPreserveRatio(true);

        setFillHeight(true);
        setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(dialog, javafx.scene.layout.Priority.ALWAYS);
    }

    /** Positions image on the LEFT and bubble on the RIGHT (Duke). */
    private void layoutLeft() {
        getChildren().setAll(displayPicture, dialog);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /** Positions bubble on the LEFT and image on the RIGHT (User). */
    private void layoutRight() {
        getChildren().setAll(dialog, displayPicture);
        setAlignment(Pos.TOP_RIGHT);
        dialog.getStyleClass().remove("reply-label");
    }

    /**
     * Factory for the user dialog row.
     *
     * @param text Message.
     * @param img  Avatar.
     * @return The dialog box configured for a user message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        final DialogBox db = new DialogBox(text, img);
        db.layoutRight();
        return db;
    }

    /**
     * Factory for the Duke dialog row.
     *
     * @param text Message.
     * @param img  Avatar.
     * @return The dialog box configured for a Duke message.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        final DialogBox db = new DialogBox(text, img);
        db.layoutLeft();
        return db;
    }
}
