package Command;


import JohnException.JohnException;
import Task.Deadline;
import Task.TaskList;
import Task.Todo;
import UI.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortCommandTest {
    @Test
    void sort_ordersByDate_thenName_and_todosLast() throws JohnException {
        TaskList list = new TaskList();
        list.add(new Todo("zzz", true));
        list.add(new Deadline("alpha", true, LocalDate.of(2025, 9, 17)));
        list.add(new Deadline("beta", true, LocalDate.of(2025, 9, 18)));
        new SortCommand().execute(list, new Ui());
        assertEquals("alpha", list.getItem(0).getName());
        assertEquals("beta", list.getItem(1).getName());
        assertTrue(list.getItem(2) instanceof Todo);
    }
}