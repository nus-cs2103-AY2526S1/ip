package cookie.ui;

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
 * Enhanced dialog box with asymmetric design for better conversation flow
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private boolean isUser;
    private boolean isError;

    private DialogBox(String text, Image img, boolean isUser, boolean isError) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.isUser = isUser;
        this.isError = isError;

        dialog.setText(text);
        displayPicture.setImage(img);

        applyStyles();
    }

    private void applyStyles() {
        // Clear existing style classes
        dialog.getStyleClass().clear();
        dialog.getStyleClass().add("label");

        if (isUser) {
            // User message styling
            setAlignment(Pos.TOP_RIGHT);
            dialog.getStyleClass().add("user-message");
            displayPicture.getStyleClass().add("user-avatar");
        } else {
            // Bot message styling
            flipLayout();
            if (isError) {
                dialog.getStyleClass().add("error-message");
            } else {
                dialog.getStyleClass().add("bot-message");
            }
            displayPicture.getStyleClass().add("bot-avatar");
        }
    }

    /**
     * Flips the dialog box for bot messages (avatar on left, text on right)
     */
    private void flipLayout() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true, false);
    }

    public static DialogBox getBotDialog(String text, Image img, boolean isError) {
        return new DialogBox(text, img, false, isError);
    }
}
