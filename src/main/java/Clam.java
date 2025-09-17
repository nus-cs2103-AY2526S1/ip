import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

enum DukeAction {
    CREATE_TODO,
    CREATE_EVENT,
    CREATE_DEADLINE,
    LIST_TASKS,
    MARK_ITEM,
    UNMARK_ITEM,
    EXIT,
    DELETE,
    ERROR
}

public class Clam {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        chatbotPrint(HORIZONTAL_LINE);

        greet();

        boolean end = false;
        Scanner sc = new Scanner(System.in);
        while(!end) {
            try {
                String input = sc.nextLine();
                chatbotPrint(HORIZONTAL_LINE);
                DukeAction action = parseInput(input);

                switch (action) {
                    case EXIT:
                        end = true;
                        bye();
                        sc.close();
                        break;
                    case LIST_TASKS:
                        printlist();
                        chatbotPrint(HORIZONTAL_LINE);
                        break;
                    case MARK_ITEM:
                        markItem(input);
                        break;
                    case UNMARK_ITEM:
                        unmarkItem(input);
                        break;
                    case CREATE_TODO:
                        createTodo(input);
                        break;
                    case CREATE_EVENT:
                        createEvent(input);
                        break;
                    case CREATE_DEADLINE:
                        createDeadline(input);
                        break;
                    case DELETE:
                        deleteItem(input);
                        break;
                    case ERROR:
                        errorMessage();
                    default:
                        //default
                }
            } catch (Exception e) {
                chatbotPrint(e.toString());
            }
        }
    }

    private static DukeAction parseInput(String input) {
        if (input.startsWith("list")) {
            return DukeAction.LIST_TASKS;
        } else if (input.startsWith("bye")) {
            return DukeAction.EXIT;
        } else if (input.startsWith("mark")) {
            return DukeAction.MARK_ITEM;
        } else if (input.startsWith("unmark")) {
            return DukeAction.UNMARK_ITEM;
        } else if (input.startsWith("delete")) {
            return DukeAction.DELETE;
        } else if (input.startsWith("todo")) {
            return DukeAction.CREATE_TODO;
        } else if (input.startsWith("deadline")) {
            return DukeAction.CREATE_DEADLINE;
        } else if (input.startsWith("event")) {
            return DukeAction.CREATE_EVENT;
        } else {
            return DukeAction.ERROR;
        }
    }

    public static void chatbotPrint(String s) {
        System.out.println("    " + s);
    }

    public static void greet() {
        String greeting = "    Hello! I'm Clam!\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static void bye() {
        String bye = "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        System.out.println(bye);
    }

    public static void errorMessage() {
        chatbotPrint("Sorry, I'm not sure what you mean. Can you try again? ");
        chatbotPrint(HORIZONTAL_LINE);
    }

    public static void printlist() {
        StringBuilder list = new StringBuilder();
        int index = 0;
        list.append("    Here are the tasks in your list:\n");
        for (Task i : tasks) {
            index++;
            list.append("    ").append(index).append(".").append(i).append("\n");
        }
        System.out.println(list.toString());

    }

    public static void markItem(String input) throws MarkException {
        try {
            int item = Integer.parseInt(input.substring(5));
            tasks.get(item - 1).markAsDone();
            chatbotPrint("Nice! I've marked this task as done:\n    "
                    + tasks.get(item - 1));
        } catch (Exception e) {
            throw new MarkException("I'm not sure which item you're trying to mark. Try again?");
        }
    }

    public static void unmarkItem(String input) throws MarkException {
        try {
            int item = Integer.parseInt(input.substring(7));
            tasks.get(item - 1).markAsUndone();
            chatbotPrint("Ok, I've marked this task as not done yet:\n    "
                    + tasks.get(item - 1));
        } catch (Exception e) {
            throw new MarkException("I'm not sure which item you're trying to unmark. Try again?");
        }
    }

    public static void createTodo(String input) throws TodoException {
        try {
            if (input.substring(5).isEmpty()) throw new Exception();
            ToDo td = new ToDo(input.substring(5));
            tasks.add(td);
            chatbotPrint("Got it. I've added this task:\n      " + td);
            chatbotPrint("Now you have " + tasks.size() + " tasks in the list.");
            chatbotPrint(HORIZONTAL_LINE);
        } catch (Exception e) {
            throw new TodoException("To create a new to-do item, the command is: todo [name of to-do]");
        }
    }

    public static void createDeadline(String input) throws DeadlineException {
        try {
            Deadline td = new Deadline(input.substring(9, input.indexOf("/by") - 1), input.substring(input.indexOf("/by") + 4));
            tasks.add(td);
            chatbotPrint("Got it. I've added this task:\n      " + td);
            chatbotPrint("Now you have " + tasks.size() + " tasks in the list.");
            chatbotPrint(HORIZONTAL_LINE);
        } catch (Exception e) {
            throw new DeadlineException("To create a new deadline item, the command is: deadline /by [due date]");
        }
    }

    public static void createEvent(String input) throws EventException {
        try {
            Event td = new Event(input.substring(6, input.indexOf("/from") - 1),
                    input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1),
                    input.substring(input.indexOf("/to") + 4));
            tasks.add(td);
            chatbotPrint("Got it. I've added this task:\n      " + td);
            chatbotPrint("Now you have " + tasks.size() + " tasks in the list.");
            chatbotPrint(HORIZONTAL_LINE);
        } catch (Exception e) {
            throw new EventException("To create a new event item, the command is: event /from [start] /to [end]");
        }
    }

    public static void deleteItem(String input) throws DeleteException {
        try {
            int item = Integer.parseInt(input.substring(7));
            Task taskToDelete = tasks.get(item - 1);
            tasks.remove(item - 1);
            chatbotPrint("I've deleted the task:\n      "
                    + taskToDelete);
            chatbotPrint("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DeleteException("I'm not sure which item you're trying to delete. Try again?");
        }
    }
}
