package weewee.parser;

import java.util.ArrayList;

import weewee.exception.WeeweeException;
import weewee.task.Deadline;
import weewee.task.Event;
import weewee.task.Task;
import weewee.task.TaskList;
import weewee.task.ToDo;
import weewee.ui.Ui;

/**
 * Parses user input into commands and executes them against a {@link TaskList}.
 */
public class CommandParser {

    /**
     * Supported command types recognized by the parser.
     */
    public enum Command {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, SORT, BYE, UNIDENTIFIED
    }

    /**
     * Determines the command type from the raw input.
     *
     * @param input raw user input
     * @return the matching {@link Command}, or {@code UNIDENTIFIED} if none match
     */
    public static Command getCommand(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        }
        if (input.startsWith("mark")) {
            return Command.MARK;
        }
        if (input.startsWith("unmark")) {
            return Command.UNMARK;
        }
        if (input.startsWith("delete")) {
            return Command.DELETE;
        }
        if (input.startsWith("todo")) {
            return Command.TODO;
        }
        if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        }
        if (input.startsWith("event")) {
            return Command.EVENT;
        }
        if (input.startsWith("find")) {
            return Command.FIND;
        }
        if (input.startsWith("sort")) {
            return Command.SORT;
        }
        if (input.startsWith("bye")) {
            return Command.BYE;
        }
        return Command.UNIDENTIFIED;
    }

    /**
     * Parses the input and performs the corresponding action.
     *
     * @param input raw user input
     * @param tasks mutable task list to operate on
     * @param ui    UI helper for formatted responses
     * @return a user-facing message describing the result
     * @throws WeeweeException if the input is invalid or out of bounds
     */
    public static String parseAndExecute(String input, TaskList tasks, Ui ui) throws WeeweeException {
        Command cmd = getCommand(input);
        switch (cmd) {
        case LIST:
            return ui.showList(tasks);

        case MARK:
            String[] marksplit = input.split(" ");
            int marknumber = Integer.parseInt(marksplit[1]);
            if (marksplit.length != 2 || marknumber < 1 || marknumber > tasks.size()) {
                throw new WeeweeException(ui.showIndexError());
            }

            tasks.get(marknumber - 1).setDone();
            return ui.showMark(tasks.get(marknumber - 1));

        case UNMARK:
            String[] unmarksplit = input.split(" ");
            int unmarknumber = Integer.parseInt(unmarksplit[1]);
            if (unmarksplit.length != 2 || unmarknumber < 1 || unmarknumber > tasks.size()) {
                throw new WeeweeException(ui.showIndexError());
            }

            tasks.get(unmarknumber - 1).setUndone();
            return ui.showUnmark(tasks.get(unmarknumber - 1));

        case DELETE:
            String[] deletesplit = input.split(" ");
            int deletenumber = Integer.parseInt(deletesplit[1]);
            if (deletesplit.length != 2 || deletenumber < 1 || deletenumber > tasks.size()) {
                throw new WeeweeException(ui.showIndexError());
            }

            Task deleted = tasks.remove(deletenumber - 1);
            return ui.showDelete(deleted, tasks);

        case TODO:
            String[] todosplit = input.split("todo ");
            if (todosplit.length < 2) {
                throw new WeeweeException(ui.showTodoError());
            }

            Task todo = new ToDo(todosplit[1].trim());
            tasks.add(todo);
            return ui.showTodo(todo, tasks);

        case DEADLINE:
            String[] deadlinesplit = input.split("deadline | /by ");
            if (deadlinesplit.length < 3) {
                throw new WeeweeException(ui.showDeadlineError());
            }

            Task deadline = new Deadline(deadlinesplit[1].trim(), deadlinesplit[2].trim());
            tasks.add(deadline);
            return ui.showDeadline(deadline, tasks);

        case EVENT:
            String[] eventsplit = input.split("event | /from | /to ");
            if (eventsplit.length < 4) {
                throw new WeeweeException(ui.showEventError());
            }

            Task event = new Event(eventsplit[1].trim(), eventsplit[2].trim(), eventsplit[3].trim());
            tasks.add(event);
            return ui.showEvent(event, tasks);

        case FIND:
            String[] findsplit = input.split("\\s+");
            if (findsplit.length < 2) {
                throw new WeeweeException(ui.showFindError());
            }

            TaskList matchingTasks = new TaskList(new ArrayList<>());

            for (int i = 0; i < tasks.size(); i++) {
                boolean isMatching = true;
                for (int j = 1; j < findsplit.length; j++) {
                    if (!tasks.get(i).getTaskName().matches(".*\\b" + findsplit[j].toLowerCase() + "\\b.*")) {
                        isMatching = false;
                        break;
                    }
                }
                if (isMatching) {
                    matchingTasks.add(tasks.get(i));
                }
            }

            return ui.showFind(matchingTasks);

        case SORT:
            String[] sortsplit = input.split("\\s+", 2);
            if (sortsplit.length < 2) {
                throw new WeeweeException(ui.showSortError());
            }

            String criteria = sortsplit[1].toLowerCase();

            TaskList sortedTasks;

            switch (criteria) {
            case "deadline":
                sortedTasks = new TaskList(
                        tasks.getAll().stream()
                                .filter(t -> t instanceof Deadline)
                                .sorted((t1, t2) -> ((Deadline) t1).getRawDate().compareTo(((Deadline) t2)
                                        .getRawDate()))
                                .collect(java.util.stream.Collectors.toCollection(ArrayList::new))
                );
                break;

            case "event start":
                sortedTasks = new TaskList(
                        tasks.getAll().stream()
                                .filter(t -> t instanceof Event)
                                .sorted((t1, t2) -> ((Event) t1).getRawStart().compareTo(((Event) t2).getRawStart()))
                                .collect(java.util.stream.Collectors.toCollection(ArrayList::new))
                );
                break;

            case "event end":
                sortedTasks = new TaskList(
                        tasks.getAll().stream()
                                .filter(t -> t instanceof Event)
                                .sorted((t1, t2) -> ((Event) t1).getRawEnd().compareTo(((Event) t2).getRawEnd()))
                                .collect(java.util.stream.Collectors.toCollection(ArrayList::new))
                );
                break;

            default:
                throw new WeeweeException(ui.showSortError());
            }

            return ui.showList(sortedTasks);

        default:
            throw new WeeweeException("Sorry, I don't understand what that means </3\n");
        }
    }
}
