package v.ui;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Manages dramatic animations for the V for Vendetta GUI.
 */
public class AnimationManager {
    /**
     * Creates a dramatic fade-in animation for dialog boxes.
     *
     * @param node The node to animate.
     * @return The fade transition.
     */
    public static FadeTransition createFadeInAnimation(Node node) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), node);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        return fadeIn;
    }
    /**
     * Creates a dramatic scale animation for dialog boxes.
     *
     * @param node The node to animate.
     * @return The scale transition.
     */
    public static ScaleTransition createScaleAnimation(Node node) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(600), node);
        scale.setFromX(0.8);
        scale.setFromY(0.8);
        scale.setToX(1.0);
        scale.setToY(1.0);
        return scale;
    }
    /**
     * Creates a dramatic entrance animation combining fade and scale.
     *
     * @param node The node to animate.
     * @return The timeline containing both animations.
     */
    public static Timeline createDramaticEntrance(Node node) {
        Timeline timeline = new Timeline();
        // Set initial state
        node.setOpacity(0.0);
        node.setScaleX(0.8);
        node.setScaleY(0.8);
        // Create animations
        FadeTransition fadeIn = createFadeInAnimation(node);
        ScaleTransition scale = createScaleAnimation(node);
        // Add to timeline
        timeline.getKeyFrames().addAll(
            new javafx.animation.KeyFrame(Duration.ZERO, e -> {
                node.setOpacity(0.0);
                node.setScaleX(0.8);
                node.setScaleY(0.8);
            }),
            new javafx.animation.KeyFrame(Duration.millis(400), e -> {
                fadeIn.play();
                scale.play();
            })
        );
        return timeline;
    }
    /**
     * Creates a subtle hover animation for buttons.
     *
     * @param node The node to animate.
     * @return The scale transition.
     */
    public static ScaleTransition createHoverAnimation(Node node) {
        ScaleTransition hover = new ScaleTransition(Duration.millis(200), node);
        hover.setFromX(1.0);
        hover.setFromY(1.0);
        hover.setToX(1.05);
        hover.setToY(1.05);
        return hover;
    }
    /**
     * Creates a dramatic exit animation for the goodbye sequence.
     *
     * @param node The node to animate.
     * @return The fade transition.
     */
    public static FadeTransition createExitAnimation(Node node) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), node);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        return fadeOut;
    }
}
