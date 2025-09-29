package johnny.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import johnny.exception.JohnnyException;
import johnny.parser.Parser;
import johnny.tasklist.TaskList;
import johnny.tasks.DeadlineTask;
import johnny.tasks.EventTask;
import johnny.tasks.PeriodTask;
import johnny.tasks.Task;
import johnny.tasks.TodoTask;
import johnny.ui.Ui;

/**
 * A class used in storing and loading tasks from the save file.
 */
public class Storage {
    // Done
    private final String filePath;

    /**
     * Constructs a new instance of Storage using the specified String, which is a
     * file path.
     * 
     * @param filePath String to the path of the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Task that is populated from reading the filePath of
     * the storage object.
     * 
     * The ui argument refers to a Ui object that prints any errors from parsing the
     * text file.
     * 
     * @param ui Ui object that prints any errors from parsing the text file / text
     *           interactions with the user
     * @return an ArrayList<Task> that is passed into a TaskList
     * @throws JohnnyException A custom exception thrown if tasks cannot
     *                         be parsed/added
     * @see TaskList
     */
    public ArrayList<Task> load(Ui ui) throws JohnnyException {
        assert ui != null : "UI cannot be null";
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        if (!file.exists()) {
            this.createNewFile(ui, file);
            return tasks; // return empty arraylist as no initial file was present
        }

        this.parseTasks(ui, file, tasks);

        return tasks;
    }

    /**
     * Creates a new file if the target file does not exist
     * 
     * @param ui   UI object for printing errors
     * @param file File object to be created
     * @throws JohnnyException A custom exception thrown if file cannot be created
     */
    private void createNewFile(Ui ui, File file) throws JohnnyException {
        // if the file doesn't exist, i.e. on first run of program,
        // create a new file
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (SecurityException | IOException e) {
            String msg = "Error creating task file: " + e.getMessage();
            throw new JohnnyException(msg);
        }
    }

    /**
     * Parse through lines in a .txt file to get tasks
     * 
     * @param ui    UI object for printing errors
     * @param file  File to be parsed
     * @param tasks ArrayList<Task> to be populated with tasks
     * @throws JohnnyException A custom exception thrown if tasks cannot be read by
     *                         scanner
     */
    private void parseTasks(Ui ui, File file, ArrayList<Task> tasks) throws JohnnyException {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                // While there are still lines to read, parse the lines into tasks
                String nextLine = sc.nextLine();
                String[] strings = nextLine.split("\\|");
                this.addTasks(ui, tasks, strings);
            }

            sc.close();

        } catch (IOException | NoSuchElementException e) {
            String msg = "Error reading from file: " + e.getMessage();
            throw new JohnnyException(msg);
        }
    }

    /**
     * Add a task to an ArrayList<Task> based on the line parsed
     * 
     * @param ui      UI object for printing errors
     * @param tasks   ArrayList<Task> to be populated
     * @param strings String array of task information
     * @throws JohnnyException A custom exception thrown if tasks cannot be
     *                         parsed/added
     */
    private void addTasks(Ui ui, ArrayList<Task> tasks, String[] strings) throws JohnnyException {
        String typeOfTask = strings[0];
        boolean completed = strings[1].equals("1");
        String taskName = strings[2];

        switch (typeOfTask) {
            case "T":
                tasks.add(new TodoTask(taskName, completed));
                break;

            case "D":
                String deadline = strings[3];
                LocalDate date = Parser.parseDate(deadline, ui);
                if (date != null) {
                    tasks.add(new DeadlineTask(taskName, completed, date));
                } else {
                    String msg = "A deadline task could not be parsed!\nCheck that the date is in dd/MM/yyyy.";
                    throw new JohnnyException(msg);
                }
                break;

            case "E":
                String start = strings[3];
                LocalDateTime startDateTime = Parser.parseDateTime(start, ui);
                String end = strings[4];
                LocalTime endTime = Parser.parseTime(end, ui);
                if (startDateTime != null && endTime != null) {
                    tasks.add(new EventTask(taskName, completed, startDateTime, endTime));
                } else {
                    String msg = "The start or end date/time of an event task could not be parsed!\n" +
                            "Check that the date/time is in dd/MM/yyyy or HH:mm respectively.";
                    throw new JohnnyException(msg);
                }
                break;

            case "P":
                String startString = strings[3];
                LocalDate startDate = Parser.parseDate(startString, ui);
                String endString = strings[4];
                LocalDate endDate = Parser.parseDate(endString, ui);
                if (startDate != null && endDate != null) {
                    tasks.add(new PeriodTask(taskName, completed, startDate, endDate));
                } else {
                    String msg = "The start or end date of a DoWithinPeriod task could not be parsed!\n" +
                            "Check that the date is in dd/MM/yyyy.";
                    throw new JohnnyException(msg);
                }
                break;

            default:
                String msg = "One of the tasks is unknown! Skipping it.";
                throw new JohnnyException(msg);
        }
    }

    /**
     * Takes a TaskList object and writes the Task objects as text
     * form into the filePath of the storage object.
     * 
     * @param tasks A TaskList object that provides the tasks to be saved into the
     *              file path
     * @see TaskList
     */
    public void save(TaskList tasks) {

        assert tasks != null : "Task list should not be null";

        File file = new File(this.filePath);

        if (!file.exists()) {
            // if the file doesn't exist, i.e. on first run of program,
            // create a new file
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (SecurityException | IOException e) {
                System.out.println("Error creating task file: " + e.getMessage());
            }
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < tasks.size(); i++) {
                String storedTask = tasks.getStoredString(i);
                if (i == tasks.size() - 1) {
                    fw.write(storedTask);
                } else {
                    fw.write(storedTask + "\n");
                }
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to saved task file: " + e.getMessage());
        }
    }
}
