package chatbot.ui;

import chatbot.client.Client;
import chatbot.client.ClientList;
import chatbot.task.Task;
import chatbot.task.TaskList;

/**
 * UI class handles communication between B33PBOP's functionality and user interface.
 */
public class UI {
    private boolean exitGuiRequested = false;

    /**
     * Return a String of the initial greeting message when the bot starts
     */
    public String greetResponse() {
        return """
                I'm B33PBOP
                What do you want?
                """;
    }

    /**
     * Return a String of the exit message when the BYE command is executed.
     */
    public String byeResponse() {
        exitGuiRequested = true;
        return "Please leave me alone\n";
    }

    public boolean isExitGuiRequested() {
        return exitGuiRequested;
    }

    /**
     * Return a String of the current list of tasks in a formatted way when the LIST command is executed.
     */
    public String listResponse(TaskList myTasks) {
        String tasks = myTasks.showTaskList();
        if (tasks.isEmpty()) {
            return "Theres nothing, keep it that way :)";
        }

        return "You really need help remembering all these?\n" + tasks;
    }

    /**
     * Return a String of the bot's response when a user marks a task as complete based on its index.
     */
    public String markTaskCompleteResponse(Task task) {
        return "Ugh. Can't you do this yourself?\n"
                + task + "\n";
    }

    /**
     * Return a String of the bot's response when a user unmarks a task as complete based on its index.
     */
    public String unmarkTaskCompleteResponse(Task task) {
        return "Make up your mind...\n"
                + task + "\n";
    }

    /**
     * Return a String of the bot's response when any add task command is executed.
     */
    public String addTaskResponse(Task newTask) {
        return "This will be the last time I'm adding this for you:\n "
                + "+ " + newTask + "\n";
    }

    /**
     * Return a String of the bot's response when the DELETE command is executed.
     */
    public String deleteTaskResponse(Task task) {
        return "Thank god, you should really keep deleting tasks:\n"
                + "- " + task + "\n";
    }

    /**
     * Return a String of the bot's response when the FIND command is executed.
     * @param foundTasksString Tasks that matches keyword of user input in String format.
     */
    public String findTaskResponse(String foundTasksString) {
        return "You are really bossy you know that\n"
                + foundTasksString + "\n";
    }

    /**
     * Return a String of the bot's response when there is an error with input
     * @param errorMessage Error message when exception is caught
     */
    public String runErrorMessage(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns a String of the bot's response when a client is deleted.
     */
    public String deleteClientResponse(Client client) {
        return "Lost a client? Boo hoo!:\n"
                + "- " + client + "\n";
    }

    /**
     * Returns a String of the bot's response when a client is deleted.
     */
    public String addClientResponse(Client client) {
        return "I wonder how long before you lose this one too\n"
                + "+ " + client + "\n";
    }

    public String listClientResponse(ClientList clientList) {
        return clientList.showClientList();
    }

    /**
     * Returns a String of the bots response when a client is updated.
     * @param client Client to be updated
     */
    public String updateClientResponse(Client client) {
        return "You r welcome.\n"
                + client;
    }

    /**
     * Returns a String of the bots response when a client failed to be updated.
     */
    public String noClientUpdateResponse() {
        return "Nothing changed, hmmm";
    }
}
