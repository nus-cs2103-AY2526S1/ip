import java.util.Scanner;

public class Paul {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = """
                 ____   _   _   _ _
                |  _ \\ / \\ | | | | |
                | |_) / _ \\| | | | |
                |  __/ ___ \\ |_| | |___
                |_| /_/   \\_\\___/|_____|
                """;
    private static final TaskList list = new TaskList();

    private static void printOutput(String output) {
        System.out.println(LINE);
        System.out.println(output);
        System.out.println(LINE);
    }

    private static void greeting() {
        printOutput("Hello I'm\n" + LOGO + "\nWhat can I do for you?");
    }

    private static void goodbye() {
        printOutput("Goodbye! Paul will miss you :(");
    }

    private static void printList() {
        printOutput("Here are the tasks in your list:\n" + list);
    }

    private static void addToDo(String input) {
        list.add(new ToDo(input.substring(5)));
        printOutput("Got it. I've added this task:\n"
                + list.get(list.size()) + "\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void addDeadline(String input) {
        String[] str = input.substring(9).split(" /by ", 2);
        list.add(new Deadline(str[0], str[1]));
        printOutput("Got it. I've added this task:\n"
                + list.get(list.size()) + "\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void addEvent(String input) {
        String[] str = input.substring(6).split(" /from | /to ");
        list.add(new Event(str[0], str[1], str[2]));
        printOutput("Got it. I've added this task:\n"
                + list.get(list.size()) + "\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void markTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        list.mark(index);

        printOutput("Nice! I've marked this task as done:\n" + list.get(index));
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        list.unmark(index);

        printOutput("OK, I've marked this task as not done yet:\n" + list.get(index));
    }

    public static void main(String[] args) {
        greeting();

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine().trim();
            Parser.CommandType command = Parser.getCommandType(input);

            switch (command) {
                case BYE -> {
                    goodbye();
                    return;
                }
                case LIST -> printList();
                case TODO -> addToDo(input);
                case DEADLINE -> addDeadline(input);
                case EVENT -> addEvent(input);
                case MARK -> markTask(input);
                case UNMARK -> unmarkTask(input);
                default -> printOutput("Sorry, I don't understand the command.");
            }
        }
    }
}
