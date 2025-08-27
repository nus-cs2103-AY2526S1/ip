public abstract class Command {
    
    public abstract void execute(Ui ui, Storage storage) throws ByteException;


    public boolean isExit() {
        return false;
    }
}


