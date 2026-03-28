package ackermann.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidCodeException;
import ackermann.exceptions.task.EmptyNameException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineByException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventFromException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventToException;
import ackermann.task.Deadline;
import ackermann.task.Event;
import ackermann.task.Task;
import ackermann.task.ToDo;

/**
 * Class to interact with saved tasks
 */
public class Storage {
    public final Path filePath;

    /**
     * Constructor for Storage
     * @param filepath
     */
    public Storage(Path filepath) {
        this.filePath = filepath;
    }

    /**
     * Returns a list of tasks based on input file
     * @return A list of tasks based on input file
     * @throws CheckedException
     */
    public TaskList load() throws CheckedException {
        try {
            Scanner fileIn = new Scanner(new File(String.valueOf(this.filePath)));
            TaskList tasks = new TaskList();

            loadTasks(fileIn, tasks);
            return tasks;
        } catch (FileNotFoundException e) {
            return createNewFile();
        }
    }

    /**
     * Creates a new txt file to store tasks
     * @return empty task list
     */
    private TaskList createNewFile() {
        File newFile = new File(String.valueOf(this.filePath));
        try {
            boolean success = newFile.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error creating new file!");
        }
        return new TaskList();
    }

    /**
     * Loads all tasks from file to array
     * @param fileIn
     * @param tasks
     * @throws CheckedException
     */
    private void loadTasks(Scanner fileIn, TaskList tasks) throws CheckedException {
        while (fileIn.hasNext()) {
            String next = fileIn.nextLine();
            String[] taskStr = next.split(" \\| ");
            String type = taskStr[0];
            boolean status = taskStr[1].equals("1");
            String value = taskStr[2];
            String tagString = "";
            if (taskStr.length > 3) {
                tagString = taskStr[3];
            }

            String[] tags;
            if (!tagString.isEmpty()) {
                tags = tagString.split(" ");
            } else {
                tags = new String[0];
            }

            switch (type) {
            case "T":
                addToDo(tasks, value, status, tags);
                break;
            case "D":
                addDeadline(tasks, value, status, tags);
                break;
            case "E":
                addEvent(tasks, value, status, tags);
                break;
            default:
                throw new InvalidCodeException();
            }
        }
    }

    /**
     * Adds toDo tasks to list of tasks for constructor
     *
     * @param tasks List of tasks to add result to.
     * @param value Name of the task.
     * @param status Checks if the task is completed.
     */
    private void addToDo(TaskList tasks, String value, boolean status, String[] tags) throws EmptyNameException {
        Task tempToDo = new ToDo(value);
        if (status) {
            tempToDo.markAsDone();
        }
        tasks.add(tempToDo);
        for (String tag : tags) {
            tempToDo.tag(tag);
        }
    }

    /**
     * Adds deadline tasks to list of tasks for constructor
     *
     * @param tasks List of tasks to add result to.
     * @param value Name of the task, and deadline to be completed by.
     *              Takes on the form "something /by something".
     * @param status Checks if the task is completed.
     */
    private void addDeadline(TaskList tasks, String value, boolean status, String[] tags)
            throws InvalidDeadlineByException, EmptyNameException {
        String[] deadlineInfo = value.split("/by ", 2);
        if (deadlineInfo.length < 2) {
            throw new InvalidDeadlineByException();
        }

        try {
            String name = deadlineInfo[0];
            LocalDate by = LocalDate.parse(deadlineInfo[1]);
            Task tempDeadline = new Deadline(name, by);
            if (status) {
                tempDeadline.markAsDone();
            }
            tasks.add(tempDeadline);
            for (String tag : tags) {
                tempDeadline.tag(tag);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }

    /**
     * Adds event tasks to list of tasks for constructor
     *
     * @param tasks List of tasks to add result to.
     * @param value Name of the task, from when and to when.
     *              Takes on the form "something /from something /to something".
     * @param status Checks if the task is completed.
     */
    private void addEvent(TaskList tasks, String value, boolean status, String[] tags)
            throws InvalidEventException, EmptyNameException {
        String[] eventInfo1 = value.split("/from ", 2);
        if (eventInfo1.length < 2) {
            throw new InvalidEventFromException();
        }
        String[] eventInfo2 = eventInfo1[1].split(" /to ", 2);
        if (eventInfo2.length < 2) {
            throw new InvalidEventToException();
        }
        try {
            LocalDate from = LocalDate.parse(eventInfo2[0]);
            LocalDate to = LocalDate.parse(eventInfo2[1]);
            Task tempEvent = new Event(eventInfo1[0], from, to);
            if (status) {
                tempEvent.markAsDone();
            }
            tasks.add(tempEvent);
            for (String tag : tags) {
                tempEvent.tag(tag);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date!\nFollow the format 'YYYY-MM-DD'.");
        }
    }

    /**
     * Saves tasks to a file
     * @param tasks information to save to the file
     */
    public void save(TaskList tasks) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            String tempStr = getString(tasks.get(i));

            if (i == tasks.size() - 1) {
                str.append(tempStr);
            } else {
                str.append(tempStr).append("\n");
            }
        }
        try {
            FileWriter myWriter = new FileWriter(String.valueOf(this.filePath));
            myWriter.write(String.valueOf(str));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error Saving New Task");
        }
    }

    /**
     * Gets the string to save to file
     * @param currTask task to stringify
     * @return stringified task
     */
    private String getString(Task currTask) {
        boolean status = currTask.getStatus().equals("X");
        String tempStr = "";

        if (status) {
            tempStr += " | 1 | " + currTask.getName();
        } else {
            tempStr += " | 0 | " + currTask.getName();
        }

        if (currTask instanceof ToDo) {
            tempStr = "T" + tempStr;
        } else if (currTask instanceof Event tempTask) {
            tempStr = "E" + tempStr + "/from " + tempTask.getSaveFrom() + " /to " + tempTask.getSaveTo();
        } else if (currTask instanceof Deadline tempTask) {
            tempStr = "D" + tempStr + "/by " + tempTask.getSaveBy();
        }

        List<String> tags = currTask.getTags();
        if (!tags.isEmpty()) {
            tempStr += " |";
            for (int i = 0; i < tags.size(); i++) {
                tempStr += " " + tags.get(i);
            }
        }
        return tempStr;
    }
}
