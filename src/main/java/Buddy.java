import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Buddy {
    private static final String DATA_DIR = "./data";
    private static final String DATA_FILE = "./data/buddy.txt";
    
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;
        
        taskCount = loadTasks(tasks);
        
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Buddy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while (true) {
            input = scanner.nextLine();
            
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Task number is out of range.");
                        System.out.println("____________________________________________________________");
                    } else {
                        tasks[taskIndex].markAsDone();
                        saveTasks(tasks, taskCount);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskIndex]);
                        System.out.println("____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please provide a valid task number.");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Task number is out of range.");
                        System.out.println("____________________________________________________________");
                    } else {
                        tasks[taskIndex].markAsNotDone();
                        saveTasks(tasks, taskCount);
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskIndex]);
                        System.out.println("____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please provide a valid task number.");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("delete ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Task number is out of range.");
                        System.out.println("____________________________________________________________");
                    } else {
                        Task deletedTask = tasks[taskIndex];
                        for (int i = taskIndex; i < taskCount - 1; i++) {
                            tasks[i] = tasks[i + 1];
                        }
                        taskCount--;
                        saveTasks(tasks, taskCount);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + deletedTask);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please provide a valid task number.");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.equals("todo")) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________________________________________________");
                } else {
                    Task newTask = new Todo(description);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    saveTasks(tasks, taskCount);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.equals("deadline")) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! The description of a deadline cannot be empty.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("deadline ")) {
                try {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Please use the format: deadline <description> /by <time>");
                        System.out.println("____________________________________________________________");
                    } else {
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        Task newTask = new Deadline(description, by);
                        tasks[taskCount] = newTask;
                        taskCount++;
                        saveTasks(tasks, taskCount);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + newTask);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please use the format: deadline <description> /by <time>");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.equals("event")) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! The description of an event cannot be empty.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("event ")) {
                try {
                    String[] parts = input.substring(6).split(" /from ");
                    if (parts.length < 2) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Please use the format: event <description> /from <start> /to <end>");
                        System.out.println("____________________________________________________________");
                    } else {
                        String description = parts[0].trim();
                        String[] timeParts = parts[1].split(" /to ");
                        if (timeParts.length < 2 || description.isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                            System.out.println("____________________________________________________________");
                            System.out.println(" OOPS!!! Please use the format: event <description> /from <start> /to <end>");
                            System.out.println("____________________________________________________________");
                        } else {
                            String from = timeParts[0].trim();
                            String to = timeParts[1].trim();
                            Task newTask = new Event(description, from, to);
                            tasks[taskCount] = newTask;
                            taskCount++;
                            saveTasks(tasks, taskCount);
                            System.out.println("____________________________________________________________");
                            System.out.println(" Got it. I've added this task:");
                            System.out.println("   " + newTask);
                            System.out.println(" Now you have " + taskCount + " tasks in the list.");
                            System.out.println("____________________________________________________________");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please use the format: event <description> /from <start> /to <end>");
                    System.out.println("____________________________________________________________");
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }
        
        scanner.close();
    }
    
    private static int loadTasks(Task[] tasks) {
        int taskCount = 0;
        try {
            Path dataPath = Paths.get(DATA_FILE);
            if (!Files.exists(dataPath)) {
                Path dirPath = Paths.get(DATA_DIR);
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                return 0;
            }
            
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(dataPath));
            for (String line : lines) {
                Task task = parseTaskFromFile(line);
                if (task != null && taskCount < tasks.length) {
                    tasks[taskCount] = task;
                    taskCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskCount;
    }
    
    private static void saveTasks(Task[] tasks, int taskCount) {
        try {
            Path dirPath = Paths.get(DATA_DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            
            ArrayList<String> lines = new ArrayList<>();
            for (int i = 0; i < taskCount; i++) {
                lines.add(formatTaskForFile(tasks[i]));
            }
            Files.write(Paths.get(DATA_FILE), lines);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    private static String formatTaskForFile(Task task) {
        String isDone = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + isDone + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + isDone + " | " + deadline.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + isDone + " | " + event.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }
    
    private static Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) return null;
            
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            
            Task task = null;
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D") && parts.length >= 4) {
                String by = parts[3];
                task = new Deadline(description, by);
            } else if (type.equals("E") && parts.length >= 5) {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
            }
            
            if (task != null && isDone) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            return null;
        }
    }
}
