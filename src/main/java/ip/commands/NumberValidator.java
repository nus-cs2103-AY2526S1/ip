package ip.commands;

/**
 * Class to check if String is a valid number
 */
public class NumberValidator {
    /**
     * Returns a boolean based on if the string is a valid number format
     * @param numberStr User input number string
     * @return true if number is valid, false otherwise
     */
    public static boolean isValidNumber(String numberStr) {
        try {
            Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
