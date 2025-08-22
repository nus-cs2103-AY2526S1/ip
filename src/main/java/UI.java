import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private final static String decoLine = "____________________________________________________________";

    public UI () {
        this.scanner = new Scanner(System.in);
    }

    public String getNextInput() {
        return this.scanner.nextLine();
    }

    public void greetUser() {
        String botGreet = """
                ____________________________________________________________
                 Hello! I'm Bobby Wasabi
                 What can I do for you?
                ____________________________________________________________
                
                """;
        System.out.println(botGreet);
    }

    public void listMessage(TaskList tasks) {
        String listOutput = UI.decoLine + "\n" + "Here are the tasks in your list:\n" + tasks + UI.decoLine;
        System.out.println(listOutput);
    }

    public void markTaskMessage(int indx, Task targetTask) {
        String curTask = String.format(
                "%d. %s\n",
                indx,
                targetTask);

        String output = String.format("""
                                Nice! I've marked this task as done:
                                   %s""",
                curTask);

        System.out.println(decoLine + "\n" + output + decoLine);
    }

    public void unmarkTaskMessage(int indx, Task targetTask) {
        String curTask = String.format(
                "%d. %s\n",
                indx,
                targetTask);

        String output = String.format("""
                                Nice! I've marked this task as not done yet:
                                   %s""",
                curTask);

        System.out.println(decoLine + "\n" + output + decoLine);
    }


    /**
     * Returns the bot's string response when a task is added to the list
     *
     * @param task Task to be added
     * @param num Number of tasks in the list
     * @return The bot's respond when a task is added
     */
    public void addTaskMessage(Task task, int num) {

        String s = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.
                ____________________________________________________________
                """,
                task, num);

        System.out.println(s);
    }

    public void deleteMessage(Task targetTask, int taskSize) {
        String output = String.format("""
                        ____________________________________________________________
                        Noted. I've removed this task:
                            %s
                        Now you have %d tasks in the list
                        ____________________________________________________________
                        """,
                targetTask, taskSize);

        System.out.println(output);
    }

    public void invalidMessage() {
        this.generateErrorMsg("Please provide a valid command!");
    }

    public void farewellUser() {
        System.out.println("""
                    ____________________________________________________________
                    Bye. Hope to see you again soon!
                    ____________________________________________________________
               
                    """);
        this.exit();
    }

    public void exit() {
        this.scanner.close();
    }


    /**
     * Generates the bot's error message from the error message given
     *
     * @param e String error message
     * @return Bot's error message response
     */
    public void generateErrorMsg(String e) {

        String s = String.format("""
                ____________________________________________________________
                OOPS!!! %s
                ____________________________________________________________
                """,
                e);

        System.out.println(s);
    }

}
