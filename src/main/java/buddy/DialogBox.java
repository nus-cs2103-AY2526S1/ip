package buddy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        this.text = new Label(text);
        this.displayPicture = new ImageView(img);

        setupTextLabel();
        setupDisplayPicture();

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.displayPicture);
        this.setSpacing(10.0);
        this.setPadding(new Insets(5, 10, 5, 10));
    }

    private void setupTextLabel() {
        this.text.setWrapText(true);
        this.text.setMaxWidth(280);
        this.text.setMinHeight(30);
        this.text.setFont(Font.font("System", FontWeight.NORMAL, 13));
        this.text.setStyle("-fx-background-color: #E8F4FD; -fx-background-radius: 15; "
                        + "-fx-padding: 12; -fx-border-radius: 15; -fx-border-width: 1; "
                        + "-fx-border-color: #CCE7F0; -fx-text-fill: #2C3E50;");
    }

    private void setupDisplayPicture() {
        this.displayPicture.setFitWidth(40.0);
        this.displayPicture.setFitHeight(40.0);
        this.displayPicture.setPreserveRatio(true);
        this.displayPicture.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0.3, 0, 1);");
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.text.setStyle("-fx-background-color: #DCF8C6; -fx-background-radius: 15; "
                       + "-fx-padding: 12; -fx-border-radius: 15; -fx-border-width: 1; "
                       + "-fx-border-color: #A8E063; -fx-text-fill: #2C3E50; "
                       + "-fx-font-weight: normal;");
        return db;
    }

    public static DialogBox getBuddyDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();

        if (isErrorMessage(text)) {
            db.text.setStyle("-fx-background-color: #FFEBEE; -fx-background-radius: 15; "
                           + "-fx-padding: 12; -fx-border-radius: 15; -fx-border-width: 2; "
                           + "-fx-border-color: #F44336; -fx-text-fill: #C62828; "
                           + "-fx-font-weight: bold;");
        } else {
            db.text.setStyle("-fx-background-color: #F5F5F5; -fx-background-radius: 15; "
                           + "-fx-padding: 12; -fx-border-radius: 15; -fx-border-width: 1; "
                           + "-fx-border-color: #E0E0E0; -fx-text-fill: #37474F; "
                           + "-fx-font-weight: normal;");
        }

        return db;
    }

    private static boolean isErrorMessage(String text) {
        return text.toLowerCase().contains("sorry") ||
               text.toLowerCase().contains("error") ||
               text.toLowerCase().contains("invalid") ||
               text.toLowerCase().contains("cannot") ||
               text.toLowerCase().contains("missing") ||
               text.contains(":-");
    }
}