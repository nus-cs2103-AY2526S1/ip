package haru.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Abstract chat message component with text and avatar, aligned left or right.
 */
public abstract class Message extends HBox {

    /**
     * Constructs a message bubble with text and an avatar image, aligned left or right.
     *
     * @param text          the message text
     * @param avatar        the avatar image
     * @param isLeftAligned true for left alignment (e.g. Haru), false for right (e.g. user)
     */
    public Message(String text, Image avatar, boolean isLeftAligned) {
        Label label = new Label(text);
        label.setWrapText(true);

        ImageView imageView = new ImageView(avatar);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        if (isLeftAligned) {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(imageView, label);
        } else {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(label, imageView);
        }
    }
}
