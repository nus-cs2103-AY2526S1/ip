import java.time.LocalDate;
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
    private static final Storage storage = new Storage();
    private static final TaskList tasks = storage.loadTasks();

    private static void printOutput(String output) {
        System.out.println(LINE);
        System.out.println(output);
        System.out.println(LINE);
    }

    private static void greetUser() {
        printOutput("Hello I'm\n" + LOGO + "\nWhat can I do for you?");
    }

    private static void byeUser() {
        printOutput("Goodbye! Paul will miss you :(");
    }

    private static void printList() {
        printOutput("Here are the tasks in your list:\n" + tasks);
    }

    private static void addToDo(String input) throws PaulException {
        String desc = input.substring(4).trim();

        if (desc.isEmpty()) {
            throw new PaulException("The description of a todo cannot be empty!");
        }

        Task task = new ToDo(desc);
        tasks.add(task);
        storage.saveTasks(tasks);

        printOutput("Got it. I've added this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadline(String input) throws PaulException {
        String[] str = input.substring(8).split(" /by ", 2);

        if (str.length < 2 || str[0].isBlank() || str[1].isBlank()) {
            throw new PaulException("A deadline must have a description and a /by date!");
        }

        LocalDate date = LocalDate.parse(str[1]);
        Task task = new Deadline(str[0].trim(), date);
        tasks.add(task);
        storage.saveTasks(tasks);

        printOutput("Got it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String input) throws PaulException {
        String[] str = input.substring(5).split(" /from | /to ");

        if (str.length < 3 || str[0].isBlank() || str[1].isBlank() || str[2].isBlank()) {
            throw new PaulException("An event must have a description, /from, and /to!");
        }

        LocalDate fromDate = LocalDate.parse(str[1]);
        LocalDate toDate = LocalDate.parse(str[2]);
        Task task = new Event(str[0].trim(), fromDate, toDate);
        tasks.add(task);
        storage.saveTasks(tasks);

        printOutput("Got it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void markTask(String input) throws PaulException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            tasks.mark(index);
            storage.saveTasks(tasks);
            printOutput("Nice! I've marked this task as done:\n" + tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("Oops! Invalid task number for mark command.");
        } catch (NumberFormatException e) {
            throw new PaulException("Please input a valid task number!");
        }
    }

    private static void unmarkTask(String input) throws PaulException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            tasks.unmark(index);
            storage.saveTasks(tasks);
            printOutput("OK, I've marked this task as not done yet:\n" + tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("Oops! Invalid task number for unmark command.");
        } catch (NumberFormatException e) {
            throw new PaulException("Please input a valid task number!");
        }
    }

    private static void deleteTask(String input) throws PaulException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            Task task = tasks.get(index);
            tasks.delete(index);
            storage.saveTasks(tasks);
            printOutput("Noted. I've removed this task:\n" + task +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("Oops! Invalid task number for delete command.");
        } catch (NumberFormatException e) {
            throw new PaulException("Please input a valid task number!");
        }
    }

    public static void main(String[] args) {
        greetUser();

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine().trim();
            Parser.CommandType command = Parser.getCommandType(input);

            try {
                switch (command) {
                    case BYE -> {
                        byeUser();
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