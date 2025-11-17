package sophia;

/**
 * An interface that has the methods related to dates in tasks
 */
public interface TaskWithDate {
    public boolean isDueWithinThreeDays();
    public String sendReminder();
}
