package jooh.gui;

import java.io.IOException;
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
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxml = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            fxml.setController(this);
            fxml.setRoot(this);
            fxml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        if (isUser) {
            // User: no profile pic, right aligned, green bubble
            displayPicture.setVisible(false);
            displayPicture.setManaged(false);

            dialog.setStyle("-fx-background-color: #DCF8C6; " +
                    "-fx-padding: 8; " +
                    "-fx-background-radius: 10; " +
                    "-fx-font-size: 13px;");
            setAlignment(Pos.TOP_RIGHT);

        } else {
            // Jooh: small circular profile pic, left aligned, grey bubble
            displayPicture.setImage(img);
            displayPicture.setClip(new Circle(20, 20, 20)); // crop to circle

            dialog.setStyle("-fx-background-color: #ECECEC; " +
                    "-fx-padding: 8; " +
                    "-fx-background-radius: 10; " +
                    "-fx-font-size: 13px;");
            setAlignment(Pos.TOP_LEFT);
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    public static DialogBox getJoohDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }
}

