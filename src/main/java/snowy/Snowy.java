package snowy;

import snowy.command.Command;

/**
 * Demonstrates a chatbot to the user which user can type and pass to it commands and
 * the chatbot will give response.
 */
public class Snowy {
    private final Storage storage;
    private final TaskList tasks;
    //private final Ui ui;
    private static final String DEFAULT_FILE_PATH = "data/snowy.txt";

    /**
     * Takes in another non-default filepath and initializes storage and task list accordingly.
     * @param filePath
     */
    public Snowy(String filePath) {
        //ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public Snowy() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(tasks, storage);
        } catch (SnowyException e) {
            return e.getMessage();
        }
    }

//    public void run() {
//        ui.greeting();
//        Scanner scanner = new Scanner(System.in);
//        boolean isExit = false;
//        while (!isExit) {
//            String input = scanner.nextLine();
//            try {
//                Command cmd = Parser.parse(input);
//                cmd.execute(tasks, ui, storage);
//            } catch (SnowyException e) {
//                ui.handleInvalidInput(e.getMessage());
//            }
//        }
//    }

//    public static void main(String[] args) throws SnowyException {
//        new Snowy("data/snowy.txt").run();
//    }
}