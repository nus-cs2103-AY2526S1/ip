package marvin.ui;

import java.util.Scanner;

import marvin.task.TaskList;

/**
 * Contains methods related to printing out values to the terminal screen.
 */
public class Ui {
    private static final int WIDTH = 80;
    private static final String DEMARCATOR = new String(new char[WIDTH]).replace("\0", "-");
    private static final String MARVIN_HEADER =
            String.format("---%s says %s", Color.getColoredTextString("Marvin", Color.RED),
                    new String(new char[WIDTH - 14]).replace("\0", "-"));
    private static final String USER_HEADER =
            String.format("---%s replies %s", Color.getColoredTextString("User", Color.YELLOW),
                    new String(new char[WIDTH - 15]).replace("\0", "-"));

    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints a stylized greeting.
     */
    public static void printGreeting(String greeting) {
        System.out.println(MARVIN_HEADER);
        System.out.println(surroundWithBox(greeting));
        System.out.println(USER_HEADER);
        System.out.print("↳");
    }


    /**
     * Prints a stylized task list for a provided task list.
     *
     * @param taskList The TaskList that is being described.
     * @param preamble Extra text to add before displaying the task list.
     */
    public static void printTaskList(TaskList taskList, String preamble) {
        System.out.println(MARVIN_HEADER);
        System.out.println(Ui.surroundWithBox(preamble + taskList));
    }

    /**
     * Prints a goodbye quip, adding a bottom demarcator to indicate end of communication.
     */
    public static void printGoodbye(String goodbye) {
        System.out.println(MARVIN_HEADER);
        System.out.println(surroundWithBox(goodbye));
        System.out.println(DEMARCATOR);
    }

    /**
     * Prints any provided text in a manner indicating that the text is from Marvin.
     */
    public static void printGeneric(String text) {
        System.out.println(MARVIN_HEADER);
        System.out.println(surroundWithBox(text));
    }

    /**
     * Prints the user header indicating the user's turn to input text.
     */
    public static void printUserInput() {
        System.out.println(USER_HEADER);
        System.out.print("↳");
    }


    /**
     * Surrounds a provided input with -/| characters to make the input boxed. Used to stylize text.
     *
     * @param input The text to be boxed.
     * @return The stylized string.
     */
    private static String surroundWithBox(String input) {
        // split string by \ns
        String[] lines = input.split("\n");
        int contentWidth = WIDTH - 4;
        for (int i = 0; i < lines.length; i++) {
            int actualContentWidth = contentWidth;
            // Java doesn't count non-visible characters in the string format
            // Therefore we manually add content width to account for non-visible characters
            // This will work as long as each line strictly has max 1 color sequence.
            if (lines[i].contains("\u001B")) {
                actualContentWidth += 9;
            }
            lines[i] = "| " + String.format("%-" + actualContentWidth + "s", lines[i].trim()) + " |";
        }
        return String.join("\n", lines);
    }
}
