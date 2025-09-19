package jerome;

import jerome.command.Command;

public class Jerome {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jerome(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.tasks = new TaskList(this.storage.load());
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (JeromeException e) {
            return e.getMessage();
        }
    }

}
