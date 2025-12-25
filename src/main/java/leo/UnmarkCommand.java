package leo;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert this.index > 0 : "Index needs to be positive";
        try {
            tasks.markUndone(index);
            storage.save(tasks);
            return ui.showUnmarked(tasks.elem(index));
        } catch (IndexOutOfBounds err) {
            return ui.showError(err);
        } catch (IOException err) {
            return ui.showError(err);
        }
    }
}

