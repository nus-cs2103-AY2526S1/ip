package bugsbunny.app;

import java.util.Scanner;

/**
 * Handles all the console input and output for the chatbot.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the ASCII welcome banner of Bugs Bunny.
     */
    public void showWelcome() {
        // Art by Shanaka Dias, obtained from https://www.asciiart.eu/cartoons/looney-tunes
        System.out.printf("              , ,\n");
        System.out.printf("             /| |\\\n");
        System.out.printf("            / | | \\\n");
        System.out.printf("            | | | |     Neeaah, Whats up Doc !?!\n");
        System.out.printf("            \\ | | /\n");
        System.out.printf("             \\|w|/    /\n");
        System.out.printf("             /_ _\\   /      ,\n");
        System.out.printf("  /\\       _:()_():_       /]\n");
        System.out.printf("  ||_     : ._=Y=_  :     / /\n");
        System.out.printf(" [)(_\\,   ',__\\W/ _,'    /  \\\n");
        System.out.printf(" [) \\_/\\    _/'='\\      /-/\\)\n");
        System.out.printf("  [_| \\ \\  ///  \\ '._  / /\n");
        System.out.printf("  :;   \\ \\///   / |  '` /\n");
        System.out.printf("  ;::   \\ `|:   : |',_.'\n");
        System.out.printf("  \"\"\"    \\_|:   : |   \n");
        System.out.printf("           |:   : |'\".\n");
        System.out.printf("           /`._.'  \\/\n");
        System.out.printf("          /  /|   /\n");
        System.out.printf("         |  \\ /  /\n");
        System.out.printf("          '. '. /\n");
        System.out.printf("            '. '\n");
        System.out.printf("            / \\ \\\n");
        System.out.printf("           / / \\'=,\n");
        System.out.printf("     .----' /   \\ (\\__\n");
        System.out.printf("snd (((____/     \\ \\  )\n");
        System.out.printf("                  '.\\_)\n");
    }

    /**
     * Returns the usage examples.
     */
    public String showCommandGuide() {
        return "You can chat with me using the following commands:\n"
                + "1. help: Show this guide. Syntax: help\n"
                + "2. list: List all the tasks you have created so far. Syntax: list\n"
                + "3. mark: Mark a task as completed. Syntax: mark <task index>\n"
                + "4. unmark: Mark a task as not completed. Syntax: unmark <task index>\n"
                + "5. delete: Delete a task. Syntax: delete <task index>\n"
                + "6. todo: Create a todo task. Syntax: todo <task name>\n"
                + "7. deadline: Create a deadline task. Syntax: deadline <task name> /by <yyyy-mm-dd hhmm>\n"
                + "8. event: Create an event task. "
                + "Syntax: event <task name> /from <yyyy-mm-dd hhmm> /to <yyyy-mm-dd hhmm>\n"
                + "9. due: Shows the tasks that are due by this date and time. Syntax: due <yyyy-mm-dd hhmm>\n"
                + "10. find: Shows the tasks that have the keyword in their name/description. Syntax: find <keyword>\n"
                + "11. bye: Exit the chat";
    }

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        System.out.println("________________________________________________________________");
    }

    /**
     * Reads a line of user input.
     *
     * @return The trimmed user's input.
     */
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    /**
     * Returns a user-friendly error message.
     *
     * @param s The error message.
     */
    public String showError(String s) {
        return "Neeah, there's a problem Doc:\n"
                + " " + s;
    }

    /**
     * Returns a message to indicate error loading from the hard disk.
     */
    public String showLoadingError() {
        return "Neeah, I can't load from the hard disk Doc";
    }

    /**
     * Returns a message to indicate error saving to the hard disk.
     */
    public String showSavingError() {
        return "Neeah, I can't save to the hard disk Doc";
    }

    /**
     * Returns a goodbye message and closes the scanner.
     */
    public String showGoodbye() {
        this.scanner.close();
        return "So long, Doc!";
    }
}

