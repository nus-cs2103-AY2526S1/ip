package bytebot.command;

import bytebot.ByteException;
import bytebot.storage.Storage;
import bytebot.ui.Ui;

/**
 * Finds tasks whose description a key word
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword == null ? "" : keyword.trim();
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ByteException {
        if (keyword.isEmpty()) {
            throw new ByteException("Invalid keyword");
        }

        ui.showMatching(storage.findTasksByKeyword(keyword));
    }
}


