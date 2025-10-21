package storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.DukeException;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import ui.Ui;

/**
 * Handles the io regarding files.
 */
public class Storage {
    /**
     * Hard coded path for temporary usage. Can be changed to be specified per use.
     */
    private static final String PATH = String.valueOf(Paths.get(System.getProperty("user.dir"),
            "data", "duke.txt"));
    /**
     * List of tasks held by each storage instance. Used to sync program and file.
     */
    private final List<Task> tasks;

    /**
     * Initialises storage instance with list of tasks read from file.
     */
    public Storage() {
        this.tasks = Storage.readFromFile();
    }

    /**
     * Returns if storage instance has empty list of tasks.
     * Used for error checking.
     * @return  boolean of whether list is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Getter function to return lists of tasks for manipulation.
     * @return  List of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Class member that ensures the file path is valid.
     * Prevents IOException from happening unexpectedly.
     */
    private static void ensureExists() {
        try {
            Path p = Paths.get(Storage.PATH);
            if (!Files.exists(p)) {
                if (!Files.exists(p.getParent())) {
                    Files.createDirectories(p.getParent());
                }
                Files.createFile(p);
            }
        } catch (IOException e) {
            Ui.displayError(e);
        }
    }

    /**
     * Reads from file and converts to List of tasks for storage instances
     * @return  List of task
     */
    public static List<Task> readFromFile() {
        //Ensure file path exists
        Storage.ensureExists();

        //Reading file
        List<Task> output = new ArrayList<>();
        Path pf = Paths.get(Storage.PATH);

        try {
            List<String> lines = Files.readAllLines(pf);
            String re = "([DTE])\\s+\\|\\s+([01])\\s+\\|\\s+(.+)";
            List<Integer> corrupted = new ArrayList<>();

            for (int i = 0; i < lines.size(); i++) {
                String s = lines.get(i);

                Pattern p = Pattern.compile(re, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(s);
                if (m.find()) {
                    Task t = null;
                    switch (m.group(1)) {
                    case "T":
                        t = new ToDo(m.group(3));
                        break;
                    case "D":
                        String[] smaller = m.group(3).split("\\|");
                        if (smaller.length != 2) {
                            corrupted.add(i);
                            break;
                        }
                        try {
                            t = new Deadline(smaller[0], Parser.stringToDate(smaller[1]));
                        } catch (DukeException e) {
                            Ui.displayError(e);
                            corrupted.add(i);
                        }
                        break;
                    case "E":
                        smaller = m.group(3).split("\\|");
                        if (smaller.length != 3) {
                            corrupted.add(i);
                            break;
                        }
                        try {
                            t = new Event(smaller[0], Parser.stringToDate(smaller[1]),
                                        Parser.stringToDate(smaller[2]));
                        } catch (DukeException e) {
                            Ui.displayError(e);
                            corrupted.add(i);
                        }
                        break;
                    default:
                        Ui.displayError(new DukeException("Fatal error in reading from file! Attempted to read " + s));
                    }
                    if (t != null) {
                        t.setCompleted(m.group(2).equals("1"));
                        output.add(t);
                    }
                } else {
                    corrupted.add(i);
                }
            }

            //Removing corrupted lines
            if (!corrupted.isEmpty()) {
                for (int i = corrupted.size() - 1; i >= 0; i--) {
                    lines.remove((int) corrupted.get(i));
                }
                Files.write(pf, lines);
            }
        } catch (IOException e) {
            Ui.displayError(e);
        }
        return output;
    }

    /**
     * Writes current list of task back to file to update it.
     * Typically used after modifications to tasks.
     * Required as software specification requires updating as the program runs.
     * @throws DukeException    Handles error during execution
     */
    public void readToFile() throws DukeException {
        Path p = Paths.get(Storage.PATH);
        List<String> lines = new ArrayList<>();

        for (Task t : this.tasks) {
            lines.add(t.toFileString());
        }
        try {
            Files.write(p, lines);
        } catch (IOException e) {
            throw new DukeException("Error while attempting to read back to file" + e.toString());
        }
    }

    /**
     * Function that writes an additional line to file.
     * Currently not used as having all functions use readToFile makes code a bit
     * more maintainable. Could be used if software specifications change.
     * @param text  String of characters to be added to file
     */
    public static void appendToFile(String text) {
        Storage.ensureExists();
        try {
            FileWriter fw = new FileWriter(Storage.PATH, true);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            Ui.displayError(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Storage s) {
            return this.tasks.equals(s.tasks);
        }
        return false;
    }
}
