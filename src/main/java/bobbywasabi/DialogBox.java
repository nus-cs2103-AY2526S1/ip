package bobbywasabi;

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

import java.io.IOException;
import java.util.Collections;


public class DialogBox extends HBox {
    @FXML
    private Circle profileCircle;

    @FXML
    private Label dialog;

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
        case "BYE":
            break;
        case "LIST":
            break;
        case "FIND":
            break;
        case "CLIENTS":
            break;
        case "EDITCLIENT":
            break;
        case "ADDCLIENT":
            dialog.getStyleClass().add("add-label");
            break;
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
        case "DELETECLIENT":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            dialog.getStyleClass().add("error-label");
            break;
        }
    }

    public void activateProfileErrorAnimation(String commandType) {
        if (!commandType.equals("OTHERS")) {
            return;
        }

        Double translateXDistance = 1.0;
        Color flashColor = Color.RED;
        Double borderWidthChange = 2.0;

        // change the border color to red then back to the original color to simulate a flash effect
        Color borderColor = (Color) profileCircle.getStroke();
        Double borderWidth = profileCircle.getStrokeWidth();

        Timeline shaketimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(profileCircle.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(50), new KeyValue(profileCircle.translateXProperty(), -translateXDistance)),
                new KeyFrame(Duration.millis(100), new KeyValue(profileCircle.translateXProperty(), translateXDistance)),
                new KeyFrame(Duration.millis(150), new KeyValue(profileCircle.translateXProperty(), -translateXDistance)),
                new KeyFrame(Duration.millis(200), new KeyValue(profileCircle.translateXProperty(), translateXDistance)),
                new KeyFrame(Duration.millis(250), new KeyValue(profileCircle.translateXProperty(), 0))
        );

        Timeline flashTimeline = new Timeline(
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
        flashTimeline.play();
        shaketimeline.play();
    }

    public static DialogBox getUserDialog(Image image, String text) {
        return new DialogBox(image, text);
    }

    public static DialogBox getBobbyWasabiDialog(Image image, String text, String commandType) {
        var db = new DialogBox(image, text);
        db.flip();
        db.activateProfileErrorAnimation(commandType);
        db.changeDialogStyle(commandType);
        return db;
    }

}
