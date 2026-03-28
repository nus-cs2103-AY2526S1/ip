package dad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    
    @Test
    public void printIntro_test() {
        String intro = "  ----------------------------------\n"
                + "Whadd'ya want?\n"
                + "  ----------------------------------";

        assertEquals(Ui.printIntro(), intro);
    }

    @Test
    public void printLine_test() {
        assertEquals(Ui.printLine(), "  ----------------------------------");
    }

    @Test
    public void printInput_test() {
        String result = "test\n";
        assertEquals(Ui.print("test"), result);
    }
}	
