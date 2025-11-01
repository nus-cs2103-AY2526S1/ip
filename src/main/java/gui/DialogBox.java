package gui;

import gui.MainWindow;

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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        setOrReplaceStyleProperty(db.dialog, "-fx-background-color", "#FF7043");
        setOrReplaceStyleProperty(db.dialog, "-fx-text-fill", "FFFFFF");
        
        return db;
    }
    /**
     * Sets or replaces a CSS style property for a given JavaFX node.
     *
     * @param node     The JavaFX node to modify.
     * @param property The CSS property to set or replace (e.g., "-fx-background-color").
     * @param value    The value to assign to the CSS property (e.g., "red").
     */
    public static void setOrReplaceStyleProperty(javafx.scene.Node node, String property, String value) {
        String style = node.getStyle();
        String regex = property + ":\\s*[^;]+;";
        String newProp = property + ": " + value + ";";
        if (style == null) style = "";
        if (style.contains(property + ":")) {
            style = style.replaceAll(regex, newProp);
        } else {
            style = style.trim() + " " + newProp;
        }
        node.setStyle(style);
    }

}
