package borat.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private final Label text;
    private final ImageView displayPicture;

    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);
        displayPicture.setFitWidth(40);
        displayPicture.setFitHeight(40);
        displayPicture.setPreserveRatio(true);
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(8);
        this.getChildren().addAll(text, displayPicture);
    }
}


