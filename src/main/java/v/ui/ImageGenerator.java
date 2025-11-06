package v.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Simple image generator for creating placeholder avatars.
 */
public class ImageGenerator {

    /**
     * Creates a simple user avatar image.
     */
    public static Image createUserImage() {
        return createAvatarImage("U", Color.LIGHTBLUE);
    }

    /**
     * Creates a simple V avatar image.
     */
    public static Image createVImage() {
        return createAvatarImage("V", Color.DARKRED);
    }

    /**
     * Creates a simple avatar image with text and background color.
     */
    private static Image createAvatarImage(String text, Color backgroundColor) {
        int width = 100;
        int height = 100;

        WritableImage image = new WritableImage(width, height);
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Fill background
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, width, height);

        // Draw text
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 24));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(text, width / 2, height / 2 + 8);

        // Draw the canvas to the image
        canvas.snapshot(null, image);

        return image;
    }
}
