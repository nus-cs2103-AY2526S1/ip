package bambam.command;

import java.io.IOException;

import bambam.BambamException;
import bambam.Messages;
import bambam.TaskList;
import bambam.TaskStorage;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    private String getHelpMessage() {
        return "🌸 Hi there! Here's your user guide to Bambam commands 💖\n"
                + "\n"
                + "Commands you can use: \n"
                + " - list\n"
                + "     \uD83D\uDCCB Show all tasks\n"
                + " - todo <desc>\n"
                + "     \uD83D\uDCDD Add a todo task\n"
                + " - deadline <desc> /by <datetime>\n"
                + "     ⏰ Add a deadline task\n"
                + " - event <desc> /from <datetime> /to <datetime>\n"
                + "     \uD83C\uDF89 Add an event task\n"
                + " - mark <x>\n"
                + "     ✅ Mark task x as done\n"
                + " - unmark <x>\n"
                + "     ❌ Mark task x as undone\n"
                + " - delete <x>\n"
                + "     \uD83D\uDDD1 Delete task x\n"
                + " - find <keyword>\n"
                + "     \uD83D\uDD0D Find task containing keyword\n"
                + " - help\n"
                + "     \uD83D\uDCD6 Obtain User Guide to Bambam\n"
                + "\n"
                + "\uD83D\uDCA1 Do note that for datetime, it should be given in the format of:\n"
                + "     Date Only -> yyyy-MM-dd\n"
                + "                 (eg: 2025-01-01)\n"
                + "     Date + Time -> yyyy-MM-dd HHmm\n"
                + "                 (eg: 2025-01-01 1800)\n"
                + "\n"
                + "Have fun managing your tasks with me! 🌸💖";
    }

    @Override
    public void execute(TaskStorage storage, Messages messages, TaskList taskList)
            throws BambamException, IOException {
        messages.printHelpMessage(getHelpMessage());
    }

    @Override
    public String getString() {
        return getHelpMessage();
    }
}
