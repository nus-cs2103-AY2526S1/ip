package Baozii;

import java.io.IOException;
import java.lang.*;

public class Baozii {
    private final TaskList tasks;
    private final Parser parser;
    private final Storage storage;
    private final UI ui;

    public Baozii() {
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage("data.txt");
        ui = new UI();
        try {
            tasks.load(storage.load(), parser);
        } catch (IOException ignored) {

        }
    }

    public String getResponse(String msg) {
        assert msg != null;
        if (msg.isEmpty()) return ui.welcome();
        Action action;
        try {
            action = parser.parse(msg);
        } catch (InvalidCommandException e) {
            return ui.showException(e);
        }
        return switch (action.type()) {
            case ADD -> ui.showAdd(tasks.add(action.task()));
            case DELETE -> ui.showDelete(tasks.delete(action.index()));
            case LIST -> ui.showList(tasks);
            case MARK -> ui.showMark(tasks.mark(action.index()));
            case UNMARK -> ui.showUnmark(tasks.unmark(action.index()));
            case FIND -> ui.showList(tasks.find(action.pattern()));
            case TAG -> ui.showTag(tasks.tag(action.index(), action.tag()));
            case QUIT -> ui.goodbye();
        };
    }
}
