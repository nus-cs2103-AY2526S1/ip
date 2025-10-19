package com.cc;


import com.cc.exceptions.EmptyTimeException;
import com.cc.exceptions.NoTaskException;
import com.cc.exceptions.WrongHeadingException;
import com.cc.parsers.Parser;
import com.cc.ui.Ui;

/**
 * class of the actual bot, instantiated when launcher is run
 */
public class CC {
    //private String FILE_PATH; delete suggested by chatgpt4.1, as a part of deletion of unused information
    private final com.cc.ui.Ui Ui;
    private final Storage storage;
    private final TaskList tasks = new TaskList();
    private final Parser parser = new Parser();

    /**
     * constructor of CC bot
     * @param filePath String of storage location
     */
    public CC(String filePath) {    //constructor for CC
        this.storage = new Storage(filePath);
        this.Ui = new Ui();
    }

    /**
     * function that puts CC bot in action
     *
     * @throws EmptyTimeException
     * @throws WrongHeadingException
     * @throws NoTaskException
     */
    public void run() throws EmptyTimeException, WrongHeadingException, NoTaskException {        //gets CC bot in action
        Storage.ensureDataFileExists();
        Ui.start();
        Ui.waitForCommand();
    }

    /**
     * function of getting response from CC
     * @param input
     * @return
     */
    public String getResponse(String input) {
        try {
            storage.ensureDataFileExists();
            return Ui.waitForCommandFxml(input, tasks, parser);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * abstraction of greeting message
     *
     * @return String of greeting
     */
    public String getGreeting() {
        return Ui.startFxml();
    }

    /**
     * past entry point for CLI mode
     * @param args
     * @throws WrongHeadingException
     * @throws EmptyTimeException
     * @throws NoTaskException
     */
    public static void main(String[] args) throws WrongHeadingException, EmptyTimeException, NoTaskException {    //main method
        new CC("data/tasks.txt").run();
    }
}
