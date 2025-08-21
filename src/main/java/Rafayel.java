import java.util.ArrayList;
import java.util.Scanner;

public class Rafayel {

    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

        public static Command parseCommand(String input) {
            if (input.equals("bye"))
                return BYE;
            if (input.equals("list"))
                return LIST;
            if (input.startsWith("mark"))
                return MARK;
            if (input.startsWith("unmark"))
                return UNMARK;
            if (input.startsWith("todo"))
                return TODO;
            if (input.startsWith("deadline"))
                return DEADLINE;
            if (input.startsWith("event"))
                return EVENT;
            if (input.startsWith("delete"))
                return DELETE;
            return UNKNOWN;
        }
    }

    private static void handleMarkCommand(String input, ArrayList<Task> tasks, int counter, boolean markTask)
            throws RafayelException {
        int minLen = markTask ? 5 : 7;
        if (input.length() <= minLen) {
            throw new RafayelException("Please state what task to be marked/unmarked.");
        }
        String[] temp = input.split(" ");
        int taskNumber = Integer.parseInt(temp[1]);

        if (taskNumber <= 0 || taskNumber > counter) {
            throw new RafayelException("Invalid task number.");
        }
        taskNumber--;
        if (markTask) {
            tasks.get(taskNumber).markAsDone();
            System.out
                    .println("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber).toString());
        } else {
            tasks.get(taskNumber).markAsUndone();
            System.out
                    .println("OK, I've marked this task as not done yet:\n  "
                            + tasks.get(taskNumber).toString());

        }

    }

    private static void printNewTaskString(Task newTask, int counter) {
        // FOR CREATING TASKS
        String TASK_START = "Got it. I've added this task:";

        System.out.println(TASK_START);
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + ++counter + " tasks in the list.");
    }

    private static void handleTodoCommand(String input, ArrayList<Task> tasks, int counter) throws RafayelException {
        if (input.length() <= 5) {
            throw new RafayelException("Please add in the description of the Todo task.");
        }

        Todo newTask = new Todo(input.substring(5).trim());
        tasks.add(newTask);

        printNewTaskString(newTask, counter);
    }

    private static void handleDeadlineCommand(String input, ArrayList<Task> tasks, int counter)
            throws RafayelException {
        if (input.length() <= 10) {
            throw new RafayelException("Please add in the description of the Deadline task.");
        }
        if (!input.contains("/by")) {
            throw new RafayelException("Deadline format is wrong. Example: deadline [desc] /by [time]");
        }

        String[] taskDate = input.substring(9).split("/by ");
        Deadline newTask = new Deadline(taskDate[0], taskDate[1]);
        tasks.add(newTask);

        printNewTaskString(newTask, counter);
    }

    private static void handleEventCommand(String input, ArrayList<Task> tasks, int counter)
            throws RafayelException {
        if (input.length() <= 6) {
            throw new RafayelException("Please add in the description of the Event task.");
        }
        if (!input.contains("/from")) {
            throw new RafayelException(
                    "Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }
        if (!input.contains("/to")) {
            throw new RafayelException(
                    "Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }

        String[] taskDate = input.substring(6).split("/");
        Event newTask = new Event(taskDate[0], taskDate[1].substring(5), taskDate[2].substring(3));

        tasks.add(newTask);

        printNewTaskString(newTask, counter);
    }

    public static void main(String[] args) {
        String LINE = "____________________________________________________________";
        String START_MSG = LINE + "\n"
                + " Hello! I'm Rafayel\n"
                + " What can I do for you?\n"
                + LINE;
        String END_MSG = " Bye. Hope to see you again soon!\n"
                + LINE;

        ArrayList<Task> tasks = new ArrayList<Task>();
        int counter = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println(START_MSG);

        while (true) {
            if (!sc.hasNextLine()) {
                System.out.println(LINE + "\n" + END_MSG);
                break;
            }

            String input = sc.nextLine();

            if (input == null) {
                System.out.println(LINE + "\n" + END_MSG);
                break;
            }

            System.out.println(LINE);

            try {
                Command command = Command.parseCommand(input);
                // System.out.println(command);

                switch (command) {
                    case BYE:
                        System.out.println(END_MSG);
                        return;

                    case LIST:
                        for (int i = 0; i < counter; i++) {
                            System.out.println(i + 1 + "." + tasks.get(i).toString());
                            // System.out.println(String.format("%d. %s", i + 1, data[i]));
                        }
                        break;

                    case MARK:
                        // Mark task
                        handleMarkCommand(input, tasks, counter, true);
                        break;

                    case UNMARK:
                        // Unmark task
                        handleMarkCommand(input, tasks, counter, false);
                        break;

                    case TODO:
                        // Create Todo Task
                        handleTodoCommand(input, tasks, counter);
                        counter++;
                        break;

                    case DEADLINE:
                        // Create Deadline Task
                        handleDeadlineCommand(input, tasks, counter);
                        counter++;
                        break;

                    case EVENT:
                        // Create Event Task
                        handleEventCommand(input, tasks, counter);
                        counter++;
                        break;

                    case DELETE:
                        // Delete tasks
                        if (input.length() <= 7) {
                            throw new RafayelException("Please indicate which task to delete (i.e. delete 1)");
                        }
                        String[] temp = input.split(" ");
                        int taskNumber = Integer.parseInt(temp[1]);

                        if (taskNumber <= 0 || taskNumber > counter) {
                            throw new RafayelException("Invalid task number.");
                        }
                        taskNumber--;

                        Task deletedTask = tasks.remove(taskNumber);
                        counter--;

                        System.out
                                .println("Noted. I've removed this task:\n  " + deletedTask.toString()
                                        + "\nNow you have "
                                        + counter + " tasks in the list.");
                        break;

                    case UNKNOWN:
                        //
                        throw new RafayelException("Please enter a valid prompt! (i.e. todo/deadline/event)");

                }
            } catch (RafayelException e) {
                System.out.println(e.getMessage());
            }

            System.out.println(LINE + "\n");
        }

        sc.close();
    }
}