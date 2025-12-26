package Baozii;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Optional<Task> add(Task task) {
        tasks.add(task);
        return Optional.of(task);
    }

    public Optional<Task> delete(int i) {
        if (i < 0 || i >= tasks.size()) return Optional.empty();
        Task task = tasks.get(i);
        tasks.remove(i);
        return Optional.of(task);
    }

    public Optional<Task> mark(int i) {
        if (i < 0 || i >= tasks.size()) return Optional.empty();
        tasks.get(i).mark();
        Task task = tasks.get(i);
        return Optional.of(task);
    }

    public Optional<Task> unmark(int i) {
        if (i < 0 || i >= tasks.size()) return Optional.empty();
        tasks.get(i).unmark();
        Task task = tasks.get(i);
        return Optional.of(task);
    }

    public Optional<Task> tag(int i, String tag) {
        if (i < 0 || i >= tasks.size()) return Optional.empty();
        tasks.get(i).tag(tag);
        Task task = tasks.get(i);
        return Optional.of(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Given the path, write the serialised string into the corresponding file for storage.
     * @param path the given path

     */
    public void store(Path path) throws IOException {
        for (Task task : tasks) {
            Files.writeString(path, task.toSerial() + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    /**
     * Given the bufferedreader, load the tasks using given parser to parse the serialised strings
     * @param br bufferedreader
     * @param parser given parser

     */
    public void load(BufferedReader br, Parser parser) throws IOException {
        for (String line; (line = br.readLine()) != null; ) {
            parser.parseTaskFromFile(line).ifPresent(tasks::add);
        }
    }

    public TaskList find(String s) {
        TaskList matched = new TaskList();
        for (Task task : tasks) if (task.match(s)) matched.add(task);
        return matched;
    }
}
