package parser;

import command.*;
import task.DukeAction;

public class Parser {


    public static Command parse(String input) {
        if (input.startsWith("list")) {
            return new ListCommand(input);
        } else if (input.startsWith("bye")) {
            return new ExitCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else {
            return new UnknownCommand(input);
        }


    }


    public static DukeAction parseInput(String input) {
        if (input.startsWith("list")) {
            return DukeAction.LIST_TASKS;
        } else if (input.startsWith("bye")) {
            return DukeAction.EXIT;
        } else if (input.startsWith("mark")) {
            return DukeAction.MARK_ITEM;
        } else if (input.startsWith("unmark")) {
            return DukeAction.UNMARK_ITEM;
        } else if (input.startsWith("delete")) {
            return DukeAction.DELETE;
        } else if (input.startsWith("todo")) {
            return DukeAction.CREATE_TODO;
        } else if (input.startsWith("deadline")) {
            return DukeAction.CREATE_DEADLINE;
        } else if (input.startsWith("event")) {
            return DukeAction.CREATE_EVENT;
        } else if (input.startsWith("find")) {
            return DukeAction.FIND_TASKS;
        } else {
            return DukeAction.ERROR;
        }
    }
}
