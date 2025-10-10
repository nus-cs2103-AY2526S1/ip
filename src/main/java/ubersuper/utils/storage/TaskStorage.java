package ubersuper.utils.storage;

import ubersuper.tasks.*;
import ubersuper.utils.LoadedResult;
import ubersuper.utils.Parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskStorage extends DataStorage<TaskList> {

    public TaskStorage() {
        super("uberSuperTasks.txt");
    }

    /**
     * Loads tasks from disk into a fresh {@link TaskList}.
     * <ul>
     *   <li>Silently skips malformed lines and keeps a count in {@link LoadedResult#skipped()}.</li>
     *   <li>Creates the {@code data/} folder and file if they do not exist.</li>
     *   <li>Parses timestamps using {@link LocalDateTime#parse(CharSequence)} (expects ISO format).</li>
     * </ul>
     *
     * @return a {@link LoadedResult} containing the populated {@link TaskList}, number of tasks loaded,
     * and number of lines skipped.
     **/
    public LoadedResult<TaskList> load() {
        TaskList tasks = new TaskList(this);

        try {
            ensureFileExists();

            List<String> lines = Files.readAllLines(dataPath, StandardCharsets.UTF_8);
            List<Task> parsedTasks = Parser.parseTaskLines(lines);

            parsedTasks.stream().filter(Objects::nonNull).forEach(tasks::add);

            int skipped = (int) parsedTasks.stream().filter(Objects::isNull).count();

            return new LoadedResult<>(tasks, tasks.size(), skipped);

        } catch (IOException e) {
            return new LoadedResult<>(tasks, 0, 0);
        }
    }

    /**
     * Saves the current {@link TaskList} to disk, overwriting the previous content.
     * <ul>
     *   <li>Ensures the {@code data/} directory exists.</li>
     *   <li>Serializes each task via {@link Task#formatString()}.</li>
     *   <li>Writes using UTF-8; truncates the file first.</li>
     * </ul>
     *
     * @param tasks list of tasks to be saved
     */
    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:LineLength", "checkstyle:CommentsIndentation"})
    public void save(TaskList tasks) {
        try {
            if (Files.notExists(dataPath.getParent())) {
                Files.createDirectories(dataPath.getParent());
            }
            List<String> lines = tasks.stream().map(Task::formatString).collect(Collectors.toList());
            Files.write(dataPath, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ioe) {
            System.out.print("Could not save tasks!");
        }
    }
}
