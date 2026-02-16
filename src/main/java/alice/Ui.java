package alice;

/**
 * Represents the Ui that deals with interactions with the users
 */
public class Ui {
    private static String BOT_NAME = "Alice";

    /**
     * Returns the name of the bot
     *
     * @return Bot name
     */
    public String getBotName() {
        return BOT_NAME;
    }

    public void showMessage(String message) {
        System.out.println("________________________________");
        System.out.println(message);
        System.out.println("________________________________");
    }

    /**
     * Prints the greeting at the start of the program
     */
    public void greet() {
        assert getBotName() != null : "Bot name must not be null";
        String greeting = String.format("Hello! I'm %s,\nWhat can I do for you?", getBotName());
        showMessage(greeting);
    }

    /**
     * Prints the farewell at the end of the program
     */
    public String exit() {
        return String.format("Bye. Hope to see you again soon!");
    }
}
