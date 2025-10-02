package com.neokortex;

/**
 * An Enum representing all the possible dialogue choices that the Chatbot can handle.
 *
 * <p>
 * The {@code DialoguePath} enum is used by the {@link com.neokortex.commands.Response} class
 * to indicate to the chatbot which Dialogue should the chatbot respond with.
 * </p>
 *
 * <p>
 * Each {@code} DialoguePath corresponds to a particular dialogue option in the {@link DialogueMap}
 * </p>
 */
public enum DialoguePath {
    INTERMEDIARY,
    CATASTROPHIC_FAILURE,
    TOO_MANY_ARGUMENTS,
    INVALID_ARGUMENTS,
    GREET,
    STARTUP,
    STARTUP_FAILURE,
    GENERIC_FAILURE,
    NO_TASK_SPECIFIED,
    NO_DEADLINE_SPECIFIED,
    NO_START_TIME_SPECIFIED,
    NO_END_TIME_SPECIFIED,
    TOO_MANY_TIMES_SPECIFIED,
    NO_TASK_ID_SPECIFIED,
    INVALID_TIME_SPECIFIED,
    SUCCESSFULLY_ADDED_TASK,
    ADD_TASK_STORAGE_FAILURE,
    TASK_ID_OOB,
    SUCCESSFULLY_DELETED_TASK,
    DELETE_TASK_STORAGE_FAILURE,
    ECHO,
    FAREWELL,
    FIND_RETURNS_EMPTY_LIST,
    FIND_SUCCESSFUL,
    LIST_EMPTY,
    LIST_NON_EMPTY,
    TASK_ALREADY_MARKED,
    SUCCESSFULLY_MARKED_TASK,
    MARK_TASK_STORAGE_FAILURE,
    TASK_NOT_MARKED,
    SUCCESSFULLY_UNMARKED_TASK,
    UNMARK_TASK_STORAGE_FAILURE,
    UNABLE_TO_SAVE_TO_STORAGE,
    SUCCESSFULLY_SAVED_TASK,
    INVALID_PATH
}
