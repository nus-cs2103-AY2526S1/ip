package nacho.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Dialog UI With Profile Picture and Text
 */
public class DialogBox extends HBox {

    private Label text;
    private Circle displayPicture;

    /**
     * Constructor for Dialog Box
     * @param s Text to be shown on UI
     * @param i Profile Image to show
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new Circle(20);
        displayPicture.setFill(new ImagePattern(i));

        // Set CSS Class
        text.getStyleClass().add("dialog_text");
        displayPicture.getStyleClass().add("dialog_picture");

        //Styling the dialog box
        text.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        this.setSpacing(15);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    private Label getTextNode() {
        return text;
    }

    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox uDB = new DialogBox(s, i);
        uDB.getTextNode().getStyleClass().add("userDB");
        return uDB;
    }

    public static DialogBox getNachoDialog(String s, Image i, boolean isError) {
        var db = new DialogBox(s, i);
        db.getTextNode().getStyleClass().add("nachoDB");
        if (isError) {
            db.getTextNode().getStyleClass().add("errorDB");
        }
        db.flip();
        return db;
    }
}
