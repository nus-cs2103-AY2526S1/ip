package ackermann.functions;

import ackermann.exceptions.CheckedException;

/**
 * Deals with operations based on users' input
 */
public class Ui {
    public static final String END = "Bye. Hope to see you again soon!";
    private static final String LINE = "-----------------------------------------------------";
    private static final String STARTUP = "Hello! I'm Ackermann\nWhat can I do for you?";

    private Parser parser;
    private TaskList tasks;

    /**
     * Constructor for Ui
     * @param tasks
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
        this.parser = new Parser(tasks);
    }

    /**
     * Prints error message for loading
     */
    public void showLoadingError() {
        System.out.println("Oops! Something went wrong.");
    }

    /**
     * Prints startup message
     */
    public void startup() {
        System.out.println(LINE);
        System.out.println(STARTUP);
        System.out.println(LINE);
    }

    /**
     * Prints end message
     */
    public void end() {
        System.out.println(END);
        System.out.println(LINE);
    }

    /**
     * Prints a line
     */
    public void printline() {
        System.out.println(LINE);
    }

    /**
     * Checks if program should terminate
     * @param input
     * @return
     * @throws CheckedException
     */
    public boolean isRun(String input) throws CheckedException {
        String result = this.parser.parse(input);
        if (result.equals(END)) {
            return false;
        } else {
            System.out.println(result);
            return true;
        }
    }

    /**
     * Prints error message
     * @param message Error message to print
     */
    public void printException(String message) {
        System.out.println(message);
        this.printline();
    }
}
