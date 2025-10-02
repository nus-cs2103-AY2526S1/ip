package com.neokortex;

import java.util.HashMap;

/**
 * Represents all possible responses that the Chatbot can have. There must be at least
 * one reponse per {@link DialoguePath} enum.
 *
 * <p>
 * The {@code DialogueMap} enum is used by the {@link Chatbot} to determine what the response
 * should be based on the given DiloguePath enum.
 * </p>
 *
 * <p>
 * A special feature that DialogueMap has is a substitution system similar to that of Format
 * Strings in Python. The rules are as follows:
 * <ul>
 *     <li>Substitution is detected by curly braces, and the content within the curly braces
 *     determines what needs to be substituted into the String</li>
 *     <li>The list of possible substitutions are as follows:
 *         <ol>
 *             <li>{name} -> the name of the Chatbot</li>
 *             <li>{r:\a} -> where \a is an integer. r indicates the use of the additional results appended to
 *             the {@link com.neokortex.commands.Response} class. the integer a refers to
 *             the index of the additional results to use.</li>
 *         </ol>
 *     </li>
 * </ul>
 * </p>
 *
 * @see com.neokortex.commands.Response
 * @see DialoguePath
 */
public class DialogueMap {
    private static final String SEP = System.lineSeparator();
    private final HashMap<DialoguePath, String> dialogueMap;

    /**
     * Constructs the {@code DialogueMap}
     */
    public DialogueMap() {
        this.dialogueMap = new HashMap<>();

        this.dialogueMap.put(DialoguePath.STARTUP,
                "Welcome to the NeoKortex! "
                        + "I'm {name}, and I am your host in this space. "
                        + "So, what do you wanna do today? "
        );
        this.dialogueMap.put(DialoguePath.TOO_MANY_ARGUMENTS, "Woah, that's too many arguments for this command.");
        this.dialogueMap.put(DialoguePath.INVALID_ARGUMENTS, "That's not how you use this command.");
        this.dialogueMap.put(DialoguePath.GREET,
                "Hi! I'm {name}, your trusty mechanical companion." + SEP
                        + "I'm here to walk you through the ins and outs of the NeoKortex."
                        + "Let me know if you need anything! I'm always happy to help."
        );
        this.dialogueMap.put(DialoguePath.STARTUP_FAILURE,
                "Welcome to the NeoKortex! "
                        + "I'm {name}, and I am your host in this space. "
                        + "Hmm... there seems to be an error with loading your data. "
                        + "Could you please check it out for me? "
                        + "In any case, welcome!"
        );
        this.dialogueMap.put(DialoguePath.GENERIC_FAILURE, "Sorry wut");
        this.dialogueMap.put(DialoguePath.INVALID_TIME_SPECIFIED, "That's not a valid time :/");
        this.dialogueMap.put(DialoguePath.NO_TASK_SPECIFIED, "Erm... so what task?");
        this.dialogueMap.put(DialoguePath.NO_DEADLINE_SPECIFIED, "But when?");
        this.dialogueMap.put(DialoguePath.NO_START_TIME_SPECIFIED, "But from when?");
        this.dialogueMap.put(DialoguePath.NO_END_TIME_SPECIFIED, "But to when?");
        this.dialogueMap.put(DialoguePath.TOO_MANY_TIMES_SPECIFIED, "Woah that's too many! Which one?");
        this.dialogueMap.put(DialoguePath.NO_TASK_ID_SPECIFIED, "Wait which task?");
        this.dialogueMap.put(DialoguePath.SUCCESSFULLY_ADDED_TASK,
                "Added:" + SEP
                        + "{r:0}" + SEP);
        this.dialogueMap.put(DialoguePath.ADD_TASK_STORAGE_FAILURE,
                "Added:" + SEP
                        + "    {r:0}" + SEP
                        + "to the list" + SEP
                        + "Woah, hold on..." + SEP
                        + "I seem to be unable to save your changes." + SEP
                        + "Could you run the Save command?"
        );
        this.dialogueMap.put(DialoguePath.TASK_ID_OOB, "Hey! Task number {r:0} is out of bounds.");
        this.dialogueMap.put(DialoguePath.SUCCESSFULLY_DELETED_TASK,
                "Deleted:" + SEP
                        + "    {r:0}");
        this.dialogueMap.put(DialoguePath.DELETE_TASK_STORAGE_FAILURE,
                "Deleted:" + SEP
                        + "    {r:0}" + SEP
                        + "from the list" + SEP
                        + "Woah, hold on..." + SEP
                        + "I seem to be unable to save your changes." + SEP
                        + "Could you run the Save command?"
        );
        this.dialogueMap.put(DialoguePath.ECHO, "{r:0}");
        this.dialogueMap.put(DialoguePath.FAREWELL, "Okey dokey, see you soon!");
        this.dialogueMap.put(DialoguePath.FIND_RETURNS_EMPTY_LIST, "Hmm... there are no tasks that match your search");
        this.dialogueMap.put(DialoguePath.FIND_SUCCESSFUL,
                "Here are all the tasks that contain: \"{r:0}\"" + SEP
                        + "Here you go!" + SEP
                        + "{r:1}"
        );
        this.dialogueMap.put(DialoguePath.LIST_EMPTY, "Hmm... there's nothing in your list right now.");
        this.dialogueMap.put(DialoguePath.LIST_NON_EMPTY,
                "Here are the tasks on your list:" + SEP
                        + "{r:0}"
        );
        this.dialogueMap.put(DialoguePath.TASK_ALREADY_MARKED, "Task {r:0} is already marked!");
        this.dialogueMap.put(DialoguePath.SUCCESSFULLY_MARKED_TASK, "Okay! I have marked the task \"{r:0}\" as done.");
        this.dialogueMap.put(DialoguePath.MARK_TASK_STORAGE_FAILURE,
                "Okay! I have marked the task \"{r:0}\" as done." + SEP
                        + "Woah, hold on..." + SEP
                        + "I seem to be unable to save your changes." + SEP
                        + "Could you run the Save command?"
        );
        this.dialogueMap.put(DialoguePath.TASK_NOT_MARKED, "Task {r:0} has not been marked yet!");
        this.dialogueMap.put(DialoguePath.SUCCESSFULLY_UNMARKED_TASK,
                "Okay! The task \"{r:0}\" is no longer marked as done."
        );
        this.dialogueMap.put(DialoguePath.UNMARK_TASK_STORAGE_FAILURE,
                "Okay! I have marked the task \"{r:0}\" as done." + SEP
                        + "Woah, hold on..." + SEP
                        + "I seem to be unable to save your changes." + SEP
                        + "Could you run the Save command?"
        );
        this.dialogueMap.put(DialoguePath.SUCCESSFULLY_SAVED_TASK,
                "Okay! I have saved the current list into storage");
        this.dialogueMap.put(DialoguePath.MARK_TASK_STORAGE_FAILURE,
                "Hmm... I can't seem to save the the list to storage :/"
        );
        this.dialogueMap.put(DialoguePath.INVALID_PATH,
                "That is not a valid path"
        );
    }

    public String getDialogueFromDirective(DialoguePath directive) {
        return this.dialogueMap.get(directive);
    }
}
