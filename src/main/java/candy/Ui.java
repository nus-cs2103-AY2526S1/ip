package candy;

/**
 * Represents a class that prints messages to user
 */
public class Ui {
    /**
     * Returns String of the welcome line
     */
    public static String printWelcome() {
        return "Hello! I am Candy \uD83C\uDF6C."
                + "\nYour sweet task manager ^-^";
    }

    /**
     * Returns String of the goodbye line
     */
    public static String printBye() {
        return "Bye! Have a sweet day! ^-^";
    }

    /**
     * Returns String of the error message
     */
    public static String printError(Exception e) {
        return e.getMessage();
    }
}
