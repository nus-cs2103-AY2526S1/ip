package ember;

import ember.exception.InvalidInputException;
import ember.exception.MissingInformationException;
import ember.task.Task;
import ember.task.TodoTask;
import ember.task.DeadlineTask;
import ember.task.EventTask;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles saving and loading the list of tasks to a text file.
 *
 * The file path is fixed to "temp/lines.txt".
 * This class ensures the directory exists and provides
 * methods to write, append, or read tasks from the file.
 */
public class ListFileWriter {
    private static final File file = new File("temp/lines.txt");

    public ListFileWriter() {
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile(); // only create if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("Error creating the file: " + e.getMessage());
        }
    }

    /**
     * Writes the entire list of tasks to the file, overwriting previous content.
     *
     * @param list the list of tasks to write to the file
     */
    public static void listToText(ArrayList<Task> list) {
        try (FileWriter fw = new FileWriter(file, false)) {
            for (Task task : list) {
                fw.write(task.parseFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }
    }

    /**
     * Reads the entire list of tasks from the file.
     *
     * @return list of tasks read from file
     */
    public static ArrayList<Task> readFromFile() throws MissingInformationException, InvalidInputException{
        ArrayList<Task> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks: " + e.getMessage());
        }

        return list;
    }

    /**
     * Parses a line from the file into the correct Task type.
     * Expected format:
     *   [T][X] description
     *   [D][ ] description (by: 2025-01-01)
     *   [E][X] description (from: 2025-01-01, to: 2025-01-02)
     */
    private static Task parseTask(String line) throws MissingInformationException, InvalidInputException {
        boolean isDone = line.charAt(4) == 'X';

        if (line.startsWith("[T]")) {
            String desc = line.substring(6).trim();
            Task t = new TodoTask(desc);
            if (isDone) t.mark();
            return t;

        } else if (line.startsWith("[D]")) {
            int byIndex = line.indexOf("(by:");
            String desc = line.substring(6, byIndex).trim();
            String by = line.substring(byIndex + 5, line.length() - 1).trim(); // yyyy-MM-dd
            Task d = new DeadlineTask(desc, by);
            if (isDone) d.mark();
            return d;

        } else if (line.startsWith("[E]")) {
            int fromIndex = line.indexOf("(from:");
            int toIndex = line.indexOf(", to:");
            String desc = line.substring(6, fromIndex).trim();
            String from = line.substring(fromIndex + 6, toIndex).trim(); // yyyy-MM-dd
            String to = line.substring(toIndex + 5, line.length() - 1).trim(); // yyyy-MM-dd
            Task e = new EventTask(desc, from, to);
            if (isDone) e.mark();
            return e;
        }

        return null;
    }
}
