package meo.ui;

import java.util.Scanner;

import meo.data.TextList;
/**
 * Handle UI input and output.
 */
public class Ui {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String DIVIDER = "ฅ^•ﻌ•^ฅ ฅ^•ﻌ•^ฅ ฅ^•ﻌ•^ฅ ฅ^•ﻌ•^ฅ";
    private static final String HELLO_MESSAGE = "Hewwo, it's Meo ฅ^•ﻌ•^ฅ";
    private static final String EXIT_MESSAGE = "Bye, it's my nap time. /ᐠ - ˕-マ｡˚ z Z ";
    private static final String COMPLETE_MESSAGE = "Good job~ Your task is done!";
    private static final String INCOMPLETE_MESSAGE = "This task is marked as not done yet...";
    private static final String DELETE_MESSAGE = "I have eaten your task.";
    private static final String ERROR_MESSAGE = "An error was caught.";
    private static final String FIND_RESULT_MESSAGE = "Here's what I found!";
    private static final String COMMAND_PROMPT = "Meow, what would you like to do?";
    private static final String LOGO = "      ⢀⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢀⣾⣿⡇⠀⠀⠀⠀⠀⢀⣼⡇\n"
                + "⠀⠀⠀⠀⠀⣸⣿⣿⡇⠀⠀⠀⠀⣴⣿⣿⠃\n"
                + "⠀⠀⠀⠀⢠⣿⣿⣿⣇⠀⠀⢀⣾⣿⣿⣿⠀\n"
                + "⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⡟⠀\n"
                + "⠀⠀⢰⡿⠉⠀⡜⣿⣿⣿⡿⠿⢿⣿⣿⠃⠀\n"
                + "⠒⠒⠸⣿⣄⡘⣃⣿⣿⡟⢰⠃⠀⢹⣿⡇⠀\n"
                + "⠚⠉⠀⠈⠻⣿⣿⣿⣿⣿⣮⣤⣤⣿⡟⠁⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠈⠙⠛⠛⠛⠛⠛⠁⠀⠒⠤\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠀⠀";

    private final Scanner in = new Scanner(System.in);

    public String showWelcomeMessage() {
        return returnMessage(HELLO_MESSAGE);
    }

    public String showCommandPrompt() {
        return returnMessage(COMMAND_PROMPT);
    }

    public String showAddedTask(String taskAdded) {
        return returnMessage("Added:", taskAdded);
    }

    public String showExitMessage() {
        return returnMessage(EXIT_MESSAGE);
    }

    public String showCompletedMessage(String taskDone) {
        return returnMessage(COMPLETE_MESSAGE, taskDone);
    }

    public String showIncompletedMessage(String taskUndone) {
        return returnMessage(INCOMPLETE_MESSAGE, taskUndone);
    }

    public String showDeletedMessage() {
        return returnMessage(DELETE_MESSAGE);
    }

    public String showErrorMessage() {
        return returnMessage(ERROR_MESSAGE);
    }

    public String showFindResultMessage(TextList taskList) {
        return returnMessage(FIND_RESULT_MESSAGE, taskList.getList());
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public String returnMessage(String... messages) {
        String result = "";
        for (String m : messages) {
            result += SEPARATOR;
            result += m;
        }
        return result;
    }

    /**
     * Print messages
     * @param messages Messages to be printed
     */
    public void printMessage(String... messages) {
        for (String m : messages) {
            System.out.println(m);
        }
    }
    /**
     * Prompts for the command, reads the command entered by user.
     * @return Command entered by user.
     */
    public String getUserCommand() {
        showCommandPrompt();
        String input = in.nextLine();
        return input;
    }
}
