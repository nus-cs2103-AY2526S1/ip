package buddy.command;

import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

/*
  Re-use notice:
  As part of AI assisted increment, ChatGPT suggested a Help Command to be added
  in the format as given below.
 */

public final class HelpCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage( String.join("\n",
                "Available commands:",
                "  list | ls",
                "  todo <desc> | t <desc>",
                "  deadline <desc> /by <date> | dl <...>",
                "  event <desc> /from <from> /to <to> | e <...>",
                "  mark <n> | m <n>",
                "  unmark <n> | um <n>",
                "  delete <n> | rm <n>",
                "  find <keyword> | f <keyword>",
                "  save | s",
                "  bye | b",
                "  help | h"
        ));
    }
}

