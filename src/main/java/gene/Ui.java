package gene;

public class Ui {

    public static final String SPACING = "    ";
    public static final String LINE = SPACING + "____________________________";
    public static final String GREETING =
            Ui.SPACING + "Hello! I'm Gene\n" +
                    Ui.SPACING + "What can I do for you?";

    /**
     * Prints to command line the formatted response with a given input
     * This is to be used as an abstraction whenever user wants to print
     * something
     *
     * @param s input String to print to user
     */
    public static void printFormatResponse(String s) {
        System.out.println(LINE);
        System.out.println(s);
        System.out.println(LINE);
    }

    public static void printGreeting() {
        printFormatResponse(GREETING);
    }
}
