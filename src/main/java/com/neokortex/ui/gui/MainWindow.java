package com.neokortex.ui.gui;

import com.neokortex.Chatbot;
import com.neokortex.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the element that encompasses the entire program. All graphical elements
 * are found within the {@code Main Window}
 *
 * <p>The {@code MainWindow} consists of:</p>
 * <ul>
 *     <li>{@link #scrollPane}: contains the dialogueContainer, for which all {@link DialogueBox}es are rooted</li>
 *     <li>{@link #dialogueContainer}: the element for which all {@link DialogueBox}es are rooted</li>
 *     <li>{@link #userInput}: the {@code TextArea} representing where the user input's their messages</li>
 *     <li>{@link #sendButton}: handles sending the user's input to be handled by the {@link Chatbot}</li>
 * </ul>
 * </p>
 *
 * <p>
 * The MainWindow also consists of the {@link Gui}, {@link Chatbot} primarily to handle the logic for
 * sending messages/commands. {@link User} is provided purely for encapsulation purposes at the moment.
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
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Gui gui;
    private Chatbot chatbot;
    private User user;

    /**
     * Initialises the {@code MainWindow}. It is run when the MainWindow is created, and performs modifications
     * on existing elements to provide a better UX experience.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
        dialogueContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));
    }

    public void setCharacters(Chatbot chatbot, User user) {
        this.chatbot = chatbot;
        this.user = user;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    /**
     * Handles what happens when the user clicks send or presses the Enter key.
     * It gets the text from the {@link #userInput} field, sends it off to be handled
     * by the {@link Chatbot}, then creates the sender's {@link DialogueBox}. Once the {@link Chatbot}
     * has a response to the user's input, it sends the message through the {@link Gui}
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        gui.say(input);
        this.chatbot.interpret(input);
        this.chatbot.respond();
        this.userInput.clear();
    }

    public VBox getDialogueContainer() {
        return this.dialogueContainer;
    }
}
