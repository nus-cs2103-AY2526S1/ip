package sunoo.command;

import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command that tells user what commands can be used.
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = """
        Commands should start with the keyword; to check aliases, type "alias"!

        1. List all commands: list
        2. Delete a command: delete TASK_INDEX (e.g., delete 2 deletes the second task in the list)
        3. Mark a command: mark TASK_INDEX (e.g., mark 1 marks the first task as done)
        4. Unmark a command: unmark TASK_INDEX (e.g., unmark 1 marks the first task as not done)
        5. Add a todo task: todo TASK_DESCRIPTION (e.g., todo Buy groceries)
        6. Add a deadline task: deadline TASK_DESCRIPTION /by yyyy-MM-dd HH:mm
           (e.g., deadline Submit report /by 2025-09-20 23:59)
        7. Add an event task: event TASK_DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm
           (e.g., event Team meeting /from 2025-09-21 14:00 /to 2025-09-21 16:00)
        8. Find tasks containing a keyword: find KEYWORD
        9. Exit the program: bye
        10. For a surprise: enhypen TITLE_TRACK""";

    @Override
    public String execute(TaskList tasks) {
        return Ui.wrapWithHorizontalLines(HELP_MESSAGE);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
