package amogus;

import java.util.Scanner;

import tasks.TaskList;

/**
 * This class deals with output given by the chatbot
 * to the user.
 */
public class UI {

    static final String HORILINES = "________________________________________________________\n";
    static final String INTRO = "Hello! I'm " + Amogus.NAME + "!\nWhat can I do for you?\n";
    static final String OUTRO = "Bye. Hope to see you again soon!\n";
    /* ChatGPT recommends a scanner object rather than instantiating one
     * each time readcommand is called.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the message.
     * @param msg any input message.
     * @return the input message.
     */
    public String showMsg(String msg) {
        System.out.println(msg);
        return msg;
    }

    /**
     * Formats given message in correct style.
     * @param msg message to be formatted.
     * @return formatted message.
     */
    public String format(String msg) {
        return HORILINES + msg + HORILINES;
    }

    /**
     * Iterates through the list of tasks, then prints them out
     * in the appropriate format
     *
     * @param tasks list of tasks
     */
    public String showTaskList(TaskList tasks) {
        /* ChatGPT recommended to use string builder instead of
         * string concatenation to prevent intermediate string objects.
         */

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).getDisplayString()).append("\n");
        }
        String msg = sb.toString();
        System.out.println(format(msg));
        return format(msg);
    }

    /**
     * Takes in user input for command
     *
     * @return full line of user input to be parsed
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public String welcome() {
        System.out.println(format(INTRO));
        return INTRO;
    }

    /**
     * Prints exit message.
     */
    public String exit() {
        System.out.println(format(OUTRO));
        return OUTRO;
    }
}
