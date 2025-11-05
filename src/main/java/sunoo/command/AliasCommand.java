package sunoo.command;

import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command that tells user what aliases to commands can be used.
 */
public class AliasCommand extends Command {
    private static final String ALIAS = """
            Type any of the following aliases instead of the full command:

            list -> l, ls, show, display
            delete -> remove
            mark -> m
            unmark -> um
            todo -> t
            deadline -> d
            event -> e
            find -> f, fd, search, lookup
            bye -> end, exit, stop, close""";

    @Override
    public String execute(TaskList tasks) {
        return Ui.wrapWithHorizontalLines(ALIAS);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
