package jerome.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jerome.TaskList;
import jerome.task.Deadline;
import jerome.task.Event;
import jerome.task.Task;
import jerome.task.Todo;

/**
 * Encapsulates loading/saving tasks to and from Jerome.txt
 * Format:
 *  T | 1/0 | description
 *  D | 1/0 | description | by
 *  E | 1/0 | description | from | to
 *
 */

public class Storage {
    private static final String DIV = " | ";
    private final Path loc;

    /**
     * Constructs a default Storage object with the file path set to {@code data/Jerome.Jerome.txt}.
     */
    public Storage() {
        this(Paths.get("data", "Jerome.txt"));
    }

    /**
     * Constructs a Storage object with a custom file path.
     *
     * @param loc Path to the file for saving/loading task data.
     */
    public Storage(Path loc) {
        this.loc = loc;
    }

    /**
     * Ensures the file and its parent directory exist.
     * Creates them if they do not exist.
     */
    public void makeExist() {
        try {
            if (loc.getParent() != null) {
                Files.createDirectories((loc.getParent()));
            }
            if (!Files.exists((loc))) {
                Files.write(loc, new byte[0]);
            }
            assert Files.exists(loc) : "File does not exist after creation attempt: " + loc;
        } catch (IOException e) {
            System.out.println("Warning, path: " + loc + " not found. Changes cannot be saved.");
        }
    }

    /**
     * Reads Jerome.Jerome.txt (if it exists) and adds the saved tasks into the list when Jerome.Jerome.java is run
     *
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(loc)) {
            return tasks;
        }

        try {
            List<String> cont = Files.readAllLines(loc, StandardCharsets.UTF_8);
            assert cont != null && !cont.isEmpty() : "Loaded file content is empty or null.";
            int lineNum = 0;
            for (String line: cont) {
                lineNum++;
                if (line.isEmpty()) {
                    continue;
                }
                String[] subStr = line.split("\\s\\|\\s");
                assert subStr.length >= 3 : "Corrupted line " + lineNum + " (Too few fields).";
                String type = subStr[0].trim();
                String status = subStr[1].trim();
                String desc = subStr[2].trim();
                boolean isUnmark;

                assert status.equals("0") || status.equals("1") : "Invalid status value in line " + lineNum;
                isUnmark = status.equals("1");

                try {
                    Task task;
                    switch(type) {
                    case "T":
                        task = new Todo(desc);
                        break;
                    case "D":
                        assert subStr.length >= 4 : "Corrupted line " + lineNum + " (Missing deadline '/by').";
                        String by = subStr[3].trim();
                        task = new Deadline(desc, by);
                        break;
                    case "E":
                        assert subStr.length >= 5 : "Corrupted line " + lineNum + " (Missing event '/from' or '/to').";
                        String from = subStr[3].trim();
                        String to = subStr[4].trim();
                        task = new Event(desc, from, to);
                        break;
                    default:
                        System.out.println("Warning: Skipping corrupted line "
                                + lineNum + " (Unknown type: " + type + ").");
                        continue;
                    }
                    if (isUnmark) {
                        task.mark();
                    }
                    tasks.add(task);
                } catch (DateTimeParseException e1) {
                    System.out.println("Warning: Skipping line "
                            + lineNum + " (Invalid datetime format).");
                } catch (Exception e2) {
                    System.out.println("Warning: Skipping corrupted line "
                            + lineNum + " (Could not construct task: " + e2.getMessage() + ").");
                }
            }

        } catch (IOException e) {
            System.out.println("Warning: Could not load tasks from " + loc + ". Starting fresh.");
        }
        return tasks;
    }

    /**
     * Formats every task in a List of tasks, and adds them into Jerome.Jerome.txt
     *
     */
    public void save(TaskList tasks) {
        assert tasks != null : "TaskList is Null";

        List<String> output = tasks.getAll().stream()
                .map(this::formatting)
                .collect(Collectors.toList());
        assert !output.isEmpty() : "Formatted task list is empty.";

        try {
            assert loc != null : "The file path is null.";

            Files.write(loc, output, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Warning: Could not save tasks to " + loc + ".");
        }
    }

    /**
     * Formats a task into a single string before being added to Jerome.Jerome.txt
     * Format:
     *  T | 1/0 | description
     *  D | 1/0 | description | by
     *  E | 1/0 | description | from | to
     *
     */
    private String formatting(Task t) {
        int status = t.isDone() ? 1 : 0;
        if (t instanceof Todo) {
            return "T" + DIV + status + DIV + t.getDescription();
        } else if (t instanceof Deadline d) {
            return "D" + DIV + status + DIV + d.getDescription() + DIV + d.getByRaw();
        } else if (t instanceof Event eve) {
            return "E" + DIV + status + DIV + eve.getDescription() + DIV + eve.getFromRaw() + DIV + eve.getToRaw();
        } else {
            return "T" + DIV + status + DIV + t;
        }
    }
}
