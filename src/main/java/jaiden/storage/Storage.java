package jaiden.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import jaiden.exception.JaidenException;
import jaiden.task.Deadline;
import jaiden.task.Event;
import jaiden.task.Task;
import jaiden.task.TaskList;
import jaiden.task.Todo;

/**
 * Class for storage.
 */
public class Storage {
    private final File data;

    /**
     * Constructor for storage.
     *
     * @param filePath File path to save data in txt format.
     */
    public Storage(String filePath) {
        assert filePath != null;
        this.data = new File(filePath);
    }

    /**
     * Loads data from local storage.
     *
     * @return List of tasks.
     * @throws JaidenException If invalid format from local storage.
     */
    public ArrayList<Task> load() throws JaidenException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner dataReader;

        if (!this.data.exists()) {
            boolean isDirCreated = new File("./data").mkdir();
            assert isDirCreated;
            try {
                boolean isFileCreated = this.data.createNewFile();
                assert isFileCreated;
            } catch (IOException e) {
                throw new JaidenException(e.getMessage());
            }
        }

        try {
            dataReader = new Scanner(this.data);
        } catch (FileNotFoundException e) {
            throw new JaidenException(e.getMessage());
        }

        while (dataReader.hasNextLine()) {
            String line = dataReader.nextLine();
            String[] temp = line.split(" \\| ");

            boolean isTodo = temp[0].equals("T");
            boolean isDeadline = temp[0].equals("D");
            boolean isEvent = temp[0].equals("E");

            if (isTodo) {
                Todo newTodo = getTodo(temp);
                tasks.add(newTodo);
            } else if (isDeadline) {
                Deadline newDeadline = getDeadline(temp);
                tasks.add(newDeadline);
            } else if (isEvent) {
                Event newEvent = getEvent(temp);
                tasks.add(newEvent);
            } else {
                throw new JaidenException("The data file is corrupted (Content not in the expected format).");
            }
        }

        return tasks;
    }

    private static Todo getTodo(String[] temp) throws JaidenException {
        if (!isCorrectTodoFormat(temp)) {
            throw new JaidenException("The data file is corrupted (Content not in the expected format).");
        }

        String description = temp[2];
        boolean isDone = temp[1].equals("1");

        return new Todo(description, isDone);
    }

    private static boolean isCorrectTodoFormat(String[] temp) {
        boolean hasCorrectLength = temp.length == 3;
        boolean hasCorrectStatus = false;
        boolean hasDescription = false;

        if (hasCorrectLength) {
            hasCorrectStatus = temp[1].equals("0") || temp[1].equals("1");
            hasDescription = !temp[2].isBlank();
        }

        return hasCorrectLength && hasCorrectStatus && hasDescription;
    }

    private static Deadline getDeadline(String[] temp) throws JaidenException {
        if (!isCorrectDeadlineFormat(temp)) {
            throw new JaidenException("The data file is corrupted (Content not in the expected format).");
        }

        String description = temp[2];
        boolean isDone = temp[1].equals("1");
        LocalDate deadline = LocalDate.parse(temp[3]);

        return new Deadline(description, isDone, deadline);
    }

    private static boolean isCorrectDeadlineFormat(String[] temp) {
        boolean hasCorrectLength = temp.length == 4;
        boolean hasCorrectStatus = false;
        boolean hasDescription = false;
        boolean hasDeadline = false;

        if (hasCorrectLength) {
            hasCorrectStatus = temp[1].equals("0") || temp[1].equals("1");
            hasDescription = !temp[2].isBlank();
            hasDeadline = !temp[3].isBlank();
        }

        return hasCorrectLength && hasCorrectStatus && hasDescription && hasDeadline;
    }

    private static Event getEvent(String[] temp) throws JaidenException {
        if (!isCorrectEventFormat(temp)) {
            throw new JaidenException("The data file is corrupted (Content not in the expected format).");
        }

        String description = temp[2];
        boolean isDone = temp[1].equals("1");
        LocalDate from = LocalDate.parse(temp[3]);
        LocalDate to = LocalDate.parse(temp[4]);

        return new Event(description, isDone, from, to);
    }

    private static boolean isCorrectEventFormat(String[] temp) {
        boolean hasCorrectLength = temp.length == 5;
        boolean hasCorrectStatus = false;
        boolean hasDescription = false;
        boolean hasFrom = false;
        boolean hasTo = false;

        if (hasCorrectLength) {
            hasCorrectStatus = temp[1].equals("0") || temp[1].equals("1");
            hasDescription = !temp[2].isBlank();
            hasFrom = !temp[3].isBlank();
            hasTo = !temp[4].isBlank();
        }

        return hasCorrectLength && hasCorrectStatus && hasDescription && hasFrom && hasTo;
    }

    /**
     * Writes to local storage.
     *
     * @param tasks Task list to be saved.
     */
    public void save(TaskList tasks) throws JaidenException {
        try {
            FileWriter dataWriter = new FileWriter(this.data);

            String msg = tasks.save();
            assert msg != null;

            dataWriter.write(msg);
            dataWriter.close();
        } catch (IOException e) {
            throw new JaidenException("Save failed.");
        }
    }
}
