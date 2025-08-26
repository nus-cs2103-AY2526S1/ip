import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;

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

    private static String SAVED_FILE_NAME = "rafayel.txt";

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
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber).toString());
        } else {
            tasks.get(taskNumber).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber).toString());

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

        String[] taskInfo = input.substring(9).split("/by ");
        LocalDateTime dateTime = handleReadDate(taskInfo[1].trim());
        Deadline newTask = new Deadline(taskInfo[0].trim(), dateTime);
        tasks.add(newTask);

        printNewTaskString(newTask, counter);
    }

    private static LocalDateTime handleReadDate(String input) {
        // check if valid format
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input);
            return dateTime;

        } catch (Exception e) {
            System.out.println("An error has occured while reading the date/time.");
        }

        return null;
    }

    private static String handleDateTimeFormetting(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        // JAN 8 2012 4:00
    }

    private static void handleEventCommand(String input, ArrayList<Task> tasks, int counter) throws RafayelException {
        if (input.length() <= 6) {
            throw new RafayelException("Please add in the description of the Event task.");
        }
        if (!input.contains("/from")) {
            throw new RafayelException("Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }
        if (!input.contains("/to")) {
            throw new RafayelException("Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }

        String[] taskInfo = input.substring(6).split("/");
        LocalDateTime dateTimeFrom = handleReadDate(taskInfo[1].substring(5).trim());
        LocalDateTime dateTimeTo = handleReadDate(taskInfo[2].substring(3).trim());

        Event newTask = new Event(taskInfo[0].trim(), dateTimeFrom, dateTimeTo);

        tasks.add(newTask);

        printNewTaskString(newTask, counter);
    }

    private static ArrayList<Task> loadFile(String fileName) throws IOException {
        // check if file is there
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                // System.out.println(line);
                if (line.trim().isEmpty()) {
                    break;
                }

                String[] parts = line.split(" \\| ");
                // for (int i = 0; i < parts.length; i++) {
                // System.out.println(parts[i]);
                // }
                // System.out.println(parts);
                if (parts.length < 2) {
                    continue;
                }

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;

                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    if (parts.length >= 4) {
                        LocalDateTime by = handleReadDate(parts[3].trim());
                        task = new Deadline(description, by);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        LocalDateTime from = handleReadDate(parts[3].trim());
                        LocalDateTime to = handleReadDate(parts[4].trim());
                        task = new Event(description, from, to);
                    }
                    break;
                }
                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
            bufferedReader.close();
            reader.close();

        } catch (IOException e) {
            System.out.println("Error");
        }

        return tasks;
    }

    private static void saveFile(String fileName, ArrayList<Task> tasks) throws Exception {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (Task task : tasks) {
                fw.write(task.saveTaskName() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("An error occurred while importing.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        String LINE = "____________________________________________________________";
        String START_MSG = LINE + "\n" + " Hello! I'm Rafayel\n" + " What can I do for you?\n" + LINE;
        String END_MSG = " Bye. Hope to see you again soon!\n" + LINE;

        ArrayList<Task> tasks = loadFile(SAVED_FILE_NAME);
        int counter = tasks.size();

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

                    System.out.println("Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have "
                            + counter + " tasks in the list.");
                    break;

                case UNKNOWN:
                    //
                    throw new RafayelException("Please enter a valid prompt! (i.e. todo/deadline/event)");

                }

            } catch (RafayelException e) {
                System.out.println(e.getMessage());
                // return;
            }

            saveFile(SAVED_FILE_NAME, tasks);

            System.out.println(LINE + "\n");
        }

        sc.close();
    }
}