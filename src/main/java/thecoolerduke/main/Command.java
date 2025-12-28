package thecoolerduke.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import thecoolerduke.exceptions.InvalidFormatException;
import thecoolerduke.feature.Task;
import thecoolerduke.feature.TaskManager;

/**
 * Provides a set of valid commands to be used when interacting with TheCoolerDuke chatbot.
 * Commands will be in the format of "[Command] [Additional Modifiers]"
 * Additional Modifiers are in String and are space separated.
 */
public enum Command {
    LIST_TASK("list") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            return tm.viewList();
        }
    },

    ADD_TODO_TASK("todo") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                //result = [taskName]
                String[] result = parseInput(modifier[0], new String[]{"/p"});
                Priority p = Priority.fromString(result[1]);
                return tm.addTodoTask(result[0], p);
            } catch (InvalidFormatException e) {
                return e.getMessage();
            }
        }
    },

    ADD_DEADLINE_TASK("deadline") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                //result = [taskName, byDatetime]
                String[] result = parseInput(modifier[0], new String[]{"/p", "/by"});
                Priority p = Priority.fromString(result[1]);
                LocalDateTime parsedBy = LocalDateTime.parse(result[2], Task.DATETIME_INPUT_FORMAT);
                return tm.addDeadlineTask(result[0], p, parsedBy);

            } catch (InvalidFormatException e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return "Invalid datetime!  Use format dd/mm/yyyy hh:mm (24 hour format).";
            }
        }
    },

    ADD_EVENT_TASK("event") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                //result = [taskName, fromDatetime, toDateTime]
                String[] result = parseInput(modifier[0], new String[]{"/p", "/from", "/to"});
                Priority p = Priority.fromString(result[1]);
                LocalDateTime parsedFrom = LocalDateTime.parse(result[2], Task.DATETIME_INPUT_FORMAT);
                LocalDateTime parsedTo = LocalDateTime.parse(result[3], Task.DATETIME_INPUT_FORMAT);

                if (!parsedTo.isAfter(parsedFrom)) {
                    throw new InvalidFormatException("Invalid datetime! /to cannot be before /from");
                }

                return tm.addEventTask(result[0], p, parsedFrom, parsedTo);

            } catch (InvalidFormatException e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return "Invalid datetime! Use format dd/mm/yyyy hh:mm (24 hour format).";
            }
        }
    },

    DELETE_TASK("delete") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                //result = [taskIdx]
                String[] result = parseInput(modifier[0], new String[]{});
                int idx = Integer.parseInt(result[0]);
                return tm.deleteTask(idx);

            } catch (InvalidFormatException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "that command isn't right: only numbers are allowed!";
            }
        }
    },

    MARK_TASK("mark") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                //result = [taskIdx]
                String[] result = parseInput(modifier[0], new String[]{});
                int idx = Integer.parseInt(result[0]);
                return tm.markTaskAsDone(idx);

            } catch (InvalidFormatException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "that command isn't right: only numbers are allowed!";
            }
        }
    },

    UNMARK_TASK("unmark") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                //result = [taskIdx]
                String[] result = parseInput(modifier[0], new String[]{});
                int idx = Integer.parseInt(result[0]);
                return tm.unmarkTaskAsDone(idx);

            } catch (InvalidFormatException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "that command isn't right: only numbers are allowed!";
            }
        }
    },

    FIND_TASK("find") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                //result = [taskName]
                String[] result = parseInput(modifier[0], new String[]{});
                return tm.findTaskByName(result[0]);

            } catch (InvalidFormatException e) {
                return e.getMessage();
            }
        }
    },

    HELP("help") {
        @Override
        public String execute(String[] modifier, TaskManager tm) {
            try {
                String result = parseHelpInput(modifier[0]);

                if (result.isEmpty()) { //case 1: only "help" is inputted
                    // return all commands separated by newlines
                    StringBuilder sb = new StringBuilder();
                    sb.append("No problem! Here's the list of commands!\n"
                            + "Also try \"help {command}\" to see a specific command!\n\n");

                    for (CommandInfo info : CommandInfo.values()) {
                        sb.append(info.getHelpText()).append("\n\n");
                    }
                    return sb.toString().trim(); // remove trailing newline

                } else { // case 1: "help {keyword}" is inputted
                    // find command matching the keyword
                    CommandInfo info = CommandInfo.fromKeyword(result);
                    if (info != null) {
                        return info.getHelpText();
                    } else {
                        throw new InvalidFormatException("No such command found in the list of commands!");
                    }
                }

            } catch (InvalidFormatException e) {
                return e.getMessage();
            }
        }
    };

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    public abstract String execute(String[] modifier, TaskManager tm);

    /**
     * Compares user input with current valid commands and returns corresponding Command object if valid.
     * If invalid command, returns null.
     *
     * @param input User input as a String
     * @return Command object else null
     */
    public static Command validateCommand(String input) {
        for (Command c : values()) {
            if (c.keyword.equals(input)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Checks that the input is of correct format based on provided acceptedModifiers.
     *
     * @param input The input string to be validated and/or modified
     * @param acceptedModifiers The list of accepted modifiers to compare and format the input string with.
     * @return A list of string values based on the input, separated by the accepted modifiers
     * @throws InvalidFormatException Throws exception if the format of the input is invalid.
     */
    public static String[] parseInput(
            String input, String[] acceptedModifiers
    ) throws InvalidFormatException {

        //check that modifier contains the accepted modifiers provided
        StringBuilder missing = new StringBuilder();
        for (String accepted: acceptedModifiers) {
            if (!input.contains(accepted)) {
                missing.append(" ").append(accepted);
            }
        }

        //throw if any missing modifiers
        if (!missing.isEmpty()) {
            throw new InvalidFormatException(String.format("Missing the modifier(s):%s!", missing));
        }

        //format the modifier into its parameters with the accepted modifiers as separators
        String regex = String.join("|", acceptedModifiers);
        String[] params = input.split(regex, acceptedModifiers.length + 1);

        //ensure that none of the parameters are empty, otherwise trim leading and trailing whitespaces as well
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].trim();

            if (params[i].isEmpty()) {
                throw new InvalidFormatException("One of the parameters is missing!");
            }
        }
        return params;
    }

    /**
     * Parses inputs for the help command specifically.
     *
     * @param input The input string to be validated and/or modified
     * @return The parsed command String
     */
    public static String parseHelpInput(String input) {
        return input.trim();
    }
}

