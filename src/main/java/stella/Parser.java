package stella;

/**
 * Responsible in making sense of the user command, for example to
 * identify the command type and also to reformat the date provided
 * by the user to a more human-readable format
 */
public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Craft Stella's response based on user's command
     *
     * @param description A String consisting of user's command
     * @return Stella's response
     * @throws IncompleteInstructionException If command contain insufficient information
     * @throws UnknownInstructionException If no such command exists
     */
    public String findCommand(String description) throws IncompleteInstructionException,
            UnknownInstructionException {
        if (description.equals(("list"))) {
            return this.tasks.printList();
        } else if (description.contains("find")) {
            if (description.length() <= 5) {
                throw new IncompleteInstructionException(description);
            }

            String keyword = "";
            if (description.length() == 6) {
                keyword = keyword + String.valueOf(description.charAt(5));
            }
            keyword = keyword + description.substring(6);

            TaskList temp = tasks.findItem(keyword);
            if (temp.getList().isEmpty()) {
                return "No items found";
            } else {
                return temp.printList();
            }
        } else if (description.contains("delete")) {
            int index = findIndexForModification("delete", description);
            return tasks.deleteItem(index);
        } else if (description.contains("unmark")) {
            int index = findIndexForModification("unmark", description);
            return tasks.modifyItem(index, "unmark");
        } else if (description.contains("mark")) {
            int index = findIndexForModification("mark", description);
            return tasks.modifyItem(index, "mark");
        } else if (description.contains("todo")) {
            if (description.length() <= 5) {
                throw new IncompleteInstructionException(description);
            }

            String details = description.substring(5);
            ToDo temp = new ToDo(details);
            return tasks.addItem(temp);
        } else if (description.contains("deadline")) {
            if (description.length() <= 9) {
                throw new IncompleteInstructionException(description);
            }

            String details = description.substring(9, description.indexOf('/'));
            String deadline = description.substring(description.indexOf('/') + 1);
            deadline = this.formatTime(deadline);
            Deadline temp = new Deadline(details, deadline);
            return tasks.addItem(temp);
        } else if (description.contains("event")) {
            if (description.length() <= 6) {
                throw new IncompleteInstructionException(description);
            }

            String details = description.substring(6, description.indexOf('/'));
            String start = description.substring(description.indexOf('/') + 1,
                    description.lastIndexOf('/'));
            String end = description.substring(description.lastIndexOf('/') + 1);
            start = this.formatTime(start);
            end = this.formatTime(end);
            Event temp = new Event(details, start, end);
            return tasks.addItem(temp);
        } else {
            throw new UnknownInstructionException(description);
        }

    }

    private int findIndexForModification(String marker, String description)
            throws IncompleteInstructionException {
        int referencePoint = 0;
        if (marker == "delete" || marker == "unmark") {
            referencePoint = 7;
        } else if (marker == "mark") {
            referencePoint = 5;
        }

        if (description.length() <= referencePoint) {
            throw new IncompleteInstructionException(description);
        }

        return Integer.valueOf(description.substring(referencePoint)) - 1;
    }

    private String formatTime(String time) {
        if (time.length() == 10) {
            return TimeConverter.convertDate(time);
        }
        if (time.length() == 15) {
            return TimeConverter.convertDateWithTime(time);
        }
        return time;
    }
}