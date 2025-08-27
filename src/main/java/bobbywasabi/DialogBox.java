package bobbywasabi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

public class DialogContainer extends HBox {
    private Image image;
    private String text;

    public DialogContainer(Image image, String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DialogContainer.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.image = image;
        this.text = text;
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogContainer getUserDialog(Image image, String text) {
        return new DialogContainer(image, text);
    }

    public static DialogContainer getBobbyWasabiDialog(Image image, String text) {
        var db = new DialogContainer(image, text);
        db.flip();
        return db;
    }

}
