package king;

import java.io.IOException;

/**
 * IOException class for King
 */
public class KingException extends IOException {
    /**
     * Enumeration of all possible IO errors for King
     */
    public enum ErrorMessage {
        INVALID_COMMAND("Error! That command is not known within mine kingdom."),
        MISSING_TASK_DESCRIPTION("Error! Thou hast forgotten the task’s description! "
                + "Provide it after thy decree of todo, deadline, or event."),
        MISSING_TASK_PRIORITY("Error! Thou hast given no priority to this task! "
                + "Use the format `[task] [description] /priority [VL (Very Low), L (Low), "
                + "M (Medium), H (High), VH (Very High)]`."),
        INCORRECT_TASK_PRIORITY("Error! Such a priority doth not exist in mine court! "
                + "Use the format `[task] [description] /priority [VL (Very Low), L (Low), "
                + "M (Medium), H (High), VH (Very High)]`."),
        FIND_MISSING_SEARCH("Error! Thou hast offered no search word! "
                + "Proclaim it thus: `find [keyword]`."),
        DEADLINE_MISSING_DEADLINE("Error! No deadline hath been proclaimed! "
                + "Speak it thus: `deadline [task] /by [date]`."),
        EVENT_MISSING_FROM_TO_DATE("Error! Both from and to dates are absent! "
                + "Utter it thus: `event [task] /from [date] /to [date]`."),
        EVENT_MISSING_FROM_DATE("Error! The ‘from’ date is missing! "
                + "Proclaim it thus: `event [task] /from [date] /to [date]`."),
        EVENT_MISSING_TO_DATE("Error! The ‘to’ date is missing! "
                + "Proclaim it thus: `event [task] /from [date] /to [date]`."),
        EVENT_FROM_AFTER_TO("Error! Thy ‘from’ date lies beyond thy ‘to’ date. Such disorder shall not stand!"),

        MARK_MISSING_INDEX("Error! No mark index hast thou given!"),
        UNMARK_MISSING_INDEX("Error! No unmark index hast thou given!"),
        DELETE_MISSING_INDEX("Error! No delete index hast thou given!"),

        INVALID_DATABASE("Error! [KingStorage] The royal archive containeth invalid data."),
        CORRUPTED_DATABASE("Error! [KingStorage] The royal archive may be corrupted! "
                + "Tasks henceforth created shall not be preserved in the record.");

        private final String message;

        ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * Creates an exception and displays the message of the exception
     *
     * @param errorMessage Error given
     */
    public KingException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
