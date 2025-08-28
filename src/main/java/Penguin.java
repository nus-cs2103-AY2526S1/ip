import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Penguin manages a list of user tasks and supports basic task management:
 * adding new tasks, marking tasks as completed or not done, deleting tasks,
 * and listing all current tasks. It reads user commands, processes them,
 * and prints corresponding responses. The program runs in a loop until the
 * user enters the exit command "bye".
 */

public class Penguin {


    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            StringBuilder content = new StringBuilder();
            for (Task task : tasks) {
                String taskType = "";
                String additionalInfo = "";

                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Deadline) {
                    taskType = "D";
                    additionalInfo = " | " + ((Deadline) task).by;
                } else if (task instanceof Event) {
                    taskType = "E";
                    additionalInfo = " | " + ((Event) task).from + " | " + ((Event) task).to;
                }

                content.append(taskType + " | " + (task.getDone() ? "1" : "0") + " | " + task.getDescription() + additionalInfo + "\n");
            }

            writeToFile("data/tasks.txt", content.toString());
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("data/tasks.txt");

        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length >= 3) {
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    Task task = null;
                    if (taskType.equals("T")) {
                        task = new Todo(description);
                    } else if (taskType.equals("D") && parts.length >= 4) {
                        String deadline = parts[3];
                        task = new Deadline(description, deadline);
                    } else if (taskType.equals("E") && parts.length >= 5) {
                        String from = parts[3];
                        String to = parts[4];
                        task = new Event(description, from, to);
                    }

                    if (task != null) {
                        task.setDone(isDone);
                        tasks.add(task);
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // File doesn't exist, return empty list
        }

        return tasks;
    }

    /**
     * Entry point of the Penguin chatbot application.
     * Initializes the chatbot and begins reading user commands from standard input.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        ArrayList<Task> tasks = loadTasks();
        System.out.println("Hello! I'm Penguin\nWhat can I do for you?\n");

        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
            String line = sc.nextLine();
            try {
                if (line.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (line.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (line.startsWith("mark ")) {
                    String num = line.substring(5).trim();
                    int idx = Integer.parseInt(num) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        Task task = tasks.get(idx);
                        task.setDone(true);
                        saveTasks(tasks);
                        System.out.println("Nice! I've marked this task as done:\n" + task);
                    } else {
                        System.out.println("invalid task");
                    }
                } else if (line.startsWith("unmark ")) {
                    String num = line.substring(7).trim();
                    int idx = Integer.parseInt(num) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        Task task = tasks.get(idx);
                        task.setDone(false);
                        saveTasks(tasks);
                        System.out.println("Nice! I've marked this task as not done yet:\n" + task);
                    } else {
                        System.out.println("invalid task");
                    }
                } else if (line.startsWith("event")) {
                    String body = line.length() > 5 ? line.substring(5).trim() : "";
                    if (body.isEmpty()) {
                        throw new PenguinException("The description of an event cannot be empty.");
                    }
                    int fromPos = body.indexOf("/from");
                    int toPos = body.indexOf("/to");
                    if (fromPos != -1 && toPos != -1 && toPos > fromPos) {
                        String desc = body.substring(0, fromPos).trim();
                        String from = body.substring(fromPos + 5, toPos).trim();
                        String to = body.substring(toPos + 3).trim();
                        Task t = new Event(desc, from, to);
                        tasks.add(t);
                        saveTasks(tasks);
                        System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("invalid task");
                    }
                } else if (line.startsWith("deadline")) {
                    String body = line.length() > 8 ? line.substring(8).trim() : "";
                    if (body.isEmpty()) {
                        throw new PenguinException("The description of a deadline cannot be empty.");
                    }
                    int byPos = body.indexOf("/by");
                    if (byPos != -1) {
                        String desc = body.substring(0, byPos).trim();
                        String by = body.substring(byPos + 3).trim();
                        Task t = new Deadline(desc, by);
                        tasks.add(t);
                        saveTasks(tasks);
                        System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("invalid task");
                    }
                } else if (line.startsWith("todo")) {
                    String desc = line.length() > 4 ? line.substring(4).trim() : "";
                    if (desc.isEmpty()) {
                        throw new PenguinException("The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(desc);
                    tasks.add(t);
                    saveTasks(tasks);
                    System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new PenguinException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (PenguinException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
            }
        }
    }
}