package uxie.interfaces;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import uxie.exceptions.UxieIOException;
import uxie.exceptions.UxieSyntaxException;
import uxie.tasks.Deadline;
import uxie.tasks.Event;
import uxie.tasks.Task;
import uxie.tasks.ToDo;

/**
 * Stores and reads Uxie's data with local file.
 *
 * @author junyan-k
 */
public class Storage {

    /** Default file path to task file. */
    private static final String DEFAULT_TASK_FILEPATH = "./tasks.csv";

    /** File path to task file. */
    private final String taskFilePath;

    /**
     * Generates Storage with {@link #DEFAULT_TASK_FILEPATH}.
     */
    public Storage() {
        taskFilePath = DEFAULT_TASK_FILEPATH;
    }

    /**
     * Generates Storage with specified taskFilePath.
     */
    public Storage(String taskFilePath) {
        this.taskFilePath = taskFilePath;
    }

    /**
     * This class represents the result of Storage::readTasks.
     * Code for this class was generated with AI.
     */
    public static class ReadTaskResult {

        /** Resulting list of Tasks. */
        private List<Task> tasks;

        /** Resulting list of indices of malformed rows. */
        private List<String> malformedRows;

        /**
         * Generates a ReadTaskResult.
         */
        public ReadTaskResult(List<String> malformedRows, List<Task> tasks) {
            this.malformedRows = malformedRows;
            this.tasks = tasks;
        }

        /**
         * Returns the list of Tasks.
         */
        public List<Task> getTasks() {
            return tasks;
        }

        /**
         * Returns malformed row indices as String joined by ",".
         */
        public String getMalformedRows() {
            return String.join(",", malformedRows);
        }

    }

    /**
     * Returns local task CSV file.
     * If not found, creates it and returns.
     *
     * @return File object referencing local task CSV file.
     * @throws UxieIOException I/O Exception encountered when trying to create new file
     */
    private File getTaskFile() throws UxieIOException {
        try {
            File taskFile = new File(taskFilePath);
            taskFile.createNewFile(); // creates file if it doesn't exist.
            return taskFile;
        } catch (IOException e) {
            throw new UxieIOException("I can't find your task file.");
        }
    }

    /**
     * Stores Task into CSV file.
     * Format is: "[type],[completion],[description],[tag],(time1),(time2)"
     * <p>
     * type: T if Todos, D if Deadline, E if Event
     * completion: 1 if completed, 0 otherwise
     * description: description of Task
     * tags: tag of Task. if without tag, this field is blank
     * time1 (if needed): if Deadline, deadline. if Event, from
     * time2 (if needed): if Event, to
     *
     * @param task Task to store.
     * @throws UxieIOException I/O exception during writing of file
     */
    public void storeTask(Task task) throws UxieIOException {
        List<String> arguments = new ArrayList<>();
        arguments.add(task.getSymbol());
        arguments.add(task.isCompleted() ? "1" : "0");
        arguments.add(task.getDesc());
        arguments.add(task.getTag());
        for (LocalDateTime dt: task.getTimeArguments()) {
            arguments.add(DateTimeParse.parseStorageWrite(dt));
        }

        try (CSVWriter taskFileWriter = new CSVWriter(new FileWriter(getTaskFile(), true))) {
            taskFileWriter.writeNext(arguments.toArray(new String[0]));
        } catch (IOException e) {
            throw new UxieIOException("I can't seem to write down this task.");
        }
    }

    /**
     * Toggles completion status of task matching index.
     * (complete -> incomplete and vice versa)
     *
     * @param index row index of Task in CSV file.
     * @throws UxieIOException I/O exception during editing of file.
     */
    public void toggleTaskCompletion(int index) throws UxieIOException {
        try (CSVReader taskFileReader = new CSVReader(new FileReader(getTaskFile()))) {
            List<String[]> taskRows = taskFileReader.readAll();
            taskFileReader.close();

            String[] taskRow = taskRows.get(index);
            taskRow[1] = taskRow[1].equals("0") ? "1" : "0";

            CSVWriter taskFileWriter = new CSVWriter(new FileWriter(getTaskFile()));
            taskFileWriter.writeAll(taskRows);
            taskFileWriter.close();
        } catch (IOException | CsvException e) {
            throw new UxieIOException("I can't seem to edit this marking.");
        }
    }

    /**
     * Toggles completion status of task matching index.
     * (complete -> incomplete and vice versa)
     *
     * @param index row index of Task in CSV file.
     * @param tag tag to set Task with
     * @throws UxieIOException I/O exception during editing of file.
     */
    public void tagTask(int index, String tag) throws UxieIOException {
        try (CSVReader taskFileReader = new CSVReader(new FileReader(getTaskFile()))) {
            List<String[]> taskRows = taskFileReader.readAll();
            taskFileReader.close();

            String[] taskRow = taskRows.get(index);
            taskRow[3] = tag;

            CSVWriter taskFileWriter = new CSVWriter(new FileWriter(getTaskFile()));
            taskFileWriter.writeAll(taskRows);
            taskFileWriter.close();
        } catch (IOException | CsvException e) {
            throw new UxieIOException("I can't seem to edit this tag.");
        }
    }

    /**
     * Converts array of Strings into a Task.
     *
     * @param arguments args for Task. {@link #storeTask(Task)} for format
     * @return Optional containing Task if valid format, or empty if invalid.
     */
    private static Optional<Task> convertTaskRow(String[] arguments) {
        if (verifyBasic(arguments)) {
            try {
                Task result = switch (arguments[0]) {
                case "T" -> convertToDoRow(arguments);
                case "D" -> convertDeadlineRow(arguments);
                case "E" -> convertEventRow(arguments);
                default -> null;
                };
                return Optional.ofNullable(result);
            } catch (UxieSyntaxException e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Verify if Task argument array has a Task type, valid completion status and description.
     */
    private static boolean verifyBasic(String[] arguments) {
        boolean hasType = !arguments[0].isBlank();
        boolean isCompletionValid = arguments[1].matches("[01]");
        boolean hasDesc = !arguments[2].isBlank();
        return hasType && isCompletionValid && hasDesc;
    }

    /**
     * Convert a Task argument array into a ToDo object.
     */
    private static ToDo convertToDoRow(String[] arguments) {
        if (arguments.length == 4) { // valid
            ToDo result = new ToDo(arguments[1].equals("1"), arguments[2]);
            // add tag if present
            if (!arguments[3].isEmpty()) {
                result.setTag(arguments[3]);
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * Convert a Task argument array into a Deadline object.
     */
    private static Deadline convertDeadlineRow(String[] arguments) throws UxieSyntaxException {
        if (arguments.length == 5 && !arguments[4].isBlank()) { // valid
            Deadline result = new Deadline(arguments[1].equals("1"), arguments[2],
                    DateTimeParse.parseStorageRead(arguments[4]));
            // add tag if present
            if (!arguments[3].isEmpty()) {
                result.setTag(arguments[3]);
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * Convert a Task argument array into an Event object.
     */
    private static Event convertEventRow(String[] arguments) throws UxieSyntaxException {
        if (arguments.length == 6 && !arguments[4].isBlank()
                && !arguments[5].isBlank()) { // valid
            Event result = new Event(arguments[1].equals("1"), arguments[2],
                    DateTimeParse.parseStorageRead(arguments[4]),
                    DateTimeParse.parseStorageRead(arguments[5]));
            // add tag if present
            if (!arguments[3].isEmpty()) {
                result.setTag(arguments[3]);
            }
            return result;
        } else {
            return null;
        }
    }

    /**
     * Reads Tasks from task file.
     *
     * @throws UxieIOException I/O exception during reading of file.
     */
    public ReadTaskResult readTasks() throws UxieIOException {
        try (CSVReader taskFileReader = new CSVReader(new FileReader(getTaskFile()))) {
            List<String[]> taskRows = taskFileReader.readAll();
            taskFileReader.close();

            List<Task> tasks = new ArrayList<>();
            List<String> malformedRows = new ArrayList<>();
            for (int i = 0; i < taskRows.size(); i++) {
                Optional<Task> maybeTask = convertTaskRow(taskRows.get(i));
                if (maybeTask.isPresent()) {
                    tasks.add(maybeTask.get());
                } else {
                    malformedRows.add(String.format("%s", i + 1));
                }
            }

            return new ReadTaskResult(malformedRows, tasks);
        } catch (IOException | CsvException e) {
            throw new UxieIOException("I can't read your file.");
        }
    }

    /**
     * Deletes task matching index.
     *
     * @param index row index of Task in CSV to delete.
     * @throws UxieIOException I/O Exception during deleting of line
     */
    public void deleteTask(int index) throws UxieIOException {
        try (CSVReader taskFileReader = new CSVReader(new FileReader(getTaskFile()))) {
            List<String[]> taskRows = taskFileReader.readAll();
            taskRows.remove(index);
            taskFileReader.close();

            CSVWriter taskFileWriter = new CSVWriter(new FileWriter(getTaskFile()));
            taskFileWriter.writeAll(taskRows);
            taskFileWriter.close();
        } catch (IOException | CsvException e) {
            throw new UxieIOException("I can't seem to delete this task.");
        }
    }

}
