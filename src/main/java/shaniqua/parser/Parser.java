package shaniqua.parser;

import shaniqua.command.AddTaskCommand;
import shaniqua.command.Command;
import shaniqua.command.DeleteTaskCommand;
import shaniqua.command.ExitCommand;
import shaniqua.command.FindCommand;
import shaniqua.command.ListCommand;
import shaniqua.command.LoadCommand;
import shaniqua.command.MarkCommand;
import shaniqua.command.StoreCommand;
import shaniqua.command.TagCommand;
import shaniqua.command.UnmarkCommand;
import shaniqua.taskcore.tasks.Deadline;
import shaniqua.taskcore.tasks.Event;
import shaniqua.taskcore.tasks.InvalidTaskDataException;
import shaniqua.taskcore.tasks.Todo;
import shaniqua.ui.Ui;

public class Parser {
    private static Ui ui;

    public static void setUi(Ui ui) {
        Parser.ui = ui;
    }
    /**
     * Parses user input string and returns corresponding Command Object.
     * Interprets command type and extracts parameters using private command-specific handlers
     *
     * @param in the string from user input to be parsed
     * @return Command Object associated with input or null if invalid command
     * @throws ParserException if input format is invalid or parameters are missing
     * @throws InvalidTaskDataException if task data (name/ date) is invalid.
     */
    public static Command parse(String in) throws ParserException, InvalidTaskDataException {
        String[] processedIn = handleInput(in);
        int idx;
        return switch (processedIn[0]) {
        case "bye" -> {
            yield new ExitCommand();
        }
        case "list" -> {
            yield new ListCommand();
        }
        case "mark" -> {
            if (!isInteger(processedIn[1])) {
                throw new ParserException();
            }
            idx = Integer.parseInt(processedIn[1]);
            yield new MarkCommand(idx);
        }
        case "unmark" -> {
            if (!isInteger(processedIn[1])) {
                throw new ParserException();
            }
            idx = Integer.parseInt(processedIn[1]);
            yield new UnmarkCommand(idx);
        }
        case "remove" -> {
            if (!isInteger(processedIn[1])) {
                throw new ParserException();
            }
            idx = Integer.parseInt(processedIn[1]);
            yield new DeleteTaskCommand(idx);
        }
        case "todo" -> {
            yield new AddTaskCommand(new Todo(processedIn[1]));
        }
        case "deadline" -> {
            String[] paramsDeadline = handleDeadline(processedIn[1]);
            yield new AddTaskCommand(new Deadline(paramsDeadline[0],
                    paramsDeadline[1]));
        }
        case "event" -> {
            String[] paramsEvents =
                    handleEvent(processedIn[1]);
            yield new AddTaskCommand(new Event(paramsEvents[0], paramsEvents[1],
                    paramsEvents[2]));
        }
        case "save" -> {
            yield new StoreCommand();
        }
        case "load" -> {
            yield new LoadCommand();
        }
        case "find" -> {
            yield new FindCommand(processedIn[1]);
        }
        case "tag" -> {
            String[] params = handleTag(processedIn[1]);
            yield new TagCommand(Integer.parseInt(params[0]), params[1]);
        }
        default -> {
            yield null;
        }
        };
    }

    /**
     * Parses string input into string commands.
     * @param param input string
     * @return String without the first argument
     */
    private static String[] handleInput(String param) {
        String[] temp = param.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < temp.length; i++) {
            sb.append(temp[i]);
            sb.append(" ");
        }
        return new String[]{temp[0], sb.toString().trim()};
    }

    private static String[] handleTag(String param) throws ParserException {
        String[] temp = param.split(" ");
        if (temp.length < 2) {
            throw new ParserException();
        }
        StringBuilder tag = new StringBuilder();
        for (int i = 1; i < temp.length; i++) {
            tag.append(temp[i]).append(" ");
        }
        return new String[]{temp[0], tag.toString().trim()};
    }

    private static String[] handleDeadline(String param) throws ParserException {
        String[] res = param.split("/by");
        if (res.length < 2) {
            throw new ParserException();
        }
        return new String[]{res[0].trim(), res[1].trim()};
    }

    private static String[] handleEvent(String param) throws ParserException {
        String[] res = param.split(" ");
        String[] outputParams = new String[3];
        if (res.length < 5 || res.length > 7) {
            throw new ParserException();
        }
        int fromIdx = 0;
        StringBuilder fromString = new StringBuilder();
        int toIdx = 0;
        StringBuilder toString = new StringBuilder();
        StringBuilder nameString = new StringBuilder();

        for (int i = 0; i < res.length; i++) {
            res[i] = res[i].trim();
            if (res[i].isEmpty()) {
                throw new ParserException();
            }
            if (res[i].equals("/to")) {
                toIdx = i;
            } else if (res[i].equals("/from")) {
                fromIdx = i;
                outputParams[1] = res[i + 1].trim();
            }
        }
        int left = Math.min(fromIdx, toIdx);
        int right = Math.max(fromIdx, toIdx);
        for (int i = 0; i < res.length; i++) {
            if (i < left) {
                nameString.append(res[i]);
                if (i != left - 1) {
                    nameString.append(" ");
                }
            }
            if (i > left && i < right) {
                if (left == fromIdx) {
                    fromString.append(" ").append(res[i]);
                } else {
                    toString.append(" ").append(res[i]);
                }
            } else if (i > right) {
                if (right == toIdx) {
                    toString.append(" ").append(res[i]);
                } else {
                    fromString.append(" ").append(res[i]);
                }
            }
        }
        outputParams[0] = nameString.toString();
        outputParams[1] = fromString.toString().trim();
        outputParams[2] = toString.toString().trim();
        return outputParams;
    }
    private static boolean isInteger(String param) {
        try {
            Integer.valueOf(param);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
