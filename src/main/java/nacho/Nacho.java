package nacho;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import nacho.commands.AddDeadlineCommand;
import nacho.commands.AddEventCommand;
import nacho.commands.AddTodoCommand;
import nacho.commands.Command;
import nacho.commands.CommandDispatcher;
import nacho.commands.DeleteTaskCommand;
import nacho.commands.FindCommand;
import nacho.commands.HelpCommand;
import nacho.commands.ListTasksCommand;
import nacho.commands.MarkTaskCommand;
import nacho.commands.SortCommand;
import nacho.commands.UnmarkTaskCommand;
import nacho.commons.QueryResult;
import nacho.commons.UiType;
import nacho.tasks.TaskList;

/**
 * Main class of Nacho Chatbot
 * Task Tracking tool based on commands from CLI input
 */
public class Nacho {

    private TaskList taskList;
    private ChatContext context;
    private Map<String, Command> commandRegistry = new HashMap<>();
    private CommandDispatcher dispatcher;

    /**
     * Nacho Bot Constructor
     * @param chatType Either "GUI" or "CLI" to provide chat context
     */
    public Nacho(UiType chatType) {
        assert chatType != null;

        // Creating chat context
        taskList = new TaskList(ExternalStorageController.getStore());

        // Add TaskList object to current chat's context
        context = new ChatContext(taskList, chatType);

        // Registering commands
        commandRegistry.put("help", new HelpCommand());
        commandRegistry.put("todo", new AddTodoCommand());
        commandRegistry.put("deadline", new AddDeadlineCommand());
        commandRegistry.put("event", new AddEventCommand());
        commandRegistry.put("mark", new MarkTaskCommand());
        commandRegistry.put("unmark", new UnmarkTaskCommand());
        commandRegistry.put("delete", new DeleteTaskCommand());
        commandRegistry.put("list", new ListTasksCommand());
        commandRegistry.put("find", new FindCommand());
        commandRegistry.put("sort", new SortCommand());

        // Creating Command dispatcher object -> will run the mapped command
        dispatcher = new CommandDispatcher(commandRegistry);
    }


    public static void main(String[] args) {

        // Greet the user
        Nacho nacho = new Nacho(UiType.CLI);
        nacho.context.reply("Hello I'm Nacho\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();

        while (!query.equals("bye")) {

            // Handle incoming command
            nacho.dispatcher.dispatch(query, nacho.context);

            query = scanner.nextLine();
        }

        // Bye Message
        nacho.context.reply("Bye. Hope to see you soon!");
    }

    /**
     * Dispatches the query to Nacho Bot along with the chat context
     * The chat context will be updated with the latest text reply from Nacho Bot
     * This reply will be utilised as display text in the GUI version
     * @param query user input command text
     * @return reply message from Nacho Bot
     */
    public QueryResult handleQuery(String query) {
        this.dispatcher.dispatch(query, this.context);
        QueryResult result = new QueryResult(
                this.context.getLatestReply(),
                this.context.getLatestMessageValidity()
        );
        return result;
    }
}
