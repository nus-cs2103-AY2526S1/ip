package taro.command;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import taro.corecomp.Storage;
import taro.corecomp.TaroException;
import taro.corecomp.TaskList;
import taro.task.Task;
import taro.ui.Ui;

/**
 * Represents the {@code reminder} command which reminds the user of tasks due in the following 3 days
 * Example:
 *     delete 2
 * removes the second task in the list.
 */
public class ReminderCommand implements Command {
    /**
     * Executes the {@code remind} command.
     * Extracts the date from the input string, compare with today + 3 iterates through the {@link TaskList},
     * and displays all tasks that due in the following 3 days
     * If no tasks match, a message is displayed instead.
     *
     * @param tasks   the current list of tasks
     * @param ui      the user interface used to display messages
     * @param storage the storage handler (not used in this command)
     * @return {@code false} as the program should not terminate after execution
     * @throws TaroException if the keyword is missing or empty
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException {
        LocalDate today = LocalDate.now();
        LocalDate until = today.plusDays(3);

        List<Task> matchedTasks = IntStream.range(0, tasks.size())
                .mapToObj(tasks::get)
                .filter(t -> {
                    LocalDate due = t.getDueDate(); // 假设Task有这个方法
                    return due != null && !due.isBefore(today) && !due.isAfter(until);
                })
                .toList();

        if (matchedTasks.isEmpty()) {
            ui.show("  (nothing)");
        } else {
            ui.show("Here is/are all task(s) due in the following 3 days:");
            String output = matchedTasks.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n  "));
            ui.show("  " + output);
        }
        return false;
    }
}
