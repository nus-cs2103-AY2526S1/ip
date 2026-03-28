package cattis;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Manages JavaFX application window configuration and FXML loading.
 */
public class Configuration {

    /** Default window size ratio relative to screen */
    private static final double DEFAULT_RATIO = 0.5;

    /** Minimum valid ratio value */
    private static final double MIN_RATIO = 0.1;

    /** Maximum valid ratio value */
    private static final double MAX_RATIO = 1.0;

    private final String resource;
    private final double horizontalRatio;
    private final double verticalRatio;
    private FXMLLoader loader;
    private Scene scene;

    /**
     * Creates configuration with default window size (50% of screen).
     */
    public Configuration(String resource) {
        this(resource, DEFAULT_RATIO, DEFAULT_RATIO);
    }

    /**
     * Creates configuration with custom window size ratios.
     */
    public Configuration(String resource, double horizontalRatio, double verticalRatio) {
        this(resource, horizontalRatio, verticalRatio, null);
    }

    /**
     * Creates configuration with custom ratios and pre-existing loader.
     */
    public Configuration(String resource, double horizontalRatio,
                         double verticalRatio, FXMLLoader loader) {
        this.resource = validateResource(resource);
        this.horizontalRatio = validateRatio(horizontalRatio, "horizontalRatio");
        this.verticalRatio = validateRatio(verticalRatio, "verticalRatio");
        this.loader = loader;
    }

    /**
     * Initializes the FXML loader if not already created.
     */
    public void initializeLoader() throws IOException {
        if (this.loader == null) {
            this.loader = createLoader();
        }
    }

    /**
     * Applies configuration to the stage (size, position, scene).
     */
    public void loadConfiguration(Stage stage) throws IOException {
        validateStage(stage);

        Rectangle2D screenBounds = getScreenBounds();
        configureStageSize(stage, screenBounds);
        centerStageOnScreen(stage, screenBounds);
        stage.setScene(getOrCreateScene());
    }

    /**
     * Gets the FXML loader, creating it if necessary.
     */
    public FXMLLoader getLoader() throws IOException {
        if (this.loader == null) {
            initializeLoader();
        }
        return this.loader;
    }

    /**
     * Gets the current scene instance.
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Gets the resource path.
     */
    public String getResource() {
        return this.resource;
    }

    /**
     * Gets the horizontal ratio.
     */
    public double getHorizontalRatio() {
        return this.horizontalRatio;
    }

    /**
     * Gets the vertical ratio.
     */
    public double getVerticalRatio() {
        return this.verticalRatio;
    }

    /**
     * Validates resource path is not null or empty.
     */
    private String validateResource(String resource) {
        if (resource == null || resource.trim().isEmpty()) {
            throw new IllegalArgumentException("Resource path cannot be null or empty");
        }
        return resource;
    }

    /**
     * Validates ratio is within acceptable bounds.
     */
    private double validateRatio(double ratio, String paramName) {
        if (ratio < MIN_RATIO || ratio > MAX_RATIO) {
            throw new IllegalArgumentException(
                    paramName + " must be between " + MIN_RATIO + " and " + MAX_RATIO);
        }
        return ratio;
    }

    /**
     * Validates stage is not null.
     */
    private void validateStage(Stage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
    }

    /**
     * Creates a new FXML loader for the resource.
     */
    private FXMLLoader createLoader() throws IOException {
        URL resourceUrl = getClass().getResource(resource);
        if (resourceUrl == null) {
            throw new IOException("FXML resource not found: " + resource);
        }
        return new FXMLLoader(resourceUrl);
    }

    /**
     * Gets or creates the scene from the FXML loader.
     */
    private Scene getOrCreateScene() throws IOException {
        if (this.scene == null) {
            initializeLoader();
            this.scene = new Scene(this.loader.load());
        }
        return this.scene;
    }

    /**
     * Gets the primary screen bounds.
     */
    private Rectangle2D getScreenBounds() {
        return Screen.getPrimary().getVisualBounds();
    }

    /**
     * Configures stage size based on screen bounds and ratios.
     */
    private void configureStageSize(Stage stage, Rectangle2D screenBounds) {
        double targetWidth = screenBounds.getWidth() * this.horizontalRatio;
        double targetHeight = screenBounds.getHeight() * this.verticalRatio;

        stage.setWidth(targetWidth);
        stage.setHeight(targetHeight);
    }

    /**
     * Centers the stage on screen.
     */
    private void centerStageOnScreen(Stage stage, Rectangle2D screenBounds) {
        double centerX = (screenBounds.getWidth() - stage.getWidth()) / 2;
        double centerY = (screenBounds.getHeight() - stage.getHeight()) / 2;

        stage.setX(centerX);
        stage.setY(centerY);
    }
}
