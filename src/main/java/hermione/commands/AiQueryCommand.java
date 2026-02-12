package hermione.commands;

import hermione.storage.TaskStorage;
import hermione.utils.AiUtils;

/**
 * Represents a command to query the AI about app features.
 * This command allows users to ask natural language questions about
 * the app's capabilities and receive AI-generated responses.
 */
public class AiQueryCommand extends Command {

    private final AiUtils aiUtils;

    /**
     * Constructor for the AiQueryCommand class.
     *
     * @param storage  The TaskStorage instance used to manage tasks.
     * @param argument The user's natural language query.
     */
    public AiQueryCommand(TaskStorage storage, String argument) {
        super(storage, argument);
        this.aiUtils = new AiUtils();
    }

    /**
     * Executes the command to query the AI about app features.
     * Takes the user's natural language query and returns an AI-generated
     * response about the app's features.
     *
     * @return A string containing the AI's response to the user's query.
     */
    @Override
    public String execute() {
        if (argument.isEmpty()) {
            return "Please provide a query after @ai. For example: @ai is there a command to add priorities?";
        }

        try {
            String response = aiUtils.askAiAboutFeature(argument);
            return response;
        } catch (Exception e) {
            return "Sorry, I couldn't process your AI query. Please try again. Error: " + e.getMessage();
        }
    }
}

