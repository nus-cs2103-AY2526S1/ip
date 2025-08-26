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

    private static void addToDo(String input) throws PaulException {
        String desc = input.substring(4).trim();
        if (desc.isEmpty()) {
            throw new PaulException("The description of a todo cannot be empty!");
        }
        Task task = new ToDo(desc);
        list.add(task);
        printOutput("Got it. I've added this task:\n" + task +
                "\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void addDeadline(String input) throws PaulException {
        String[] str = input.substring(8).split(" /by ", 2);
        if (str.length < 2 || str[0].isBlank() || str[1].isBlank()) {
            throw new PaulException("A deadline must have a description and a /by date!");
        }
        Task task = new Deadline(str[0].trim(), str[1]);
        list.add(task);
        printOutput("Got it. I've added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void addEvent(String input) throws PaulException {
        String[] str = input.substring(5).split(" /from | /to ");
        if (str.length < 3 || str[0].isBlank() || str[1].isBlank() || str[2].isBlank()) {
            throw new PaulException("An event must have a description, /from, and /to!");
        }
        Task task = new Event(str[0].trim(), str[1], str[2]);
        list.add(task);
        printOutput("Got it. I've added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    private static void markTask(String input) throws PaulException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            list.mark(index);
            printOutput("Nice! I've marked this task as done:\n" + list.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("Oops! Invalid task number for mark command.");
        } catch (NumberFormatException e) {
            throw new PaulException("Please input a number!");
        }
    }

    private static void unmarkTask(String input) throws PaulException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            list.unmark(index);
            printOutput("OK, I've marked this task as not done yet:\n" + list.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("Oops! Invalid task number for unmark command.");
        } catch (NumberFormatException e) {
            throw new PaulException("Please input a number!");
        }
    }

    private static void deleteTask(String input) throws PaulException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            Task task = list.get(index);
            list.delete(index);
            printOutput("Noted. I've removed this task:\n" + task +
                    "\nNow you have " + list.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("Oops! Invalid task number for delete command.");
        } catch (NumberFormatException e) {
            throw new PaulException("Please input a number!");
        }
    }

    public static void main(String[] args) {
        greeting();

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine().trim();
            Parser.CommandType command = Parser.getCommandType(input);

            try {
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
                    case DELETE -> deleteTask(input);
                    case UNKNOWN -> printOutput("Sorry, I don't understand the command.");
                }
            } catch (PaulException e) {
                printOutput(e.getMessage());
            }
        }
    }
}
