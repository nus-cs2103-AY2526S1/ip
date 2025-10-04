package angsoontong;

import angsoontong.task.Task;
import angsoontong.ui.Ui;
import angsoontong.storage.Storage;
import angsoontong.parser.Parser;
import angsoontong.task.TaskList;
import java.util.ArrayList;


public class AngSoonTong {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * constructor to initialize AngSoonTong
     * @param filePath outlines location for which tasks will be written to and saved
     */
    public AngSoonTong(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();  // start empty, then fill

        try {
            ArrayList<Task> loaded = storage.load();
            for (Task t : loaded) {
                tasks.add(t);
            }
            System.out.println("[Storage] Loaded " + loaded.size() + " task(s).");
        } catch (Exception e) {
            System.err.println("[Storage] Could not load tasks, starting fresh: " + e.getMessage());
        }
    }

    // commented out old main() and run() method meant for CLI
//    public void run() {
//        ui.showGreeting();
//        boolean running = true;
//        Scanner sc = new Scanner(System.in);
//
//        while (running) {
//            String input = sc.nextLine();
//            if (input.equals("bye")) {
//                ui.showGoodbye();
//                running = false;
//            } else {
//                String response = Parser.parse(input, tasks, ui, storage);
//                ui.show(response);
//            }
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        new AngSoonTong("data/tasks.txt").run();
//    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return Parser.parse(input, tasks, ui, storage);
    }
}
