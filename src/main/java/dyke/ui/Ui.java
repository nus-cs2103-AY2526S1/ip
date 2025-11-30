package dyke.ui;

/**
 * Houses methods for printing messages to CLI
 */
public class Ui {
    private static final String BAR = "\t______________________"
            + "________________________"
            + "______________";

    /**
     * Prints message with stylization
     * @param message Message from other Classes
     */
    public void printMessage(String message) {
        String[] sep = message.split("\n");
        System.out.println(BAR);
        for (String s : sep) {
            System.out.println("\t" + s);
        }
        System.out.println(BAR);
    }

    /**
     * Prints message without stylization
     * @param message
     */
    public void printMessageNoBar(String message) {
        String[] sep = message.split("\n");
        for (String s : sep) {
            System.out.println("\t" + s);
        }
    }

    /**
     * Prints and returns welcome message
     * @return Welcome message
     */
    public String welcomeMessage() {
        String welcome = "Hello! I'm DYKE\n"
                + "What can I do for you?\n\n"
                + "\t\t\t\t\t\t\tpsst! type 'help' for help, duh.";
        this.printMessage(welcome);
        return welcome;
    }

}
