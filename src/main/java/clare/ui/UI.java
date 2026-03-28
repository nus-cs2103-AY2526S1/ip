package clare.ui;

/**
 * Represents the user interface to respond and display messages
 */
public class UI {

    private static void clareSays(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints out the welcome message
     */
    public void welcome() {
        String welcomeText = "Hello, I am Clare!\nSo happy to see you today.\nWhat can I help?";
        clareSays(welcomeText);
    }

    /**
     * Print put the farewell message
     */
    public void farewell() {
        String farewellText = "Bye dear. I will miss you!";
        clareSays(farewellText);
    }

    /**
     * Prints out the msg given in the ui format
     *
     * @param msg the message to be printed out
     */
    public void showMessage(String msg) {
        clareSays(msg);
    }

    /**
     * Prints out all available commands
     */
    @SuppressWarnings("checkstyle:Regexp")
    public void showHelp() {
        clareSays("""
                Available Commands:

                todo <description>: Add a simple task

                deadline <description> /by YYYY-MM-DD: Add task with deadline

                event <description> /from YYYY-MM-DD /to YYYY-MM-DD: Add event

                list: View all tasks

                mark <number>: Mark task as completed

                unmark <number>: Unmark completed task

                delete <number>: Remove task

                find <keyword>: Search tasks

                due [YYYY-MM-DD]: Show due tasks

                sort [-a/-d] [title/deadline/start]: sort list according to title/deadline/start,
                in -a ascending, -d descending

                help: Show this help message

                bye: close the app
                """
        );
    }
}
