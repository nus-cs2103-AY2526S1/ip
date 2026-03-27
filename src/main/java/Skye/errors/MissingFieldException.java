package Skye.errors;

public class MissingFieldException extends SkyeException{

    /**
     * Throws an error about missing fields
     * @param fieldType Type of the field, eg. task
     * @param fieldName Name of the field, eg. description
     */
    public MissingFieldException(String fieldType, String fieldName) {
        super(String.format("The '%s' field of a %s cannot be empty!", fieldName, fieldType));
    }
}
