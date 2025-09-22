package king;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import king.parser.KingParser;
import king.task.Deadline;
import king.task.Event;
import king.task.KingTaskList;
import king.task.Task;
import king.task.Todo;
import king.ui.KingUI;

/**
 * King bot class that manages the parser, task list and UI
 */
public class King {
    private KingUI kingUI;
    private KingParser kingParser;
    private KingTaskList kingTaskList;

    /**
     * Instantiates the King bot to handle user messages
     */
    public King() {
        try {
            // Initialise UI, Storage, Parser and TaskList
            kingUI = new KingUI();
            kingParser = new KingParser("");
            kingTaskList = new KingTaskList();
        } catch (KingException e) {
            System.out.println(e);
        }
    }

    /**
     * Generates a bot introduction
     *
     * @return Generated bot introduction on start of bot
     */
    public String getIntroduction() {
        return kingUI.showIntroduction();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @return Generated response to user command
     */
    public String getResponse(String input) {
        kingParser.setNewInput(input);
        try {
            return checkCommands();
        } catch (KingException e) {
            return kingUI.showError(e);
        } catch (IndexOutOfBoundsException e) {
            return kingUI.showInvalidTask();
        } catch (DateTimeParseException e) {
            return kingUI.showInvalidDateTime();
        }
    }

    /**
     * Checks through all the commands and returns the appropriate string message
     *
     * @return Message from King
     * @throws KingException Error in command
     */
    private String checkCommands() throws KingException {
        if (kingParser.checkParser(KingParser.Commands.HELP)) {
            // help command - lists the possible commands you can give to the King bot
            return checkHelpCommand();
        } else if (kingParser.checkParser(KingParser.Commands.LIST)) {
            // list command - lists all the current tasks in your task list
            return checkListCommand();
        } else if (kingParser.checkParser(KingParser.Commands.DUE)) {
            // due command - lists all tasks on a certain due date
            return checkDueCommand();
        } else if (kingParser.checkParser(KingParser.Commands.FIND)) {
            // find command - finds all tasks with a certain name
            return checkFindCommand();
        } else if (kingParser.checkParser(KingParser.Commands.TODO)) {
            // todo command - creates a new todo task
            return checkTodoCommand();
        } else if (kingParser.checkParser(KingParser.Commands.DEADLINE)) {
            // deadline command - creates a new deadline task
            return checkDeadlineCommand();
        } else if (kingParser.checkParser(KingParser.Commands.EVENT)) {
            // event command - creates a new event task
            return checkEventCommand();
        } else if (kingParser.checkParser(KingParser.Commands.MARK)) {
            // mark command - marks a task complete
            return checkMarkCommand();
        } else if (kingParser.checkParser(KingParser.Commands.UNMARK)) {
            // unmark command - marks a task incomplete
            return checkUnmarkCommand();
        } else if (kingParser.checkParser(KingParser.Commands.DELETE)) {
            // delete command - deletes a task
            return checkDeleteCommand();
        } else if (kingParser.checkParser(KingParser.Commands.CLEAR)) {
            // clear command - removes all tasks
            return checkClearCommand();
        } else if (kingParser.checkParser(KingParser.Commands.BYE)) {
            // bye command - end of conversation
            return kingUI.showBye();
        } else {
            return kingUI.showInvalidCommand();
        }
    }

    /**
     * Checks the help command and returns the help text
     *
     * @return Help message
     */
    private String checkHelpCommand() {
        return kingUI.showHelp();
    }

    /**
     * Checks the list command for [/sorted] parameter and returns the list of tasks
     *
     * @return Message for list of tasks
     */
    private String checkListCommand() {
        if (kingParser.getListMatcher().group(1) != null
                && kingParser.getListMatcher().group(1).trim().equals("/sorted")) {
            return kingUI.showSortedList(kingTaskList.getTasks());
        } else {
            return kingUI.showAllList(kingTaskList.getTasks());
        }
    }

    /**
     * Checks the parameters of the due command and returns the list of tasks
     *
     * @return Message for list of tasks due on date
     */
    private String checkDueCommand() {
        return kingUI.showDueList(
                kingTaskList.getTasks(),
                LocalDate.parse(kingParser.getDueMatcher().group(1)));
    }

    /**
     * Checks the parameters of the find command and return the list of tasks
     *
     * @return Message for list of tasks matching find substring
     */
    private String checkFindCommand() {
        return kingUI.showFindList(
                kingTaskList.getTasks(),
                kingParser.getFindMatcher().group(1).split(" "));
    }

    /**
     * Checks the parameters of the todo command and returns the created task
     *
     * @return Message for success / error in creating todo task
     * @throws KingException Error in creation of todo task
     */
    private String checkTodoCommand() throws KingException {
        Todo newTask = new Todo(
                kingParser.getTodoMatcher().group(1),
                Task.getPriorityFromString(kingParser.getTodoMatcher().group(2))
        );
        kingTaskList.addTask(newTask);
        return kingUI.showTaskCreate(newTask, kingTaskList.getSize());
    }

    /**
     * Checks the parameters of the deadline command and returns the created task
     *
     * @return Message for success / error in creating deadline task
     * @throws KingException Error in creation of deadline task
     */
    private String checkDeadlineCommand() throws KingException {
        Deadline newTask = new Deadline(
                kingParser.getDeadlineMatcher().group(1),
                Task.getPriorityFromString(kingParser.getDeadlineMatcher().group(2)),
                LocalDate.parse(kingParser.getDeadlineMatcher().group(3)));
        kingTaskList.addTask(newTask);
        return kingUI.showTaskCreate(newTask, kingTaskList.getSize());
    }

    /**
     * Checks the parameters of the event command and returns the created event
     *
     * @return Message for success / error in creating event task
     * @throws KingException Error in creation of event task
     */
    private String checkEventCommand() throws KingException {
        Event newTask = new Event(
                kingParser.getEventMatcher().group(1),
                Task.getPriorityFromString(kingParser.getEventMatcher().group(2)),
                LocalDate.parse(kingParser.getEventMatcher().group(3)),
                LocalDate.parse(kingParser.getEventMatcher().group(4)));
        kingTaskList.addTask(newTask);
        return kingUI.showTaskCreate(newTask, kingTaskList.getSize());
    }

    /**
     * Checks the parameters of the mark command and returns the marked event
     *
     * @return Message for success in marking task complete
     */
    private String checkMarkCommand() {
        int idx = Integer.parseInt(kingParser.getMarkMatcher().group(1)) - 1;
        kingTaskList.markDone(idx);
        return kingUI.showMark(kingTaskList.getTask(idx));
    }

    /**
     * Checks the parameters of the unmark command and returns the unmarked event
     *
     * @return Message for success in unmarking task complete
     */
    private String checkUnmarkCommand() {
        int idx = Integer.parseInt(kingParser.getUnmarkMatcher().group(1)) - 1;
        kingTaskList.unmarkDone(idx);
        return kingUI.showUnmark(kingTaskList.getTask(idx));
    }

    /**
     * Checks the parameters of the delete command and returns the deleted event
     *
     * @return Message for success in deleting task
     */
    private String checkDeleteCommand() {
        int idx = Integer.parseInt(kingParser.getDeleteMatcher().group(1)) - 1;
        return kingUI.showDelete(kingTaskList.deleteTask(idx), kingTaskList.getSize());
    }

    /**
     * Clears the items in the list
     *
     * @return Message for success in clearing task list
     */
    private String checkClearCommand() {
        int prev = kingTaskList.getSize();
        kingTaskList.clearAll();
        return kingUI.showClear(prev);
    }
}
