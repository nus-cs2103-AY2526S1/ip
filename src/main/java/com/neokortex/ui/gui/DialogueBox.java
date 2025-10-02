package com.neokortex.ui.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * Represents a DialogueBox, or a message element in the Graphical Interface.
 *
 * <p>Each dialogue box consists of:</p>
 * <ul>
 *     <li>{@link #dialogue}: the message contained within the DialogueBox</li>
 *     <li>{@link #imageContainer}: contains the profilePicture corresponding to the part sending the message</li>
 *     <li>{@link #displayPicture}: the display image used by the messenger</li>
 *     <li>{@link #messageBox}: the element that contains the message</li>
 * </ul>
 *
 * <p>
 * <b>Note: displayPicture can be null</b>
 * </p>
 *
 * <p><b>Credit:</b></p>
 * <p>
 * This code and associated FXML heavily references the JavaFX tutorial, without which
 * I would not have been able to write this class. The tutorial can be found here:
 * <ul><li>https://se-education.org/guides/tutorials/javaFx.html</li></ul>
 * </p>
 *
 * <p>
 * Furthermore, this code was written under partial guidance from generative AI.
 * </p>
 */
public class DialogueBox extends HBox {
    private static final String RESPONSE_COLOR = "#DAE5E3";

    @FXML
    private Label dialogue;
    @FXML
    private Pane imageContainer;
    @FXML
    private ImageView displayPicture;
    @FXML
    private StackPane messageBox;

    /**
     * Constructs a {@code DialogueBoc} with the given text and profile picture
     *
     * @param text the message to show
     * @param profilePicture the profilePicture of the messenger (can be null)
     */
    public DialogueBox(String text, Image profilePicture) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogue.setText(text);

        if (profilePicture == null) {
            this.getChildren().remove(displayPicture);
        } else {
            this.displayPicture.setImage(profilePicture);

            Circle clip = new Circle();
            clip.radiusProperty().bind(this.imageContainer.widthProperty().divide(2));
            clip.centerXProperty().bind(this.imageContainer.widthProperty().divide(2));
            clip.centerYProperty().bind(this.imageContainer.heightProperty().divide(2));

            this.displayPicture.setClip(clip);
        }
    }

    /**
     * Constructs a {@code DialogueBox} with the given text. Does not take in a profile picture
     * as profile pictures are <b>optional</b>. In the event that {@code FXML} cannot be loaded,
     * print the error to console.
     *
     * @param text the message to show
     */
    public DialogueBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogue.setText(text);
        this.getChildren().remove(displayPicture);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    private void setColor(String color) {
        messageBox.setStyle(String.format("-fx-background-color: %s; -fx-background-radius: 20px;", color));
    }

    /**
     * Factory method that creates a new sender dialogue box, which is the dialogue box from the user's perspective.
     * Constructs a {@code DialogueBox} with the given text and profile picture.
     *
     * <p><b>Note: the DialogueBox must be manually attached.</b></p>
     *
     * @param text the message to show
     * @param profilePicture the profilePicture of the sender (can be null)
     * @return the constructed {@code DialogueBox}
     */
    public static DialogueBox createSenderDialogueBox(String text, Image profilePicture) {
        return new DialogueBox(text, profilePicture);
    }

    /**
     * Factory method that creates a new response dialogue box, which is the dialogue box from the chatbot's
     * perspective. Constructs a {@code DialogueBox} with the given text and profile picture.
     *
     * <p><b>Note: the DialogueBox must be manually attached.</b></p>
     *
     * @param text the message to show
     * @param profilePicture the profilePicture of the responder (can be null)
     * @return the constructed {@code DialogueBox}
     */
    public static DialogueBox createResponseDialogueBox(String text, Image profilePicture) {
        DialogueBox db = new DialogueBox(text, profilePicture);
        db.flip();
        db.setColor(RESPONSE_COLOR);
        return db;
    }
}
