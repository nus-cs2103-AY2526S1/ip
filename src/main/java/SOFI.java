import java.util.Scanner;
import java.util.ArrayList;

public class SOFI {
    static class Task {
        protected String description;
        protected boolean isDone;

        Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        // Mark task as done
        void markAsDone() {
            isDone = true;
        }

        // Mark task as not done
        void markAsNotDone() {
            isDone = false;
        }

        // Get task status as [ ] or [X]
        String getStatusIcon() {
            return isDone ? "[X]" : "[ ]";
        }
    }
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n" +
                "Hello! I'm SOFI\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    System.out.println((i + 1) + "." + task.getStatusIcon() + " " + task.description);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task task = taskList.get(taskNumber);
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:\n" + "   " + task.getStatusIcon() + " " +
                        task.description);
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task task = taskList.get(taskNumber);
                task.markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:\n" + "   " + task.getStatusIcon() + " " +
                        task.description);
                System.out.println("____________________________________________________________");
            } else {
                Task newTask = new Task (userInput);
                taskList.add(newTask);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
    }
}

