package com.neokortex.ui.gui;

import com.neokortex.ui.Ui;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents the Graphical User Interface (GUI) version of this program.
 *
 * <p>
 * Aside from providing the implementations as specified in {@link Ui}, the GUI
 * provides a few extra features specific to it.
 *
 * <p><b>Additional Features:</b></p>
 * <ul>
 *     <li>The ability to set the sender and responder's profile picture</li>
 * </ul>
 * </p>
 */
public class Gui extends Ui {
    private final VBox root;
    private Image senderProfilePicture;
    private Image responderProfilePicture;

    public Gui(VBox root) {
        this.root = root;
    }

    public void setSenderProfilePicture(Image senderProfilePicture) {
        this.senderProfilePicture = senderProfilePicture;
    }

    public void setResponderProfilePicture(Image responderProfilePicture) {
        this.responderProfilePicture = responderProfilePicture;
    }

    @Override
    public void say(String message) {
        this.root.getChildren().add(DialogueBox.createSenderDialogueBox(message, this.senderProfilePicture));
    }

    @Override
    public void respond(String message) {
        this.root.getChildren().add(DialogueBox.createResponseDialogueBox(message, this.responderProfilePicture));
    }

    @Override
    public void respond(String[] messages) {
        for (String message : messages) {
            this.respond(message);
        }
    }
}
