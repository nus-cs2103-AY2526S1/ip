package parser;

import java.util.Objects;

import exceptions.EmptyTaskException;
import exceptions.InvalidCommandArgumentException;
import exceptions.NoSpaceAfterCommandException;
import exceptions.NotACommandException;
import exceptions.UserInputException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;

public class Parser {
    private static final String COMMAND_LIST = """
        ALRIGHT HERE, GIMME SOMETHING TO DO AFTER RAGGGGGHHHHHH!!!
        
        help: see list of commands
        
        list: see all tasks
        
        delete: delete task at position
            delete [list number]
            e.g. delete 3
        mark: mark task as complete
            mark [list number]
            e.g. mark 5
        unmark: mark task as incomplete
            unmark [list number]
            e.g. unmark 3
        todo: create a todo task
            todo [description]
            e.g. todo go to the beach
        deadline: create a deadline task
            deadline [description] /by [date and time]
            e.g. deadline work /by 20-05-2025 0800
        event: create an event task
            event [description] /from [date and time] /to [date and time]
            e.g. event bbq /from 20-05-2025 0800 /to 20-05-2025 1800
        """;
    /**
     * Returns proper output based on user input
     * @param input Raw input of the user taken from Ui class.
     * @throws UserInputException If user input doesn't fit format
     **/
    public static String processCommand(String input) throws UserInputException {
        String response = "";
        //Commands without args below
        if (Objects.equals(input.trim().toLowerCase(), "bye")) {
            response = "bye";
        } else if (Objects.equals(input.trim().toLowerCase(), "help")) {
            response += COMMAND_LIST;
        } else if (Objects.equals(input, "list")) {
            response = TaskList.getTaskList();
        } else if (checkSpecificCommand(input, "find")) {
            response = handleFind(input); 
        } else if (checkSpecificCommand(input, "mark")) {
            response = handleMark(input);
        } else if (checkSpecificCommand(input, "unmark")) {
            response = handleUnmark(input);
        } else if (checkSpecificCommand(input, "delete")) {
            response = handleDelete(input);
        } else if (checkSpecificCommand(input, "todo")) {
            response = handleTodo(input);
        } else if (checkSpecificCommand(input, "deadline")) {
            response = handleDeadline(input);
        } else if (checkSpecificCommand(input, "event")) {
            response = handleEvent(input);
        } else {
            throw new NotACommandException();
        }
        TaskList.updateListToSave();
        return response;
    }
    
    private static String handleFind(String input) 
            throws InvalidCommandArgumentException, NoSpaceAfterCommandException {
        String msg = getMessageOnly(input, "find");
        if (msg.isEmpty()) {
            throw new InvalidCommandArgumentException("find");
        }
        return TaskList.getFlexibleSelectiveTaskList(msg);
    }

    private static String handleMark(String input) 
            throws InvalidCommandArgumentException, NoSpaceAfterCommandException {
        String msg = getMessageOnly(input, "mark");
        if (msg.isEmpty()) {
            throw new InvalidCommandArgumentException("mark");
        }
        int n = Integer.parseInt(msg);
        if (n <= 0) {
            throw new InvalidCommandArgumentException("mark", "List number must be positive!!!");
        }
        TaskList.markTaskAt(n);
        return String.format("ok ITS MARKED AS DONE!!!! Done:\n %s. %s", n, TaskList.getTaskAt(n));
    }

    private static String handleUnmark(String input) 
            throws NoSpaceAfterCommandException, InvalidCommandArgumentException {
        String msg = getMessageOnly(input, "unmark");
        if (msg.isEmpty()) {
            throw new InvalidCommandArgumentException("unmark");
        }
        int n = Integer.parseInt(msg);
        if (n <= 0) {
            throw new InvalidCommandArgumentException("unmark", "List number must be positive!!!");
        }
        TaskList.unmarkTaskAt(n);
        return String.format("WHY'D YOU UNMARK IT?!?! Not Done:\n %s. %s", n, TaskList.getTaskAt(n));
    }

    private static String handleDelete(String input)
            throws NoSpaceAfterCommandException, InvalidCommandArgumentException {
        String msg = getMessageOnly(input, "delete");
        if (msg.isEmpty()) {
            throw new InvalidCommandArgumentException("delete");
        }
        int n = Integer.parseInt(msg);
        if (n <= 0) {
            throw new InvalidCommandArgumentException("delete", "List number must be positive!!!");
        }
        
        Task toDelete = TaskList.deleteTaskAt(n);
        if (toDelete == null) {
            return "YOUR LIST AIN'T THAT LONG BUDDY";
        } else {
            return String.format("ok ITS GONE:\n %s. Deleted: %s", n, toDelete);
        }
    }

    private static String handleTodo(String input)
            throws EmptyTaskException, NoSpaceAfterCommandException {
        String msg = getMessageOnly(input, "todo");

        Task task = new TodoTask(msg);
        if (msg.isEmpty()) {
            throw new EmptyTaskException(task);
        }
        TaskList.addTask(task);
        return String.format("OK THEN THERE!!! Added:\n %s. %s", TaskList.getSize(), task);
    }

    private static String handleDeadline(String input)
            throws EmptyTaskException, NoSpaceAfterCommandException {
        String msg = getMessageOnly(input, "deadline").split("/by")[0];

        Task task = new DeadlineTask(msg, getDeadlineTime(input));
        if (msg.isEmpty()) {
            throw new EmptyTaskException(task);
        }
        TaskList.addTask(task);
        return String.format("YOU BETTER DO IT IN TIME!!!!!!! Added:\n %s. %s", TaskList.getSize(), task);
    }

    private static String handleEvent(String input) 
            throws NoSpaceAfterCommandException, EmptyTaskException {
        String msg = getMessageOnly(input, "event").split("/from")[0];

        Task task = new EventTask(msg, getFromTime(input), getToTime(input));
        TaskList.addTask(task);
        if (msg.isEmpty()) {
            throw new EmptyTaskException(task);
        }
        return String.format("BE THERE OR ELSE!!!!! Added:\n %s. %s", TaskList.getSize(), task);
    }
    
    private static boolean checkSpecificCommand(String input, String command) {
        int len = command.length();
        return input.length() >= len && Objects.equals(input.substring(0, len).toLowerCase(), command);
    }

    private static String getMessageOnly(String input, String command) throws NoSpaceAfterCommandException {
        String messageAndSpace = input.substring(command.length());
        if (messageAndSpace.isEmpty() || messageAndSpace.charAt(0) != ' ') {
            throw new NoSpaceAfterCommandException(command);
        }
        return messageAndSpace.substring(1);
    }


    private static String getFromTime(String input) {
        String[] fromSplit = input.split("/from");
        if (fromSplit.length < 2) {
            return "?";
        }
        String[] toSplit = fromSplit[1].split("/to");
        return toSplit[0].trim();
    }

    private static String getToTime(String input) {
        String[] toSplit = input.split("/to");
        if (toSplit.length < 2) {
            return "?";
        }
        String[] fromSplit = toSplit[1].split("/from");
        return fromSplit[0].trim();
    }

    private static String getDeadlineTime(String input) {
        String[] deadlineSplit = input.split("/by");
        if (deadlineSplit.length < 2) {
            return "?";
        }
        return deadlineSplit[1].trim();
    }

}
