package command;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exceptions.MissingTaskNumberException;
import exceptions.TaskNumberOutOfRangeException;
import sunday.DateTimeHelper;
import sunday.Storage;
import sunday.TaskList;
import sunday.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class UpdateCommand extends Command{
    private final String args;

    public UpdateCommand(String args) {
        this.args = args;
    }

    /**
     * Applies updates to an existing task based on key–value tokens that follow {@code /changes},
     * then replaces the original task at the same position and saves the result.
     *
     * @param taskList the list that contains the task to update; must not be {@code null}
     * @param ui       UI used to report messages; must not be {@code null}
     * @param storage  persistence used to save changes; must not be {@code null}
     * @throws exceptions.MissingTaskNumberException
     *         if the task number is missing or the argument is blank
     * @throws exceptions.TaskNumberOutOfRangeException
     *         if the task number is not in {@code [1, taskList.size()]}
     * @throws IllegalArgumentException
     *         if the command is malformed (e.g., missing {@code /changes}), if a {@code Todo} is updated without
     *         {@code /desc}, if date/time values cannot be parsed, or if {@code /to} is earlier than {@code /from}
     * @throws Exception if an underlying parsing or storage error occurs
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        assert ui != null : "UI cannot be null";
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert args != null : "Input cannot be null";

        if (args.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing /changes. Usage: update <n> /changes [/desc ...] [/by ...] [/from ...] [/to ...]");
        }

        String[] parts = args.split("/changes", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException(
                    "Missing /changes. Usage: update <n> /changes [/desc ...] [/by ...] [/from ...] [/to ...]");
        }

        final int taskNumber;
        try {
            taskNumber = Integer.parseInt(parts[0].trim());
        } catch (NumberFormatException e) {
            throw new MissingTaskNumberException();
        }
        if (taskNumber <= 0 || taskNumber > taskList.size()) {
            throw new TaskNumberOutOfRangeException(taskNumber, taskList.size());
        }

        String changes = parts[1];
        Task selectedTask = taskList.get(taskNumber - 1);

        String newDesc = strAfter(changes, "/desc");
        String newBy   = strAfter(changes, "/by");
        String newFrom = strAfter(changes, "/from");
        String newTo   = strAfter(changes, "/to");

        Task updated;

        //AI used here for ensuring that the if else statement checks everything.
        switch (selectedTask.getTaskType()) {
            case ("T"): {
                if (newDesc == null || newDesc.isBlank()) {
                    throw new IllegalArgumentException("Todo update requires /desc.");
                }
                updated = new Todo(newDesc, selectedTask.isDone());
                break;
            }
            case ("D"): {
                String finalDesc = (newDesc == null || newDesc.isBlank()) ? selectedTask.getTaskName() : newDesc;
                LocalDate finalBy = (newBy == null || newBy.isBlank())
                        ? selectedTask.getDeadLine()
                        : DateTimeHelper.parseDate(newBy);
                updated = new Deadline(finalDesc, finalBy, selectedTask.isDone());
                break;
            }
            case ("E"): {
                String finalDesc = (newDesc == null || newDesc.isBlank()) ? selectedTask.getTaskName() : newDesc;
                LocalDateTime finalFrom = (newFrom == null || newFrom.isBlank())
                        ? selectedTask.getStartDate()
                        : DateTimeHelper.parseDateTime(newFrom);
                LocalDateTime finalTo = (newTo == null || newTo.isBlank())
                        ? selectedTask.getEndDate()
                        : DateTimeHelper.parseDateTime(newTo);
                updated = new Event(finalDesc, finalFrom, finalTo, selectedTask.isDone());
                break;
            }
            default:  throw new IllegalStateException("Unknown task type: " + selectedTask.getTaskType());
        }

        taskList.delete(taskNumber - 1, storage);
        taskList.insertAt(taskNumber - 1, updated, storage);

        System.out.println("Updated task:");
        System.out.println(updated);
    }

    //AI Generated. (ChatGPT-5)
    private static String strAfter(String src, String afterStr) {
        int at = src.indexOf(afterStr);
        if (at < 0) return null;
        int start = at + afterStr.length();
        String rest = src.substring(start).trim();
        int next = rest.indexOf(" /");
        String val = (next >= 0 ? rest.substring(0, next) : rest).trim();
        return val.isEmpty() ? null : val;
    }
}
