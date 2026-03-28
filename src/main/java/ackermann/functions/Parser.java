package ackermann.functions;

import static java.lang.Integer.parseInt;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import ackermann.codewords.Codeword;
import ackermann.codewords.DeadlineCodeword;
import ackermann.codewords.DeleteCodeword;
import ackermann.codewords.EventCodeword;
import ackermann.codewords.FindCodeword;
import ackermann.codewords.ListCodeword;
import ackermann.codewords.MarkCodeword;
import ackermann.codewords.TagCodeword;
import ackermann.codewords.TodoCodeword;
import ackermann.codewords.UnmarkCodeword;
import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidCodeException;
import ackermann.exceptions.InvalidDeleteException;
import ackermann.exceptions.InvalidFindException;
import ackermann.exceptions.InvalidMarkException;
import ackermann.exceptions.InvalidTagException;
import ackermann.exceptions.InvalidTargetException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineByException;
import ackermann.exceptions.task.InvalidDeadline.InvalidDeadlineException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventFromException;
import ackermann.exceptions.task.InvalidEvent.InvalidEventToException;
import ackermann.exceptions.task.InvalidTodo.InvalidTodoException;

/**
 * Parser to parse codewords with values
 */
public class Parser {
    private enum Codewords {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, TAG;

        public static Parser.Codewords check(String x) throws InvalidCodeException {
            try {
                if (x.toLowerCase().equals(x)) {
                    return Parser.Codewords.valueOf(x.toUpperCase());
                } else {
                    throw new InvalidCodeException();
                }
            } catch (IllegalArgumentException e) {
                throw new InvalidCodeException();
            }
        }
    }
    private TaskList tasks;

    /**
     * Constructor of Parser
     *
     * @param tasks task list to update to/ retrieve info from
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
        assert this.tasks != null : "Null tasks";
    }

    /**
     * Parses the codeword and details to perform operation
     * @param input
     * @return A boolean on whether the program should terminate
     * @throws CheckedException
     */
    public String parse(String input) throws CheckedException {
        String[] words = input.split(" ", 2);
        Parser.Codewords codeword = Parser.Codewords.check(words[0]);
        assert codeword != null : "Empty Codeword not handled";

        return switch (codeword) {
            case BYE -> Ui.END;
            case LIST -> this.list(tasks);
            case MARK, UNMARK -> this.handleMark(words);
            case DELETE -> this.handleDelete(words);
            case TODO -> this.addToDo(this.tasks, words);
            case DEADLINE -> this.addDeadline(this.tasks, words);
            case EVENT -> this.addEvent(this.tasks, words);
            case FIND -> this.find(this.tasks, words);
            case TAG -> this.handleTag(words);
            default -> throw new InvalidCodeException();
        };
    }

    /**
     * Tags a task
     * @param words In form of codeword, index, tag name
     * @return
     * @throws CheckedException
     */
    private String handleTag(String[] words) throws CheckedException {
        try {
            if (words.length == 1 || words[1].isEmpty()) {
                throw new InvalidTagException();
            }
            String[] newWords = words[1].split(" ");

            Codeword codeword = new TagCodeword(this.tasks, newWords);
            return codeword.execute();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTagException();
        }
    }

    /**
     * Handles logic for mark or unmark
     * @param words mark and target
     * @throws CheckedException
     */
    private String handleMark(String[] words) throws CheckedException {
        try {
            int target = parseInt(words[1]);
            String mark = words[0];

            if (mark.equals("mark")) {
                return this.mark(tasks, target);
            } else if (mark.equals("unmark")) {
                return this.unmark(tasks, target);
            } else {
                throw new InvalidCodeException();
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidMarkException();
        }
    }

    /**
     * Lists out all tasks
     * @param tasks tasks to list
     */
    public String list(TaskList tasks) throws CheckedException {
        Codeword codeword = new ListCodeword(tasks);
        return codeword.execute();
    }

    /**
     * Marks a task as completed
     *
     * @param tasks List to find task.
     * @param i     Key to find task.
     * @throws CheckedException
     */
    public String mark(TaskList tasks, int i) throws CheckedException {
        int id = i - 1;
        Codeword codeword = new MarkCodeword(tasks, id);
        return codeword.execute();
    }

    /**
     * Marks a task as uncompleted
     *
     * @param tasks List to find task.
     * @param i     Key to find task.
     * @throws InvalidTargetException
     */
    public String unmark(TaskList tasks, int i) throws CheckedException {
        int id = i - 1;
        Codeword codeword = new UnmarkCodeword(tasks, id);
        return codeword.execute();
    }

    /**
     * Handles logic for deleting
     * @param words mark and target
     * @throws CheckedException
     */
    private String handleDelete(String[] words) throws CheckedException {
        try {
            int target = parseInt(words[1]);
            int id = target - 1;
            Codeword codeword = new DeleteCodeword(tasks, id);
            return codeword.execute();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidDeleteException();
        }
    }

    /**
     * Adds toDo task to list of tasks
     *
     * @param tasks List to add task to.
     * @param words codeword and task value
     */
    public String addToDo(TaskList tasks, String[] words) throws CheckedException {
        if (words.length == 1 || words[1].isEmpty()) {
            throw new InvalidTodoException();
        }
        String input = words[1];
        Codeword codeword = new TodoCodeword(tasks, input);
        return codeword.execute();
    }

    /**
     * Adds deadline tasks to list of tasks
     *
     * @param tasks List of tasks to add result to.
     * @param words Codeword, Name of the task, and deadline to be completed by.
     *              Takes on the form "something /by something".
     */
    public String addDeadline(TaskList tasks, String[] words) throws CheckedException {
        if (words.length == 1 || words[1].isEmpty()) {
            throw new InvalidDeadlineException();
        }
        String input = words[1];
        String[] info = input.split(" /by ", 2);
        if (info.length < 2) {
            throw new InvalidDeadlineByException();
        }
        try {
            System.out.println(info[0]);
            LocalDate date = LocalDate.parse(info[1]);
            Codeword codeword = new DeadlineCodeword(tasks, info[0], date);
            return codeword.execute();
        } catch (DateTimeParseException e) {
            return "Invalid Date!\nFollow the format 'YYYY-MM-DD'.";
        }
    }

    /**
     * Adds event tasks to list of tasks
     *
     * @param tasks List of tasks to add result to.
     * @param words Codeword, Name of the task, from when and to when.
     *              Takes on the form "something /from something /to something".
     */
    public String addEvent(TaskList tasks, String[] words) throws CheckedException {
        if (words.length == 1 || words[1].isEmpty()) {
            throw new InvalidEventException();
        }
        String input = words[1];
        String[] info = input.split(" /from ", 2);
        if (info.length < 2) {
            throw new InvalidEventFromException();
        }

        String[] info2 = info[1].split(" /to ", 2);
        if (info2.length < 2) {
            throw new InvalidEventToException();
        }
        try {
            LocalDate from = LocalDate.parse(info2[0]);
            LocalDate to = LocalDate.parse(info2[1]);
            Codeword codeword = new EventCodeword(tasks, info[0], from, to);
            return codeword.execute();
        } catch (DateTimeParseException e) {
            return "Invalid Date!\nFollow the format 'YYYY-MM-DD'.";
        }
    }

    /**
     * Finds tasks of which substring of names is equals to keyword
     *
     * @param tasks tasks to find from
     * @param words Codeword and keyword to search
     */
    public String find(TaskList tasks, String[] words) throws CheckedException {
        if (words.length == 1 || words[1].isEmpty()) {
            throw new InvalidFindException();
        }
        String keyword = words[1];
        try {
            Codeword codeword = new FindCodeword(tasks, keyword);
            return codeword.execute();
        } catch (InvalidFindException e) {
            return e.getMessage();
        }
    }
}
