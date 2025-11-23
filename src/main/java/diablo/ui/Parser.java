package diablo.ui;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Parses strings into string arrays.
 */
public class Parser {
    private String byeHelp = "bye - says bye to the chatbot and exits";
    private String listHelp = "list - lists all current tasks in the task list";
    private String markHelp = "mark - marks a specific task in the list as done.\n" +
            "\texample: mark 1\n" +
            "\t\t-> this marks the first task";
    private String deleteHelp = "delete - deletes a specific task in the list\n" +
            "\texample: delete 1\n" +
            "\t\t-> this deletes the first task";
    private String findHelp = "find - finds a task based on a keyword\n" +
            "\texample: find work\n" +
            "\t\t-> this finds all tasks with work in the description";
    private String deadlineHelp = "deadline - adds a deadline task to the task list\n" +
            "\texample: deadline do work /by 2024-10-3\n" +
            "\t\t-> this adds a deadline task with the description (do work) and a deadline 2024-10-3";
    private String toDoHelp = "todo - adds a todo task to the task list\n " +
            "\texample: todo go home " +
            "\t\t-> this adds a todo task with the description (go home)";
    private String eventHelp = "event - adds an event task to the task list\n" +
            "\texample: event party /from 2024-10-3 /to 2024-10-5\n" +
            "\t\t-> this adds an event task with the description (party) from 2024-10-3 to 2024-10-5";

    private String[] helpMessages = new String[] {byeHelp,
            listHelp,
            markHelp,
            deleteHelp,
            findHelp,
            deadlineHelp,
            toDoHelp,
            eventHelp
    };
    public Parser() {};

    /**\
     * Takes a string and parses it based on the input logic of Diablo, returning a string array with the required
     * information.
     *
     * @param input String to be parsed.
     * @return String array containing the parsed contents of the string.
     */
    public String[] parse(String input) {
        String firstWord = input.split(" ")[0];
        switch (firstWord) {
        case "bye": {
            return formatForBye();
        }
        case "list": {
            return formatForList();
        }
        case "find": {
            return formatForFind(input);
        }
        case "mark": {
            return formatForMark(input);
        }
        case "delete": {
            return formatForDelete(input);
        }
        case "deadline": {
            return formatForDeadline(input);
        }
        case "todo": {
            return formatForToDo(input);
        }
        case "event": {
            return formatForEvent(input);
        }
        case "help": {
            return formatForHelp(input);
        }
        default: {
            return formatForDefault();
        }

        }
    }

    private String[] formatForBye() {
        return new String[] {"bye"};
    }

    private String[] formatForList() {
        return new String[] {"list"};
    }

    private String[] formatForFind(String input) {
        int stringLen = input.length();
        return new String[] {"find", input.substring(5, stringLen)};
    }

    private String[] formatForMark(String input) {
        // Split by whitespace
        String[] parts = input.trim().split("\\s+");
        String lastToken = parts[parts.length - 1];
        try {
            Integer.parseInt(lastToken);
        } catch (NumberFormatException e) {
            return new String[] {"mark", "0"};
        }
        return new String[] {"mark", lastToken};
    }


    private String[] formatForDelete(String input) {
        // Split by whitespace
        String[] parts = input.trim().split("\\s+");
        String lastToken = parts[parts.length - 1];
        try {
            Integer.parseInt(lastToken);
        } catch (NumberFormatException e) {
            return new String[] {"delete", "0"};
        }
        return new String[] {"delete", lastToken};
    }

    private String[] formatForDeadline(String input) {
        int startBy = input.indexOf("/by");
        // No date
        if (startBy == -1 || (startBy + 4) > input.length()) {
            return new String[] {"deadline", "-1", "Please indicate a deadline!"};
        } else {
            // No event
            if ((startBy - 1) <= 9) {
                return new String[] {"deadline", "-1", "Please indicate what the deadline is for!"};
            }
            String description = input.substring(9, startBy - 1);
            String day = input.substring(startBy + 4);
            try {
                LocalDate date = LocalDate.parse(day);
            } catch (DateTimeParseException e) {
                return new String[] {"deadline", "-1", "Please write the date in the format yyyy-mm-dd"};
            }
            return new String[] {"deadline", description, day};
        }
    }

    private String[] formatForToDo(String input) {
        String noSpaces = input.replace(" ", "");
        int todoLength = noSpaces.length();
        if (todoLength < 5) {
            return new String[] {"todo", "-1", "Please include something you want to do!"};
        } else {
            String description = input.substring(5);
            return new String[] {"todo", description};
        }
    }

    private String[] formatForEvent(String input) {
        int startFrom = input.indexOf("/from");
        int startTo = input.indexOf("/to");
        if (startFrom == -1 || startTo == -1) {
            return new String[] {"event", "-1", "Please include both /from and /to dates!"};
        } else if ((startTo + 4 >= input.length())) {
            return new String[] {"event", "-1", "Your /to date is missing!"};
        } else if ((startTo - startFrom) <= 6 ) {
            return new String[]{"event", "-1", "Your /from date is missing!"};
        } else {
            if ((startFrom - 1) <= 6) {
                return new String[] {"event", "-1", "Please indicate what the event is!"};
            }
            String description = input.substring(6, startFrom - 1);
            String from = input.substring(startFrom + 6, startTo - 1);
            String to = input.substring(startTo + 4);
            try {
                LocalDate dateFrom = LocalDate.parse(from);
                LocalDate dateTo = LocalDate.parse(to);
            } catch (DateTimeParseException e) {
                return new String[] {"event", "-1", "Please write the dates in the format yyyy-mm-dd"};
            }
            return new String[] {"event", description, from, to};
        }
    }

    private String[] formatForHelp(String input) {
        if (input.length() <= 4) {
            StringBuilder allHelpMessage = new StringBuilder();
            allHelpMessage.append(helpMessages[0]);
            for (int i = 1; i < helpMessages.length; i++) {
                allHelpMessage.append("\n");
                allHelpMessage.append(helpMessages[i]);
            }
            return new String[] {"help", allHelpMessage.toString()};
        }
        String commandThatNeedsHelp = input.substring(5);
        String message = "";
        switch (commandThatNeedsHelp) {
            case "bye": {
                message = byeHelp;
                break;
            }
            case "list": {
                message = listHelp;
                break;
            }
            case "find": {
                message = findHelp;
                break;
            }
            case "mark": {
                message = markHelp;
                break;
            }
            case "delete": {
                message = deleteHelp;
                break;
            }
            case "deadline": {
                message = deadlineHelp;
                break;
            }
            case "todo": {
                message = toDoHelp;
                break;
            }
            case "event": {
                message = eventHelp;
                break;
            }
            default: {
                message = "Please input a command!";
            }
        }

        return new String[] {"help", message};
    }

    private String[] formatForDefault() {
        return new String[] {""};
    }

}
