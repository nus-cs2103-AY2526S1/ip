package stewie.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import stewie.command.CommandType;

/**
 * Enhanced dialog box with smooth animations and better visual feedback
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/stewie/gui/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        // Add entrance animation
        addEntranceAnimation();
    }

    /**
     * Adds a smooth entrance animation to the dialog box
     */
    private void addEntranceAnimation() {
        // Start invisible and scaled down
        setOpacity(0);
        setScaleX(0.8);
        setScaleY(0.8);

        // Fade in animation
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), this);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        // Scale up animation
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), this);
        scaleUp.setFromX(0.8);
        scaleUp.setFromY(0.8);
        scaleUp.setToX(1.0);
        scaleUp.setToY(1.0);

        // Play animations together
        fadeIn.play();
        scaleUp.play();
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.dialog.getStyleClass().add("user-label");
        return dialogBox;
    }

    /**
     * Enhanced method to change dialog style with better visual feedback
     */
    private void changeDialogStyle(CommandType commandType) {
        // Remove any existing command-specific styles
        dialog.getStyleClass().removeIf(styleClass ->
                styleClass.equals("add-label")
                        || styleClass.equals("marked-label")
                        || styleClass.equals("delete-label")
                        || styleClass.equals("error-label")
                        || styleClass.equals("success-label"));

        switch (commandType) {
        case DEADLINE:
        case EVENT:
        case TODO:
            dialog.getStyleClass().add("add-label");
            addPulseAnimation();
            break;
        case MARK:
        case UNMARK:
            dialog.getStyleClass().add("marked-label");
            addSuccessAnimation();
            break;
        case DELETE:
            dialog.getStyleClass().add("delete-label");
            addShakeAnimation();
            break;
        case LIST:
        case FIND:
            dialog.getStyleClass().add("info-label");
            break;
        default:
            dialog.getStyleClass().add("default-label");
        }
    }

    /**
     * Adds a subtle pulse animation for success actions
     */
    private void addPulseAnimation() {
        ScaleTransition pulse = new ScaleTransition(Duration.millis(200), dialog);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setToX(1.05);
        pulse.setToY(1.05);
        pulse.setCycleCount(2);
        pulse.setAutoReverse(true);
        pulse.play();
    }

    /**
     * Adds a success animation
     */
    private void addSuccessAnimation() {
        ScaleTransition scale = new ScaleTransition(Duration.millis(150), this);
        scale.setFromX(1.0);
        scale.setToX(1.02);
        scale.setCycleCount(2);
        scale.setAutoReverse(true);
        scale.play();
    }

    /**
     * Adds a subtle shake animation for delete actions
     */
    private void addShakeAnimation() {
        Timeline timeline = new Timeline();
        // Simple shake effect by slightly moving the dialog
        for (int i = 0; i < 6; i++) {
            final int index = i;
            timeline.getKeyFrames().add(new javafx.animation.KeyFrame(
                    Duration.millis(50 * i),
                    e -> setTranslateX((index % 2 == 0) ? 2 : -2)
            ));
        }
        timeline.getKeyFrames().add(new javafx.animation.KeyFrame(
                Duration.millis(300),
                e -> setTranslateX(0)
        ));
        timeline.play();
    }

    public static DialogBox getStewieDialog(String s, Image i, CommandType commandType) {
        var db = new DialogBox(s, i);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
