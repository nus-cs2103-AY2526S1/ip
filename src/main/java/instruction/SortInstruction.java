package instruction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import util.ShrekException;

/**
 * Represents an instruction to sort tasks by various criteria.
 */
public class SortInstruction extends Instruction {
    private SortCriteria criteria;

    /**
     * Enum representing different sorting criteria.
     */
    public enum SortCriteria {
        DESCRIPTION, DATE, TYPE
    }

    /**
     * Constructs a SortInstruction with the specified sorting criteria.
     *
     * @param criteria the criteria to sort by
     */
    public SortInstruction(SortCriteria criteria) {
        this.criteria = criteria;
    }

    /**
     * Executes the sort instruction by sorting tasks and returning the sorted list.
     * The original task list order is preserved in storage.
     *
     * @param tasks   the task list to sort
     * @param ui      the user interface for generating messages
     * @param storage the storage system
     * @return formatted string of sorted tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ShrekException {
        ArrayList<Task> taskList = tasks.getAllTasks();

        // Create a copy to sort (preserve original order in storage)
        ArrayList<Task> sortedTasks = new ArrayList<>(taskList);

        switch (criteria) {
        case DESCRIPTION:
            Collections.sort(sortedTasks, Comparator.comparing(Task::getDescription));
            break;
        case DATE:
            Collections.sort(sortedTasks, new DateTimeComparator());
            break;
        case TYPE:
            Collections.sort(sortedTasks, new TypeComparator());
            break;
        default:
            throw new ShrekException("Unknown sorting criteria");
        }

        return ui.printSortedTasks(sortedTasks, criteria);
    }

    /**
     * Comparator for sorting by date/time (Deadlines and Events first, then by time)
     */
    private static class DateTimeComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            // Get comparable date/time for each task
            ComparableDateTime dt1 = getComparableDateTime(t1);
            ComparableDateTime dt2 = getComparableDateTime(t2);

            return dt1.compareTo(dt2);
        }

        private ComparableDateTime getComparableDateTime(Task task) {
            if (task instanceof task.Deadline) {
                return new ComparableDateTime(((task.Deadline) task).getBy());
            } else if (task instanceof task.Event) {
                return new ComparableDateTime(((task.Event) task).getFrom());
            } else {
                // Todos go at the end with maximum date
                return new ComparableDateTime();
            }
        }
    }

    /**
     * Comparator for sorting by task type
     */
    private static class TypeComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            // Order: Deadline -> Event -> Todo
            int priority1 = getTypePriority(t1);
            int priority2 = getTypePriority(t2);

            if (priority1 != priority2) {
                return Integer.compare(priority1, priority2);
            }

            // If same type, sort by description
            return t1.getDescription().compareTo(t2.getDescription());
        }

        private int getTypePriority(Task task) {
            if (task instanceof task.Deadline) {
                return 1;
            }
            if (task instanceof task.Event) {
                return 2;
            }
            return 3; // Todo
        }
    }

    /**
     * Helper class to handle comparison of different date/time types
     */
    private static class ComparableDateTime implements Comparable<ComparableDateTime> {
        private static final LocalDateTime MAX_DATE = LocalDateTime.MAX;
        private LocalDateTime dateTime;

        public ComparableDateTime() {
            this.dateTime = MAX_DATE;
        }

        public ComparableDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        @Override
        public int compareTo(ComparableDateTime other) {
            return this.dateTime.compareTo(other.dateTime);
        }
    }
}
