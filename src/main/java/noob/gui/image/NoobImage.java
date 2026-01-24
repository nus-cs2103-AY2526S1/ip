package noob.gui.image;

import javafx.scene.image.Image;

public class NoobImage extends Image {
    private static final String imagePath = "/images/logo.jpg";

    public NoobImage() {
        super(NoobImage.class.getResourceAsStream(imagePath));
    }
}
