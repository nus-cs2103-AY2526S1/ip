package v.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Manages cycling background images with seamless transitions.
 */
public class BackgroundManager {
    private final AnchorPane rootPane;
    private final List<String> backgroundImages;
    private final List<String> randomBackgrounds;
    private int currentIndex = 0;
    private Timeline backgroundCycle;
    private Random random = new Random();
    private ImageView backgroundView;
    /**
     * Constructs a BackgroundManager for the given root pane.
     *
     * @param rootPane the AnchorPane to apply background images to
     */
    public BackgroundManager(AnchorPane rootPane) {
        this.rootPane = rootPane;
        this.backgroundImages = new ArrayList<>();
        this.randomBackgrounds = new ArrayList<>();

        // Set dark background immediately to prevent white flashes
        rootPane.setStyle("-fx-background-color: #0a0a0a;");
        // Create background ImageView as the first child
        backgroundView = new ImageView();
        backgroundView.setPreserveRatio(false); // Fill entire area, don't preserve ratio
        backgroundView.setSmooth(true);
        backgroundView.setCache(true);
        backgroundView.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundView.fitHeightProperty().bind(rootPane.heightProperty());
        // Ensure ImageView is positioned correctly
        AnchorPane.setTopAnchor(backgroundView, 0.0);
        AnchorPane.setBottomAnchor(backgroundView, 0.0);
        AnchorPane.setLeftAnchor(backgroundView, 0.0);
        AnchorPane.setRightAnchor(backgroundView, 0.0);
        rootPane.getChildren().add(0, backgroundView);
        initializeBackgroundImages();
        startBackgroundCycle();
    }
    private void initializeBackgroundImages() {
        // Add all background images in order - starting with background6 as requested
        backgroundImages.add("/images/background6.jpg");
        backgroundImages.add("/images/background1.webp");
        backgroundImages.add("/images/background2.webp");
        backgroundImages.add("/images/background3.jpg");
        backgroundImages.add("/images/background4.jpg");
        backgroundImages.add("/images/background5.jpg");

        // Create list of random backgrounds (excluding background6)
        randomBackgrounds.add("/images/background1.webp");
        randomBackgrounds.add("/images/background2.webp");
        randomBackgrounds.add("/images/background3.jpg");
        randomBackgrounds.add("/images/background4.jpg");
        randomBackgrounds.add("/images/background5.jpg");
    }
    private void startBackgroundCycle() {
        // Set initial background to background6
        setBackground(backgroundImages.get(0));
        // No automatic cycling - backgrounds will change on scroll/new messages
    }
    private void setBackground(String imagePath) {
        try {
            java.net.URL url = BackgroundManager.class.getResource(imagePath);
            if (url == null) {
                System.err.println("Failed to resolve background image URL: " + imagePath);
                // Set dark fallback to prevent white flashes
                rootPane.setStyle("-fx-background-color: #0a0a0a;");
                return;
            }

            // Load image with proper sizing for fullscreen
            Image backgroundImage = new Image(url.toExternalForm(),
                rootPane.getWidth() > 0 ? rootPane.getWidth() : 800,
                rootPane.getHeight() > 0 ? rootPane.getHeight() : 600,
                false, true, true); // Don't preserve ratio, use background loading
            // Set ImageView - this will stretch to fill the entire pane
            backgroundView.setImage(backgroundImage);
            // Keep dark background as fallback in case ImageView fails
            rootPane.setStyle("-fx-background-color: #0a0a0a;");
            System.out.println("Background set to: " + imagePath);
        } catch (Exception e) {
            System.err.println("Failed to load background image: " + imagePath + " - " + e.getMessage());
            // Fallback to dark background
            rootPane.setStyle("-fx-background-color: #0a0a0a;");
        }
    }
    /**
     * Stops the background cycling animation.
     */
    public void stop() {
        if (backgroundCycle != null) {
            backgroundCycle.stop();
        }
    }
}
