package burrito;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.time.LocalDate;


import java.io.IOException;
import java.util.Collections;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox dialogWrapper;
    @FXML
    private HBox nameWrapper;


    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Circle clip = new Circle(img.getWidth() / 2, img.getHeight() / 2, img.getHeight() / 2);
        ImageView imageView = new ImageView(img);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        Image img2 =  imageView.snapshot(parameters, null);

        LocalDate myObj = LocalDate.now();

        name.getStyleClass().add("label-name");
        date.getStyleClass().add("label-date");
        name.setText(myObj + "");
        date.setText("You");
        dialog.setText(text);
        displayPicture.setImage(img2);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        LocalDate myObj = LocalDate.now();
        name.getStyleClass().remove("label-name");
        date.getStyleClass().remove("label-date");
        name.getStyleClass().add("label-date");
        date.getStyleClass().add("label-name");
        name.setText("Burrito");
        date.setText("" + myObj);


        dialogWrapper.setAlignment(Pos.TOP_LEFT);
        nameWrapper.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getBurritoDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
