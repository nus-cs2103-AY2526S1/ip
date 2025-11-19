package goober;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import goober.helper.Formatter;
import goober.helper.Parser;
import goober.storage.SaveData;
import goober.storage.Storage;
import goober.task.Deadline;
import goober.task.Event;
import goober.task.Priority;
import goober.task.Task;
import goober.task.Todo;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Goober chatbot.
 */
public class Goober {
    private static final String SAVE_FILE_NAME = "GooberTasks.ser";
    private SaveData saveData;

    /**
     * Goober constructor. Sets up storage.
     */
    public Goober() {
        startUp();
    }

    private void startUp() {
        saveData = Storage.getOrCreateSave(SAVE_FILE_NAME);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String line = input.trim();
        String cmd = line.split(" ", 2)[0].toLowerCase();
        try {
            switch (cmd) {
            case "hello":
                return "Hello, I'm Goober! How may I help you?";
            case "bye":
                return exit();
            case "list":
                return getNumberedTasks();
            case "mark":
                return markCompleteTask(line);
            case "unmark":
                return unmarkCompleteTask(line);
            case "todo":
                return addTodo(line);
            case "deadline":
                return addDeadline(line);
            case "event":
                return addEvent(line);
            case "delete":
                return deleteTask(line);
            case "find":
                return find(line);
            case "priority":
                return setPriority(line);
            default:
                return "Sorry, I don't recognise that command! :(";
            }
        } catch (IllegalArgumentException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            String msg = "Wrong date time format!\nAccepted date formats: ";
            return Formatter.toNumberList(Parser.FORMAT_LIST, msg);
        } catch (IndexOutOfBoundsException e) {
            return "Index out of bounds!";
        }
    }

    private String getNumberedTasks() {
        List<Task> tasks = saveData.getTaskList();
        if (tasks.isEmpty()) {
            return "You've got no tasks!";
        }
        return Formatter.toNumberList(tasks, "Here are your tasks:");
    }

    private String addTodo(String line) {
        String flag = "todo";
        String description = Parser.getFlagArg(line, flag);

        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty!");
        }

        Task task = new Todo(description);
        task.setPriority(Priority.of(Parser.getFlagArg(line, "/p")));
        return addTask(task);
    }

    private String addDeadline(String line) {
        String flag = "deadline";
        String byFlag = "/by";
        String description = Parser.getFlagArg(line, flag);
        String by = Parser.getFlagArg(line, byFlag);

        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a deadline cannot be empty!");
        }
        if (by.isEmpty()) {
            throw new IllegalArgumentException("The " + byFlag + " of a deadline cannot be empty!");
        }

        LocalDateTime byDate = Parser.parseDateTime(by);
        Task task = new Deadline(description, byDate);
        task.setPriority(Priority.of(Parser.getFlagArg(line, "/p")));
        return addTask(task);
    }

    private String addEvent(String line) {
        String flag = "event";
        String fromFlag = "/from";
        String toFlag = "/to";
        String description = Parser.getFlagArg(line, flag);
        String from = Parser.getFlagArg(line, fromFlag);
        String to = Parser.getFlagArg(line, toFlag);

        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of an event cannot be empty!");
        }
        if (from.isEmpty()) {
            throw new IllegalArgumentException("The " + fromFlag + " of a event cannot be empty!");
        }
        if (to.isEmpty()) {
            throw new IllegalArgumentException("The " + toFlag + " of a event cannot be empty!");
        }

        LocalDateTime fromDate = Parser.parseDateTime(from);
        LocalDateTime toDate = Parser.parseDateTime(to);
        Task task = new Event(description, fromDate, toDate);
        task.setPriority(Priority.of(Parser.getFlagArg(line, "/p")));
        return addTask(task);
    }

    private String addTask(Task task) {
        saveData.getTaskList().add(task);
        String msg = String.format(
                "Got it. I've added this task:\n%s\nNow you have %s tasks in the list.",
                task.toString(), saveData.getTaskList().size());
        updateSaveData();
        return msg;
    }

    private String deleteTask(String line) {
        String flag = "delete";
        String index = Parser.getFlagArg(line, flag);
        if (index.isEmpty()) {
            throw new IllegalArgumentException("The task number of a delete cannot be empty!");
        }
        return deleteTask(Integer.parseInt(index));
    }

    private String deleteTask(int index) {
        Task task = saveData.getTaskList().get(index - 1);
        saveData.getTaskList().remove(task);
        String msg = String.format(
                "Noted. I've removed this task:\n%s\nNow you have %s tasks in the list.",
                task.toString(), saveData.getTaskList().size());
        updateSaveData();
        return msg;
    }

    private String markCompleteTask(String line) {
        String flag = "mark";
        String index = Parser.getFlagArg(line, flag);
        if (index.isEmpty()) {
            throw new IllegalArgumentException("The task number of a mark cannot be empty!");
        }
        return markCompleteTask(Integer.parseInt(index));
    }

    private String markCompleteTask(int index) {
        Task task = saveData.getTaskList().get(index - 1);
        task.markComplete();
        updateSaveData();
        return "Nice! I've marked this task as done:\n  " + task;
    }

    private String unmarkCompleteTask(String line) {
        String flag = "unmark";
        String index = Parser.getFlagArg(line, flag);
        if (index.isEmpty()) {
            throw new IllegalArgumentException("The task number of a unmark cannot be empty!");
        }
        return unmarkCompleteTask(Integer.parseInt(index));
    }

    private String unmarkCompleteTask(int index) {
        Task task = saveData.getTaskList().get(index - 1);
        task.unmarkComplete();
        updateSaveData();
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    private void updateSaveData() {
        Storage.saveToFile(saveData, SAVE_FILE_NAME);
    }

    private String find(String line) {
        String flag = "find";
        String query = Parser.getFlagArg(line, flag);
        List<Task> searchResult = saveData.searchTask(query);

        String msg = "Here are the matching tasks in your list:";
        return Formatter.toNumberList(searchResult, msg);
    }

    private String setPriority(String line) {
        String flag = "priority";
        String priorityFlag = "/p";
        String index = Parser.getFlagArg(line, flag);
        String priorityStr = Parser.getFlagArg(line, priorityFlag);

        if (index.isEmpty()) {
            throw new IllegalArgumentException("The task number of a prioritise cannot be empty!");
        }

        Priority priority = Priority.of(priorityStr);
        return setPriority(Integer.parseInt(index), priority);
    }

    private String setPriority(int index, Priority priority) {
        Task task = saveData.getTaskList().get(index - 1);
        task.setPriority(priority);
        updateSaveData();
        return "OK, I've updated the priority of this task:\n  " + task;
    }

    private String exit() {
        float delayTime = 1.5F;
        PauseTransition delay = new PauseTransition(Duration.seconds(delayTime));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return "Bye! Hope to see you again soon!";
    }
}
