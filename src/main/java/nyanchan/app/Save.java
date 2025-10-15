package nyanchan.app;

import nyanchan.exceptions.IncorrectFormatException;

import nyanchan.tasks.Task;
import nyanchan.tasks.Todo;
import nyanchan.tasks.Event;
import nyanchan.tasks.Deadline;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles saving and loading of tasks to and from disk.
 */
public class Save {
    private static final String FILE_PATH = "./data/nyanchan.txt";

    /**
     * Reads tasks from the save file into the given list.
     * Creates the file and folder if they do not exist.
     *
     * @param to list to load tasks into
     * @throws FileNotFoundException if the file cannot be found
     */
    public static void read(List<Task> to) throws FileNotFoundException {
        File file = new File(FILE_PATH);

        // Create folder and file if missing
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                return;
            } catch (IOException e) {
                System.out.println("HISS! Error in creating file.");
                return;
            }
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                to.add(Save.unserialize(line));
            } catch (IncorrectFormatException e) {
                System.out.println("HISS! Data corrupted.");
            }
        }
    }

    /**
     * Writes all tasks in the list to the save file.
     * Creates directories if needed.
     *
     * @param from list of tasks to save
     */
    public static void write(List<Task> from) {
        File file = new File(Save.FILE_PATH);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task t : from) {
                try {
                    writer.write(Save.serialize(t) + System.lineSeparator());
                } catch (IncorrectFormatException e) {
                    System.out.println("HISS! Error in serializing.");
                }
            }
        } catch (IOException e) {
            System.out.println("HISS! Error in writer.");
        }
    }

    /**
     * Converts a {@code Task} into its string form for saving.
     *
     * @param t task to serialize
     * @return serialized string
     * @throws IncorrectFormatException if task format is invalid
     */
    public static String serialize(Task t) throws IncorrectFormatException {
        String done = t.getDone() ? "1" : "0";
        if (t instanceof Todo) {
            return "T | " + done + " | " + t.getDescription();
        } else if (t instanceof Deadline d) {
            return "D | " + done + " | " + d.getDescription() + " | " + d.getDeadline().format(Deadline.INPUT_FORMAT);
        } else if (t instanceof Event e) {
            return "E | " + done + " | " + e.getDescription() + " | " + e.getStartDate().format(Event.INPUT_FORMAT)
                    + " | " + e.getEndDate().format(Event.INPUT_FORMAT);
        } else {
            throw new IncorrectFormatException();
        }
    }

    /**
     * Converts a line of text into a {@code Task} object.
     *
     * @param s serialized task line
     * @return the deserialized task
     * @throws IncorrectFormatException if line format is invalid
     */
    public static Task unserialize(String s) throws IncorrectFormatException {
        String[] elements = s.split(" \\| ");
        Task task;
        if (elements[0].equals("T")) {
            task = new Todo(elements[2]);
        } else if (elements[0].equals("D")) {
            task = new Deadline(elements[2], elements[3]);
        } else if (elements[0].equals("E")) {
            task = new Event(elements[2], elements[3], elements[4]);
        } else {
            throw new IncorrectFormatException();
        }
        boolean done = elements[1].equals("1");
        if (done) {
            task.markAsDone();
        }
        return task;
    }
}
