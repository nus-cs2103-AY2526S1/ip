package parse;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.CannotLoad;
import exceptions.CannotStore;
import exceptions.DuplicationError;
import exceptions.EmptyList;
import exceptions.EventTimelineInvalid;
import exceptions.InvalidDateInput;
import exceptions.InvalidElementInList;
import exceptions.InvalidInput;
import exceptions.NoDeadlineProvided;
import storetasks.TaskList;

/**
 * Validates everything that the user types in throughout the chatbot.
 * If the input is invalid, then an error is thrown and the user is notiifed of the error
 * so that they can fix their resopnse.
 */
public class Parser {

    /**
     * The enum class is in charge of splitting the commands into different categories.
     */
    public enum Commands {
        LIST {
            /**
             * Returns the printed out list to the user on the GUI
             *
             * @param e The TaskList that contains the user's tasks.
             * @param statement The user statement from the GUI.
             * @return The printed out list.
             */
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert statement != null : "the statement is null";
                return e.printList();
            }
        },
        MARK {
            /**
             * marks the list
             * @param e the TaskList you are working on
             * @param statement user statement
             * @return string comment.
             */
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert statement != null : "the statement is null";
                if (e.lengthOfList() > 0) {
                    return e.mark(Integer.parseInt(statement[1]));
                }
                throw new EmptyList();
            }
        },
        UNMARK {
            /**
             * unmarks element in list
             * @param e the TaskList you are working on
             * @param statement user statement
             * @return string comment.
             */
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert e != null : "the tasklist does not exist";
                assert statement.length >= 2 : "the input is invalid";
                if (e.lengthOfList() > 0) {
                    return e.unmark(Integer.parseInt(statement[1]));
                }
                throw new EmptyList();
            }
        },

        DELETE {
            /**
             * deletes element in list
             * @param e the TaskList you are working on
             * @param statement user statement
             * @return string comment
             */
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert e != null : "the tasklist does not exist";
                assert statement.length >= 2 : "the input is invalid";
                if (e.lengthOfList() > 0) {
                    return e.delete(Integer.parseInt(statement[1]));
                }
                throw new EmptyList();
            }
        },
        FIND {
            /**
             * finds task in list
             * @param e the TaskList you are working on
             * @param statement user statement
             * @return string comment
             */
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert statement != null : "the statement is null";
                if (e.lengthOfList() > 0) {
                    return e.find(String.join(" ",
                            Arrays.copyOfRange(statement, 1, statement.length)));
                }
                throw new EmptyList();
            }
        },
        BYE {
            /**
             * shuts down the thing
             * @param e the TaskList you are working on
             * @param statement user statement
             * @return string comment
             */
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert statement != null : "the statement is null";
                return "BYEBYEBYE";
            }
        },
        ADDTOLIST {
            /**
             * adds task to the list
             * @param e the TaskList you are working on
             * @param statement user statement
             * @return string comment
             */
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert statement != null : "the statement is null";
                return e.addToList(String.join(" ", statement));
            }
        },

        ADDTAG {
            /**
             * @param e the TaskList you are working on
             * @param statement user statement
             * @return
             * @throws InvalidDateInput the date is invalid
             * @throws InvalidInput the input is invalid
             * @throws EmptyList you are deleting/doing something an empty list
             * @throws CannotStore unable to store user input
             * @throws CannotLoad can not load user input
             * @throws EventTimelineInvalid event is invalid
             */
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert statement != null : "the statement is null";
                return e.addTag(statement);
            }
        },
        DELETETAG {
            public String run(TaskList e, String ... statement) throws
                    InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                    CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                    NoDeadlineProvided {
                assert statement != null : "the statement is null";
                return e.deleteTag(statement);
            }
        };

        /**
         * does enum task in the list
         * @param e the TaskList you are working on
         * @param statement user statement
         * @return string comment
         */
        public abstract String run(TaskList e, String ... statement) throws
                InvalidDateInput, InvalidInput, EmptyList, CannotStore,
                CannotLoad, EventTimelineInvalid, InvalidElementInList, DuplicationError,
                NoDeadlineProvided;
    }

    /**
     * Checks the command to see if it matches any of the above enums
     * @param s command
     * @return Commands appropriate command it correlates with
     */
    public Commands checkerOfCommand(String s) {
        for (Commands c : Commands.values()) {
            if (s.equalsIgnoreCase(c.name())) {
                return c;
            }
        }
        ArrayList<String> a = new ArrayList<>(Arrays.asList("todo", "event", "deadline"));
        for (String stringy : a) {
            if (s.equalsIgnoreCase(stringy)) {
                return Commands.ADDTOLIST;
            }
        }
        return null;
    }

    /**
     * This function checks to see what the user wants to do.
     *
     * @param s user's message
     * @param e the echo they have
     * @return formatted String
     *
     */
    public static String validityOfWords(String s, TaskList e) throws
            EventTimelineInvalid, InvalidInput, EmptyList,
            InvalidDateInput, CannotStore, CannotLoad, InvalidElementInList,
            DuplicationError, NoDeadlineProvided {
        s = s.trim();
        // split the statement
        // basically p is the user string in an array
        String[] p = s.split("\\s+");
        Parser pa = new Parser();
        Commands command = pa.checkerOfCommand(p[0].toLowerCase());
        try {
            return command.run(e, p);
        } catch (NullPointerException error) {
            throw new InvalidInput();
        }
    }
}
