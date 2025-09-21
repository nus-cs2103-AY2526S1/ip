package itachi.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import itachi.task.Todo;



public class ToDoTest {

    @Test
    public void constructor_validDescription_returnsCorrectString() {
        Todo todo = new Todo("buy milk");
        assertEquals("[T][ ] buy milk", todo.toString());
    }

    @Test
    public void toString_taskMarkedDone_returnsMarkedFormat() {
        Todo todo = new Todo("play bball");
        todo.markAsDone();
        assertEquals("[T][X] play bball", todo.toString());
    }

    @Test
    public void markAsNotDone_afterMarkingDone_unmarksCorrectly() {
        Todo todo = new Todo("dance");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("[T][ ] dance", todo.toString());
        assertEquals("T | 0 | dance", todo.toSaveFormat());
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void getStatusIcon_taskNotDone_returnsEmptyBox() {
        Todo todo = new Todo("read book");
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void toSaveFormat_taskNotDone_returnsUnmarkedSaveFormat() {
        Todo todo = new Todo("exercise");
        assertEquals("T | 0 | exercise", todo.toSaveFormat());
    }

    @Test
    public void toSaveFormat_taskMarkedDone_returnsMarkedSaveFormat() {
        Todo todo = new Todo("exercise");
        todo.markAsDone();
        assertEquals("T | 1 | exercise", todo.toSaveFormat());
    }
}
