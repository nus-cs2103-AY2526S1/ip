package com.neokortex.commands;

import com.neokortex.DialoguePath;

/**
 * Represents a general {@code Response} class which details the responses derived from the various state of Command
 * handling done by the {@link CommandHandler}.
 *
 * <p>The {@code Response} class has 3 fields:</p>
 * <ul>
 *     <li>{@link #directive}: indicates to the Chatbot what response it should exhibit
 *     through the {@link DialoguePath} enum</li>
 *     <li>{@link #responseStatus}: represents the general status after completing a given stage in the Command
 *     Handling process</li>
 *     <li>{@link #attachedResults}: the channel of communication between the results of a given stage
 *     of the Command Handling process and the {@link com.neokortex.Chatbot}. Contains results that will
 *     be needed by the {@code Chatbot} when responding to the user.</li>
 * </ul>
 */
public class Response {
    private DialoguePath directive;
    private ResponseStatus responseStatus;
    private String[] attachedResults;

    /**
     * Constructs a {@code Response} from the given directive and responseType. Note:
     * {@link #attachedResults} must be provided to the Response separately through the
     * {@link #attachResults} method.
     *
     * @param directive the {@link DialoguePath} that dictates what the Chatbot should respond with.
     * @param responseType the type of {@link Response} as a result of that stage of Command handling.
     */
    public Response(DialoguePath directive, ResponseStatus responseType) {
        this.directive = directive;
        this.responseStatus = responseType;
    }

    public void attachResults(String[] additionalResults) {
        this.attachedResults = additionalResults;
    }

    public String[] getAttachedResults() {
        return this.attachedResults;
    }

    public DialoguePath getDirective() {
        return this.directive;
    }

    public ResponseStatus getStatus() {
        return this.responseStatus;
    }

}
