package marcus.exception;

public class InvalidInputError extends Exception {
    public InvalidInputError() {
        super("I'm sorry, I don't know what you want me to do.\n"
                + "Type 'help' to see the list of available commands.");
    }
}
