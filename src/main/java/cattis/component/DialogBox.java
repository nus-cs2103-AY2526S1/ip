package cattis.component;

import java.io.IOException;
import java.util.Collections;

import cattis.window.StartWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box for chatbot
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    private DialogBox(String text) {
        try {
            dialog = new Label(text);
            FXMLLoader fxmlLoader = new FXMLLoader(
                    StartWindow.class.getResource("/view/component/Dialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
    }

    /**
     * Create Response Dialog box
     */
    private void createResponseDialogBox(boolean isError) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        if (!isError) {
            dialog.setStyle("-fx-background-color: #2c2d38;");
        } else {
            dialog.setStyle("-fx-background-color: #4a211e;");
        }
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text);
    }

    public static DialogBox getCattisDialog(String text, boolean isError) {
        var db = new DialogBox(text);
        db.createResponseDialogBox(isError);
        return db;
    }
}
