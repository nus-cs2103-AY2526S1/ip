package peppa.storage;

import peppa.exception.SaveFileCorruptedException;
import peppa.task.Task;
import peppa.task.TaskList;
import peppa.task.ToDo;
import peppa.task.Event;
import peppa.task.Deadline;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Persists the user’s tasks: saves the current list to disk and recreates it on start-up.
 * Internally wraps a {@link java.io.File} and handles both serialization and parsing.
 */
public class Storage {
    private final File file;

    /**
     * Builds a Storage backed by the given path and guarantees that
     * the file (and its parent directory) exist, loading any data found.
     *
     * @param filePath location of the save file relative to the project root.
     */
    public Storage(String filePath) { //creating a new instance of Save will try to create a saveFile
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        this.file = new File(filePath);
        try {
            ensureFileAndParentExist();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Serialises every task in {@code tasks} to the backing file,
     * overwriting any previous contents.
     *
     * @param tasks current in-memory task list.
     * @return {@code true} on success, {@code false} if any I/O error occurs.
     */
    public boolean save(TaskList tasks) {
        assert tasks != null : "TaskList to save should not be null";
        ArrayList<Task> tl = tasks.getTaskList();
        assert tl != null : "Task list should not be null";
        try {
            recreateFileForSave();
            try (FileWriter writer = new FileWriter(file)) {
                writeAllTasks(tl, writer);
            }
            System.out.println("Successfully saved to ./data/peppa.command.Peppa.txt");
            return true;
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reads the backing file, reconstructs all tasks it contains and
     * returns them as a list; returns {@code null} if the file is missing
     * or irreparably corrupted.
     *
     * @return list of tasks recovered from disk, or {@code null} on failure.
     * @throws IOException if a low-level I/O error prevents reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        assert file != null : "File path should not be null";
        if (!file.exists()) {
            return null;
        }
        try {
            return readAllTasksFromFile();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        } catch (SaveFileCorruptedException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Ensures the parent directory and file exist, creating them if necessary.
     *
     * @throws IOException if an I/O error occurs while creating directories or file.
     */
    private void ensureFileAndParentExist() throws IOException {
        if (this.file.exists()) {
            assert this.file.isFile() : "file should be a file if it exists";
            // attempt to read to verify file is usable
            load();
            return;
        }

        File parentDir = this.file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            assert created : "Parent directory should be created successfully";
        }
        boolean createdFile = this.file.createNewFile();
        assert createdFile : "Save file should be created successfully";
    }

    /**
     * Prepares the backing file for a fresh save by deleting any existing file
     * and creating a new empty file.
     *
     * @throws IOException if an I/O error occurs.
     */
    private void recreateFileForSave() throws IOException {
        if (file.exists()) {
            boolean deleted = file.delete();
            assert deleted : "Existing file should be deleted successfully";
        }
        boolean created = file.createNewFile();
        assert created : "File should be created successfully for saving";
    }

    /**
     * Writes all tasks to the provided writer, one line per task.
     *
     * @param tasks  list of tasks to write.
     * @param writer writer to use for output.
     * @throws IOException if an I/O error occurs while writing.
     */
    private void writeAllTasks(ArrayList<Task> tasks, FileWriter writer) throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            String saveFileDesc = formatTaskForSave(tasks.get(i));
            assert saveFileDesc != null : "Save file description should not be null";
            writer.write(saveFileDesc + "\n");
        }
    }

    /**
     * Returns the save-file representation of a task.
     *
     * @param task task to format.
     * @return formatted save-file line.
     */
    private String formatTaskForSave(Task task) {
        return task.toSaveFileFormat();
    }

    /**
     * Reads all tasks from the backing file and returns them as a list.
     *
     * @return list of tasks recovered from disk, or {@code null} on failure.
     * @throws FileNotFoundException if the file is not found.
     * @throws SaveFileCorruptedException if the file contains an unknown entry.
     */
    private ArrayList<Task> readAllTasksFromFile() throws FileNotFoundException, SaveFileCorruptedException {
        ArrayList<Task> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                assert line != null : "Line read from file should not be null";
                Task t = parseLineToTask(line);
                if (t != null) {
                    data.add(t);
                }
            }
        }
        return data;
    }

    /**
     * Parses a single save-file line into a Task instance.
     *
     * @param line save-file line to parse.
     * @return parsed Task instance.
     * @throws SaveFileCorruptedException if the line is malformed or unknown.
     */
    private Task parseLineToTask(String line) throws SaveFileCorruptedException {
        String[] splitLine = line.split(" \\| ");
        assert splitLine.length >= 3 : "Save file line should have at least 3 fields";

        Task newTask;
        switch (splitLine[0]) {
            case "T":
                newTask = new ToDo(splitLine[2]);
                break;
            case "E":
                assert splitLine.length >= 5 : "Event should have at least 5 fields";
                newTask = new Event(splitLine[2], splitLine[3], splitLine[4]);
                break;
            case "D":
                assert splitLine.length >= 4 : "Deadline should have at least 4 fields";
                newTask = new Deadline(splitLine[2], splitLine[3]);
                break;
            default:
                throw new SaveFileCorruptedException();
        }

        if (Objects.equals(splitLine[1], "1")) {
            newTask.markAsDone();
        }
        return newTask;
    }
}
