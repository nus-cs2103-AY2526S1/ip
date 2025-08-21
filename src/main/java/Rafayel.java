import java.util.ArrayList;
import java.util.Scanner;

public class Rafayel {

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

        // FOR CREATING TASKS
        String TASK_START = "Got it. I've added this task:";

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
                if (input.equals("bye")) {
                    System.out.println(END_MSG);
                    break;

                } else if (input.equals("list")) {

                    for (int i = 0; i < counter; i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                        // System.out.println(String.format("%d. %s", i + 1, data[i]));
                    }
                } else if (input.startsWith("mark")) {
                    if (input.length() <= 5) {
                        throw new RafayelException("Please state what task to be marked as complete.");
                    }
                    String[] temp = input.split(" ");
                    int taskNumber = Integer.parseInt(temp[1]);

                    if (taskNumber <= 0 || taskNumber > counter) {
                        throw new RafayelException("Invalid task number.");
                    }
                    taskNumber--;

                    tasks.get(taskNumber).markAsDone();
                    System.out
                            .println("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber).toString());

                } else if (input.startsWith("unmark")) {
                    if (input.length() <= 7) {
                        throw new RafayelException("Please state what task to be marked as complete.");
                    }
                    String[] temp = input.split(" ");
                    int taskNumber = Integer.parseInt(temp[1]);
                    if (taskNumber <= 0 || taskNumber > counter) {
                        throw new RafayelException("Invalid task number.");
                    }
                    taskNumber--;

                    tasks.get(taskNumber).markAsUndone();
                    System.out
                            .println("OK, I've marked this task as not done yet:\n  "
                                    + tasks.get(taskNumber).toString());

                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new RafayelException("Please add in the description of the Todo task.");
                    }

                    System.out.println(TASK_START);
                    Todo newTask = new Todo(input.substring(5).trim());
                    System.out.println("  " + newTask.toString());
                    tasks.add(newTask);
                    System.out.println("Now you have " + ++counter + " tasks in the list.");

                } else if (input.startsWith("deadline")) {
                    if (input.length() <= 10) {
                        throw new RafayelException("Please add in the description of the Deadline task.");
                    }
                    if (!input.contains("/by")) {
                        throw new RafayelException("Deadline format is wrong. Example: deadline [desc] /by [time]");
                    }

                    System.out.println(TASK_START);
                    String[] taskDate = input.substring(9).split("/by ");
                    Deadline newTask = new Deadline(taskDate[0], taskDate[1]);
                    System.out.println("  " + newTask.toString());
                    tasks.add(newTask);
                    System.out.println("Now you have " + ++counter + " tasks in the list.");

                } else if (input.startsWith("event")) {
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

                    System.out.println(TASK_START);
                    String[] taskDate = input.substring(6).split("/");
                    Event newTask = new Event(taskDate[0], taskDate[1].substring(5), taskDate[2].substring(3));
                    System.out.println("  " + newTask.toString());
                    tasks.add(newTask);
                    System.out.println("Now you have " + ++counter + " tasks in the list.");

                } else if (input.startsWith("delete")) {
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
                            .println("Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have "
                                    + counter + " tasks in the list.");

                } else {
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