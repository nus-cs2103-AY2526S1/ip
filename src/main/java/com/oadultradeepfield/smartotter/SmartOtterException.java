package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.util.CustomIO;

/**
 * Exception class for handling SmartOtter-specific errors.
 * Automatically formats the error message for consistent display.
 */
public class SmartOtterException extends Exception {
    public SmartOtterException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return CustomIO.formatError(super.getMessage());
    }
}
