package ui;


/**
 * Represents a message with a type and content.
 * <p>
 * This class provides different types of messages such as normal, error, info, success, and warning.
 * Each message has a type and the actual message content.
 * </p>
 */
public class Message {

    /**
     * Enum representing the different message types.
     */
    public enum Type { NORMAL, ERROR, INFO, SUCCESS, WARNING }

    private String message;
    private Type type;

    private Message(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * Constructs a new <code>Message</code> of normal type.
     *
     * @param message the content of the message
     */
    public static Message normal(String message) {
        return new Message(Type.NORMAL, message);
    }

    /**
     * Constructs a new <code>Message</code> of error type.
     *
     * @param message the content of the message
     */
    public static Message error(String message) {
        return new Message(Type.ERROR, message);
    }

    /**
     * Constructs a new <code>Message</code> of info type.
     *
     * @param message the content of the message
     */
    public static Message info(String message) {
        return new Message(Type.INFO, message);
    }

    /**
     * Constructs a new <code>Message</code> of success type.
     *
     * @param message the content of the message
     */
    public static Message success(String message) {
        return new Message(Type.SUCCESS, message);
    }

    /**
     * Constructs a new <code>Message</code> of warning type.
     *
     * @param message the content of the message
     */
    public static Message warning(String message) {
        return new Message(Type.WARNING, message);
    }


    public String getMessage() {
        return message;
    }

    public Type getType() {
        return type;
    }
}
