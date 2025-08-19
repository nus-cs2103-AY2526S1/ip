import java.util.Scanner;

public class Rafayel {
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
                    System.out.println(i + 1 + "." + tasks[i].getString());
                    // System.out.println(String.format("%d. %s", i + 1, data[i]));
                }
            } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                if (input.length() <= 5) {
                    System.out.println("Please state what task to be marked as complete.");
                } else {
                    String[] temp = input.split(" ");
                    // check if it is an int -- if (temp[2])
                    int taskNumber = Integer.parseInt(temp[1]);
                    if (taskNumber <= 0 && taskNumber >= counter) {
                        System.out.println("Invalid task number.");
                    }
                    taskNumber--;

                    tasks[taskNumber].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskNumber].getString());
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
                            .println("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber].getString());
                }
            } else {
                Task newTask = new Task(input);
                tasks[counter] = newTask;
                counter++;

                System.out.println("added: " + input);
            }

            System.out.println(LINE);
        }

        sc.close();
    }
}
