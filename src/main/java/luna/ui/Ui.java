package luna.ui;

/**
 * Handles greeting and exit messages.
 * Displays messages directly to the user.
 */
public class Ui {
    /**
     * Displays greeting message when application starts.
     */
    public void greeting() {
        System.out.println(" Hello! I'm luna");
        System.out.println(" What can I do for you?");
    }

    /**
     * Displays farewell message when application closes.
     */
    public void exit() {
        System.out.println(" Bye~~ Hope to see you again soon!");
    }
}
