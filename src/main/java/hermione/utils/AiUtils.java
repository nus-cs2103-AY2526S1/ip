package hermione.utils;

import java.util.List;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * Utility class for AI-powered interactions using LangChain4j.
 */
public class AiUtils {
    private final ChatModel model;

    /**
     * Constructs an AiUtils instance with the configured chat model.
     */
    public AiUtils() {
        this.model = OpenAiChatModel.builder()
                .apiKey(System.getenv("LLM_API_KEY"))
                .baseUrl("https://api.groq.com/openai/v1")
                .modelName("llama-3.3-70b-versatile")
                .build();
    }

    /**
     * Gets an AI response based on the system and user prompts.
     *
     * @param systemPrompt The system prompt to set context for the AI.
     * @param userPrompt   The user's input query.
     * @return ChatResponse containing the AI's response.
     */
    public ChatResponse getAiResponse(String systemPrompt, String userPrompt) {
        ChatRequest req = ChatRequest.builder()
                .messages(List.of(
                        SystemMessage.from(systemPrompt),
                        UserMessage.from(userPrompt)
                ))
                .build();

        ChatResponse res = model.chat(req);
        return res;
    }

    /**
     * Asks the AI about app features based on user's natural language query.
     *
     * @param userPrompt The user's query about app features.
     * @return AI-generated response about the app's features.
     */
    public String askAiAboutFeature(String userPrompt) {
        String systemPrompt = "You are helping users of a CLI app. Answer the user's query "
                + "about the features of the app, based on the app's commands given below. "
                + "Limit your answer to one sentence.\n\n"
                + "1. todo <description> (shorthand: t) - Adds a todo task.\n"
                + "2. deadline <description> /by <date> (shorthand: dl) - Adds a task with a deadline.\n"
                + "3. event <description> /from <start date> /to <end date> (shorthand: e) - Adds an event task.\n"
                + "4. fixed <description> /duration <duration> (shorthand: fi) - Adds a fixed duration task.\n"
                + "5. list (shorthand: l) - Lists all tasks.\n"
                + "6. mark <task_number> (shorthand: m) - Marks a task as completed.\n"
                + "7. unmark <task_number> (shorthand: um) - Unmarks a task as not completed.\n"
                + "8. delete <task_number> (shorthand: d) - Deletes a task.\n"
                + "9. find <keyword> (shorthand: fd) - Finds tasks containing the keyword.\n"
                + "10. help (shorthand: h) - Shows the help message.\n"
                + "11. bye (shorthand: b) - Exits the application.";

        return getAiResponse(systemPrompt, userPrompt).aiMessage().text();
    }

    /**
     * Converts a natural language command into an executable app command.
     *
     * @param userPrompt The user's natural language request.
     * @return The app command that the AI generated.
     */
    public String commandAiToGenerate(String userPrompt) {
        String systemPrompt = "You are an assistant for a CLI task management app. "
                + "Based on the user's request, generate a valid command that the app understands. "
                + "Only respond with the command, nothing else. "
                + "Do not add any explanation or extra text.\n\n"
                + "Available commands:\n"
                + "1. todo <description> (shorthand: t) - Adds a todo task.\n"
                + "2. deadline <description> /by <date> (shorthand: dl) - Adds a task with a deadline. "
                + "Date format: yyyy-MM-dd or yyyy-MM-dd HHmm.\n"
                + "3. event <description> /from <start date> /to <end date> (shorthand: e) - "
                + "Adds an event task. Date format: yyyy-MM-dd or yyyy-MM-dd HHmm.\n"
                + "4. fixed <description> /duration <duration> (shorthand: fi) - Adds a fixed duration task.\n"
                + "5. list (shorthand: l) - Lists all tasks.\n"
                + "6. mark <task_number> (shorthand: m) - Marks a task as completed.\n"
                + "7. unmark <task_number> (shorthand: um) - Unmarks a task as not completed.\n"
                + "8. delete <task_number> (shorthand: d) - Deletes a task.\n"
                + "9. find <keyword> (shorthand: fd) - Finds tasks containing the keyword.\n"
                + "10. help (shorthand: h) - Shows the help message.\n"
                + "11. bye (shorthand: b) - Exits the application.\n\n"
                + "Examples:\n"
                + "User: 'add a task to buy groceries by tomorrow' -> "
                + "Response: 'deadline buy groceries /by 2026-02-13'\n"
                + "User: 'show me all my tasks' -> Response: 'list'\n"
                + "User: 'mark task 1 as done' -> Response: 'mark 1'";

        return getAiResponse(systemPrompt, userPrompt).aiMessage().text();
    }
}

