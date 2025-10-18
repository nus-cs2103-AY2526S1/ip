// TagCommand.java
package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

public class TagCommand implements Command {
    private static final String TAGUSAGE = "Use: tag [task number] [#tag]";
    private static final String UNTAGUSAGE = "Use: untag [task number] [#tag]";
    private final String fullCommand;
    private final boolean isTag;

    public TagCommand(String fullCommand, boolean isTag) {
        this.fullCommand = fullCommand;
        this.isTag = isTag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        String[] parts = fullCommand.split(" ", 3);


        if (parts.length < 3) {
            throw isTag ? new CharliException(TAGUSAGE) : new CharliException(UNTAGUSAGE);
        }

        int index;

        // Check input track num is an integer
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw isTag ? new CharliException(TAGUSAGE) : new CharliException(UNTAGUSAGE);
        }

        // Check track num exists
        boolean isValidIndex = index >= 0 && index < tasks.size();
        if (!isValidIndex) { throw new CharliException("Track number doesn't exist!"); }

        String tag = parts[2].trim();
        if (isTag) {
            // Add tag to task
            tasks.get(index).addTag(tag);
            return "Tagged track " + (index + 1) + " with " + tag + "\n" + tasks.get(index).toString();
        } else {
            tasks.get(index).removeTag(tag);
            return "Untagged track " + (index + 1) + " with " + tag + "\n" + tasks.get(index).toString();
        }



    }

    @Override public boolean isExit() { return false; }
}

