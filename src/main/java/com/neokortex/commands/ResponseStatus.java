package com.neokortex.commands;

/**
 * An Enum representing various {@code ResponseStatus} that could arise from Command Handling.
 *
 * <p>
 * The {@code Month} enum provides general way to communicate about the overall status of the Command interpretation
 * process, facilitating the control flow of the {@link CommandHandler}. The {@link CommandHandler} often checks the
 * overall {@code ResponseStatus} in order to decide if the program should proceed to the next stage.
 * </p>
 *
 * <p>
 * {@code ResponseStatus} is primarily used when returning {@link Response}s during command Handling done by the
 * {@link CommandHandler}
 *
 * @see CommandHandler
 * @see Response
 */
public enum ResponseStatus {
    CATASTROPHIC_FAILURE,
    TOTAL_FAILURE,
    PARSE_FAILURE,
    FACTORY_FAILURE,
    EXECUTION_FAILURE,
    SUCCESS,
    EXIT_PROGRAM,
    INVALID
}
