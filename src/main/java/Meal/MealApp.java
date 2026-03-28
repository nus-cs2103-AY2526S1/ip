package Meal;

import javafx.application.Platform;

public class MealApp {
    private final Storage storage = new Storage("data/mealapp.txt");
    private final TaskList tasks;

    /**
     * Creates new mealapp instance and loads tasks
     */
    public MealApp() {
        tasks = new TaskList(storage.load());
    }

    /**
     * Processes user's input
     * @param input
     * @return
     */
    public String getResponse(String input) {
        GuiUi ui = new GuiUi();
        try {
            Command c = Parser.parse(input);
            boolean shouldExit = c.execute(tasks, ui, storage);
            String out = ui.flush();
            if (shouldExit) {
                Platform.exit();
            }
            return out.isBlank() ? "(no output)" : out;
        } catch (Exception e) {
            ui.showError(e.getMessage());
            return ui.flush();
        }
    }
}
