import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

/**
 * DialogBox control for chat bubbles.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) { // Assisted by ChatGPT to obtain the desired layout
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Clip avatar into a circle
        double radius = 20; // avatar assumed 40x40 in FXML
        Circle clip = new Circle(radius, radius, radius);
        displayPicture.setClip(clip);

        // ✅ Bubble auto-sizes like Telegram
        dialog.setWrapText(true);
        dialog.setMaxWidth(280); // max width before wrapping
        dialog.setMinHeight(Region.USE_PREF_SIZE);

        // spacing between avatar and bubble
        this.setSpacing(10);
    }

    /** Creates a dialog box for user messages (right aligned, blue bubble). */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);

        // Bubble first, then avatar
        db.getChildren().setAll(db.dialog, db.displayPicture);

        db.dialog.setStyle("-fx-background-color: #0084ff; -fx-text-fill: white; "
                + "-fx-padding: 10; -fx-background-radius: 15; -fx-font-size: 14;");
        HBox.setMargin(db.dialog, new Insets(0, 5, 0, 0));
        return db;
    }

    /** Creates a dialog box for Joe messages (left aligned, gray bubble). */
    public static DialogBox getJoeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_LEFT);

        // Avatar first, then bubble
        db.getChildren().setAll(db.displayPicture, db.dialog);

        db.dialog.setStyle("-fx-background-color: #e5e5ea; -fx-text-fill: black; "
                + "-fx-padding: 10; -fx-background-radius: 15; -fx-font-size: 14;");
        HBox.setMargin(db.dialog, new Insets(0, 0, 0, 5));
        return db;
    }
}
