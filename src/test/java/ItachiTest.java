import org.junit.jupiter.api.Test;

import itachi.Itachi;


public class ItachiTest {

    @Test
    void constructor_fileDoesNotExist_shouldNotThrow() {
        String fakePath = "data/nonexistent.txt";

        // The constructor should handle missing file gracefully
        new Itachi(fakePath); // No exception should be thrown
    }
}
