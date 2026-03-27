package tom;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.util.Pair;

/**
 * Represents a chatbot to help with keeping track of tasks to be done.
 */
public class Tom {
    private static final String DEFAULT_FILE_PATH = "./data/tom.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs Tom.
     * @param path Path for txt file that stores previous tasks.
     * @throws IOException If input is in an incorrect format.
     */
    public Tom(java.nio.file.Path path) throws IOException {
        ui = new Ui();
        storage = new Storage(path);
        taskList = new TaskList(storage.load());
    }

    public Tom() throws IOException {
        this(Paths.get(DEFAULT_FILE_PATH));
    }

    /**
     * Runs the chatbot.
     * @param input Parsed user input.
     * @return String for the UI to display.
     */
    public String run(String input) {
        Parser parser = new Parser(input);
        try {
            Pair<Pair<String, String>, Pair<Optional<Integer>, Optional<Task>>> p = parser.parse();
            String command = p.getKey().getKey();
            int idx = p.getValue().getKey().orElse(-1);
            Task task = p.getValue().getValue().orElse(new Task("NA"));
            String keyword = p.getKey().getValue();

            Storage.writeLines(taskList.getTasks());
            return getResponse(command, idx, task, keyword);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public String getResponse(String command, int idx, Task task, String keyword) {
        String response = "";
        try {
            switch(command) {
            case "bye":
                response = ui.bye();
                Storage.writeLines(taskList.getTasks());
                break;
            case "list":
                response = taskList.list();
                Storage.writeLines(taskList.getTasks());
                break;
            case "mark":
                response = taskList.mark(idx);
                Storage.writeLines(taskList.getTasks());
                break;
            case "unmark":
                response = taskList.unmark(idx);
                Storage.writeLines(taskList.getTasks());
                break;
            case "prioritise":
                response = taskList.prioritise(idx);
                Storage.writeLines(taskList.getTasks());
                break;
            case "todo", "deadline", "event":
                response = taskList.add(task);
                Storage.writeLines(taskList.getTasks());
                break;
            case "delete":
                response = taskList.delete(idx);
                Storage.writeLines(taskList.getTasks());
                break;
            case "find":
                response = taskList.find(keyword);
                Storage.writeLines(taskList.getTasks());
                break;
            default:
                throw new TomException("Unknown command");
            }
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }
}
