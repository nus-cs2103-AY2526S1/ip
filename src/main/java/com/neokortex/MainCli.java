package com.neokortex;

import java.nio.file.Path;
import java.util.Scanner;

import com.neokortex.commands.CommandHandler;
import com.neokortex.commands.factory.CompleteCommandFactory;
import com.neokortex.commands.parsers.CompleteCommandParser;
import com.neokortex.core.ApplicationContext;
import com.neokortex.core.Storage;
import com.neokortex.task.ListLoadWrapper;
import com.neokortex.ui.Ui;
import com.neokortex.ui.cli.Cli;

/**
 * Main entry point for the CLI version of the {@code Chatbot}. Elias it the name of the CLI version of the Chatbot.
 *
 * <p>
 * The CLI version of the {@code Chatbot} has the following features:
 * <ul>
 *     <li>Adding different types of tasks to a task list</li>
 *     <li>Persistent Storage of TaskLists</li>
 *     <li>The ability to process the task list</li>
 * </ul>
 * </p>
 *
 * @see ApplicationContext
 * @see CommandHandler
 * @see Cli
 * @see Storage
 */
public class MainCli {
    public static final String LIST_STORAGE_DIRECTORY = "data";
    public static final String TO_DO_LIST_FILENAME = "ToDoList";
    private static final String NAME = "Elias";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Path path = Path.of(LIST_STORAGE_DIRECTORY,
                TO_DO_LIST_FILENAME);


        Ui cli = new Cli();
        Storage storage = new Storage(path);
        ListLoadWrapper wrapper = storage.loadListFromStorage();

        ApplicationContext context =
                new ApplicationContext(
                        wrapper.getTaskList(),
                        storage);
        CompleteCommandParser parser = new CompleteCommandParser();
        CompleteCommandFactory factory = new CompleteCommandFactory(context);
        CommandHandler handler = new CommandHandler(parser, factory);

        Chatbot elsria = new Chatbot(NAME);
        elsria.setUi(cli);
        elsria.setCommandHandler(handler);
        elsria.displayStartupMessage(!wrapper.getFailedSerializations().isEmpty());

        String prompt;
        while (sc.hasNextLine()) {
            prompt = sc.nextLine();
            elsria.interpret(prompt);
            elsria.respond();
        }
    }
}
