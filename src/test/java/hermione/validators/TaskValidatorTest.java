package hermione.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hermione.exceptions.TaskValidationException;

public class TaskValidatorTest {

    private final String validDateString = "1/1/2021 1600";
    private final String invalidDateString = "2021-01-01 1600";
    private TaskValidator validator;

    @BeforeEach
    public void setUp() {
        this.validator = new TaskValidator();
    }

    @Test
    public void validateFields_invalidTaskType_throwsException() {
        String[] invalidFields = { "X", "0", "Description" };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

    @Test
    public void validateFields_validToDoFields_noException() {
        String[] validFields = { "T", "0", "Description" };
        Assertions.assertDoesNotThrow(() -> this.validator.validateFields(validFields));
    }

    @Test
    public void validateFields_emptyToDoTaskDescription_throwsException() {
        String[] invalidFields = { "T", "0", "" };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

    @Test
    public void validateFields_invalidToDoTaskIsComplete_throwsException() {
        String[] invalidFields = { "T", "Yes", "Description" };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

    @Test
    public void validateFields_validDeadlineFields_noException() {
        String[] validFields = { "D", "0", "Description", validDateString };
        Assertions.assertDoesNotThrow(() -> this.validator.validateFields(validFields));
    }

    @Test
    public void validateFields_emptyDeadlineTaskDescription_throwsException() {
        String[] invalidFields = { "D", "0", "", validDateString };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

    @Test
    public void validateFields_invalidDeadlineTaskIsComplete_throwsException() {
        String[] invalidFields = { "D", "Yes", "Description", validDateString };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

    @Test
    public void validateFields_invalidDeadlineTaskByDate_throwsException() {
        String[] invalidFields = { "D", "0", "Description", invalidDateString };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

    @Test
    public void validatefields_validEventFields_noException() {
        String[] validFields = { "E", "0", "Description", validDateString, validDateString };
        Assertions.assertDoesNotThrow(() -> this.validator.validateFields(validFields));
    }

    @Test
    public void validateFields_emptyEventTaskDescription_throwsException() {
        String[] invalidFields = { "E", "0", "", validDateString, validDateString };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

    @Test
    public void validateFields_invalidEventTaskFromDate_throwsException() {
        String[] invalidFields = { "E", "0", "Description", invalidDateString, validDateString };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

    @Test
    public void validateFields_invalidEventTaskToDate_throwsException() {
        String[] invalidFields = { "E", "0", "Description", validDateString, invalidDateString };
        Assertions.assertThrows(
                TaskValidationException.class, () -> this.validator.validateFields(invalidFields));
    }

}
