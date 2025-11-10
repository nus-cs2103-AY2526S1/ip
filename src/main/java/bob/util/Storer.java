package bob.util;

import bob.misc.InvalidDataFormatException;
import bob.tasks.Deadline;
import bob.tasks.Task;
import bob.tasks.TaskList;
import bob.tasks.ToDo;
import bob.tasks.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Storer class that is accesses data to save or load tasks
 */
public class Storer {
    private final File file;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * Constructor for Storer class
     * @param filePath path to access data file
     */
    public Storer(String filePath) {
        this.file = new File(filePath);

        File parentFolder = this.file.getParentFile();
        if (parentFolder != null && !parentFolder.exists()) {
            boolean dirCreated = parentFolder.mkdirs();
            assert dirCreated: "directory should have been created";
        }

        if (!this.file.exists()) {
            try {
                boolean fileCreated = this.file.createNewFile();
                assert fileCreated: "data file should have been created";
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the associated TaskList into data file
     * @param taskList taskList to be saved
     */
    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            assert taskArrayList != null: "returned ArrayList should be non null";
            for (Task things : taskArrayList) {
                writer.write(things.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error accessing data: " + e.getMessage() + "\n");
        }
    }

    /**
     * Loads data from data file into associated taskList
     * @param taskList taskList to be modified
     * @return taskList
     */
    public TaskList load(TaskList taskList) {
        if (!this.file.exists()) {
            return taskList;
        }

        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" ", 2);
                String command = parts[0].toLowerCase();

                switch (command) {
                case "t":
                    parseToDo(line, taskList);
                    break;
                case "d":
                    parseDeadline(line, taskList);
                    break;
                case "e":
                    parseEvent(line, taskList);
                    break;
                default:
                    throw new InvalidDataFormatException("Invalid Task format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error accessing data: " + e.getMessage() + "\n");
        } catch (InvalidDataFormatException e) {
            System.out.println(e.getMessage());
        }

        return taskList;
    }


    private void parseToDo(String line, TaskList taskList) throws InvalidDataFormatException {
        Pattern p = Pattern.compile("^T /done (\\d) /des (.+)$");
        Matcher m = p.matcher(line);
        if (!m.matches()) {
            throw new InvalidDataFormatException("Invalid Task format: " + line);
        }
        boolean isDone = m.group(1).equals("1");
        String desc = m.group(2);
        taskList.fileAddItem(new ToDo(desc, isDone));
    }

    private void parseDeadline(String line, TaskList taskList) throws InvalidDataFormatException {
        Pattern p = Pattern.compile("^D /done (\\d) /des (.+) /by (.+)$");
        Matcher m = p.matcher(line);
        if (!m.matches()) {
            throw new InvalidDataFormatException("Invalid Task format: " + line);
        }
        boolean isDone = m.group(1).equals("1");
        String desc = m.group(2);
        String byStr = m.group(3);

        try {
            LocalDate byDate = LocalDate.parse(byStr, DATE_FORMAT);
            taskList.fileAddItem(new Deadline(desc, isDone, byDate));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format, use yyyy-MM-dd\n");
        }
    }

    private void parseEvent(String line, TaskList taskList) throws InvalidDataFormatException {
        Pattern p = Pattern.compile("^E /done (\\d) /des (.+) /from (.+) /to (.+)$");
        Matcher m = p.matcher(line);
        if (!m.matches()) {
            throw new InvalidDataFormatException("Invalid Task format: " + line);
        }
        boolean isDone = m.group(1).equals("1");
        String desc = m.group(2);
        String fromStr = m.group(3);
        String toStr = m.group(4);

        try {
            LocalDate from = LocalDate.parse(fromStr, DATE_FORMAT);
            LocalDate to = LocalDate.parse(toStr, DATE_FORMAT);
            taskList.fileAddItem(new Event(desc, isDone, from, to));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format, use yyyy-MM-dd\n");
        }
    }

}
