package Skye.errors;

public class ErrorFieldException extends SkyeException{

    /**
     * Throws an error about error in field content
     * @param fieldType Type of the field, eg. task
     * @param fieldName Name of the field, eg. description
     */
    public ErrorFieldException(String fieldType, String fieldName) {
        super(String.format("The input %s of %s is incorrect!", fieldName, fieldType));
    }
}
