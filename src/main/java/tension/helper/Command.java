package tension.helper;

/**
 * Stores all details of a command
 */
public class Command {

    private int index;
    private boolean isMarked;
    private String commandName;
    private String fullWord;

    /**
     * initializes the command given the following parameters
     *
     * @param index the index the command is on
     * @param commandWord the specific keyword that dictates the command
     * @param fullWord the whole command
     */
    public Command(int index, String commandWord, boolean isMarked, String fullWord) {
        this.index = index;
        this.commandName = commandWord;
        this.isMarked = isMarked;
        this.fullWord = fullWord;
    }

    public int getIndex() {
        return index;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getTag() {
        assert commandName.equals("tag");
        return fullWord.split("\\s+")[2];
    }

    public String getFullWord() {
        return fullWord;
    }

    public boolean getIsMarked() {
        return isMarked;
    }
}
