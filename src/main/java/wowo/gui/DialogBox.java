package wowo.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Containing the dialog logic between bot and user
 */
public class DialogBox extends HBox {
    private static final String BOT_STYLE =
            "-fx-padding:10 12 10 12; -fx-background-radius:14;"
                    + "-fx-background-color:#f2f3f5; -fx-text-fill:#111;"
                    + "-fx-border-color:#e5e6eb; -fx-border-radius:14;";

    private static final String ERROR_STYLE =
            "-fx-padding:10 12 10 12; -fx-background-radius:14;"
                    + "-fx-background-color:#ffe8e6; -fx-text-fill:#9f2d2d;"
                    + "-fx-border-color:#ffb3ac; -fx-border-radius:14;";

    private static final String USER_STYLE =
            "-fx-padding:10 12 10 12; -fx-background-radius:14;"
                    + "-fx-background-color:#2f6fed; -fx-text-fill:white;";

    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        assert text != null : "Dialog text must not be null";
        assert img != null : "Display image must not be null";

        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            // --- make wrapping bullet-proof ---
            dialog.setWrapText(true);
            dialog.setTextOverrun(OverrunStyle.CLIP);
            dialog.setMinWidth(0);
            dialog.setMinHeight(Region.USE_COMPUTED_SIZE);
            HBox.setHgrow(dialog, Priority.ALWAYS);

            dialog.maxWidthProperty().bind(Bindings.createDoubleBinding((
                    ) -> {
                        double avatar = displayPicture.getBoundsInParent().getWidth();
                        double spacing = getSpacing();
                        double pad = getPadding().getLeft() + getPadding().getRight();
                        // small extra margin so rounded bubble doesn’t hit the avatar
                        double bubbleMargin = 8;
                        double isAvailable = Math.max(0, getWidth() - avatar - spacing - pad - bubbleMargin);
                        return isAvailable;
                    },
                    widthProperty(),
                    displayPicture.boundsInParentProperty(),
                    spacingProperty(),
                    paddingProperty()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load DialogBox.fxml", e);
        }

        assert dialog != null : "FXML did not inject Label 'dialog'";
        assert displayPicture != null : "FXML did not inject ImageView 'displayPicture'";

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /** Flip so the avatar is on the left and text on the right (user side). */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(USER_STYLE);
        return db;
    }

    public static DialogBox getBotErrorDialog(String text, Image img) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxBotError.fxml"));
            DialogBox db = new DialogBox(text, img); // reuse your ctor path if you prefer
            loader.setController(db);
            loader.setRoot(db);
            loader.load();
            db.dialog.setText(text);
            db.displayPicture.setImage(img);
            return db;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load DialogBoxBotError.fxml", e);
        }
    }

    /** Bot on the RIGHT (no flip) + gray bubble. */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        // do NOT flip -> bot stays on RIGHT
        db.dialog.setStyle(BOT_STYLE); // gray by default
        return db;
    }

    public void markAsError() {
        dialog.setStyle(ERROR_STYLE);
    }
}
