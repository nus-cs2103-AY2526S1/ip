package yapbot.taskmanager;

import yapbot.parser.Parser;
import yapbot.ui.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TaskList {
    private static final List<Task> TASKS = new ArrayList<>();

    public static Task addTask(Task task) {
        TASKS.add(task);
        return task;
    }

    public static Task getTask(int index) {
        return TASKS.get(index);
    }

    public static List<Task> copy() {
        List<Task> copied = new ArrayList<>();
        copied.addAll(TASKS);
        return copied;
    }

    public static void clear() {
        TASKS.clear();
    }

    public static int numOfTasks() {
        return TASKS.size();
    }

    public static String markTasks(String[] indices) {
        return Arrays.stream(indices)
                .map(Parser::getTaskIndex)
                .map(TaskList::getTask)
                .map(Task::mark)
                .map(Task::toString)
                .reduce("Nice! I've marked these tasks as done:\n", (taskList, task) -> taskList + task + "\n")
                + UI.lineBreak();
    }

    public static String unmarkTasks(String[] indices) {
        return Arrays.stream(indices)
                .map(Parser::getTaskIndex)
                .map(TaskList::getTask)
                .map(Task::unmark)
                .map(Task::toString)
                .reduce("OK, I've marked these tasks as not done:\n", (taskList, task) -> taskList + task + "\n")
                + UI.lineBreak();
    }

    public static String deleteTasks(String[] indices) {
        List<Task> copy = TaskList.copy();
        TaskList.clear();
        return Stream.iterate(0, i -> i + 1)
                .limit(copy.size())
                .map(taskNum -> filterTask(copy, indices, taskNum))
                .filter(task -> !task.isEmpty())
                .reduce("Noted. I've removed these tasks:\n", (taskList, task) -> taskList + task + "\n")
                + UI.numOfTasks();
    }

    private static String filterTask(List<Task> copy, String[] indices, int taskNum) {
        if (contains(indices, taskNum)) {
            return copy.get(taskNum).toString();
        } else {
            TaskList.addTask(copy.get(taskNum));
            return "";
        }
    }

    private static boolean contains(String[] indices, int taskNum) {
        return Arrays.stream(indices)
                .map(Parser::getTaskIndex)
                .anyMatch(index -> index == taskNum);
    }

    public static String findTasks(String keyword) {
        return TASKS.stream()
                .map(Task::toString)
                .filter(task -> task.contains(keyword))
                .reduce("Here are the tasks that matches with " + "'" + keyword + "'\n", (taskList, task) -> taskList + task + "\n")
                + UI.lineBreak();
    }

    public static String listTasks() {
        return TASKS.stream()
                .map(Task::toString)
                .reduce("Here are the tasks in your list:\n", (taskList, task) -> taskList + task + "\n")
                + UI.numOfTasks();
    }

    public static String getReminder() {
        return TaskList.sort()
                .map(Task::toString)
                .reduce("Here are the tasks sorted by time:\n", (response, task) -> response + task + "\n")
                + UI.numOfTasks();
    }

    public static Stream<? extends Task> sort() {
        return TASKS.stream()
                .sorted(Task::compareTo);
    }

    public static String getTasksAsTxt() {
        return TASKS.stream()
                .map(task -> task + System.lineSeparator())
                .reduce("", (response, task) -> response + task);
    }
}