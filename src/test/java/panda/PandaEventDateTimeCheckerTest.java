package panda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import panda.task.Event;
import panda.task.Task;

public class PandaEventDateTimeCheckerTest {
    @Test
    public void hasNoOverlapWithOtherEvents_noOverlap_trueReturned() throws PandaException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Event("test1", "2025-09-19 09:00", "2025-09-19 10:00"));
        tasks.add(new Event("test2", "2025-09-19 11:00", "2025-09-19 12:00"));
        Event event = new Event("test3", "2025-09-19 10:00", "2025-09-19 11:00");
        assertTrue(PandaEventDateTimeChecker.hasNoOverlapWithOtherEvents(event, tasks));
    }

    @Test
    public void hasNoOverlapWithOtherEvents_startOverlap_falseReturned() throws PandaException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Event("test1", "2025-09-19 09:00", "2025-09-19 10:00"));
        tasks.add(new Event("test2", "2025-09-19 11:00", "2025-09-19 12:00"));
        Event event = new Event("test3", "2025-09-19 09:30", "2025-09-19 10:30");
        assertFalse(PandaEventDateTimeChecker.hasNoOverlapWithOtherEvents(event, tasks));
    }

    @Test
    public void hasNoOverlapWithOtherEvents_endOverlap_falseReturned() throws PandaException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Event("test1", "2025-09-19 09:00", "2025-09-19 10:00"));
        tasks.add(new Event("test2", "2025-09-19 11:00", "2025-09-19 12:00"));
        Event event = new Event("test3", "2025-09-19 10:30", "2025-09-19 11:30");
        assertFalse(PandaEventDateTimeChecker.hasNoOverlapWithOtherEvents(event, tasks));
    }
}
