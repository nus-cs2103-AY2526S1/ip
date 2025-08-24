package lynx.command;

public abstract class LynxCommand {

    private String input;

    public LynxCommand(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public abstract String getMessageForAll();

    public abstract String getMessageByDate();

    public abstract String getMessageById();

    public abstract String getMessageByKeyword();

}
