package gokschat;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox{
    private Label text;
    private ImageView displayPicture;


    /**
     *
     *
     * @param message
     * @param profileImage
     */
    public DialogBox(String message, Image profileImage) {
        text = new Label(message);
        displayPicture = new ImageView(profileImage);

        //Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, displayPicture);
    }
}
