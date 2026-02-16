package talkgpt.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import talkgpt.tasklist.TaskList;
import talkgpt.task.Task;
import talkgpt.task.ToDo;

import org.junit.jupiter.api.Test;

public class UiTest {
    private Ui ui = new Ui();

    Task task1 = new ToDo("read book", "reading");
    Task task2 = new ToDo("write code", "coding");
    Task task3 = new ToDo("go jogging", "exercise");

    private TaskList taskList = new TaskList();
    {
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        task2.mark();
    }

    @Test
    public void testGoodbye() {
        String expected = "Bye. Hope to see you again soon!\n";
        assertEquals(expected, ui.goodbye());
    }

    @Test
    public void testAddTask() {
        Task task = new ToDo("read book", "reading");
        String expected = "Got it. I've added this task:\n" +
                " " + task + "\n" +
                "Now you have 1 tasks in the list.\n";
        assertEquals(expected, ui.addTask(task, 1));
    }

    @Test
    public void testDeleteTask() {
        Task task = new ToDo("read book", "reading");
        String expected = "Noted. I've removed this task:\n" +
                " " + task + "\n" +
                "Now you have 0 tasks in the list.\n";
        assertEquals(expected, ui.deleteTask(task, 0));
    }

    @Test
    public void testMarkTask() {
        Task task = new ToDo("read book", "reading");
        task.mark();
        String expected = "Nice! I've marked this task as done:\n" +
                " " + task + "\n" +
                "Now you have 1 tasks in the list.\n";
        assertEquals(expected, ui.markTask(task, 1));
    }

    @Test
    public void testUnmarkTask() {
        Task task = new ToDo("read book", "reading");
        task.mark();
        task.unmark();
        String expected = "OK, I've marked this task as not done yet:\n" +
                " " + task + "\n" +
                "Now you have 1 tasks in the list.\n";
        assertEquals(expected, ui.unmarkTask(task, 1));
    }

    @Test
    public void testListTasks() {
        String expected = "Here are the tasks in your list:\n" +
                "1. [T][ ] read book\n" +
                "2. [T][X] write code\n" +
                "3. [T][ ] go jogging\n" + "\n" +
                "Now you have 3 tasks in the list.\n";

        assertEquals(expected, ui.listView(taskList));
    }

    @Test
    public void testFindTasks() {
        TaskList foundTasks = taskList.findTask("code");
        String expected = "Here are the matching tasks in the list:\n" +
                "1. [T][X] write code\n" + "\n" +
                "Now you have 1 tasks in the list.\n";
        assertEquals(expected, ui.findTask(foundTasks));
    }

    @Test
    public void testTagView(){
        String expected = "Here are the tasks with the tag: coding\n" +
                "1. [T][X] write code\n" + "\n" +
                "Now you have 1 tasks in the list.\n";
        assertEquals(expected, ui.tagView(taskList, "coding"));

        String expectedEmpty = "There are no tasks with the tag: fun\n" +
                "Now you have 0 tasks in the list.\n";
        assertEquals(expectedEmpty, ui.tagView(taskList, "fun"));
    }
}
