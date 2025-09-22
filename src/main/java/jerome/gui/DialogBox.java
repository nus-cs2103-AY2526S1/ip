package jerome.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * A JavaFX control that represents a chat dialog box, consisting of a text label and a circular profile image.
 * The dialog box layout is defined via FXML, and this class acts as both the root and controller using fx:root.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor that loads the DialogBox layout from FXML and sets the text and image.
     *
     * @param text The message text to display in the dialog box.
     * @param img  The profile image to display.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            dialog.maxWidthProperty().bind(
                    javafx.beans.binding.Bindings.max(
                            this.widthProperty().multiply(0.7),
                            400.0
                    )
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        dialog.setText(text);
        HBox.setHgrow(dialog, javafx.scene.layout.Priority.ALWAYS);
        displayPicture.setImage(img);
        //Set images to be circles
        double radius = 30;
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);
    }

    /**
     * Creates a DialogBox representing the user's input.
     * Text appears on the left, and image appears on the right.
     *
     * @param text The user's message text.
     * @param img  The user's profile image.
     * @return A DialogBox formatted for user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.getChildren().setAll(db.dialog, db.displayPicture);
        return db;
    }

    /**
     * Creates a DialogBox representing the chatbot's response.
     * Image appears on the left, and text appears on the right.
     *
     * @param text The chatbot's response text.
     * @param img  The chatbot's profile image.
     * @return A DialogBox formatted for bot output.
     */
    public static DialogBox getJeromeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_LEFT);
        db.getChildren().setAll(db.displayPicture, db.dialog);
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_LEFT);
        db.getChildren().setAll(db.displayPicture, db.dialog);

        // Apply error style
        db.dialog.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-background-color: #ffeeee; -fx-padding: 8; -fx-background-radius: 10;");
        return db;
    }

}



