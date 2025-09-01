public class LunarBot {
    private Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public LunarBot() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception e) {
            ui.showMessage("Loading error!");
            taskList = new TaskList();
        }
    }

    public void run() {
        while(true) {
            if (ui.isBye()) {
                break;
            }
            Parser.parse(ui.readCommand()).execute(this.ui, this.taskList);
        }
    }

    public static void main(String[] args) {
        new LunarBot().run();
    }
}