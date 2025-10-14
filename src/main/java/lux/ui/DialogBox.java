package lux.ui;

import java.io.IOException;
import java.util.Collections;

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

/**
 * Reusable UI component representing a chat bubble used in the JavaFX UI.
 *
 * <p>DialogBox is backed by an FXML layout and provides factory methods to
 * create user and Lux dialog instances.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Improve spacing and visual appearance via CSS classes
        dialog.getStyleClass().add("dialog-label");
        // Default bubble style (user vs lux will add specific classes where used)
        // Add image-view class to allow drop shadow and consistent sizing from CSS
        displayPicture.getStyleClass().add("image-view");

        // - Limit the width of the dialog text so it wraps at a comfortable line length.
        //   Bind to a fraction of the parent width when available.
        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            double max = newVal.doubleValue() * 0.6; // allow bubble up to 60% of dialog box width
            dialog.setMaxWidth(max);
        });

        // Make avatar circular by applying a clip that reacts to fitWidth/fitHeight
        var clip = new javafx.scene.shape.Circle();
        // Bind center and radius so the clip always matches the ImageView size
        clip.centerXProperty().bind(displayPicture.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(displayPicture.fitHeightProperty().divide(2));
        clip.radiusProperty().bind(
            javafx.beans.binding.Bindings.min(displayPicture.fitWidthProperty(),
            displayPicture.fitHeightProperty()).divide(2));
        displayPicture.setClip(clip);

        // Responsive avatar sizing: 60 when windowed, 100 when maximized.
        // We need access to the window, so wait until the DialogBox is attached to a scene.
        this.sceneProperty().addListener((obsScene, oldScene, newScene) -> {
            if (newScene == null) {
                return;
            }
            var window = newScene.getWindow();
            if (window == null) {
                return;
            }
            // The Window API doesn't expose maximizedProperty directly; cast to Stage
            if (window instanceof javafx.stage.Stage stage) {
                // Create a NumberBinding<Double> that evaluates to 100.0 when maximized, else 60.0
                javafx.beans.binding.NumberBinding sizeBinding = javafx.beans.binding.Bindings
                    .when(stage.maximizedProperty())
                    .then(100.0)
                    .otherwise(60.0);
                // Bind fitWidth/fitHeight so ImageView scales uniformly
                displayPicture.fitWidthProperty().bind(sizeBinding);
                displayPicture.fitHeightProperty().bind(sizeBinding);
            }
        });
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-bubble");
        return db;
    }

    public static DialogBox getLuxDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("lux-bubble");
        db.flip();
        return db;
    }
}
