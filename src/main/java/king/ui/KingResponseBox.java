package king.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import king.MainWindow;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class KingResponseBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private KingResponseBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/KingResponseBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        if (text.contains("Error")) {
            dialog.setTextFill(Paint.valueOf("#FF7F7F"));
        }

        displayPicture.setImage(img);
    }

    /**
     * Returns the King's dialog box
     *
     * @param text Text to show from the King
     * @param img  Image to use for the King
     * @return King dialog box
     */
    public static KingResponseBox getKingDialog(String text, Image img) {
        return new KingResponseBox(text, img);
    }
}
