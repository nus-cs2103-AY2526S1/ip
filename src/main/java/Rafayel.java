import java.util.Scanner;

public class Rafayel {

    // public String getDescriptionFromInput(String input, Integer taskLength) {

    // String[] temp = input.split(" ");
    // if ()
    // return ;
    // }
    public static void main(String[] args) {
        String LINE = "____________________________________________________________";
        String START_MSG = LINE + "\n"
                + " Hello! I'm Rafayel\n"
                + " What can I do for you?\n"
                + LINE;
        String END_MSG = " Bye. Hope to see you again soon!\n";

        Task[] tasks = new Task[100]; // assume less than 100
        // String[] data = new String[100];
        int counter = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println(START_MSG);

        while (true) {
            String input = sc.nextLine();
            System.out.println(LINE);

            if (input.equals("bye")) {
                System.out.println(END_MSG);
                break;

            } else if (input.equals("list")) {

                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + "." + tasks[i].toString());
                    // System.out.println(String.format("%d. %s", i + 1, data[i]));
                }
            } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                if (input.length() <= 5) {
                    System.out.println("Please state what task to be marked as complete.");
                } else {
                    String[] temp = input.split(" ");
                    int taskNumber = Integer.parseInt(temp[1]);

                    if (taskNumber <= 0 && taskNumber >= counter) {
                        System.out.println("Invalid task number.");
                    }
                    taskNumber--;

                    tasks[taskNumber].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskNumber].toString());
                }

            } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                if (input.length() <= 7) {
                    System.out.println("Please state what task to be marked as complete.");
                } else {
                    String[] temp = input.split(" ");
                    int taskNumber = Integer.parseInt(temp[1]);
                    if (taskNumber <= 0 && taskNumber >= counter) {
                        System.out.println("Invalid task number.");
                    }
                    taskNumber--;

                    tasks[taskNumber].markAsUndone();
                    System.out
                            .println("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber].toString());
                }
            } else {
                // CREATE TASKS
                String TASK_START = "Got it. I've added this task:";
                String[] temp = input.split(" ");

                boolean taskCreated = true;
                if (temp[0].equals("todo")) {
                    System.out.println(TASK_START);
                    Todo newTask = new Todo(input.substring(5));
                    System.out.println("  " + newTask.toString());
                    tasks[counter] = newTask;
                    counter++;

                } else if (temp[0].equals("deadline")) {
                    System.out.println(TASK_START);
                    String[] taskDate = input.substring(9).split("/by ");
                    Deadline newTask = new Deadline(taskDate[0], taskDate[1]);
                    System.out.println("  " + newTask.toString());
                    tasks[counter] = newTask;
                    counter++;

                } else if (temp[0].equals("event")) {
                    System.out.println(TASK_START);
                    String[] taskDate = input.substring(6).split("/");
                    Event newTask = new Event(taskDate[0], taskDate[1].substring(5), taskDate[2].substring(3));
                    System.out.println("  " + newTask.toString());
                    tasks[counter] = newTask;
                    counter++;

                } else {
                    taskCreated = false;
                    System.out.println("Please enter a valid kind of task type!");
                }

                if (taskCreated) {
                    System.out.println("Now you have " + counter + " tasks in the list.\n");
                }
            }

            System.out.println(LINE);
        }

        sc.close();
    }
}
