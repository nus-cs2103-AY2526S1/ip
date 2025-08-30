package stella;
public class Stella {
    public static void main(String[] args) {
        TaskList lists = new TaskList(Storage.readFile());
        Parser parser = new Parser(lists);
        Ui ui = new Ui(parser);
        ui.callInteraction();
    }
}
