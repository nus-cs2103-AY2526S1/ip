package bobby.parser;

import bobby.exception.BobbyException;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Processes commands sent by the user.
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public static boolean checkBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    /**
     * Checks if command is valid and processes it accordingly
     * @param input what the user types in
     * @return true if command is valid
     * @throws BobbyException if there is a problem with the input
     */
    public String processCommand(String input) throws BobbyException {
        String[] split = input.split(" ", 2);
        String command = split[0];
        Command commandEnum;
        try {
            commandEnum = Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BobbyException("command not recognised.");
        }

        if (commandEnum.expectsArguments() && split.length == 1) {
            throw new BobbyException(command + " requires arguments.");
        }

        if (!commandEnum.expectsArguments() && split.length > 1) {
            throw new BobbyException(command + " should not have any arguments.");
        }

        switch (commandEnum) {
        case BYE:
            return Ui.outputGoodbye();

        case LIST:
            return taskList.toString();

        case FIND:
            return taskList.findTasks(split[1]).toString();

        case MARK:
        case UNMARK:
        case DELETE:
            int num;

            try {
                num = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new BobbyException(command + " task number must be an integer.");
            }
            if (split[0].equalsIgnoreCase("mark")) {
                taskList.markTask(num);
                return "I've marked this task.\n    " + taskList.getTask(num);
            } else if (split[0].equalsIgnoreCase("unmark")) {
                taskList.unmarkTask(num);
                return "I've unmarked this task.\n  " + taskList.getTask(num);
            } else {
                Task task = taskList.getTask(num);
                taskList.deleteTask(num);
                return "I've deleted this task.\n   " + task;
            }
        case TODO:
            taskList.addTask(0, false, split[1]);
            return "I've added this ToDo.\n   " + taskList.getLastTask();
        case DEADLINE:
            taskList.addTask(1, false, split[1]);
            return "I've added this Deadline.\n   " + taskList.getLastTask();
        case EVENT:
            taskList.addTask(2, false, split[1]);
            return "I've added this Event.\n   " + taskList.getLastTask();
        case SNOOZE:
            int taskNum = Integer.parseInt(split[1].substring(0, 1));
            taskList.snoozeTasks(split[1]);
            return "I've snoozed this Task.\n   " + taskList.getTask(taskNum);
        default:
            return "";
        }
    }

    /**
     * enumeration for the commands that the user may input
     */
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        SNOOZE;

        /**
         * checks if a command expects argument
         * @return true if command expects arguments, false if not
         */
        public boolean expectsArguments() {
            switch (this) {
            case BYE:
            case LIST:
                return false;
            default:
                return true;
            }
        }
    }
}
