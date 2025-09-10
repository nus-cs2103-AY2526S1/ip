package bobbywasabi;

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

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    public DialogBox(Image image, String text) {
        assert image != null
                : "Image in DialogBox is null!";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);

    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        dialog.getStyleClass().add("reply-label");
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    private void changeDialogStyle(String commandType) {
        System.out.println(commandType);
        switch(commandType) {
        case "TODO":
            dialog.getStyleClass().add("add-label");
            break;
        case "EVENT":
            dialog.getStyleClass().add("add-label");
            break;
        case "DEADLINE":
            dialog.getStyleClass().add("add-label");
            break;
        case "MARK":
            dialog.getStyleClass().add("marked-label");
            break;
        case "UNMARK":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DELETE":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
        }
    }

    public static DialogBox getUserDialog(Image image, String text) {
        return new DialogBox(image, text);
    }

    public static DialogBox getBobbyWasabiDialog(Image image, String text, String commandType) {
        var db = new DialogBox(image, text);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }

}
