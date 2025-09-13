package bobbywasabi;

import java.io.IOException;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Represents a single dialog box in the UI, showing a profile image and text.
 * <p>
 * Can represent either the user's message or the bot's message. The dialog box
 * supports styling based on command type and can play animations (e.g., shake and flash)
 * for error commands.
 */
public class DialogBox extends HBox {
    @FXML
    private Circle profileCircle;

    @FXML
    private Label dialog;

    /**
     * Constructs a DialogBox with a profile image and message text.
     *
     * @param image The profile image for the dialog. Must not be null.
     * @param text  The text to display in the dialog.
     */
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
        profileCircle.setFill(new ImagePattern(image));

    }

    /**
     * Flips the dialog box horizontally, reversing the order of the profile image and text.
     * <p>
     * Used to differentiate bot messages from user messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        dialog.getStyleClass().add("reply-label");
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Changes the style of the dialog text based on the command type.
     * <p>
     * Adds different CSS classes for add, delete, mark/unmark, and error messages.
     *
     * @param commandType The command type corresponding to the dialog message.
     */
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "ADDCLIENT":
        case "TODO":
        case "EVENT":
        case "DEADLINE":
            dialog.getStyleClass().add("add-label");
            break;
        case "MARK":
        case "UNMARK":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DELETE":
        case "DELETECLIENT":
            dialog.getStyleClass().add("delete-label");
            break;
        case "OTHERS":
            dialog.getStyleClass().add("error-label");
            break;
        default:
            break;
        }
    }

    /**
     * Activates an error animation on the profile image if the command is invalid.
     * <p>
     * The animation combines a horizontal shake and a red flash border effect.
     *
     * @param commandType The command type of the dialog. Only triggers if the command is "OTHERS".
     */
    public void activateProfileErrorAnimation(String commandType) {
        if (!commandType.equals("OTHERS")) {
            return;
        }

        Double translateXDistance = 1.0;

        Color flashColor = Color.RED;
        Double borderWidthChange = 2.0;
        Color borderColor = (Color) profileCircle.getStroke();
        Double borderWidth = profileCircle.getStrokeWidth();

        Timeline shaketimeline = createShakeTimeline(translateXDistance);
        Timeline flashTimeline = createFlashTimeline(borderColor, flashColor,
                borderWidth, borderWidthChange);

        flashTimeline.play();
        shaketimeline.play();
    }

    /**
     * Creates a timeline that produces a horizontal "shake" animation
     * on the profile image circle. The shake effect moves the image
     * left and right several times in quick succession to visually
     * indicate an error or invalid command.
     *
     * @param translateXDistance The horizontal distance in pixels to
     *                           shift the profile image during the shake.
     * @return A {@link Timeline} configured with the shake animation.
     */
    public Timeline createShakeTimeline(double translateXDistance) {

        return new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(profileCircle.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(50), new KeyValue(
                        profileCircle.translateXProperty(), -translateXDistance)),
                new KeyFrame(Duration.millis(100), new KeyValue(
                        profileCircle.translateXProperty(), translateXDistance)),
                new KeyFrame(Duration.millis(150), new KeyValue(
                        profileCircle.translateXProperty(), -translateXDistance)),
                new KeyFrame(Duration.millis(200), new KeyValue(
                        profileCircle.translateXProperty(), translateXDistance)),
                new KeyFrame(Duration.millis(250), new KeyValue(
                        profileCircle.translateXProperty(), 0))
        );
    }

    /**
     * Creates a timeline that produces a flashing border animation
     * on the profile image circle. The flash briefly changes the
     * border color and width to highlight an error, before restoring
     * the original appearance.
     *
     * @param borderColor        The original border color of the profile image.
     * @param flashColor         The temporary color used during the flash effect.
     * @param borderWidth        The original border width of the profile image.
     * @param borderWidthChange  The border width to apply during the flash effect.
     * @return A {@link Timeline} configured with the flashing border animation.
     */
    public Timeline createFlashTimeline(Color borderColor, Color flashColor,
            Double borderWidth, Double borderWidthChange) {

        return new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(profileCircle.strokeProperty(), flashColor),
                        new KeyValue(profileCircle.strokeWidthProperty(), borderWidthChange)
                ),
                new KeyFrame(Duration.millis(250),
                        new KeyValue(profileCircle.strokeProperty(), borderColor),
                        new KeyValue(profileCircle.strokeWidthProperty(), borderWidth)
                )
        );
    }

    /**
     * Creates a dialog box representing a user message.
     *
     * @param image The user's profile image.
     * @param text  The text of the user's message.
     * @return A {@link DialogBox} instance for the user.
     */
    public static DialogBox getUserDialog(Image image, String text) {
        return new DialogBox(image, text);
    }

    /**
     * Creates a dialog box representing a bot message.
     * <p>
     * The dialog box will be flipped to the left, styled according to the command type,
     * and an error animation will play if applicable.
     *
     * @param image       The bot's profile image.
     * @param text        The text of the bot's message.
     * @param commandType The type of command this message is responding to.
     * @return A {@link DialogBox} instance for the bot.
     */
    public static DialogBox getBobbyWasabiDialog(Image image, String text, String commandType) {
        var db = new DialogBox(image, text);
        db.flip();
        db.activateProfileErrorAnimation(commandType);
        db.changeDialogStyle(commandType);
        return db;
    }

}
