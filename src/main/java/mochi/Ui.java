package mochi;

/**
 * Handles visuals or texts displayed to the user, such as menus or error messasges.
 */
public class Ui {
    private String response;
    /**
     * Returns a UI object to display Mochi specific messages.
     */
    public Ui() {
        this.response = "";
        // Initialization message
        System.out.println("""
            ____________________________________________________________
              __  __            _     _
             |  \\/  | ___   ___| |__ (_)
             | |\\/| |/ _ \\ / __| '_ \\| |
             | |  | | (_) | (__| | | | |
             |_|  |_|\\___/ \\___|_| |_|_| \n
             Program starting...
            ____________________________________________________________""");
    }

    /**
     * Returns the result of all string output and resets output string.
     *
     * @return string to be output to the user
     */
    public String output() {
        String t = response;
        response = "";
        return t;
    }

    /**
     * Prints out a list of commands for user reference.
     */
    public void showHelp() {
        this.response = """
             1. 'todo <description>'
                -> create a todo task
             2. 'deadline <description> /by <due date>'
                -> create a deadline task with due date
             3. 'event <description> /from <start time> /to <end time'>
                -> create an event task with start and end time
             4. 'list'
                -> display the list of tasks created and their details
             5. 'mark <number>'
                -> check off a task, specified by its order in the list
                   as completed
             6. 'unmark <number>'
                -> uncheck a task to set it to incomplete
             7. 'find <word>'
                -> prints a list of tasks that contain the word(s) given
             8. 'tag <number> <word>'
                -> tags a word to a task, that will be displayed with a '#'
             9. 'untag <number> <word>
                -> removes a tag from a particular task
             10. 'bye'
                -> exit the program
            """;
    }

    public void error(Exception e) {
        this.response = e.toString();
    }

    public void print(String s) {
        this.response = this.response.concat(s);
    }

    /**
     * Prints out the exit message when user quits.
     */
    public void exit() {
        this.response = "Bye. Hope to see you again soon!";
    }
}
