package bubbles;

/**
 * Represents a message to be displayed in the Bubbles chatbot.
 * The {@code Message} class stores a string and provides a method to print it.
 */
public class Message {
    String msg;


    /**
     * Constructs a {@code Message} with the given text.
     *
     * @param msg the message content to be displayed
     */
    public Message(String msg){
        this.msg = msg;
    }

    /**
     * Prints the message followed by a horizontal line for separation.
     */
    public void message(){
        System.out.println(this.msg);
        System.out.println("--------------------------------------------------------");
    }
}
