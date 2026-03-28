package lenny.gui;

import java.net.URL;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


/**
 * Reusable chat bubble control used to display a single message in the UI.
 * <p>
 * This control is backed by {@code DialogBox.fxml} and loaded using the
 * <em>fx:root</em> pattern. It shows the message text and an optional profile
 * image (avatar).
 * </p>
 */
public class DialogBox extends HBox {

    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

    /**
     * Constructs a dialog box by loading {@code DialogBox.fxml}, binding this
     * instance as both root and controller, and setting the message text and
     * avatar image.
     *
     * @param text the message to display
     * @param img  the avatar image to show (maybe {@code null})
     * @throws RuntimeException if the FXML cannot be found or loaded
     */
    private DialogBox(String text, Image img) {
        try {
            URL url = DialogBox.class.getResource("/view/DialogBox.fxml");
            Objects.requireNonNull(url, "DialogBox.fxml not found under /view");
            FXMLLoader fxml = new FXMLLoader(url);
            fxml.setRoot(this);
            fxml.setController(this);
            fxml.load();

            this.setAlignment(Pos.TOP_RIGHT);

            final double h_gap = 4;
            this.setSpacing(h_gap);
            this.setPadding(new Insets(6, 8, 6, 8));

            displayPicture.setFitWidth(44);
            displayPicture.setFitHeight(44);
            displayPicture.setPreserveRatio(true);

            HBox.setMargin(displayPicture, Insets.EMPTY);
            HBox.setMargin(dialog, Insets.EMPTY);

            dialog.setText(text);

            dialog.setWrapText(true);
            dialog.setTextOverrun(OverrunStyle.CLIP);
            dialog.setMinWidth(0);
            double padLR = getPadding().getLeft() + getPadding().getRight();
            dialog.maxWidthProperty().bind(
                    widthProperty()
                            .subtract(displayPicture.fitWidthProperty())
                            .subtract(h_gap)
                            .subtract(padLR)
            );
            dialog.setPrefHeight(Region.USE_COMPUTED_SIZE);
            dialog.setMinHeight(Region.USE_PREF_SIZE);
            this.widthProperty().addListener((obs, oldW, newW) -> {
                dialog.setPrefHeight(Region.USE_COMPUTED_SIZE);
            });
            if (img != null) {
                displayPicture.setImage(img);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a dialog box representing a <strong>user</strong> message, with
     * the avatar shown on the left.
     *
     * @param text    the user message
     * @param userImg the user's avatar image
     * @return a {@code DialogBox} configured for a user message
     */
    public static DialogBox getUserDialog(String text, Image userImg) {
        // FXML order is ImageView then Label → already correct for user
        DialogBox db = new DialogBox(text, userImg);
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }

    /**
     * Creates a dialog box representing a <strong>Lenny</strong> (bot) message,
     * with the avatar shown on the right.
     *
     * @param text     the bot message
     * @param lennyImg the bot's avatar image
     * @return a {@code DialogBox} configured for a bot message
     */
    public static DialogBox getLennyDialog(String text, Image lennyImg) {
        DialogBox db = new DialogBox(text, lennyImg);
        if (db.getChildren().size() >= 2) {
            Node first = db.getChildren().remove(0);
            db.getChildren().add(first);
        }
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }
}

