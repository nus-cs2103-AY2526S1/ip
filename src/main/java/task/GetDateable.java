package task;

import java.time.LocalDate;

/**
 * Interface for classes that have a date
 */
public interface GetDateable {

    /**
     * Retrieves the date
     * @return the date
     */
    public LocalDate getDate();
}
