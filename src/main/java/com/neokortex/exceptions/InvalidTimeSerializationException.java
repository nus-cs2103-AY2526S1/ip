package com.neokortex.exceptions;

/**
 * Represents an exception that can arise when attempting to deserialize times with an invalid format
 */
public class InvalidTimeSerializationException extends RuntimeException {
    public InvalidTimeSerializationException(String serialization) {
        super(String.format("\"%s\" is not a valid serialization", serialization));
    }
}
