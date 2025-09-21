package thopz;

/**
 * Parser class to parse user inputs
 */

public class Parser {

    /**
     * Parser class to parse user inputs
     * if bye end the program,
     * Include various way to split the data to achieve right command
     * Invalid inputs throw and exception
     * Deadline would have one /, so splitting words and taking the required once
     * find would require a space before inputting the string to filter the task list
     *
     * @param inp - Input string
     * @return Commands
     * @throws IllegalArgumentException if no valid input
     */
    public static Command parse(String inp) {
        assert inp!=null : "Command should not be null";
        if (inp.equals("bye")) {
            return new ExitCommand();

        } else if (inp.equals("list")) {
            return new ListCommand();

        } else if (inp.startsWith("mark ")) {
            return parseMarkCommand(inp);

        } else if (inp.startsWith("deadline ")) {
            return parseDeadlineCommand(inp);

        } else if (inp.startsWith("event ")) {
            return parseEventCommand(inp);

        } else if (inp.startsWith("todo ")) {
            return parseTodoCommand(inp);

        } else if (inp.startsWith("delete ")) {
            return parseDeleteCommand(inp);

        } else if (inp.startsWith("find ")) {
            return parseFindCommand(inp);

        } else {
            throw new IllegalArgumentException("uhh, idk what you are saying! You gotta start with what kind"
                    + " of task you wish to add,(e.g todo homework, deadline eating/Monday, etc");
        }
    }

    /**
     *
     * Parser for mark command to parse based on spacing
     *
     * @param inp input string with mark
     * @return a MarkCommand
     * @throws IllegalArgumentException if invalid number is used (not within list)
     */

    private static Command parseMarkCommand (String inp) {
        try {
            String[] parts = inp.split(" ");
            int no = Integer.parseInt(parts[1]);
            return new Markcommand(no);

        } catch (Exception e) {
            throw new IllegalArgumentException("invalid number");
        }
    }

    /**
     *
     * Parser for add deadline command to parse based on '/'
     *
     * @param inp input string with "deadline"
     * @return a AddDeadlineCommand
     * @throws IllegalArgumentException if the deadline is not inputted in the right format (e.g no slash)
     */

    private static Command parseDeadlineCommand (String inp) {
        String[] parts = inp.split(" /", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("You should add a / after your description to specify deadline"
                    + "Also note that there should be a space between your slash "
                    + "e.g deadline fight / 12/12/2025 19:55");
        }
        String desc = parts[0].substring(9);
        String due = parts[1];
        return new AddDeadlineCommand(desc, due);

    }

    /**
     *
     * Parser for add deadline command to parse based on spacing
     *
     * @param inp input string with "delete"
     * @return a RemoveCommand
     * @throws IllegalArgumentException if invalid number is inputted
     */


    private static Command parseDeleteCommand (String inp) {
        try {
            String[] parts = inp.split(" ");
            int no = Integer.parseInt(parts[1]);
            return new RemoveCommand(no);

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid delete command, use delete <task_no>, e.g(delete 2)..");
        }
    }

    /**
     *
     * Parser for add event command to parse based on 2 '/'
     *
     * @param inp input string with "event"
     * @return an AddEventCommand
     * @throws IllegalArgumentException if invalid description or format is not followed
     */

    private static Command parseEventCommand (String inp) {

        String des = inp.substring(6);
        int count = inp.split(" /", -1).length - 1;
        if (des.isEmpty()) {
            throw new IllegalArgumentException("You should give an description!");
        } else if (count < 2) {
            throw new IllegalArgumentException("You should add 2 slashes / after your description "
                    + "ensure that you have a space before your 2 slashes"
                    + "to specify event start and end date e.g(eating / Monday / Tuesday");
        }
        String[] parts = inp.split(" /", 3);
        String desc = parts[0].substring(6);
        String begin = parts[1];
        String end = parts[2];
        return new AddEventCommand(desc, begin, end);

    }

    /**
     *
     * Parser for todo command, to parse based on space
     *
     * @param inp input string with "todo"
     * @return an AddTodoCommand
     * @throws IllegalArgumentException if invalid description or format is not followed
     */

    private static Command parseTodoCommand (String inp) {
        String desc= inp.substring(5);
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("You should give an description!");
        }
        String description = inp.substring(5);
        return new AddTodoCommand(description);
    }

    /**
     *
     * Parser for find command, to parse based on space
     *
     * @param inp input string with "find"
     * @return a FindCommand
     * @throws IllegalArgumentException if invalid description or format is not followed
     */

    private static Command parseFindCommand (String inp) {
        String find = inp.substring(5);
        if (find.isEmpty()) {
            throw new IllegalArgumentException("You should give an description!");
        } else {
            return new FindCommand(find);
        }

    }




}

