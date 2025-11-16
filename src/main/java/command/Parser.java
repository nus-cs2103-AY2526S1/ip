package command;

import java.util.Objects;

import exception.CorruptedFileException;
import exception.FrennyException;
import exception.InvalidCommandException;
import exception.TimeFormatException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;


/**
 * The Parser class is responsible for processing user input and history data.
 * It interprets commands and manages tasks in the TaskList.
 */
public class Parser {
    private static String list(TaskList taskList) {
        StringBuilder response = new StringBuilder();
        String listMessage = Ui.showListMessage(taskList.getListSize());
        response.append(listMessage).append("\n");
        String result = taskList.printList();
        response.append(result);
        return response.toString();
    }

    private static String find(TaskList taskList, String[] parts) {
        boolean emptyKeyword = parts.length < 2 || parts[1].trim().isEmpty();
        if (emptyKeyword) {
            String errorMessage = "The search keyword cannot be empty my fren :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
        String keyword = parts[1].trim();
        StringBuilder response = new StringBuilder();
        String findMessage = Ui.showFindMessage();
        response.append(findMessage).append("\n");
        String findResult = taskList.searchTasksByKeyword(keyword).printList();
        response.append(findResult);
        return response.toString();
    }

    private static String delete(TaskList taskList, String[] parts) {
        try {
            boolean emptyKeyword = parts.length < 2 || parts[1].trim().isEmpty();
            if (emptyKeyword) {
                String errorMessage = "Please provide valid task numbers to delete my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
            StringBuilder response = new StringBuilder();
            int[] taskNumbers = stringToIntArray(parts[1]);
            Task[] tasks = taskList.getTasks(taskNumbers);
            String deleteMessage = Ui.showDeleteMessage(tasks);
            response.append(deleteMessage).append("\n");
            taskList.deleteTasks(taskNumbers);
            String result = Ui.showListSize(taskList.getListSize());
            response.append(result);
            return response.toString();
        } catch (NumberFormatException e) {
            String errorMessage = "Please provide valid task numbers to delete my fren :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

    private static String mark(TaskList taskList, String[] parts) {
        try {
            boolean emptyKeyword = parts.length < 2 || parts[1].trim().isEmpty();
            if (emptyKeyword) {
                String errorMessage = "Please provide valid task numbers to mark my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
            int[] taskNumbers = stringToIntArray(parts[1]);
            taskList.markTasks(taskNumbers);
            Task[] tasks = taskList.getTasks(taskNumbers);
            return Ui.showMarkMessage(tasks);
        } catch (NumberFormatException e) {
            String errorMessage = "Please provide valid task numbers to mark my fren :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

    private static String unmark(TaskList taskList, String[] parts) {
        try {
            boolean emptyKeyword = parts.length < 2 || parts[1].trim().isEmpty();
            if (emptyKeyword) {
                String errorMessage = "Please provide valid task numbers to unmark my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
            int[] taskNumbers = stringToIntArray(parts[1]);
            taskList.unmarkTasks(taskNumbers);
            Task[] tasks = taskList.getTasks(taskNumbers);
            return Ui.showUnmarkMessage(tasks);
        } catch (NumberFormatException e) {
            String errorMessage = "Please provide valid task numbers to unmark my fren :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

    private static String todo(TaskList taskList, String[] parts) {
        try {
            Todo todo = Todo.addTodoTask(parts[1], false);
            StringBuilder response = new StringBuilder();
            String addMessage = Ui.showAddMessage(todo);
            response.append(addMessage).append("\n");
            taskList.addTask(todo);
            String result = Ui.showListSize(taskList.getListSize());
            response.append(result);
            return response.toString();
        } catch (FrennyException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            String errorMessage = "The description of a task cannot be empty my fren :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

    private static String deadline(TaskList taskList, String[] parts) {
        try {
            Deadline deadline = Deadline.addDeadlineTask(parts[1], false);
            StringBuilder response = new StringBuilder();
            String addMessage = Ui.showAddMessage(deadline);
            response.append(addMessage).append("\n");
            taskList.addTask(deadline);
            String result = Ui.showListSize(taskList.getListSize());
            response.append(result);
            return response.toString();
        } catch (FrennyException | TimeFormatException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            String errorMessage = "The description of a task cannot be empty my fren :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

    private static String event(TaskList taskList, String[] parts) {
        try {
            Event event = Event.addEventTask(parts[1], false);
            StringBuilder response = new StringBuilder();
            String addMessage = Ui.showAddMessage(event);
            response.append(addMessage).append("\n");
            taskList.addTask(event);
            String result = Ui.showListSize(taskList.getListSize());
            response.append(result);
            return response.toString();
        } catch (FrennyException | TimeFormatException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            String errorMessage = "The description of a task cannot be empty my fren :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

    private static String edit(TaskList taskList, String[] parts) {
        try {
            boolean emptyKeyword = parts.length < 2 || parts[1].trim().isEmpty();
            if (emptyKeyword) {
                String errorMessage = "Please provide valid task number to edit my fren :(";
                System.out.println(errorMessage);
                return errorMessage;
            }
            int taskNumber = Integer.parseInt(parts[1]);
            return taskList.editTask(taskNumber);
        } catch (NumberFormatException e) {
            String errorMessage = "Please provide valid task number to edit my fren :(";
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

    private static String handleInvalidCommand() {
        String errorMessage = "I don't get what you want :(";
        System.out.println(errorMessage);
        return errorMessage;
    }

    private static boolean getIsDone(String status) throws CorruptedFileException {
        if (Objects.equals(status, "1")) {
            return true;
        } else if (Objects.equals(status, "0")) {
            return false;
        } else {
            throw new CorruptedFileException("Corrupted mark status in file :(");
        }
    }
    /**
     * Processes a line from the history file and adds the corresponding task to the TaskList.
     *
     * @param taskList The TaskList to which the task will be added.
     * @param input    The line from the history file representing a task.
     * @throws FrennyException         If there is a general error related to task creation.
     * @throws TimeFormatException     If there is an error in the time format.
     * @throws InvalidCommandException If the command in the file is invalid.
     * @throws CorruptedFileException  If the file is corrupted.
     */
    public static void processHistory(TaskList taskList, String input)
            throws FrennyException, TimeFormatException, InvalidCommandException, CorruptedFileException {
        assert taskList != null : "TaskList should not be null";
        String[] parts = input.split(" \\| ", 2);

        boolean isDone = getIsDone(parts[0]);
        String[] taskParts = parts[1].split(" ", 2);
        String commandTypeString = taskParts[0];
        CommandType commandType = CommandType.fromString(commandTypeString);
        switch (commandType) {
        case TODO -> {
            Todo todo = Todo.addTodoTask(taskParts[1], isDone);
            taskList.addTask(todo);
        }
        case DEADLINE -> {
            Deadline deadline = Deadline.addDeadlineTask(taskParts[1], isDone);
            taskList.addTask(deadline);
        }
        case EVENT -> {
            Event event = Event.addEventTask(taskParts[1], isDone);
            taskList.addTask(event);
        }
        default -> throw new CorruptedFileException("Corrupted task type in file :(");
        }
    }

    /**
     * Processes user input and performs the corresponding action on the TaskList.
     *
     * @param taskList The TaskList to be modified based on user input.
     * @param input    The user input command.
     */
    public static String processInput(TaskList taskList, String input) {
        // Implementation for adding a task
        assert taskList != null : "TaskList should not be null";
        // Trim input to avoid issues with leading/trailing spaces
        input = input.trim();
        System.out.println(input);
        if (input.isEmpty()) {
            return handleInvalidCommand();
        }
        String[] parts = input.split(" ", 2);
        String commandTypeString = parts[0];
        CommandType commandType = CommandType.fromString(commandTypeString);
        switch (commandType) {
        case BYE -> {
            return Ui.showOutro();
        }
        case LIST -> {
            return list(taskList);
        }
        case FIND -> {
            return find(taskList, parts);
        }
        case DELETE -> {
            return delete(taskList, parts);
        }
        case MARK -> {
            return mark(taskList, parts);
        }
        case UNMARK -> {
            return unmark(taskList, parts);
        }
        case TODO -> {
            return todo(taskList, parts);
        }
        case DEADLINE -> {
            return deadline(taskList, parts);
        }
        case EVENT -> {
            return event(taskList, parts);
        }
        case EDIT -> {
            return edit(taskList, parts);
        }
        default -> {
            return handleInvalidCommand();
        }
        }
    }

    private static int[] stringToIntArray(String input) {
        input = input.trim();
        String[] parts = input.split(" ");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()) {
                continue;
            }
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }
}
