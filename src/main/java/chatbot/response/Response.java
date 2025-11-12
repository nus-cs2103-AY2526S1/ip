package chatbot.response;


/**
 * Represents a response from the chatbot.
 * A Response contains the message to be displayed to the user
 * and a flag indicating whether the chatbot should exit after this response.
 */
public class Response {
    private String responseMessage;
    private boolean isExit;

    /**
     * Constructs a Response object with the given message and exit flag.
     *
     * @param responseMessage the message text of the response
     * @param isExit true if the chatbot should terminate after this response; false otherwise
     */
    public Response(String responseMessage, boolean isExit) {
        this.responseMessage = responseMessage;
        this.isExit = isExit;
    }

    public String getResponse() {
        return responseMessage;
    }

    public boolean isExit() {
        return isExit;
    }
}
