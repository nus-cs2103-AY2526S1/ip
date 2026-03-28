package nacho.tasks;

import java.util.Comparator;

/**
 * Comparator Class for comparing title of two Task Objects lexicographically
 */
public class TitleComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        String title1 = t1.getTitle();
        String title2 = t2.getTitle();
        int diffInt = title1.compareTo(title2);
        if (diffInt < 0) {
            return -1; // If title1 is lexicographically smaller
        } else if (diffInt > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
