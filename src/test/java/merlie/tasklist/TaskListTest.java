package merlie.tasklist; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import merlie.exception.MerlieException;
import merlie.task.Todo;
import merlie.ui.Ui;

public class TaskListTest {
    @Test
    void testIsTaskInList_detectsDuplicate() throws MerlieException {
        Ui ui = new Ui(); // real instance
        TaskList list = new TaskList();
        Todo t1 = new Todo("Read book");
        Todo t2 = new Todo("Read book");

        list.add(t1);
        assertTrue(list.isTaskInList(t2, ui, null));
    }

    @Test
    void testIsTaskInList_detectsNonDuplicate() throws MerlieException {
        Ui ui = new Ui(); // real instance
        TaskList list = new TaskList();
        Todo t1 = new Todo("Read book");
        Todo t2 = new Todo("Write essay");

        list.add(t1);
        assertFalse(list.isTaskInList(t2, ui, null));
    }
}
