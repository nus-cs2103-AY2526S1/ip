package com.neokortex.ui;

/**
 * Represents a generic UI class. A Ui class for this {@code Chatbot} program provides the following features.
 *
 * <p><b>Features: </b></p>
 * <ul>
 *     <li>say{String}: to display the user sending the message</li>
 *     <li>respond(String): to display the message response by the {@code Chatbot}</li>
 *     <li>respond(String[]): to display multiple message responses by the {@code Chatbot}</li>
 * </ul>
 *
 * <p>
 * All child classes must provide implementations for the aforementioned features.
 * </p>
 */
public abstract class Ui {
    public abstract void say(String message);
    public abstract void respond(String message);
    public abstract void respond(String[] messages);
}
