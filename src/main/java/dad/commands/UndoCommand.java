package dad.commands;

import dad.DadException;

public class UndoCommand extends Command {

    @Override
    public String execute() {
        Command latestCommand = Command.pushdown.pop();
        return latestCommand.undo(); 
    }
}
