package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.tasks.Task;
import dyke.ui.Ui;

/**
 * Houses methods for {@code FindCommand}
 */
public class FindCommand extends Command {
    private final String[] keyword;

    /**
     * Creates {@code FindCommand}
     * @param keyword Keywords for searching in the {@code Library}
     */
    public FindCommand(String[] keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        try {
            String msg = this.handleFind(keyword, library);
            ui.printMessage(msg);
            return msg;
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
            return e.getMessage();
        }
    }

    private String handleFind(String[] keyword, Library library) throws DykeException {
        if (keyword.length < 2 || keyword[1].equals(" ") || keyword[1].isEmpty()) {
            throw new DykeException("i'm sorry, i did not get that :(");
        }
        Library findLibrary = new Library();
        for (Task t : library.getTasks()) {
            if (t.encode().contains(keyword[1])) {
                findLibrary.addTask(t);
            }
        }
        int size = findLibrary.size();
        return size > 0 ? findLibrary.printList()
                : "There are no tasks with the keyword: " + keyword[1];
    }
}
