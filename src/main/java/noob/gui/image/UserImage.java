package noob.gui.image;

import javafx.scene.image.Image;

public class UserImage extends Image {
    private static final String imagePath = "/images/laugh.jpg";

    public UserImage() {
        super(UserImage.class.getResourceAsStream(imagePath));
    }
}
