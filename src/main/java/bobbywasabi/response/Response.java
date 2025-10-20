package bobbywasabi.response;

import java.util.Random;

import bobbywasabi.client.Client;
import bobbywasabi.client.ClientList;
import bobbywasabi.tasks.Task;
import bobbywasabi.tasks.TaskList;

/**
 * Handles user interaction through the console.
 * <p>
 * Provides methods to format and display messages for tasks, clients, search results,
 * confirmations, errors, and general bot responses.
 * Each response may include a randomly selected personal phrase and emoji for personality.
 */
public class Response {
    private static final String[] BOBBY_WASABI_PHRASES = new String[] {
        "Stay kickin'!",
        "Wasabi power!",
        "Chop-chop, let's go!",
        "Hi-ya! Mission complete.",
        "Wax on, wax off!",
        "Keep your guard up!",
        "Strike first, ask questions later!",
        "That’s the Wasabi way!",
        "You got this, sensei!",
        "Another victory for the dojo!"
    };

    private static final String[] BOBBY_WASABI_EMOJIS = {
        "🥋", "👊", "💥", "🔥", "😎", "🥷", "👐", "🤙", "🦶", "🏆"
    };

    private Random random = new Random();

    /**
     * Adds a random personal phrase and emoji to a message.
     *
     * @param input The original message.
     * @return      The input message appended with a random phrase and emoji.
     */
    private String addRandomPersonalPhraseToMessage(String input) {
        int phraseIndex = random.nextInt(BOBBY_WASABI_PHRASES.length);
        int emojiIndex = random.nextInt(BOBBY_WASABI_EMOJIS.length);
        return input + "\n" + BOBBY_WASABI_PHRASES[phraseIndex]
                + BOBBY_WASABI_EMOJIS[emojiIndex];
    }

    /**
     * Returns a formatted message listing all tasks in the task list.
     *
     * @param tasks The TaskList containing all tasks.
     * @return A String listing the tasks.
     */
    public String listMessage(TaskList tasks) {
        String listOutput = "Here are the tasks in your list:\n" + tasks;
        return addRandomPersonalPhraseToMessage(listOutput);
    }

    /**
     * Returns a formatted message listing all clients.
     *
     * @param clients The ClientList containing all clients.
     * @return A String listing the clients.
     */
    public String clientsMessage(ClientList clients) {
        String clientOutput = "Here are your clients:\n" + clients;
        return addRandomPersonalPhraseToMessage(clientOutput);
    }

    /**
     * Returns a formatted message for tasks matching a search query.
     *
     * @param tasks A formatted string of matching tasks.
     * @return A String displaying search results or a message if no matches are found.
     */
    public String findMessage(String tasks) {
        String output = tasks.isEmpty()
                ? "Sorry! We could not find any matching tasks :/\n"
                : "Here are the matching tasks in your list:\n";
        String botResponse = output + tasks;
        return addRandomPersonalPhraseToMessage(botResponse);
    }

    /**
     * Returns a confirmation message after marking a task as completed.
     *
     * @param indx       1-based index of the task in the list.
     * @param targetTask The Task object that was marked.
     * @return A String confirming the task has been marked.
     */
    public String markTaskMessage(int indx, Task targetTask) {
        assert targetTask != null
                : "TargetTask in markTaskMessage is null!";
        String curTask = String.format(
                "%d. %s\n",
                indx,
                targetTask);

        String output = String.format("""
                                Nice! I've marked this task as done:
                                %s
                                """,
                curTask);

        return addRandomPersonalPhraseToMessage(output);
    }

    /**
     * Returns a confirmation message after unmarking a task.
     *
     * @param indx       1-based index of the task in the list.
     * @param targetTask The Task object that was unmarked.
     * @return A String confirming the task has been unmarked.
     */
    public String unmarkTaskMessage(int indx, Task targetTask) {
        assert targetTask != null
                : "TargetTask in unmarkTaskMessage is null!";
        String curTask = String.format(
                "%d. %s\n",
                indx,
                targetTask);

        String output = String.format("""
                                Nice! I've marked this task as not done yet:
                                %s
                                """,
                curTask);

        return addRandomPersonalPhraseToMessage(output);
    }


    /**
     * Returns a confirmation message after adding a task.
     *
     * @param task The Task object that was added.
     * @param num  Total number of tasks after addition.
     * @return A String confirming the task addition.
     */
    public String addTaskMessage(Task task, int num) {
        assert task != null
                : "task in addTaskMessage is null!";
        String output = String.format("""
                Got it. I've added this task:
                %s
                Now you have %d tasks in the list.
                """,
                task, num);

        return addRandomPersonalPhraseToMessage(output);
    }

    /**
     * Returns a confirmation message after deleting a task.
     *
     * @param targetTask The Task object that was deleted.
     * @param taskSize   Total number of tasks remaining.
     * @return A String confirming the task deletion.
     */
    public String deleteMessage(Task targetTask, int taskSize) {
        assert targetTask != null
                : "targetTask in deleteMessage is null!";
        String output = String.format("""
                        Noted. I've removed this task:
                        %s
                        Now you have %d tasks in the list
                        """,
                targetTask, taskSize);

        return addRandomPersonalPhraseToMessage(output);
    }

    /**
     * Returns a confirmation message after deleting a client.
     *
     * @param targetClient The Client object that was deleted.
     * @param clientSize   Total number of clients remaining.
     * @return A String confirming the client deletion.
     */
    public String deleteClientMessage(Client targetClient, int clientSize) {
        assert targetClient != null
                : "targetClient in deleteClientMessage is null!";
        String output = String.format("""
                        Noted. I've removed this client:
                        %s
                        Now you have %d clients in your contacts
                        """,
                targetClient, clientSize);

        return addRandomPersonalPhraseToMessage(output);
    }

    /**
     * Returns a confirmation message after adding a client.
     *
     * @param client     The Client object that was added.
     * @param clientSize Total number of clients after addition.
     * @return A String confirming the client addition.
     */
    public String addClientMessage(Client client, int clientSize) {
        assert client != null
                : "client in addClientMessage is null!";

        String output = String.format("""
                        I have added this clients:
                        %s
                        Now you have %d clients in your contacts
                        """,
                client, clientSize);

        return addRandomPersonalPhraseToMessage(output);
    }

    /**
     * Returns a confirmation message after editing a client.
     *
     * @param client The updated Client object.
     * @return A String confirming the client update.
     */
    public String editClientMessage(Client client) {
        assert client != null
                : "client in editClientMessage is null!";

        String output = String.format("""
                        Client has been updated as follows:
                        %s
                        """,
                client);

        return addRandomPersonalPhraseToMessage(output);
    }

    /**
     * Returns a generic error message prompting the user to enter a valid command.
     *
     * @return A String indicating invalid command input.
     */
    public String invalidMessage() {
        return this.generateErrorMsg("Please provide a valid command!");
    }

    /**
     * Returns a farewell message when the bot exits.
     *
     * @return A String containing the farewell message.
     */
    public String farewellUser() {
        String output =
               """
               Bye. Hope to see you again soon!
               """;

        return addRandomPersonalPhraseToMessage(output);
    }


    /**
     * Formats a custom error message for display.
     *
     * @param e The error message content.
     * @return A String containing the formatted error message.
     */
    public String generateErrorMsg(String e) {
        assert !e.trim().isEmpty()
                : "Error message cannot be empty!";

        String output = String.format("""
                OOPS!!! %s
                """,
                e);

        return addRandomPersonalPhraseToMessage(output);
    }

}
