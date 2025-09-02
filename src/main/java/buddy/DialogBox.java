package buddy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;

    private DialogBox(String text) {
        this.text = new Label(text);
        this.text.setWrapText(true);
        this.text.setMaxWidth(300);
        this.text.setStyle("-fx-background-color: #E6E6FA; -fx-background-radius: 10; -fx-padding: 10;");

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().add(this.text);
        this.setSpacing(5.0);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text) {
        DialogBox db = new DialogBox(text);
        db.text.setStyle("-fx-background-color: #ADD8E6; -fx-background-radius: 10; -fx-padding: 10;");
        return db;
    }

    public static DialogBox getBuddyDialog(String text) {
        DialogBox db = new DialogBox(text);
        db.flip();
        return db;
    }
}