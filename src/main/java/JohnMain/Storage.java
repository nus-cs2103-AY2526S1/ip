package JohnMain;

import JohnException.JohnException;

import Parser.DateParser;

import Task.Deadline;
import Task.Event;
import Task.TaskItem;
import Task.TaskList;
import Task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Handles persistence of tasks to and from the filesystem.
 * Responsible for reading a saved task list at startup and saving updates
 * after task mutations.
 */
public class Storage {
    private final File file = new File("data/john.txt");

    /**
     * Constructs a John.John.Storage bound to the given file path.
     * creating the file and directory if they are missing.
     */
    protected Storage() {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("[Warn] Cannot create save file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from disk into memory.
     *
     * @return Task list with tasks from the save file; empty list if file is missing.
     */
    public TaskList load() {
        TaskList list = new TaskList();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                list.add(deserialize(line));
            }
        } catch (Exception e) {
            System.out.println("[Warn] Load failed: " + e.getMessage());
        }
        return list;
    }

    /**
     * Writes the given tasks to disk, overwriting any existing contents.
     *
     * @param tasks List of tasks to save.
     */
    protected void save(TaskList tasks) {
        try (FileWriter fw = new FileWriter(file)) {
            for (TaskItem t : tasks.view()) {
                fw.write(t.toSaveString());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("[Warn] Save failed: " + e.getMessage());
        }
        assert file.exists() : "John.John.Storage.save: file should exist post-save";
    }

    /**
     * Converted the saved string to corresponding task item.
     *
     * @param line Saved line in data file
     * @return Task item corresponding to line in data file
     * @throws JohnException If saved item is invalid or in unknown format
     */
    private TaskItem deserialize(String line) throws JohnException {
        String[] parts = line.split("\\|");
        assert parts.length >= 3 : "Corrupt load: too few fields";
        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String desc = parts[2];

        switch (type) {
        case "T":
            assert parts.length == 3 : "John.John.Storage T expects 3 fields";
            return new Todo(desc, isDone);
        case "D":
            assert parts.length == 4 : "John.John.Storage D expects 4 fields";
            LocalDate by = DateParser.parseStorage(parts[3]);
            return new Deadline(desc, isDone, by);
        case "E":
            assert parts.length == 5 : "John.John.Storage E expects 5 fields";
            LocalDate from = DateParser.parseStorage(parts[3]);
            LocalDate to   = DateParser.parseStorage(parts[4]);
            return new Event(desc, isDone, from, to);
        default:
            throw new JohnException("Unknown type: " + type);
        }
    }
}