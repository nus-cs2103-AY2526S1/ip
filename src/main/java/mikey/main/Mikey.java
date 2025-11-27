package mikey.main;

import mikey.exception.MikeyException;
import mikey.parser.Parser;
import mikey.storage.Storage;
import mikey.task.*;
import mikey.ui.Ui;

import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Mikey {
    private static final String LINE = "  ___________________________________________________________";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    private boolean exit = false;

    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Initializes a Mikey instance
     * @param filePath File path of data file
     */
    public Mikey(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(storage.load());
    }

    /**
     * Processes the ParseResult returned from parser
     * @param result ParseResult returned from parser
     * @return String to be printed by Mikey
     * @throws MikeyException Error thrown when user says "bye"
     */
    public String processParseResult(Parser.ParseResult result) throws MikeyException {

        if (result.isError) {
            return ui.printError(result.errorMessage);
        }
        switch (result.command) {
        case LIST:
            return ui.printTasks(tasks);
        case BYE:
            exit = true;
            ui.bye();
            throw new MikeyException(ui.bye());
        case MARK:
            Task marked = tasks.markTask(result.arguments.index - 1);
            storage.save(tasks.getList());
            return ui.printMarkTask(marked);
        case UNMARK:
            Task unmarked = tasks.unmarkTask(result.arguments.index - 1);
            storage.save(tasks.getList());
            return ui.printUnmarkTask(unmarked);
        case TODO:
            Task todo = new Todo(result.arguments.description);
            tasks.addTask(todo);
            storage.save(tasks.getList());
            return ui.printAddTask(todo, tasks);
        case DEADLINE:
            Task deadline = new Deadline(result.arguments.description, result.arguments.byRaw);
            tasks.addTask(deadline);
            storage.save(tasks.getList());
            return ui.printAddTask(deadline, tasks);
        case EVENT:
            Task event = new Event(result.arguments.description, result.arguments.fromRaw,
                    result.arguments.toRaw);
            tasks.addTask(event);
            storage.save(tasks.getList());
            return ui.printAddTask(event, tasks);
        case DELETE:
            Task deleted = tasks.deleteTask(result.arguments.index - 1);
            storage.save(tasks.getList());
            return ui.printDeleteTask(deleted);
        case FIND:
            List<Task> foundTasks = tasks.findTasks(result.arguments.keyword);
            return ui.printFoundTasks(foundTasks);
        case TAG:
            Task tagged = tasks.tagTask(result.arguments.index - 1, result.arguments.label);
            storage.save(tasks.getList());
            return ui.printTagTask(tagged);
        default:
            return "";
        }
    }
    /**
     * Runs the program and asks for input
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();
        while (true) {
            String input = scanner.nextLine();
            Parser.ParseResult result = parser.parse(input);
            if (result.isError) {
                ui.printError(result.errorMessage);
            } else {
                try {
                    String response = this.processParseResult(result);
                    System.out.println(response);
                } catch (MikeyException e) {
                    System.out.println(e.getMessage());
                    break;
                }

            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Parser.ParseResult result = parser.parse(input);
            return processParseResult(result);
        } catch (MikeyException e) {
            return e.getMessage();
        }
    }

    public Ui getUi() {
        return ui;
    }

    public boolean isExit() {
        return exit;
    }

    public static void main(String[] args) {
        new Mikey("data/mikey.txt").run();
    }
}
