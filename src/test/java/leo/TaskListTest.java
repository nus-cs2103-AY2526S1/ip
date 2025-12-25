package leo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTask_increasesSize_andElemWorks() throws Exception {
        TaskList list = new TaskList();
        assertEquals(0, list.size());

        ToDo t1 = new ToDo("eat chocolate");
        list.addTask(t1);
        assertEquals(1, list.size());
        assertEquals(t1.toString(), list.elem(1));

        ToDo t2 = new ToDo("drink bubbleTea");
        list.addTask(t2);
        assertEquals(2, list.size());
        assertEquals(t2.toString(), list.elem(2));
    }

    @Test
    public void deleteTask_validIndex_removesCorrectItem() throws Exception {
        TaskList list = new TaskList();
        ToDo t1 = new ToDo("eat cake");
        ToDo t2 = new ToDo("go home");
        list.addTask(t1);
        list.addTask(t2);

        list.deleteTask(1);
        assertEquals(1, list.size());
        // after deleting the first, the original second is now at index 1
        assertEquals(t2.toString(), list.elem(1));
    }

    @Test
    public void deleteTask_invalidIndices_throw() {
        TaskList list = new TaskList();
        list.addTask(new ToDo("only"));

        assertThrows(IndexOutOfBounds.class, () -> list.deleteTask(0));
        assertThrows(IndexOutOfBounds.class, () -> list.deleteTask(2));
    }

    @Test
    public void markUndone() throws Exception {
        TaskList list = new TaskList();
        ToDo t = new ToDo("drink vanilla latte");
        list.addTask(t);

        list.markDone(1);
        assertEquals("T | 1 | drink vanilla latte", t.toSaveFormat());

        list.markUndone(1);
        assertEquals("T | 0 | drink vanilla latte", t.toSaveFormat());
    }

    @Test
    public void markDone_markUndoneInvalidIndices_throw() {
        TaskList list = new TaskList();
        list.addTask(new ToDo("x"));

        assertThrows(IndexOutOfBounds.class, () -> list.markDone(0));
        assertThrows(IndexOutOfBounds.class, () -> list.markDone(2));
        assertThrows(IndexOutOfBounds.class, () -> list.markUndone(0));
        assertThrows(IndexOutOfBounds.class, () -> list.markUndone(2));
    }

    @Test
    public void elem_returnsTaskString_andInvalidThrows() throws Exception {
        TaskList list = new TaskList();
        ToDo t = new ToDo("peek");
        list.addTask(t);

        assertEquals(t.toString(), list.elem(1));
        assertThrows(IndexOutOfBounds.class, () -> list.elem(0));
        assertThrows(IndexOutOfBounds.class, () -> list.elem(2));
    }

    @Test
    public void iterate_formatsAllItemsWith1BasedIndex() {
        TaskList list = new TaskList();
        ToDo a = new ToDo("alpha");
        ToDo b = new ToDo("beta");
        list.addTask(a);
        list.addTask(b);

        String expected = "1. " + a.toString() + "\n" + "2. " + b.toString();
        assertEquals(expected, list.iterate());
    }

    @Test
    public void find_returnsOnlyMatches_with1BasedMatchNumbering() {
        TaskList list = new TaskList();
        ToDo a = new ToDo("buy milk");
        ToDo b = new ToDo("buy bread");
        ToDo c = new ToDo("milk tea");
        list.addTask(a);
        list.addTask(b);
        list.addTask(c);

        String expected = "1. " + a.toString() + "\n" + "2. " + c.toString();
        assertEquals(expected, list.find("milk"));
    }
}
