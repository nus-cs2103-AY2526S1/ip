package panda;

import java.time.LocalDateTime;
import java.util.ArrayList;

import panda.task.Event;
import panda.task.Task;

/**
 * PandaEventDateTimeChecker handles the checking of DateTime for Events,
 * ensuring that it has a valid format and that the event to be added
 * does not overlap with another existing event.
 */
public class PandaEventDateTimeChecker {

    /**
     * Returns a boolean based on whether the new event overlaps with the
     * dateTime of another event.
     *
     * @param event Event that is to be added to existing tasks.
     * @param tasks Existing list of tasks.
     * @return true if there is no overlap with other events, false otherwise.
     */
    public static boolean hasNoOverlapWithOtherEvents(Event event, ArrayList<Task> tasks) {
        for (Task task : tasks) {
            if (task instanceof Event listEvent) {
                if (isEqualOrAfter(event.getStart(), listEvent.getEnd())) {
                    continue;
                }
                if (isEqualOrAfter(listEvent.getStart(), event.getEnd())) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public static boolean hasMismatchedStartAndEnd(Event event) {
        return isEqualOrAfter(event.getStart(), event.getEnd());
    }

    private static boolean isEqualOrAfter(LocalDateTime first, LocalDateTime second) {
        return first.compareTo(second) >= 0;
    }
}
