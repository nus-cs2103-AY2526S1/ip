package chash.command;

/*
Enum reference
https://stackoverflow.com/a/3978690
https://stackoverflow.com/a/604426
https://stackoverflow.com/a/59608518
https://stackoverflow.com/a/26118954
https://stackoverflow.com/a/23128025
*/

/** Enumeration of supported CHASH command types. */
public enum CommandTypeEnum {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    FIND,
    HELP;
}
