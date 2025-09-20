package lyra;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * DialogBox class for displaying user and bot messages in the GUI.
 * Implements asymmetric conversation design with improved styling.
 */
public class DialogBox extends HBox {

    private Label text;
    private VBox messageContainer;

    public DialogBox(Label l, boolean isUser) {
        text = l;
        messageContainer = new VBox();
        
        // Configure text styling
        text.setWrapText(true);
        text.setFont(Font.font("Segoe UI", 14));
        text.setMinHeight(Label.USE_PREF_SIZE);
        text.setMaxWidth(400); // Increased max width for better space usage
        
        // Configure message container
        messageContainer.getChildren().add(text);
        messageContainer.setMaxWidth(400);
        
        // Set up layout based on user type
        if (isUser) {
            setupUserLayout();
        } else {
            setupBotLayout();
        }
    }

    private void setupUserLayout() {
        // User messages: right-aligned with blue styling
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setSpacing(8);
        this.setPadding(new javafx.geometry.Insets(8, 20, 8, 50));
        
        text.setStyle(
            "-fx-background-color: #0078d4; " +
            "-fx-background-radius: 18; " +
            "-fx-padding: 12 16; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: 500; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2, 0, 0, 1);"
        );
        
        this.getChildren().add(messageContainer);
    }

    private void setupBotLayout() {
        // Bot messages: left-aligned with light styling
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(8);
        this.setPadding(new javafx.geometry.Insets(8, 50, 8, 20));
        
        text.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-background-radius: 18; " +
            "-fx-padding: 12 16; " +
            "-fx-text-fill: #333333; " +
            "-fx-border-color: #e1e5e9; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 18; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 1, 0, 0, 1);"
        );
        
        this.getChildren().add(messageContainer);
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(new Label(text), true);
    }

    public static DialogBox getLyraDialog(String text) {
        return new DialogBox(new Label(text), false);
    }

    public static DialogBox getErrorDialog(String text) {
        DialogBox errorBox = new DialogBox(new Label(text), false);
        errorBox.text.setStyle(
            "-fx-background-color: #fff5f5; " +
            "-fx-background-radius: 18; " +
            "-fx-padding: 12 16; " +
            "-fx-text-fill: #dc2626; " +
            "-fx-border-color: #fecaca; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 18; " +
            "-fx-font-weight: 500; " +
            "-fx-effect: dropshadow(gaussian, rgba(220,38,38,0.1), 2, 0, 0, 1);"
        );
        return errorBox;
    }

    public static DialogBox getSuccessDialog(String text) {
        DialogBox successBox = new DialogBox(new Label(text), false);
        successBox.text.setStyle(
            "-fx-background-color: #f0fdf4; " +
            "-fx-background-radius: 18; " +
            "-fx-padding: 12 16; " +
            "-fx-text-fill: #16a34a; " +
            "-fx-border-color: #bbf7d0; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 18; " +
            "-fx-font-weight: 500; " +
            "-fx-effect: dropshadow(gaussian, rgba(22,163,74,0.1), 2, 0, 0, 1);"
        );
        return successBox;
    }
}
