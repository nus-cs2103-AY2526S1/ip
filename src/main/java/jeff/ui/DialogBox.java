package jeff.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String s, Image i) {
        text = new Label(s);
        text.setWrapText(true);
        displayPicture = new ImageView(i);
        displayPicture.setFitWidth(40);
        displayPicture.setFitHeight(40);
        this.setSpacing(10);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }
}
